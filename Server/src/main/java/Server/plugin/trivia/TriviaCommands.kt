package plugin.trivia

import core.game.node.entity.player.Player
import core.game.system.command.CommandPlugin
import core.game.system.command.CommandSet
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import core.plugin.PluginManifest
import plugin.CorePluginTypes.Managers

@InitializablePlugin
@PluginManifest(name="Trivia")
class TriviaCommands : CommandPlugin() {
    val manager = TriviaManager()

    override fun parse(player: Player?, name: String?, args: Array<String?>?): Boolean {
        when (name){
            "trivia" -> {
                if(args?.size!! < 2){
                    player?.sendMessage("Format: ::trivia start|stop|answer")
                    return true
                }
                when(args[1]){
                    "start" -> {if(manager.continuous) player?.sendMessage("A game is already in progress!") else manager.start(); return true}
                    "stop"  -> {manager.stop();return true}
                    "answer"-> {if(args.size < 3) player?.sendMessage("Format: ::trivia answer type out answer") else manager.submit(player!!,args[2]);return true}
                }
            }
        }
        return false
    }

    override fun newInstance(arg: Any?): Plugin<Any?>? {
        Managers.register(manager)
        link(CommandSet.PLAYER)
        manager.parseRewards()
        return this
    }

}