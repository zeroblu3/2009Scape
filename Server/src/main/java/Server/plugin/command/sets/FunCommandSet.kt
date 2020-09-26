package plugin.command.sets

import core.game.node.entity.npc.NPC
import core.game.world.map.RegionManager
import core.game.world.update.flag.context.Animation
import core.plugin.InitializablePlugin
import plugin.command.Command
import plugin.command.CommandSet
import java.util.*

@InitializablePlugin
class FunCommandSet : CommandSet(Command.Privilege.ADMIN) {

    var npcs: List<NPC> = ArrayList()


    override fun defineCommands() {
        define("npcareaanim") { player, args ->
            if (args.size < 3) {
                reject(player, "Syntax error: ::npcareaanim <Animation ID> <String>")
                return@define
            }
            npcs = RegionManager.getLocalNpcs(player.location, 10)
            for (n in npcs) {
                n.sendChat(args.slice(2 until args.size).joinToString(" "))
                n.lock(6)
                n.faceTemporary(player, 6)
                n.animator.animate(Animation(args[1].toInt()))
                n.animate(Animation.create(-1), 6)

            }
        }
    }
}