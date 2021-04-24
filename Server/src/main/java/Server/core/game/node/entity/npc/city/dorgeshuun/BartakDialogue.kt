package core.game.node.entity.npc.city.dorgeshuun

import core.game.content.dialogue.DialoguePlugin
import core.game.content.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable


/**
 * @author qmqz
 */

@Initializable
class BartakDialogue(player: Player? = null) : DialoguePlugin(player){

	override fun open(vararg args: Any): Boolean {
		npc = args[0] as NPC
		npc(FacialExpression.CHILD_GUILTY,"Oh no! What's broken?").also { stage = 0 }
		return true
	}

	override fun handle(interfaceId: Int, buttonId: Int): Boolean {
		when (stage) {
			0 -> player("What? Nothing's broken.").also { stage++ }
			1 -> npc(FacialExpression.CHILD_SUSPICIOUS,"I'm sorry. I'm just a bit jumpy.").also { stage++ }
			2 -> npc(FacialExpression.CHILD_SUSPICIOUS,"I'm in charge of all the metalworking of Dorgesh-Kaan. ",
				"It's a big responsibility!").also { stage++ }
			3 -> npc(FacialExpression.CHILD_SUSPICIOUS,"If something metal breaks I have to fix it. ",
				"And lots of things are made of metal!").also { stage++ }
			4 -> player("Don't worry, I'm sure you're up to the task!").also { stage++ }
			5 -> npc(FacialExpression.CHILD_SUSPICIOUS,"I hope you're right.").also { stage = 99 }

			// when using ores on furnace near bartak
			10 -> npc(FacialExpression.CHILD_SUSPICIOUS,"Get away from that furnace!").also { stage++ }
			11 -> player("Are you worried I'll break your furnace?").also { stage++ }
			12 -> npc(FacialExpression.CHILD_SUSPICIOUS,"I'm worried the furnace will break you!").also { stage++ }
			13 -> npc(FacialExpression.CHILD_SUSPICIOUS,"It's set up to make glass and jewellery at the moment. ",
				"It's not safe to use it to smelt ore. ",
				"What if you accidentally burn yourself?").also { stage++ }
			14 -> npc(FacialExpression.CHILD_SUSPICIOUS,"What if you accidentally burn yourself and the surface people ",
				"see that you're burnt and use that as a ",
				"pretext to attack us?").also { stage++ }
			15 -> npc(FacialExpression.CHILD_SUSPICIOUS,"We'd all be killed in our sleep! ",
				"And it would be my fault!").also { stage++ }
			16 -> npc(FacialExpression.CHILD_SUSPICIOUS,"No, no, no, no, no - I can't take the risk.").also { stage++ }
			17 -> player("Alright, calm down! ",
				"I won't use your furnace. ",
				"To smelt ore.").also { stage = 99 }

			99 -> end()
		}
		return true
	}

	override fun newInstance(player: Player): DialoguePlugin { return BartakDialogue(player) }
	override fun getIds(): IntArray { return intArrayOf(5778) }
}