package core.game.node.entity.npc.city.toweroflife

import core.game.content.dialogue.DialoguePlugin
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.entity.player.link.quest.Quest
import core.plugin.Initializable

/**
 * @author qmqz
 */

@Initializable
class BonafidoDialogue(player: Player? = null) : DialoguePlugin(player) {

	override fun open(vararg args: Any): Boolean {
		npc = args[0] as NPC

		if (player.questRepository.getQuest("Tower of Life").isCompleted(player)) {
			player("You still hangin' around the tower?").also { stage = 10 }
		} else {
			player("Hi there.").also { stage = 0 }
		}

		return true
	}

	override fun handle(interfaceId: Int, buttonId: Int): Boolean {
		when (stage) {
			0 -> npc("A'up, mate, how's it gowin'?").also { stage++ }
			1 -> player("Not bad, thank you.").also { stage++ }
			2 -> player("What are you building here?").also { stage++ }
			3 -> npc("Nowt at moment. We're on strike!").also { stage++ }
			4 -> player("You're on strike? Whatever for?").also { stage++ }
			5 -> npc("Those strange alchemists are up ta somefin',",
				"best go ta one ov 'em.").also { stage++ }
			6 -> player("Oh, okay.").also { stage = 99 }

			10 -> npc("Yeah, we need another job.",
				"But til then, we figured we may as well wait",
				"ta see what else would turns up.").also { stage++ }
			11 -> player("Fair enough.").also { stage = 99 }

			99 -> end()
		}
		return true
	}

	override fun newInstance(player: Player): DialoguePlugin {
		return BonafidoDialogue(player)
	}

	override fun getIds(): IntArray {
		return intArrayOf(5580)
	}
}
