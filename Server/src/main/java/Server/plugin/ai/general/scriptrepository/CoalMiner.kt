package plugin.ai.general.scriptrepository

import core.game.interaction.DestinationFlag
import core.game.interaction.MovementPulse
import core.game.node.Node
import core.game.node.`object`.GameObject
import core.game.node.item.Item
import core.game.system.task.Pulse
import core.game.world.map.zone.ZoneBorders
import core.tools.Items
import plugin.ai.AIPlayer
import plugin.ai.skillingbot.SkillingBotAssembler
import plugin.skill.Skills

class CoalMiner() : Script() {
    var state = State.TO_BANK
    var ladderSwitch = false

    val bottomLadder = ZoneBorders(3016,9736,3024,9742)
    val topLadder = ZoneBorders(3016,3336,3022,3342)
    val mine = ZoneBorders(3027,9733,3054,9743)
    val bank = ZoneBorders(3009,3355,3018,3358)

    override fun tick() {
        when(state){

            State.MINING -> {
                if(bot.inventory.freeSlots() == 0){
                    state = State.TO_BANK
                }
                if(!mine.insideBorder(bot)){
                    scriptAPI.walkTo(mine.randomLoc)
                } else {
                    val rock = scriptAPI.getNearestNode("rocks",true)
                    rock?.interaction?.handle(bot,rock.interaction[0])
                }
            }

            State.TO_BANK -> {
                if(bank.insideBorder(bot)){
                    val bank = scriptAPI.getNearestNode("bank booth",true)
                    if(bank != null) {
                        bot.pulseManager.run(object : BankingPulse(this, bank){
                            override fun pulse(): Boolean {
                                state = State.BANKING
                                return super.pulse()
                            }
                        })
                    }

                } else {
                    if(!ladderSwitch) {
                        val ladder = scriptAPI.getNearestNode(30941, true)
                        ladder ?: scriptAPI.walkTo(bottomLadder.randomLoc).also { return }
                        ladder?.interaction?.handle(bot, ladder.interaction[0]).also { ladderSwitch = true }
                    } else {
                        if (!bank.insideBorder(bot)) scriptAPI.walkTo(bank.randomLoc).also { return }
                    }
                }
            }

            State.BANKING -> {
                scriptAPI.bankItem(Items.COAL_453)
                if(bot.bank.getAmount(Items.COAL_453) > 10){
                    state = State.TO_GE
                } else {
                    state = State.TO_MINE
                }
            }

            State.TO_MINE -> {
                if(ladderSwitch){
                    if(!topLadder.insideBorder(bot.location)){
                        scriptAPI.walkTo(topLadder.randomLoc)
                    } else {
                        val ladder = scriptAPI.getNearestNode("Ladder",true)
                        if(ladder != null){
                            ladder.interaction.handle(bot,ladder.interaction[0])
                            ladderSwitch = false
                        } else {
                            scriptAPI.walkTo(topLadder.randomLoc)
                        }
                    }
                } else {
                    if(!mine.insideBorder(bot)){
                        scriptAPI.walkTo(mine.randomLoc)
                    } else {
                        state = State.MINING
                    }
                }
            }

            State.TO_GE -> {
                scriptAPI.teleportToGE()
                state = State.SELLING
            }

            State.SELLING -> {
                scriptAPI.sellOnGE(Items.COAL_453)
                state = State.GO_BACK
            }

            State.GO_BACK -> {
                scriptAPI.teleport(bank.randomLoc)
                state = State.TO_MINE
            }

        }
    }

    open class BankingPulse(val script: Script, val bank: Node) : MovementPulse(script.bot,bank, DestinationFlag.OBJECT){
        override fun pulse(): Boolean {
            script.bot.faceLocation(bank.location)
            return true
        }
    }

    override fun newInstance(): Script {
        val script = CoalMiner()
        script.bot = SkillingBotAssembler().produce(SkillingBotAssembler.Wealth.POOR,bot.startLocation)
        return script
    }

    enum class State {
        MINING,
        TO_MINE,
        TO_BANK,
        BANKING,
        TO_GE,
        SELLING,
        GO_BACK
    }

    init {
        equipment.add(Item(Items.IRON_PICKAXE_1267))
        skills.put(Skills.MINING,75)
    }

}