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
class BlackGuardDialogue(player: Player? = null) : DialoguePlugin(player){

	override fun open(vararg args: Any): Boolean {
		npc = args[0] as NPC
		sendDialogue("The guard ignores you.").also { stage = 99 }
		return true
	}

	override fun handle(interfaceId: Int, buttonId: Int): Boolean {
		when (stage) {
			99 -> end()
		}
		return true
	}

	override fun newInstance(player: Player): DialoguePlugin { return BlackGuardDialogue(player) }
	override fun getIds(): IntArray { return intArrayOf(2130, 2131, 2132, 2133) }
}