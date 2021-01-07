package plugin.skill.construction.decoration.costume

import core.cache.def.impl.ObjectDefinition
import core.game.interaction.OptionHandler
import core.game.node.Node
import core.game.node.entity.player.Player
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import plugin.interaction.item.toys.DiangoReclaimInterface

/**
 * Handles the Toy Box POH
 * ToyBoxPlugin.java
 * @author Lee
 * @date 10/2/2017
 */
@InitializablePlugin
class ToyBoxPlugin : OptionHandler() {
    @Throws(Throwable::class)
    override fun newInstance(arg: Any?): Plugin<Any?> {
        ObjectDefinition.forId(18802).handlers["option:open"] = this
        return this
    }

    override fun handle(player: Player, node: Node, option: String): Boolean {
        DiangoReclaimInterface.open(player)
        return true
    }
}