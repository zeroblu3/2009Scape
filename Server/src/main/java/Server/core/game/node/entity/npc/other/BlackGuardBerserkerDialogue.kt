package core.game.node.entity.npc.other

import core.game.content.dialogue.DialoguePlugin
import core.game.content.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * @author qmqz
 */

@Initializable
class BlackGuardBerserkerDialogue(player: Player? = null) : DialoguePlugin(player){

	private val chats = arrayOf(0, 1, 2, 3, 4)

	override fun open(vararg args: Any): Boolean {
		npc = args[0] as NPC
		chats.shuffle()
		player("Hello.").also { stage = chats[0] }
		return true
	}

	override fun handle(interfaceId: Int, buttonId: Int): Boolean {
		when (stage) {
			0 -> sendDialogue("The guard ignores you.").also { stage = 99 }
			1 -> npc(FacialExpression.CHILD_SUSPICIOUS,"Obey the law!").also { stage = 99 }
			2 -> npc(FacialExpression.CHILD_SUSPICIOUS,"I'm keeping an eye on you!").also { stage = 99 }
			3 -> npc(FacialExpression.CHILD_SUSPICIOUS,"Stay out of trouble!").also { stage = 99 }
			4 -> npc(FacialExpression.CHILD_SUSPICIOUS,"Out of the way, human!").also { stage = 99 }


			99 -> end()
		}
		return true
	}

	override fun newInstance(player: Player): DialoguePlugin { return BlackGuardBerserkerDialogue(player) }
	override fun getIds(): IntArray { return intArrayOf(2134, 2135, 2136) }
}