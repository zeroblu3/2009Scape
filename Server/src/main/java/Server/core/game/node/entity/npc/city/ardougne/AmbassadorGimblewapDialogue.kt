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
class AmbassadorGimblewapDialogue(player: Player? = null) : DialoguePlugin(player){

	override fun open(vararg args: Any): Boolean {
		npc = args[0] as NPC
		npc(FacialExpression.CHILD_GUILTY, "I can't believe that I've been made to wait so long.").also { stage = 0 }
		return true
	}

	override fun handle(interfaceId: Int, buttonId: Int): Boolean {
		when (stage) {
			0 -> player(FacialExpression.ASKING, "What are you waiting for?").also { stage++ }
			1 -> npc(FacialExpression.CHILD_NORMAL, "I have been waiting for an audience with", "the King for days!").also { stage++ }
			2 -> player(FacialExpression.HALF_THINKING, "Well, he's clearly a busy man.").also { stage++ }
			3 -> npc(FacialExpression.CHILD_GUILTY, "Bah! I think he's just trying to insult the gnome nation,",
				"by making me wait. The cheek of these humans. ",
				"Just because they're vertically challenged",
				"they think they can ignore us.").also { stage++ }
			4 -> player(FacialExpression.WORRIED, "I'm not ignoring you.").also { stage++ }
			5 -> npc(FacialExpression.CHILD_THINKING, "Hmm.", "I should be thankful for small mercies, I suppose.").also { stage = 99 }

			99 -> end()
		}
		return true
	}

	override fun newInstance(player: Player): DialoguePlugin { return AmbassadorGimblewapDialogue(player) }
	override fun getIds(): IntArray { return intArrayOf(4580) }
}