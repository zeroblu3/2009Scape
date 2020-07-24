package plugin.skill.agility.shortcuts

import core.game.node.`object`.GameObject
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.diary.DiaryType
import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.update.flag.context.Animation
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import plugin.skill.agility.AgilityHandler
import plugin.skill.agility.AgilityShortcut

/**
 * Handles a pipe shortcut.
 * @author Woah
 */
@InitializablePlugin
class PipeShortcut : AgilityShortcut {

    /**
     * Constructs a new {@Code PipeShortcut} {@Code Object}
     */
    constructor() : super(intArrayOf(), 0, 0.0, "")

    /**
     * Constructs a new {@Code PipeShortcut} {@Code Object}
     *
     * @param ids        the ids.
     * @param level      the level.
     * @param experience the experience.
     * @param options    the options.
     */
    constructor(ids: IntArray?, level: Int, experience: Double, vararg options: String?) : super(ids, level, experience, *options)

    override fun newInstance(arg: Any?): Plugin<Any> {
        configure(PipeShortcut(intArrayOf(2290), 49, 0.0, "squeeze-through")) //Yanille Dungeon
        configure(PipeShortcut(intArrayOf(5099), 22, 8.5, "squeeze-through")) //Brimhaven Dungeon Pipe Red Dragons -> Black Demons
        configure(PipeShortcut(intArrayOf(5100), 34, 10.0, "squeeze-through")) //Brimhaven Dungeon Pipe Moss Giants -> Moss Giants
        configure(PipeShortcut(intArrayOf(9293), 70, 10.0, "squeeze-through")) //Taverley Dungeon
        configure(PipeShortcut(intArrayOf(20210), 35, 10.0, "squeeze-through")) //Barbarian Outpost
        configure(PipeShortcut(intArrayOf(29370), 51, 10.0, "squeeze-through")) //Edgeville Dungeon
        return this
    }

    override fun run(player: Player, obj: GameObject, option: String, failed: Boolean) {
        if (obj.id == 29370 && !player.achievementDiaryManager.getDiary(DiaryType.VARROCK).isComplete(2, 5))
            player.achievementDiaryManager.getDiary(DiaryType.VARROCK).updateTask(player, 2, 5, true)

        GameWorld.Pulser.submit(object : Pulse(1, player) {
            override fun pulse(): Boolean {
                when (obj.id) {

                    2290, 9293, 29370 -> {
                        player.lock(7)
                        AgilityHandler.forceWalk(player, -1, player.location, pipeDestination(player, obj, 6), Animation.create(10580), 10, 0.0, null)
                        player.animate(Animation(844), 4)
                        player.animate(Animation(10579), 5)
                        return true
                    }

                    5099, 5100 -> {
                        player.lock(5)
                        AgilityHandler.forceWalk(player, -1, player.location, pipeDestination(player, obj, 7), Animation.create(10580), 10, 0.0, null)
                        player.animate(Animation(844), 5)
                        player.animate(Animation(10579), 6)
                        return true
                    }

                    20210 -> {
                        player.lock(3)
                        AgilityHandler.forceWalk(player, -1, player.location, pipeDestination(player, obj, 3), Animation.create(10580), 15, 0.0, null)
                        return true
                    }
                }
                return false
            }
        })
    }
}