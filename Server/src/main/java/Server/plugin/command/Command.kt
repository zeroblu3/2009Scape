package plugin.command

import core.game.node.entity.player.Player
import plugin.stringtools.colorize

/**
 * Base class used for Commands, automatically adds itself to a mapping and declares an abstract
 * function handle for handling the command.
 * @author Ceikry
 */
class Command(val name: String, val privilege: Privilege, val handle: (Player, Array<String>) -> Unit) {
    fun attemptHandling(player: Player, args: Array<String>?){
        args ?: return
        if(player.rights.ordinal >= privilege.ordinal){
            handle(player,args)
        }
    }

    fun reject(player: Player, message: String){
            player.sendMessage(colorize("%R$message"))
    }

    enum class Privilege{
        STANDARD,
        MODERATOR,
        ADMIN
    }
}

object CommandMapping {
    private val mapping = hashMapOf<String,Command>()

    fun get(name: String): Command?{
        return mapping[name]
    }

    fun register(command: Command){
        mapping[command.name] = command
    }

    fun getCommands(): Array<Command> {
        return mapping.values.toTypedArray()
    }

    fun getNames(): Array<String> {
        return mapping.keys.toTypedArray()
    }
}