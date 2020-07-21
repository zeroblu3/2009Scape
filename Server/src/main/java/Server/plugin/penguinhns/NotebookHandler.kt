package plugin.penguinhns

import core.cache.def.impl.ItemDefinition
import core.game.interaction.OptionHandler
import core.game.node.Node
import core.game.node.entity.player.Player
import core.plugin.InitializablePlugin
import core.plugin.Plugin

@InitializablePlugin
class NotebookHandler : OptionHandler(){
    override fun handle(player: Player?, node: Node?, option: String?): Boolean {
        val total = player?.getAttribute("phns:points",0)
        val weekly = player?.getAttribute("phns:weekly",0)
        player?.dialogueInterpreter?.sendDialogue("Total points: $total","Penguins spied this week: $weekly")
        return true
    }

    override fun newInstance(arg: Any?): Plugin<Any> {
        ItemDefinition.forId(13732).configurations["option:read"] = this
        return this
    }

}