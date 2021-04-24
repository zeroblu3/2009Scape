package core.game.node.entity.npc.city.keldagrim

import core.game.content.dialogue.DialoguePlugin
import core.game.content.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * @author qmqz
 */

@Initializable
class AudmannDialogue(player: Player? = null) : DialoguePlugin(player){

	override fun open(vararg args: Any): Boolean {
		npc = args[0] as NPC
		npc(FacialExpression.CHILD_NORMAL,"Oh, don't bother me human.").also { stage = 0 }
		return true
	}

	override fun handle(interfaceId: Int, buttonId: Int): Boolean {
		when (stage) {
			0 -> player("Why not? What's wrong?").also { stage++ }
			1 -> npc(FacialExpression.CHILD_NORMAL,"You are wrong, human. ",
				"Your attire is outrageous. ","Your presence is obnoxious.").also { stage++ }
			2 -> player("What? What are you saying?").also { stage++ }
			3 -> npc(FacialExpression.CHILD_NORMAL, "I'm saying you're in my way.").also { stage = 99 }

			99 -> end()
		}
		return true
	}

	override fun newInstance(player: Player): DialoguePlugin { return AudmannDialogue(player) }
	override fun getIds(): IntArray { return intArrayOf(2201) }
}