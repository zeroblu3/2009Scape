package core.game.node.entity.npc.city.miscellania

import core.game.content.dialogue.DialoguePlugin
import core.game.content.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * @author qmqz
 */

@Initializable
class AlvissDialogue(player: Player? = null) : DialoguePlugin(player){
	fun gender (male : String = "sir", female : String = "madam") = if (player.isMale) male else female

	override fun open(vararg args: Any?): Boolean {
		npc = args[0] as NPC
		npc(FacialExpression.CHILD_NORMAL,"Good day, " + gender() + ".")
		if (!player.questRepository.hasStarted("Royal Trouble")) {
			stage = 0
		} else {

		}

		return true
	}

	override fun handle(interfaceId: Int, buttonId: Int): Boolean {
		when(stage){
			0 -> player("Good day.").also { stage = 99 }

			99 -> end()
		}
		return true
	}

	override fun newInstance(player: Player?): DialoguePlugin {
		return AlvissDialogue(player)
	}

	override fun getIds(): IntArray {
		return intArrayOf(3933)
	}
}