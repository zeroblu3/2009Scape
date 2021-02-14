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
class BentamirDialogue(player: Player? = null) : DialoguePlugin(player){

	override fun open(vararg args: Any): Boolean {
		npc = args[0] as NPC
		npc(FacialExpression.CHILD_NORMAL,"Do you mind? You're in my home.").also { stage = 0 }
		return true
	}

	override fun handle(interfaceId: Int, buttonId: Int): Boolean {
		when (stage) {
			0 -> player("I'm sorry, should I have knocked?").also { stage++ }
			1 -> npc(FacialExpression.CHILD_NORMAL,"Very funny, human.").also { stage++ }
			2 -> npc(FacialExpression.CHILD_NORMAL,"We can't all live in plush houses, you know.",
				"But that doesn't mean us mining dwarves don't","work hard.").also { stage++ }
			3 -> player("Where do you do your mining?").also { stage++ }
			4 -> npc(FacialExpression.CHILD_NORMAL,"Normally the coal mine to the north.",
				"We need a lot of coal to keep our steam engines", "going, you know.").also { stage++ }
			5 -> player("Can I do a bit of mining there as well?").also { stage++ }
			6 -> npc(FacialExpression.CHILD_NORMAL,"I'm not sure, but as long as no one notices",
				"I don't think anyone is going to care.").also { stage = 99 }


			99 -> end()
		}
		return true
	}

	override fun newInstance(player: Player): DialoguePlugin { return BentamirDialogue(player) }
	override fun getIds(): IntArray { return intArrayOf(2192) }
}