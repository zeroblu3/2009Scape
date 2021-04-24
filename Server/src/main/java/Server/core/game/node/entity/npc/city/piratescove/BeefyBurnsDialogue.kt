package core.game.node.entity.npc.city.piratescove

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
class BeefyBurnsDialogue(player: Player? = null) : DialoguePlugin(player){

	override fun open(vararg args: Any): Boolean {
		npc = args[0] as NPC
		player("What are you cooking?").also { stage = 0 }
		return true
	}

	override fun handle(interfaceId: Int, buttonId: Int): Boolean {
		when (stage) {
			0 -> npc ("My speciality!", "What else could I be cooking?").also { stage++ }
			1 -> player("Ok, and your speciality is...?").also { stage++ }
			2 -> npc("Boiled shark guts with a hint of rosemary", "and a dash of squid ink.").also { stage++ }
			3 -> player( "I think I'll stick to making my own food.").also { stage++ }
			4 -> npc("Your loss!").also { stage = 99 }

			99 -> end()
		}
		return true
	}

	override fun newInstance(player: Player): DialoguePlugin { return BeefyBurnsDialogue(player) }
	override fun getIds(): IntArray { return intArrayOf(4541) }
}