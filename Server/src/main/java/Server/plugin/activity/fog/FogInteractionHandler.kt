package plugin.activity.fog

import core.game.content.global.action.ClimbActionHandler
import core.game.node.Node
import core.game.node.`object`.GameObject
import core.game.node.entity.player.Player
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import plugin.interaction.`object`.LadderManagingPlugin
import plugin.quest.PluginInteraction
import plugin.quest.PluginInteractionManager

@InitializablePlugin
class FogInteractionHandler : PluginInteraction(30204){

    override fun handle(player: Player?, node: Node?): Boolean {
        when(node?.id){
            30204 -> ClimbActionHandler.climbLadder(player!!,node as GameObject,"climb-down").also { return true }
        }
        return false
    }

    override fun fireEvent(identifier: String?, vararg args: Any?): Any {
        return Unit
    }

    override fun newInstance(arg: Any?): Plugin<Any> {
        PluginInteractionManager.register(this,PluginInteractionManager.InteractionType.OBJECT)
        return this
    }

}