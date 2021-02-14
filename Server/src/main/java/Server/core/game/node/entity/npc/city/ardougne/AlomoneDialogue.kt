package core.game.node.entity.npc.city.ardougne

import core.game.content.dialogue.DialoguePlugin
import core.game.content.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * @author qmqz
 */

@Initializable
class AlomoneDialogue(player: Player? = null) : DialoguePlugin(player){

	override fun open(vararg args: Any?): Boolean {
		npc = args[0] as NPC
		player("Hello again.")
		stage = 0
		return true
	}

	override fun handle(interfaceId: Int, buttonId: Int): Boolean {
		when(stage){
			0 -> npc(FacialExpression.ANNOYED, "You have crossed my path too many times intruder.", "Leave or face my wrath.").also { stage++ }
			1 -> player(FacialExpression.HALF_ROLLING_EYES, "Yeah, whatever.").also { stage = 99 }

			99 -> end()
		}
		return true
	}

	override fun newInstance(player: Player?): DialoguePlugin {
		return AlomoneDialogue(player)
	}

	override fun getIds(): IntArray {
		return intArrayOf(891)
	}
}