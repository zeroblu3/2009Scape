package plugin.activity.gnomecooking.crunchies

import core.game.content.ItemNames
import core.game.interaction.NodeUsageEvent
import core.game.interaction.UseWithHandler
import core.game.node.item.Item
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import plugin.skill.Skills

/**
 * Handles garnishing gnome crunchies
 * @author Ceikry
 */

@InitializablePlugin
class GnomeCrunchyGarnisher : UseWithHandler(9578,9580,9582,9584) {
    override fun newInstance(arg: Any?): Plugin<Any> {
        addHandler(ItemNames.CHOCOLATE_DUST_1975, ITEM_TYPE,this)
        addHandler(ItemNames.GNOME_SPICE_2169, ITEM_TYPE, this)
        addHandler(ItemNames.EQUA_LEAVES_2128, ITEM_TYPE, this)
        return this
    }

    override fun handle(event: NodeUsageEvent?): Boolean {
        event ?: return false
        val player = event.player
        val used = event.used.asItem()
        val with = event.usedWith.asItem()

        val product = when(with.id){
            ItemNames.CHOCOLATE_DUST_1975 -> {
                when(used.id){
                    9578 -> ItemNames.CHOCCHIP_CRUNCHIES_2209
                    else -> -1
                }
            }

            ItemNames.GNOME_SPICE_2169 -> {
                when(used.id){
                    9584 -> ItemNames.WORM_CRUNCHIES_2205
                    9580 -> ItemNames.SPICY_CRUNCHIES_2213
                    else -> -1
                }
            }

            ItemNames.EQUA_LEAVES_2128 -> {
                when(used.id){
                    9582 -> ItemNames.TOAD_CRUNCHIES_2217
                    else -> -1
                }
            }

            else -> -1
        }

        if(product == -1) return false

        player.inventory.remove(used)
        if(with.id != ItemNames.GNOME_SPICE_2169)
            player.inventory.remove(with)
        player.inventory.add(Item(product))
        player.skills.addExperience(Skills.COOKING,64.0)
        return true
    }

}