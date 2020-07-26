package plugin.command

import core.game.node.entity.player.Player
import core.game.system.SystemLogger.log
import core.game.system.command.CommandPlugin
import core.game.system.command.CommandSet
import core.game.world.map.Location
import core.game.world.map.RegionManager
import core.game.world.map.build.DynamicRegion
import core.game.world.repository.Repository
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

/**
 * Represents the commands related to teleporting.
 * @author 'Vexia
 * @author Emperor
 */
@InitializablePlugin
class TeleportCommand : CommandPlugin() {
    @Throws(Throwable::class)
    override fun newInstance(arg: Any?): Plugin<Any?>? {
        link(CommandSet.ADMINISTRATOR)
        return this
    }

    override fun parse(player: Player?, name: String?, args: Array<String?>?): Boolean {
        var args = args
        val target: Player?
        var n: String? = ""
        when (name) {
            "tele" -> {
                if (args!!.size == 2 && args[1]!!.contains(",")) {
                    args = args[1]!!.split(",".toRegex()).toTypedArray()
                    val x = args[1]!!.toInt() shl 6 or args[3]!!.toInt()
                    val y = args[2]!!.toInt() shl 6 or args[4]!!.toInt()
                    val z = args[0]!!.toInt()
                    player!!.properties.teleportLocation = Location.create(x, y, z)
                    return true
                }
                if (args.size < 2) {
                    player!!.debug("syntax error: x, y, (optional) z")
                    return false
                }
                player!!.properties.teleportLocation = Location.create(args[1]!!.toInt(), args[2]!!.toInt(), if (args.size > 3) args[3]!!.toInt() else 0)
                return true
            }
            "telecs" -> {
                try {
                    val loc = player!!.playerFlags.lastSceneGraph
                    val x = (loc.regionX - 6 shl 3) + toInteger(args!![1]!!)
                    val y = (loc.regionY - 6 shl 3) + toInteger(args[2]!!)
                    player.pulseManager.clear()
                    player.properties.teleportLocation = Location.create(x, y, player.location.z)
                } catch (t: Throwable) {
                    // region is changing
                    t.printStackTrace()
                }
                return true
            }
            "teler" -> {
                val regionId = args!![1]!!.toInt()
                var x = 32
                var y = 32
                if (args.size > 3) {
                    x = toInteger(args[2]!!)
                    y = toInteger(args[3]!!)
                }
                player!!.properties.teleportLocation = Location.create((regionId shr 8 shl 6) + x, (regionId and 0xFF shl 6) + y, 0)
                player.debug("Current location=" + player.properties.teleportLocation)
                return true
            }
            "telers" -> {
                val regionId = args!![1]!!.toInt()
                player!!.properties.teleportLocation = Location.create(regionId shr 8 shl 6, regionId and 0xFF shl 6, 0)
                player.debug("Current location=" + player.properties.teleportLocation)
                return true
            }
            "telere" -> {
                val regionId = args!![1]!!.toInt()
                player!!.properties.teleportLocation = Location.create((regionId shr 8 shl 6) + 63, (regionId and 0xFF shl 6) + 63, 0)
                player.debug("Current location=" + player.properties.teleportLocation)
                return true
            }
            "teleto" -> {
                if (args!!.size < 1) {
                    player!!.debug("syntax error: name")
                    return true
                }
                var i = 1
                while (i < args.size) {
                    if (i == 1) {
                        n += args[i]
                        i++
                        continue
                    }
                    n += " " + args[i]
                    i++
                }
                target = Repository.getPlayer(n)
                if (target == null) {
                    player!!.debug("syntax error: name")
                    return true
                }
                if (target.getAttribute<Any?>("fc_wave") != null) {
                    player!!.sendMessage("You cannot teleport to a player who is in the Fight Caves.")
                    return true
                }
                player!!.properties.teleportLocation = target.location
            }
            "teletome" -> {
                if (args!!.size < 1) {
                    player!!.debug("syntax error: name")
                    return true
                }
                var i = 1
                while (i < args.size) {
                    if (i == 1) {
                        n += args[i]
                        i++
                        continue
                    }
                    n += " " + args[i]
                    i++
                }
                target = Repository.getPlayer(n)
                if (target == null) {
                    player!!.debug("syntax error: name")
                    return true
                }
                target.properties.teleportLocation = player!!.location
            }
            "pos", "position", "loc" -> {
                val l = player!!.location
                val r = player.viewport.region
                player.packetDispatch.sendMessage("Absolute: " + l + ", regional: [" + l.localX + ", " + l.localY + "], chunk: [" + l.chunkOffsetX + ", " + l.chunkOffsetY + "], flag: [" + RegionManager.isTeleportPermitted(l) + ", " + RegionManager.getClippingFlag(l) + ", " + RegionManager.isLandscape(l) + "].")
                player.packetDispatch.sendMessage("Region: [id=" + l.regionId + ", active=" + r.isActive + ", instanced=" + (r is DynamicRegion) + "], obj=" + RegionManager.getObject(l) + ".")
                player.packetDispatch.sendMessage("Object: " + RegionManager.getObject(l) + ".")
                log("Viewport: " + l.getSceneX(player.playerFlags.lastSceneGraph) + "," + l.getSceneY(player.playerFlags.lastSceneGraph))
                val loc = "Location.create(" + l.x + ", " + l.y + ", " + l.z + ")"
                log(loc + "; " + player.playerFlags.lastSceneGraph + ", " + l.localX + ", " + l.localY)
                val stringSelection = StringSelection(loc)
                val clpbrd = Toolkit.getDefaultToolkit().systemClipboard
                clpbrd.setContents(stringSelection, null)
                return true
            }
        }
        return false
    }
}