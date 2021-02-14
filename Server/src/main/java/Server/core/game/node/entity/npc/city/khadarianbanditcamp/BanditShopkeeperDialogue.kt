package core.game.node.entity.npc.city.khadarianbanditcamp

import core.game.content.dialogue.DialoguePlugin
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * @author qmqz
 */

@Initializable
class BanditShopkeeperDialogue(player: Player? = null) : DialoguePlugin(player){

	override fun open(vararg args: Any): Boolean {
		npc = args[0] as NPC
		player("Hello.").also { stage = 0 }
		return true
	}

	override fun handle(interfaceId: Int, buttonId: Int): Boolean {
		when (stage) {
			0 -> npc("Stuff for sale. You buying?").also { stage++ }
			1 -> options("Yes", "No").also { stage++ }
			2 -> when(buttonId) {
				1 -> end().also { npc.shop.open(player) }
				2 -> npc("No? 'Bye then.").also { stage = 99 }
			}

			99 -> end()
		}
		return true
	}

	override fun newInstance(player: Player): DialoguePlugin { return BanditShopkeeperDialogue(player) }
	override fun getIds(): IntArray { return intArrayOf(1917) }
}