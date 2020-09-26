package plugin.command

import core.game.node.entity.player.Player
import core.plugin.Plugin
import plugin.stringtools.colorize

abstract class CommandSet(val defaultPrivilege: Command.Privilege) : Plugin<Any?> {
    override fun newInstance(arg: Any?): Plugin<Any?> {
        defineCommands()
        return this
    }

    override fun fireEvent(identifier: String?, vararg args: Any?): Any {
        return Unit
    }

    abstract fun defineCommands()


    fun reject(player: Player, message: String){
        player.sendMessage(colorize("%R$message"))
    }

    fun define(name: String, privilege: Command.Privilege = defaultPrivilege, handle: (Player, Array<String>) -> Unit){
        CommandMapping.register(Command(name,privilege,handle))
    }
}