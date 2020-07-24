package plugin.skill.agility.shortcuts

import core.game.node.`object`.GameObject
import core.game.node.entity.impl.ForceMovement
import core.game.node.entity.player.Player
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.map.Location
import core.game.world.update.flag.context.Animation
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import plugin.skill.agility.AgilityHandler
import plugin.skill.agility.AgilityShortcut

/**
 * Handles the Basalt Rock shortcut.
 * @author Woah
 */
@InitializablePlugin
class BasaltRockShortcut : AgilityShortcut {

    /**
     * Constructs a new {@Code BasaltRockShortcut} {@Code Object}
     */
    constructor() : super(intArrayOf(), 0, 0.0, "")

    val noJump = "I can't jump from here."

    /**
     * Constructs a new {@Code BasaltRockShortcut} {@Code Object}
     *
     * @param ids        the ids.
     * @param level      the level.
     * @param experience the experience.
     * @param options    the options.
     */
    constructor(ids: IntArray?, level: Int, experience: Double, vararg options: String?) : super(ids, level, experience, *options)

    /**
     * Basalt Rock Index : Direction South (Barbarian Outpost) to North (Lighthouse)
     * Beach -> Rock 1 -> Rock etc... -> Rocky Shore
     * 2522, 3600 R1, 3601, 3602 R2
     */
    override fun newInstance(arg: Any?): Plugin<Any> {
        configure(BasaltRockShortcut(intArrayOf(4550), 1, 0.0, "jump-to")) //Beach South*
        configure(BasaltRockShortcut(intArrayOf(4551), 1, 0.0, "jump-across")) //Beach South Rock 1*
        configure(BasaltRockShortcut(intArrayOf(4552), 1, 0.0, "jump-across")) //South Rock 2
        configure(BasaltRockShortcut(intArrayOf(4553), 1, 0.0, "jump-across")) //South Rock 2 (other side)
        configure(BasaltRockShortcut(intArrayOf(4554), 1, 0.0, "jump-across")) //Middle Rock 3
        configure(BasaltRockShortcut(intArrayOf(4555), 1, 0.0, "jump-across")) //Middle Rock 3 (other side)
        configure(BasaltRockShortcut(intArrayOf(4556), 1, 0.0, "jump-across")) //North Rock 4
        configure(BasaltRockShortcut(intArrayOf(4557), 1, 0.0, "jump-across")) //North Rock 4 (other side)
        configure(BasaltRockShortcut(intArrayOf(4558), 1, 0.0, "jump-across")) //Rocky Shore North Rock 5*
        configure(BasaltRockShortcut(intArrayOf(4559), 1, 0.0, "jump-to")) //Rocky Shore North*
        return this
    }

    override fun run(player: Player, obj: GameObject, option: String, failed: Boolean) {
        GameWorld.Pulser.submit(object : Pulse(0, player) {
            override fun pulse(): Boolean {
                when (obj.id) {

                4552 -> {
                    if (player.location == Location.create(2522,3600,0)) {
                        player.sendMessage(noJump)
                    } else {
                        player.lock(4)
                        AgilityHandler.forceWalk(player, -1, player.location, Location.create(2522, 3600, 0), Animation.create(819), 20, 0.0, null)
                        AgilityHandler.forceWalk(player, -1, Location.create(2522, 3600, 0), pipeDestination(player, obj, 3), Animation.create(769), 20, 0.0, null, 1)
                    }
                    return true
                }

                4553 -> {
                    if (player.location == Location.create(2522,3602,0)) {
                        player.sendMessage(noJump)
                    } else {
                        player.lock(4)
                        AgilityHandler.forceWalk(player, -1, player.location, Location.create(2522, 3602, 0), Animation.create(819), 20, 0.0, null)
                        AgilityHandler.forceWalk(player, -1, Location.create(2522, 3602, 0), pipeDestination(player, obj, 3), Animation.create(769), 20, 0.0, null, 1)
                    }
                    return true
                }

                4554 -> {
                    if (player.location == Location.create(2518, 3611, 0)) {
                        player.sendMessage(noJump)
                    } else {
                        player.lock(4)
                        AgilityHandler.forceWalk(player, -1, player.location, Location.create(2518, 3611, 0), Animation.create(819), 20, 0.0, null)
                        AgilityHandler.forceWalk(player, -1, Location.create(2518, 3611, 0), Location.create(2516, 3611, 0), Animation.create(769), 20, 0.0, null, 1)
                    }
                    return true
                    }

                4555 -> {
                    if (player.location == Location.create(2516, 3611, 0)) {
                        player.sendMessage(noJump)
                    } else {
                        player.lock(4)
                        AgilityHandler.forceWalk(player, -1, player.location, Location.create(2516, 3611, 0), Animation.create(819), 20, 0.0, null)
                        AgilityHandler.forceWalk(player, -1, Location.create(2516, 3611, 0), pipeDestination(player, obj, 3), Animation.create(769), 20, 0.0, null, 1)
                    }
                    return true
                    }

                4556 -> {
                    if (player.location == Location.create(2514, 3613, 0)) {
                        player.sendMessage(noJump)
                    } else {
                        player.lock(4)
                        AgilityHandler.forceWalk(player, -1, player.location, Location.create(2514, 3613, 0), Animation.create(819), 20, 0.0, null)
                        AgilityHandler.forceWalk(player, -1, Location.create(2514, 3613, 0), pipeDestination(player, obj, 3), Animation.create(769), 20, 0.0, null, 1)
                    }
                    return true
                }

                4557 -> {
                    if (player.location == Location.create(2514, 3615, 0)) {
                        player.sendMessage(noJump)
                    } else {
                        player.lock(4)
                        AgilityHandler.forceWalk(player, -1, player.location, Location.create(2514, 3615, 0), Animation.create(819), 20, 0.0, null)
                        AgilityHandler.forceWalk(player, -1, Location.create(2514, 3615, 0), pipeDestination(player, obj, 3), Animation.create(769), 20, 0.0, null, 1)
                    }
                    return true
                }
//                4558 -> {
//                    player.lock(4)
//                    AgilityHandler.forceWalk(player, -1, player.location, Location.create(2514,3615,0), Animation.create(819), 20, 0.0, null)
//                    AgilityHandler.forceWalk(player, -1, Location.create(2514,3615,0), pipeDestination(player, obj, 3), Animation.create(769), 20, 0.0, null, 2)
//                    return true
//                }
//                4550 -> {
//                    if (Location.) {
//
//                    }
//                }

                }
                return false
            }
        })
    }
}