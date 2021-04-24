import core.game.content.dialogue.DialoguePlugin
import core.game.content.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * @author qmqz
 * Note: Missing clue scroll/gnome restaurant dialogue
 */

@Initializable
class AmbassadorSpanfippleDialogue(player: Player? = null) : DialoguePlugin(player){

	override fun open(vararg args: Any): Boolean {
		npc = args[0] as NPC
		npc(FacialExpression.CHILD_NORMAL, "It's all very white round here, isn't it?").also { stage = 0 }
		return true
	}

	override fun handle(interfaceId: Int, buttonId: Int): Boolean {
		when (stage) {
			0 -> player(FacialExpression.ASKING, "Well, it is the White Knights' Castle.").also { stage++ }
			1 -> npc(FacialExpression.CHILD_NORMAL, "I think it would all look better in pink. ",
				"At least then I wouldn't be squinting all the time.").also { stage++ }
			2 -> player(FacialExpression.HALF_THINKING, "Yes, but then they'd have to become the Pink Knights.",
				"I think they'd have problems recruiting then.").also { stage++ }
			3 -> npc(FacialExpression.CHILD_NORMAL, "You're probably right. Maybe brown, then.").also { stage++ }
			4 -> player(FacialExpression.WORRIED, "I think that may be worse...").also { stage++ }
			5 -> npc(FacialExpression.CHILD_NORMAL, "Bah, humans have no sense of style...").also { stage = 99 }

			99 -> end()
		}
		return true
	}

	override fun newInstance(player: Player): DialoguePlugin { return AmbassadorSpanfippleDialogue(player) }
	override fun getIds(): IntArray { return intArrayOf(4581) }
}