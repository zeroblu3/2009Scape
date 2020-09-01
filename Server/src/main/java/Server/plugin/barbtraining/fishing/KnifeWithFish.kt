package plugin.barbtraining.fishing

import core.game.interaction.NodeUsageEvent
import core.game.interaction.UseWithHandler
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import core.tools.ItemNames

@InitializablePlugin
/**
 * Handles using a knife with barbarian fishing fish
 * @author Ceikry
 */
class KnifeWithFish : UseWithHandler(11328,11330,11332){
    override fun handle(event: NodeUsageEvent?): Boolean {
        event?.player ?: return false
        event.player.pulseManager.run(FishCuttingPulse(event.player,event.usedItem.id))
        return true
    }

    override fun newInstance(arg: Any?): Plugin<Any> {
        addHandler(ItemNames.KNIFE, ITEM_TYPE,this)
        return this
    }

}