package core.game.node.entity.npc.city.neitiznot

import core.game.content.dialogue.DialoguePlugin
import core.game.content.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * @author qmqz
 */

@Initializable
class AnneIsaaksonDialogue(player: Player? = null) : DialoguePlugin(player){

	override fun open(vararg args: Any): Boolean {
		npc = args[0] as NPC
		npc(FacialExpression.HAPPY, "Hello visitor, how are you?").also { stage = 0 }
		return true
	}

	override fun handle(interfaceId: Int, buttonId: Int): Boolean {
		when (stage) {
			0 -> player(FacialExpression.FRIENDLY,"Better than expected.",
				"It's a lot... nicer... here than I was expecting.",
				"Everyone seems pretty happy.").also { stage++ }
			1 -> npc(FacialExpression.HAPPY, "Of course, the Burgher is strong and wise,", "and looks after us well.").also { stage++ }
			2 -> player(
				FacialExpression.THINKING, "I think some of those Jatizso citizens have",
				"got the wrong idea about this place.").also { stage = 99 }

			99 -> end()
		}
		return true
	}

	override fun newInstance(player: Player): DialoguePlugin { return AnneIsaaksonDialogue(player) }
	override fun getIds(): IntArray { return intArrayOf(5512) }
}