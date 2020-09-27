package plugin.command.sets

import core.game.component.Component
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.QuestRepository
import core.plugin.InitializablePlugin
import plugin.command.Command
import plugin.command.CommandSet

@InitializablePlugin
class QuestCommandSet : CommandSet(Command.Privilege.ADMIN){
    override fun defineCommands() {
        /**
         * Completes all quests
         */
        define("allquest"){player,_->
            for (quest in QuestRepository.getQuests().values) {
                quest.finish(player)
            }
        }

        /**
         * Displays the currently implemented quests
         */
        define("quests",Command.Privilege.STANDARD){ player, _ ->
            sendQuests(player)
        }
    }

    /**
     * Sends the list of quests.
     * @param player the player.
     */
    private fun sendQuests(player: Player?) {
        player!!.interfaceManager.open(Component(275))
        for (i in 0..310) {
            player.packetDispatch.sendString("", 275, i)
        }
        var lineId = 11
        player.packetDispatch.sendString("<col=ecf0f1>" + "Available Quests" + "</col>", 275, 2)
        for (q in QuestRepository.getQuests().values) {
            // Add a space to beginning and end of string for the strikethrough
            player.packetDispatch.sendString("<col=ecf0f1>" + (if (q.isCompleted(player)) "<str> " else "") + q.name + " ", 275, lineId++)
        }
    }
}