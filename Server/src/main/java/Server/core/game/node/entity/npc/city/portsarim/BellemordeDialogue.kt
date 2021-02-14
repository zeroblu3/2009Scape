package core.game.node.entity.npc.city.portsarim

import core.game.content.dialogue.DialoguePlugin
import core.game.content.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * @author qmqz
 */

@Initializable
class BeefyBurnsDialogue(player: Player? = null) : DialoguePlugin(player){

	override fun open(vararg args: Any): Boolean {
		npc = args[0] as NPC

		if (player.equipment.contains(6544, 1) || player.equipment.contains(4677, 1) ) {
			player("Hello puss.").also { stage = 0 }
		} else {
			player("Hello puss.").also { stage = 10 }
		}

		return true
	}

	override fun handle(interfaceId: Int, buttonId: Int): Boolean {
		when (stage) {
			0 -> npc(FacialExpression.CHILD_NORMAL, "Hello human.").also { stage++ }
			1 -> player("Would you like a fish?").also { stage++ }
			2 -> npc(FacialExpression.CHILD_NORMAL, "I don't want your fish.", "I hunt and eat what I need by myself.").also { stage = 99 }

			10 -> npc(FacialExpression.CHILD_NORMAL, "Hiss!").also { stage = 99 }

			99 -> end()
		}
		return true
	}

	override fun newInstance(player: Player): DialoguePlugin { return BeefyBurnsDialogue(player) }
	override fun getIds(): IntArray { return intArrayOf(2942) }
}