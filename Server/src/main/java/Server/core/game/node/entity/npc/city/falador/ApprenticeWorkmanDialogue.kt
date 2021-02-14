package core.game.node.entity.npc.city.falador

import core.game.content.dialogue.DialoguePlugin
import core.game.content.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * @author qmqz
 */

@Initializable
class AnneIsaaksonDialogue(player: Player? = null) : DialoguePlugin(player){

	override fun open(vararg args: Any): Boolean {
		npc = args[0] as NPC
		player( "Hiya.").also { stage = 0 }
		return true
	}

	override fun handle(interfaceId: Int, buttonId: Int): Boolean {
		when (stage) {
			0 -> npc("Sorry, I haven't got time to chat. ",
					"We've only just finished a collossal order of furniture ",
					"for the Varrock area, and already there's more work ",
					"coming in.").also { stage++ }
			1 -> player(FacialExpression.ASKING, "Varrock?").also { stage++ }
			2 -> npc("Yeah, the Council's had it redecorated.").also { stage++ }
			3 -> npc(3236, "Oi - stop gabbing and get that chair finished!").also { stage++ }
			4 -> npc("You'd better let me get on with my work.").also { stage++ }
			5 -> player("Ok, bye.").also { stage = 99 }

			99 -> end()
		}
		return true
	}

	override fun newInstance(player: Player): DialoguePlugin { return AnneIsaaksonDialogue(player) }
	override fun getIds(): IntArray { return intArrayOf(3235) }
}