package core.game.node.entity.npc.city.rellekka

import core.game.content.dialogue.DialoguePlugin
import core.game.content.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * @author qmqz
 * Note: Missing quest dialogue
 * Note: Missing fremennik name
 */

@Initializable
class BjornDialogue(player: Player? = null) : DialoguePlugin(player){

	override fun open(vararg args: Any): Boolean {
		npc = args[0] as NPC
		npc("Hey! Itsh you again! Whatshyerfashe!").also { stage = 0 }
		return true
	}

	override fun handle(interfaceId: Int, buttonId: Int): Boolean {
		when (stage) {
			0 -> player (player.username+"?").also { stage++ }
			1 -> npc("Nah nah nah, not them, the other one, whatshyerfashe!").also { stage++ }
			2 -> player("[Fremennik name]?").also { stage++ }
			3 -> npc(FacialExpression.SUSPICIOUS, "Thatsh what I said diddle I?").also { stage++ }
			4 -> player("Um.... okay. I'll leave you to your drinking.").also { stage++ }
			5 -> npc("Thanksh pal! You're alright!").also { stage = 99 }

			99 -> end()
		}
		return true
	}

	override fun newInstance(player: Player): DialoguePlugin { return BjornDialogue(player) }
	override fun getIds(): IntArray { return intArrayOf(1284) }
}