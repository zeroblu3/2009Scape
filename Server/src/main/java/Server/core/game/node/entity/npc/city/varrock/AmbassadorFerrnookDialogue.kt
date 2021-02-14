package core.game.node.entity.npc.city.varrock

import core.game.content.dialogue.DialoguePlugin
import core.game.content.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * @author qmqz
 */

@Initializable
class AmbassadorFerrnookDialogue(player: Player? = null) : DialoguePlugin(player){

	override fun newInstance(player: Player): DialoguePlugin {
		return AmbassadorFerrnookDialogue(player)
	}

	override fun open(vararg args: Any): Boolean {
		npc = args[0] as NPC
		player(FacialExpression.ASKING, "Hello Ambassador. Are you here visiting King Roald?")
		stage = 0
		return true
	}

	override fun handle(interfaceId: Int, buttonId: Int): Boolean {
		when (stage) {
			0 -> npc(FacialExpression.CHILD_NORMAL, "Well, in theory, but he always seems to be busy.").also { stage++ }
			1 -> player(FacialExpression.HALF_GUILTY, "You don't seem that upset by that, though...").also { stage++ }
			2 -> npc(FacialExpression.CHILD_FRIENDLY, "Oh no, I like travelling, and if you become a diplomat",
					"patience is a vital skill.").also { stage++ }
			3 -> player(FacialExpression.HALF_THINKING, "I'll try to remember that.").also { stage = 99 }

			99 -> end()
		}
		return true
	}

	override fun getIds(): IntArray {
		return intArrayOf(4582)
	}
}