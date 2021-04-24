package core.game.node.entity.npc.city.keldagrim

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
class BlasidarTheSculptorDialogue(player: Player? = null) : DialoguePlugin(player){

	override fun open(vararg args: Any): Boolean {
		npc = args[0] as NPC
		npc(FacialExpression.CHILD_SUSPICIOUS,"The new statue looks beautiful, don't you agree?").also { stage = 0 }
		return true
	}

	override fun handle(interfaceId: Int, buttonId: Int): Boolean {
		when (stage) {
			0 -> player("Oh yes, quite.").also { stage++ }
			1 -> npc(FacialExpression.CHILD_NORMAL,"My finest piece of work, without a doubt.").also { stage++ }
			2 -> player("Say, I was wondering, did you do the statues", "down in the mines as well?").also { stage++ }
			3 -> npc(FacialExpression.CHILD_NORMAL,"What, out with the trolls?").also { stage++ }
			4 -> npc(FacialExpression.CHILD_NORMAL,"If only!",
				"But no, they've been there for many thousands ",
				"of years, actually.",
				"Been there before dwarven recorded history.").also { stage++ }
			5 -> player("No no, I'm sure they weren't there before!").also { stage++ }
			6 -> npc(FacialExpression.CHILD_NORMAL,"Of course they were, don't be silly!").also { stage++ }
			7 -> player("Alright... my mistake then.").also { stage = 99 }

			99 -> end()
		}
		return true
	}

	override fun newInstance(player: Player): DialoguePlugin { return BlasidarTheSculptorDialogue(player) }
	override fun getIds(): IntArray { return intArrayOf(2141) }
}