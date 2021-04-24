package core.game.node.entity.npc.city.fishingplatform

import core.game.content.dialogue.DialoguePlugin
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
* @author qmqz
 * Note: quest dialog missing
*/

@Initializable
class BaileyDialogue(player: Player? = null) : DialoguePlugin(player){

	override fun open(vararg args: Any): Boolean {
		npc = args[0] as NPC
		player("Hello Bailey.").also { stage = 0 }
		return true
	}

	override fun handle(interfaceId: Int, buttonId: Int): Boolean {
		when (stage) {
			0 -> npc("Well, hello again, " + player.username + ". What brings you back out here?").also { stage++ }
			1 -> player("Just looking around.").also { stage++ }
			2 -> npc("Well, be very careful out there! ",
				"Those dratted slugs have been joined by", "giant lobsters!").also { stage = 99 }

			99 -> end()
		}
		return true
	}

	override fun newInstance(player: Player): DialoguePlugin { return BaileyDialogue(player) }
	override fun getIds(): IntArray { return intArrayOf(695) }
}