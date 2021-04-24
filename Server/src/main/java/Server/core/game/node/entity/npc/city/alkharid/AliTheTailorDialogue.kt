package core.game.node.entity.npc.city.alkharid

import core.game.content.dialogue.DialoguePlugin
import core.game.content.dialogue.FacialExpression
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * @author qmqz
 */

@Initializable
class AliTheTailorDialogue(player: Player? = null) : DialoguePlugin(player){

	override fun open(vararg args: Any?): Boolean {
		npc = args[0] as NPC
		player(FacialExpression.FRIENDLY,"Hello there!")
		stage = 0
		return true
	}

	override fun handle(interfaceId: Int, buttonId: Int): Boolean {
		when(stage){
			0 -> npc(FacialExpression.FRIENDLY,"I'm a little busy at the moment. What is it?").also { stage++ }
			1 -> options ("What can you tell me about Al Kharid?", "So, what do you do here?",
				"I hear you work for Ali Morrisane...", "I hear you've been threatening the other shopkeepers.").also {  stage++ }
			2 -> when(buttonId){
				1 -> player(FacialExpression.ASKING, "What can you tell me about Al Kharid?").also { stage = 10 }
				2 -> player(FacialExpression.ASKING, "So, what do you do here?").also { stage = 20 }
				3 -> player(FacialExpression.ASKING, "I hear you work for Ali Morrisane...").also { stage = 30 }
				4 -> player(FacialExpression.HALF_ASKING, "I hear you've been threatening the other shopkeepers.").also { stage = 40 }
			}

			10 ->npc(FacialExpression.FRIENDLY,"Oh, it has wonderful weather.").also { stage++ }
			11 ->player(FacialExpression.LAUGH,"Wonderful? It's hot and dry all the time!").also { stage++ }
			12 ->npc(FacialExpression.FRIENDLY,"Not quite as hot as back home.").also { stage++ }
			13 ->player(FacialExpression.HALF_ASKING,"Where's home, then?").also { stage++ }
			14 ->npc(FacialExpression.HALF_GUILTY,"A town to the south of the pass,","called Pollnivneach.").also { stage = 1 }

			20 ->npc(FacialExpression.SAD,"If I had cloth, patterns and customers, I'd be a tailor.",
				"As it is, I'm a tailor with nothing to do.").also { stage++ }
			21 ->npc(FacialExpression.SAD,"The silk merchant won't even sell me any silks,", "because he doesn't trust me!").also { stage = 1 }
			22 ->player(FacialExpression.HALF_ASKING,"Anything I can do to help?").also { stage++ }
			23 ->npc(FacialExpression.SUSPICIOUS,"No, no, it's all being dealt with").also { stage = 1 }


			30 -> npc(FacialExpression.FRIENDLY,"Of course, he's the one who's going to obtain",
				"cloth and clothes patterns so I can ", "set up shop here.").also { stage++ }
			31 -> npc(FacialExpression.HALF_GUILTY,"And in such a good location, too. ", "Not out of the way like back home.").also { stage++ }
			32 -> npc(FacialExpression.HAPPY,"The customers will soon pour in!").also { stage++ }
			33 -> player(FacialExpression.THINKING, "Maybe I should talk to him...").also { stage++ }
			34 -> npc(FacialExpression.FRIENDLY,"Why not?", "He's always happy to talk to possible business partners.").also { stage = 1 }

			40 -> npc(FacialExpression.SUSPICIOUS,"Me, threaten people?").also { stage++ }
			41 -> player(FacialExpression.THINKING,"One of the shopkeepers did say they were threatened ", "by a man with large scissors...").also { stage++ }
			42 -> npc(FacialExpression.SUSPICIOUS,"Oh, them. Don't mind them.",
				"I think they're worried about ","what effect our shops will have.").also { stage++ }
			43 -> npc(FacialExpression.LAUGH,"For all they know, when we open our shops ", "their wares will look cheap and shabby",
				"by comparison!").also { stage = 99 }

			99 -> end()
		}
		return true
	}

	override fun newInstance(player: Player?): DialoguePlugin {
		return AliTheTailorDialogue(player)
	}

	override fun getIds(): IntArray {
		return intArrayOf(2822)
	}
}