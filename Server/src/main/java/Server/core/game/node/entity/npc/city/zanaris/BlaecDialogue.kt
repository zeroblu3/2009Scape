package core.game.node.entity.npc.city.zanaris

import core.game.content.dialogue.DialoguePlugin
import core.game.content.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * @author qmqz
 */

@Initializable
class BlaecDialogue(player: Player? = null) : DialoguePlugin(player){

	private val chats = arrayOf(0, 1, 2)

	override fun open(vararg args: Any): Boolean {
		npc = args[0] as NPC
		chats.shuffle()
		player("Hello.").also { stage = chats[0] }
		return true
	}

	override fun handle(interfaceId: Int, buttonId: Int): Boolean {
		when (stage) {
			0 -> npc("Greetin's " + player.username + ", fine day today!").also { stage = 99 }
			1 -> npc("Please leave me alone, I'm busy trapping the pygmy shrews.").also { stage = 99 }
			2 -> npc("Wunnerful weather we're having today!").also { stage = 99 }

			99 -> end()
		}
		return true
	}

	override fun newInstance(player: Player): DialoguePlugin { return BlaecDialogue(player) }
	override fun getIds(): IntArray { return intArrayOf(3115) }
}