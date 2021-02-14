package core.game.node.entity.npc.city.keldagrim

import core.game.content.dialogue.DialoguePlugin
import core.game.content.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.item.Item
import core.plugin.Initializable

/**
 * @author qmqz
 */

@Initializable
class BlandebirDialogue(player: Player? = null) : DialoguePlugin(player){

	override fun open(vararg args: Any): Boolean {
		npc = args[0] as NPC
		player("Hello, I wonder if you could help me on this","whole brewing thing...").also { stage = 0 }
		return true
	}

	override fun handle(interfaceId: Int, buttonId: Int): Boolean {
		when (stage) {
			0 -> npc(FacialExpression.CHILD_NORMAL,"I might be able to - what do you need to know?").also { stage++ }
			1 -> options("How do I brew ales?", "What do I do once my ale has matured?",
				"Do you have any spare ale yeast?", "That's all I need to know, thanks.").also { stage++ }
			2 -> when(buttonId) {
				1 -> npc(FacialExpression.CHILD_NORMAL,"Well first off you need to fill the vat with water - two",
					"bucketfuls should do the trick. Then you'll need to",
					"put in two handfuls of barley malt - that's",
					"roasted barley by the way.").also { stage = 10 }
				2 -> npc(FacialExpression.CHILD_NORMAL,"Well, once you've got a full vat of the good stuff, ",
					"just turn the valve and your barrel will fill ",
					"up with eight pints of whatever your chosen tipple is.",
					"Mind it's an empty barrel, though.").also { stage = 1 }
				3 -> npc(FacialExpression.CHILD_NORMAL,"Well, as a matter of fact I do, although I wouldn't",
					"describe it as spare. This ale yeast I've got is the",
					"best money can buy, but if you've got a pot I'll fill it",
					"for you for 25GP - very cheap as it happens.").also {
					if (!player.inventory.contains(995, 25) && !player.inventory.contains(1931, 1)) {
						player("I don't have an empty pot, or enough money I'm afraid.").also { stage = 1 }
					}
					if (player.inventory.contains(995, 25) && !player.inventory.contains(1931, 1)) {
						player("I don't have an empty pot I'm afraid.").also { stage = 1 }
					}
					if (!player.inventory.contains(995, 25) && !player.inventory.contains(1931, 1)) {
						player("I don't have enough money I'm afraid.").also { stage = 1 }
					}
					if (player.inventory.contains(995, 25) && player.inventory.contains(1931, 1)) {
						stage = 30
					}
				}
				4 -> end()
			}

			10 -> npc(FacialExpression.CHILD_NORMAL,"After that you'll be putting your main ingredient ",
				"in - this will decide which ale it is you're brewing.",
				"There should be some good guides around with", "recipes in.").also { stage++ }
			11 -> npc(FacialExpression.CHILD_NORMAL,"Lastly you pour a pot full of ale yeast in, which'll",
				"start it off fermenting. Then all you have to do",
				"is wait for the good stuff.").also { stage = 1 }

			20 -> npc(FacialExpression.CHILD_NORMAL,"After that you pour a pot full of ale yeast in,",
				"which'll start it off fermenting.",
				"Then all you have to do is wait for the ", "good stuff.").also { stage = 1 }

			30 -> options("That's a good deal - please fill my pot with ale yeast for 25GP.", "No, that's too much I'm afraid.").also { stage++ }
			31 -> when(buttonId) {
				1 -> player.inventory.remove(Item(995, 25)).also {
					player.inventory.remove(Item(1931, 1)).also {
					player.inventory.add(Item(5767, 1))
				} }.also { end() }
				2 -> player("No, that's too much I'm afraid.").also { stage = 1 }
			}

			99 -> end()
		}
		return true
	}

	override fun newInstance(player: Player): DialoguePlugin { return BlandebirDialogue(player) }
	override fun getIds(): IntArray { return intArrayOf(2321) }
}