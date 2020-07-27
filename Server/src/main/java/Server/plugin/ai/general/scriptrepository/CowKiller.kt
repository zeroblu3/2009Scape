package plugin.ai.general.scriptrepository

import core.game.interaction.DestinationFlag
import core.game.interaction.MovementPulse
import core.game.node.item.Item
import core.game.system.SystemLogger
import core.game.system.task.Pulse
import core.game.world.map.Location
import core.game.world.map.path.Pathfinder
import core.tools.ItemNames

class CowKiller : Script() {
    var state = State.KILLING
    var gateOpened = false
    override fun tick() {
        when(state){
            State.KILLING -> {
                scriptAPI.attackNpcInRadius(bot,"Cow",10)
                state = State.LOOTING
            }

            State.LOOTING -> {
                bot.pulseManager.run(object: Pulse(4){
                    override fun pulse(): Boolean {
                        state = State.KILLING
                        scriptAPI.takeNearestGroundItem(ItemNames.COWHIDE)
                        state = if(bot.inventory.getAmount(ItemNames.COWHIDE) > 22){
                            State.BANKING
                        } else {
                            State.KILLING
                        }
                        return true
                    }
                })
            }

            State.BANKING -> {
                if(bot.location.x >= 3253 && bot.location.y != 3267){
                    Pathfinder.find(bot, Location.create(3253, 3267, 0)).walk(bot)
                }
                if(bot.location == Location.create(3253, 3267, 0)){
                    val closedGate = scriptAPI.getNearestNode(15516,true)
                    if(closedGate != null && !gateOpened){
                        SystemLogger.log("Opening closed gate.")
                        closedGate.interaction.handle(bot,closedGate.interaction[0])
                        gateOpened = true
                    } else {
                        Pathfinder.find(bot,Location.create(3237, 3261, 0)).walk(bot)
                    }
                }
                when(bot.location){
                    Location.create(3237, 3261, 0) -> Pathfinder.find(bot,Location.create(3219, 3254, 0)).walk(bot)
                    Location.create(3219, 3254, 0) -> Pathfinder.find(bot,Location.create(3226, 3236, 0)).walk(bot)
                    Location.create(3226, 3236, 0) -> Pathfinder.find(bot,Location.create(3212, 3227, 0)).walk(bot)
                    Location.create(3212, 3227, 0) -> {
                        val stairs = scriptAPI.getNearestGameObject(bot.location,36776)
                        stairs?.interaction?.handle(bot,stairs.interaction[0])
                    }
                    Location.create(3206, 3229, 1) -> {
                        val stairs = scriptAPI.getNearestNode(36777,true)
                        stairs?.interaction?.handle(bot,stairs.interaction[1])
                    }
                    Location.create(3206, 3229, 2) -> {
                        Pathfinder.find(bot,Location.create(3206, 3219, 2)).walk(bot)
                    }
                    Location.create(3206, 3219, 2) -> {
                        val bank = scriptAPI.getNearestNode(36786,true)
                        bot.pulseManager.run(object: MovementPulse(bot,bank, DestinationFlag.OBJECT){
                            override fun pulse(): Boolean {
                                val numHides = bot.inventory.getAmount(ItemNames.COWHIDE)
                                bot.bank.add(Item(ItemNames.COWHIDE,numHides))
                                bot.inventory.remove(Item(ItemNames.COWHIDE,numHides))
                                bot.pulseManager.run(object: Pulse(4){
                                    override fun pulse(): Boolean {
                                        if(bot.bank.getAmount(ItemNames.COWHIDE) > 10){
                                            scriptAPI.teleportToGE()
                                            state = State.TELE_GE
                                        } else {
                                            state = State.BACK_TO_COWS
                                            gateOpened = false
                                        }
                                        return true
                                    }
                                })
                                return true
                            }
                        })
                    }
                }
            }


            State.BACK_TO_COWS -> {
                when(bot.location) {
                    Location.create(3222, 3218, 0) -> Pathfinder.find(bot,Location.create(3226, 3236, 0)).walk(bot)
                    Location.create(3208, 3220, 2) -> Pathfinder.find(bot, Location.create(3206, 3219, 2)).walk(bot)
                    Location.create(3206, 3219, 2) -> Pathfinder.find(bot, Location.create(3206, 3229, 2)).walk(bot)
                    Location.create(3206, 3229, 2) -> {
                        val stairs = scriptAPI.getNearestNode(36778, true)
                        stairs?.interaction?.handle(bot, stairs.interaction[0])
                    }
                    Location.create(3206, 3229, 1) -> {
                        val stairs = scriptAPI.getNearestNode(36777,true)
                        stairs?.interaction?.handle(bot,stairs.interaction[2])
                    }
                    Location.create(3206, 3229, 0) -> Pathfinder.find(bot,Location.create(3226, 3236, 0)).walk(bot)
                    Location.create(3226, 3236, 0) -> Pathfinder.find(bot,Location.create(3219, 3254, 0)).walk(bot)
                    Location.create(3219, 3254, 0) -> Pathfinder.find(bot,Location.create(3237, 3261, 0)).walk(bot)
                    Location.create(3237, 3261, 0) -> Pathfinder.find(bot,Location.create(3252, 3267, 0)).walk(bot)
                    Location.create(3252, 3267, 0) -> {
                        val closedGate = scriptAPI.getNearestNode(15516,true)
                        if(closedGate != null && !gateOpened){
                            SystemLogger.log("Opening closed gate.")
                            closedGate.interaction.handle(bot,closedGate.interaction[0])
                            gateOpened = true
                        } else {
                            Pathfinder.find(bot,Location.create(3258, 3262, 0)).walk(bot)
                            state = State.KILLING
                            gateOpened = false
                        }
                    }
                }
            }


            State.TELE_GE -> {
                state = State.SELL_GE
                scriptAPI.teleportToGE()
            }

            State.SELL_GE -> {
                state = State.TELE_LUM
                scriptAPI.sellOnGE(ItemNames.COWHIDE)
            }

            State.TELE_LUM -> {
                state = State.BACK_TO_COWS
                scriptAPI.teleport(Location.create(3222, 3218, 0))
            }
        }
    }

    enum class State {
        KILLING,
        LOOTING,
        BANKING,
        BACK_TO_COWS,
        SELL_GE,
        TELE_GE,
        TELE_LUM
    }

    override fun init() {
        super.init()
    }
}