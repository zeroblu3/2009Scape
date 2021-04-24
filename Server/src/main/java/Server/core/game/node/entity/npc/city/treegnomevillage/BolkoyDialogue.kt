package core.game.node.entity.npc.city.treegnomevillage

import core.game.content.dialogue.DialoguePlugin
import core.game.content.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * @author qmqz
 * Note: Missing quest dialogue & treasure trails dialogue
 */

@Initializable
class BolkoyDialogue(player: Player? = null) : DialoguePlugin(player){

	override fun open(vararg args: Any): Boolean {
		npc = args[0] as NPC
		player("Hello.").also { stage = 0 }
		return true
	}

	override fun handle(interfaceId: Int, buttonId: Int): Boolean {
		when (stage) {
			0 -> npc(FacialExpression.CHILD_NORMAL,"Welcome, welcome.",
				"It's good to see you again.", "The village is much safer now you have ",
				"returned the orbs.").also { stage++ }
			1 -> npc(FacialExpression.CHILD_NORMAL,"By the way, I'm the village shop keeper.",
				"Would you like to buy something?").also { stage++ }
			2 -> options("What have you got?", "No thank you.").also { stage++ }
			3 -> when(buttonId) {
				1 -> npc(FacialExpression.CHILD_NORMAL,"Take a look.").also { stage = 10 }
				2 -> npc(FacialExpression.CHILD_NORMAL,"Ok, maybe later.").also { stage = 20 }
			}

			10 -> end().also { npc.shop.open(player) }
			20 -> end()

			99 -> end()
		}
		return true
	}

	override fun newInstance(player: Player): DialoguePlugin { return BolkoyDialogue(player) }
	override fun getIds(): IntArray { return intArrayOf(471) }
}