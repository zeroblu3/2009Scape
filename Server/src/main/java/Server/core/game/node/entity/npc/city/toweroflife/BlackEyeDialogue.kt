package core.game.node.entity.npc.city.toweroflife

import core.game.content.dialogue.DialoguePlugin
import core.game.content.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * @author qmqz
 */

@Initializable
class BlackEyeDialogue(player: Player? = null) : DialoguePlugin(player){

	override fun open(vararg args: Any): Boolean {
		npc = args[0] as NPC

		if (player.questRepository.isComplete("Tower of Life")) {
			player("Say, that's a nice helmet.").also { stage = 10 }
		} else {
			player("What's going on?").also { stage = 0 }
		}

		return true
	}

	override fun handle(interfaceId: Int, buttonId: Int): Boolean {
		when (stage) {
			0 -> npc("Ah, we be building this 'ere tower.","Lookin' good ain't it?").also { stage++ }
			1 -> player("It does look pretty impressive, but what", "are you doing hanging around?").also { stage++ }
			2 -> npc("Tea break, naturally.").also { stage++ }
			3 -> player("Naturally. Not sure why I asked.").also { stage++ }
			4 -> npc("Fancy some?").also { stage++ }
			5 -> player("Tea? Sure.").also { stage++ }
			6 -> npc("You'll find a kettle in the box to the-").also { stage++ }
			7 -> player("Erm...yeah...no...sorry...gotta go.").also { stage = 99 }

			10 -> npc("Thank you - just as nice as the one in","your bank!").also { stage = 99 }

			99 -> end()
		}
		return true
	}

	override fun newInstance(player: Player): DialoguePlugin { return BlackEyeDialogue(player) }
	override fun getIds(): IntArray { return intArrayOf(5589) }
}