package core.game.node.entity.npc.city.lunarisle

import core.game.content.dialogue.DialoguePlugin
import core.game.content.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * @author qmqz
 */

@Initializable
class BabaYagaDialog(player: Player? = null) : DialoguePlugin(player){

	override fun open(vararg args: Any): Boolean {
		npc = args[0] as NPC
		player("Hello there.").also { stage = 0 }
		return true
	}

	override fun handle(interfaceId: Int, buttonId: Int): Boolean {
		when (stage) {
			0 -> npc("Ah, a stranger to our island.","How can I help?").also { stage++ }
			1 -> options("Have you got anything to trade?", "It's a very interesting house you have here.", "I'm good thanks, bye.").also { stage++ }
			2 -> when (buttonId) {
				1 -> end().also { npc.shop.open(player) }
				2 -> player("It's a very interesting house you have here.", "Does he have a name?").also { stage = 10 }
				3 -> player("I'm good thanks, bye.").also { stage = 99 }
			}

			10 -> npc("Why of course. It's Berty.").also { stage++ }
			11 -> player("Berty? Berty the Chicken leg house?").also { stage++ }
			12 -> npc("Yes.").also { stage++ }
			13 -> player("May I ask why?").also { stage++ }
			14 -> npc("It just has that certain ring to it, don't you think?", "Beeerteeee!").also { stage++ }
			15 -> player("You're ins...").also { stage++ }
			16 -> npc("Insane? Very.").also { stage = 1 }

			99 -> end()
		}
		return true
	}

	override fun newInstance(player: Player): DialoguePlugin { return BabaYagaDialog(player) }
	override fun getIds(): IntArray { return intArrayOf(4513) }
}