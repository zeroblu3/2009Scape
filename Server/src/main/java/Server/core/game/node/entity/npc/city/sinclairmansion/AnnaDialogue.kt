package core.game.node.entity.npc.city.sinclairmansion

import core.game.content.dialogue.DialoguePlugin
import core.game.content.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * @author qmqz
 * Note: Missing quest dialogue
 */

@Initializable
class AnnaDialogue(player: Player? = null) : DialoguePlugin(player){

	override fun open(vararg args: Any): Boolean {
		npc = args[0] as NPC
		npc("Apparently you aren't as stupid as you look.").also { stage = 99 }
		return true
	}

	override fun handle(interfaceId: Int, buttonId: Int): Boolean {
		when (stage) {
			99 -> end()
		}
		return true
	}

	override fun newInstance(player: Player): DialoguePlugin { return AnnaDialogue(player) }
	override fun getIds(): IntArray { return intArrayOf(814) }
}