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

        /**
         * Force animation + messages on all NPCs in a radius of 10 from the player.
         */
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


        /**
         * Transform a player's appearance into that of an NPC.
         */
        define("pnpc"){player,args ->
            if(args.size < 2){
                reject(player,"Usage: ::pnpc <npcid>")
                return@define
            }

            val pnpc_id = args[1].toIntOrNull()
            if(pnpc_id == null){
                reject(player,"<npcid> must be a valid integer.")
                return@define
            }

            player.appearance.transformNPC(pnpc_id)
            player.sendMessage("Transformed into NPC $pnpc_id")
        }


        /**
         * Toggle invisibility
         */
        define("invis"){player,_ ->
            player.isInvisible = !player.isInvisible
            player.sendMessage("You are now ${if(player.isInvisible) "invisible" else "visible"} to others.")
        }


        /**
         * Toggle 1-hit kills
         */
        define("1hit"){player,_ ->
            player.setAttribute("1hko", !player.getAttribute("1hko", false))
            player.sendMessage("1-hit KO mode " + if (player.getAttribute("1hko", false)) "on." else "off.")
        }


        /**
         * Toggle god mode
         */
        define("god"){player,_ ->
            player.setAttribute("godMode", !player.getAttribute("godMode",false))
            player.sendMessage("God mode ${if(player.getAttribute("godMode",false)) "enabled." else "disabled."}")
        }

        define("mrboneswildride"){ player, args ->
            if (args.size < 2) {
                reject(player, "Syntax error: ::mrboneswildride <boolean>")
                return@define
            }
            val minecart = args[1].toBoolean()
            if (minecart) {
                player.appearance.rideCart(true)
            } else {
                player.appearance.rideCart(false)
            }

        }
    }
}