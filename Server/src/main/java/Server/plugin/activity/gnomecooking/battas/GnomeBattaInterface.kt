package plugin.activity.gnomecooking.battas

import core.game.component.Component
import core.game.component.ComponentDefinition
import core.game.component.ComponentPlugin
import core.game.content.ItemNames
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import plugin.skill.Skills


private const val WORM_BATTA = 2219
private const val TOAD_BATTA = 2221
private const val CHEESE_TOM_BATTA = 2223
private const val FRUIT_BATTA = 2225
private const val VEG_BATTA = 2227

/**
 * Handles the gnome batta interface
 * @author Ceikry
 */
@InitializablePlugin
class GnomeBattaInterface : ComponentPlugin() {

    override fun open(player: Player?, component: Component?) {
        component ?: return
        player ?: return
        super.open(player, component)
        player.packetDispatch.sendItemOnInterface(FRUIT_BATTA,component.id,component.id,3)
        player.packetDispatch.sendItemOnInterface(TOAD_BATTA,component.id,component.id,14)
        player.packetDispatch.sendItemOnInterface(WORM_BATTA,component.id,component.id,25)
        player.packetDispatch.sendItemOnInterface(VEG_BATTA,component.id,component.id,34)
        player.packetDispatch.sendItemOnInterface(CHEESE_TOM_BATTA,component.id,component.id,47)
    }


    override fun handle(player: Player?, component: Component?, opcode: Int, button: Int, slot: Int, itemId: Int): Boolean {
        player    ?: return false
        component ?: return false

        when(button){
            3 -> attemptMake(CookedProduct.HALF_MADE_FR,player)
            14 -> attemptMake(CookedProduct.HALF_MADE_TO,player)
            25 -> attemptMake(CookedProduct.HALF_MADE_WO,player)
            34 -> attemptMake(CookedProduct.HALF_MADE_VE,player)
            47 -> attemptMake(CookedProduct.HALF_MADE_CT,player)
        }
        return true
    }

    private fun attemptMake(batta: CookedProduct, player: Player){
        if(!player.inventory.containsItem(Item(ItemNames.GNOME_SPICE_2169)) && (batta == CookedProduct.HALF_MADE_TO || batta == CookedProduct.HALF_MADE_WO) ){
            player.dialogueInterpreter.sendDialogue("You need gnome spices for this.")
            return
        }

        if(player.skills.getLevel(Skills.COOKING) < batta.levelReq){
            player.dialogueInterpreter.sendDialogue("You don't have the needed level to make this.")
            return
        }

        var hasAll = true
        for(item in batta.requiredItems){
            if(!player.inventory.containsItem(item)){
                hasAll = false
                break
            }
        }

        if(!hasAll){
            player.dialogueInterpreter.sendDialogue("You don't have all the ingredients needed for this.")
            return
        }

        player.inventory.remove(*batta.requiredItems)
        player.inventory.remove(Item(ItemNames.HALF_BAKED_BATTA_2249))
        player.inventory.add(Item(batta.product))
        player.skills.addExperience(Skills.COOKING,batta.experience)
        player.interfaceManager.close()
    }

    internal enum class CookedProduct(val product: Int,val levelReq: Int, val experience: Double, val requiredItems: Array<Item>){
        HALF_MADE_CT(9478,29,40.0, arrayOf(Item(ItemNames.TOMATO), Item(ItemNames.CHEESE_1985))),
        HALF_MADE_FR(9480,25,40.0, arrayOf(Item(ItemNames.EQUA_LEAVES_2128,4), Item(ItemNames.LIME_CHUNKS_2122), Item(ItemNames.ORANGE_CHUNKS_2110), Item(ItemNames.PINEAPPLE_CHUNKS_2116))),
        HALF_MADE_TO(9482,26,40.0, arrayOf(Item(ItemNames.EQUA_LEAVES_2128), Item(ItemNames.CHEESE_1985), Item(ItemNames.TOADS_LEGS_2152))),
        HALF_MADE_VE(9483,28,40.0, arrayOf(Item(ItemNames.TOMATO,2), Item(ItemNames.CHEESE_1985), Item(ItemNames.DWELLBERRIES_2126), Item(ItemNames.ONION_1957), Item(ItemNames.CABBAGE))),
        HALF_MADE_WO(9485,27,40.0, arrayOf(Item(ItemNames.KING_WORM_2162), Item(ItemNames.CHEESE_1985)))
    }

    override fun newInstance(arg: Any?): Plugin<Any> {
        ComponentDefinition.put(434,this)
        return this
    }

}