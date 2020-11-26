package plugin.activity.gnomecooking

import core.cache.def.impl.ItemDefinition
import core.game.content.ItemNames
import core.game.interaction.OptionHandler
import core.game.node.Node
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import core.tools.RandomFunction
import plugin.dialogue.DialoguePlugin

val gnomeItems = arrayOf(ItemNames.FRUIT_BATTA_2277, ItemNames.TOAD_BATTA_2255, ItemNames.CHEESE_TOM_BATTA_2259, ItemNames.WORM_BATTA_2253, ItemNames.VEGETABLE_BATTA_2281,
        ItemNames.CHOCOLATE_BOMB_2185, ItemNames.VEG_BALL_2195, ItemNames.TANGLED_TOADS_LEGS_2187, ItemNames.WORM_HOLE_2191, ItemNames.TOAD_CRUNCHIES_2217, ItemNames.WORM_CRUNCHIES_2205, ItemNames.CHOCCHIP_CRUNCHIES_2209, ItemNames.SPICY_CRUNCHIES_2213)

@InitializablePlugin
class GCRewardTokenHandler : OptionHandler() {
    override fun newInstance(arg: Any?): Plugin<Any> {
        val def = ItemDefinition.forId(9474)
        def.handlers["option:check"] = this
        def.handlers["option:activate"] = this
        return this
    }

    override fun handle(player: Player?, node: Node?, option: String?): Boolean {
        player ?: return false
        node ?: return false
        option ?: return false

        when(option){
            "check" -> {
                val charges = player.getAttribute("$GC_BASE_ATTRIBUTE:$GC_REDEEMABLE_FOOD",0)
                player.dialogueInterpreter.sendDialogue("You have $charges redeemable charges.")
            }
            "activate" -> {
                player.dialogueInterpreter.open(939382893)
            }
        }
        return true
    }

    @InitializablePlugin
    class RewardTokenActivationDialogue(player: Player? = null) : DialoguePlugin(player){
        override fun newInstance(player: Player?): DialoguePlugin {
            return RewardTokenActivationDialogue(player)
        }

        override fun open(vararg args: Any?): Boolean {
            player.dialogueInterpreter.sendOptions("How many charges?","1","5","10")
            stage = 0
            return true
        }

        override fun handle(interfaceId: Int, buttonId: Int): Boolean {
            when(stage){
                0 -> end().also {
                    when (buttonId) {
                        1 -> sendCharges(1, player)
                        2 -> sendCharges(5, player)
                        3 -> sendCharges(10, player)
                    }
                }
            }
            return true
        }

        fun sendCharges(amount: Int, player: Player){
            val playerCharges = player.getAttribute("$GC_BASE_ATTRIBUTE:$GC_REDEEMABLE_FOOD",0)
            if(playerCharges < amount){
                player.dialogueInterpreter.sendDialogue("You don't have that many charges.")
                return
            }

            if(player.inventory.freeSlots() < amount){
                player.dialogueInterpreter.sendDialogue("You don't have enough space in your inventory.")
                return
            }

            val itemList = ArrayList<Item>()

            for(charge in 0 until amount){
                itemList.add(Item(gnomeItems.random()))
            }

            player.dialogueInterpreter.sendDialogue("You put in for delivery of $amount items. Wait a bit...")
            GameWorld.Pulser.submit(DeliveryPulse(player,itemList))
            player.setAttribute("/save:$GC_BASE_ATTRIBUTE:$GC_REDEEMABLE_FOOD", playerCharges - amount)
        }

        class DeliveryPulse(val player: Player,val items: ArrayList<Item>) : Pulse(RandomFunction.random(15,30)){
            override fun pulse(): Boolean {
                player.inventory.add(*items.toTypedArray())
                player.dialogueInterpreter.sendDialogue("Your food delivery has arrived!")
                return true
            }
        }

        override fun getIds(): IntArray {
            return intArrayOf(939382893)
        }

    }

}