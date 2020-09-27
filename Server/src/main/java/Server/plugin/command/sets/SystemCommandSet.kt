package plugin.command.sets

import core.game.system.SystemManager
import core.game.system.SystemState
import core.plugin.InitializablePlugin
import plugin.command.Command
import plugin.command.CommandSet

@InitializablePlugin
class SystemCommandSet : CommandSet(Command.Privilege.ADMIN){
    override fun defineCommands() {
        /**
         * Start an update countdown
         */
        define("update"){player,args ->
            if (args.size > 1) {
                SystemManager.getUpdater().setCountdown(args[1].toInt())
            }
            SystemManager.flag(SystemState.UPDATING)
        }

        /**
         * Cancel an update countdown
         */
        define("cancelupdate"){player,_ ->
            SystemManager.getUpdater().cancel()
        }


    }
}