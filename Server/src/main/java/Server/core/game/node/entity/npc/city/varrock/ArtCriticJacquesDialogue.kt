package core.game.node.entity.npc.city.varrock

import core.game.content.dialogue.DialoguePlugin
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * @author qmqz
 */

@Initializable
class ArtCriticJacquesDialogue(player: Player? = null) : DialoguePlugin(player){

	override fun open(vararg args: Any): Boolean {
		npc = args[0] as NPC
		player( "Hello.").also { stage = 0 }
		return true
	}

	override fun handle(interfaceId: Int, buttonId: Int): Boolean {
		when (stage) {
			0 -> npc("Ahh many greetinks and welcomes to ze Museum!").also { stage++ }

			1 -> options("Who are you?", "What do you do here?").also { stage++ }
			2 -> when(buttonId){
				1 -> npc("Ahhh, mon amie! My name iz Jacques and I", "am ze Museum's finest art critic!").also { stage = 10 }
				2 -> npc("I critique ze art! See the beautifool colours ",
					"and ze masterful use of ze brush to capture",
					"perfectly ze royal presence...").also { stage = 20 }
			}

			10 -> player("Looks to me like you're the ONLY one.").also { stage++ }
			11 -> npc("Zis iz true, mon amie.",
				"'owever, in ze future, I will 'ave my own entire floor",
				"of ze Museum with paintings galore!").also { stage++ }
			12 -> player("Uhh...", "Has that been approved or are you just dreaming?").also { stage++ }
			13 -> npc("Ah, I see madame iz 'aving ze little bon mot, eh?").also { stage++ }
			14 -> player("Err...bone what?").also { stage++ }
			15 -> npc("Ze bon mot, ze laugh, ze fun of poking at me, eh? ",
				"I shall 'ave my gallery, you vill see eventually, ",
				"mon amie. Zen I shall be ze, 'ow you say, ", "laughing last!").also { stage = 1 }

			20 -> options("Umm...it's a painting.", "Yes, I see!").also { stage++ }
			21 -> when (buttonId) {
				1 -> npc("JUST a painting? 'ow could you say such a t'ing? ",
					"When you learn 'ow to appreciate ze wonderful works of",
					"art, you may talk to me.").also { stage = 99 }
				2 -> player("And look at the use of shade and contrast - ", "isn't it just magnificent?").also { stage = 30 }
			}

			30 -> npc("Oui...so eet eez. So perfect it almost makes me ",
				"weep wiz joy! The grandiloquent strokes of ze brush ",
				"convey a kind of mystic quality...",
				"eet iz almost as if 'e were 'ere with us.").also { stage++ }
			31 -> npc("My father, 'e too waz ze critic of art...",
				"'e also saw ze poetry in ze painting. ",
				"Zis reminds me of a verse 'e would read me - ",
				"would you like to 'ear it?").also { stage++ }
			32 -> options("Yes please.", "No thanks.").also { stage++ }
			33 -> when(buttonId) {
				1 -> npc("Ahh, good. Zis verse is from a poem by a most",
					"talented writer of ze poetry, Charles Baudelaire. ",
					"My father would place me on 'is knee and read ",
					"to me like zis...").also { stage = 40 }
				2 -> npc("Ahh well, eet iz not for everyone.").also { stage = 99 }
			}

			40 -> npc("Je trône dans l'azur comme un sphinx incompris; ",
				"J'unis un coeur de neige à la blancheur des cygnes; ",
				"Je hais le mouvement qui déplace les lignes, ",
				"Et jamais je ne pleure et jamais je ne ris.").also { stage++ }
			41 -> options("That was nice, what did it mean?", "Beautiful.", "I didn't like it, but I still like the painting.").also { stage++ }
			42 -> when(buttonId) {
				1 -> npc("Ahh yes, I forget, open ze ears and I ","shall tell madame.").also { stage = 50 }
				2 -> npc("Ahh yes, it eez. ", "Feel free to look around ze Museum some more.").also { stage = 99 }
				3 -> npc("Ahh well, eet iz not for everyone but", "I am glad you like ze painting.").also { stage = 99 }
			}

			50 -> npc("Swan-white of heart, a sphinx no mortal knows,",
				"My throne is in the heaven's azure deep,",
				"I hate all movements that disturb my pose,",
				"I smile not ever, neither do I weep.").also { stage++ }
			51 -> options("Wow!", "I still didn't like it.").also { stage++ }
			52 -> when(buttonId) {
				1 -> player("That's powerful stuff...and yes, I see what you ",
					"mean about the painting inspiring you ",
					"to remember that poem...").also { stage = 60 }
				2 -> npc("Eet iz such a shame, ",
					"but you must 'ave your own opinion, no? ",
					"Feel free to look around ze Museum some more.").also { stage = 99 }
			}

			60 -> npc("I am glad you enjoyed. ",
				"Please, take ze time to appreciate ze Museum and ze ",
				"wealth of information 'ere. Come visit me when ",
				"I 'ave my own gallery!").also { stage = 99 }

			99 -> end()
		}
		return true
	}

	override fun newInstance(player: Player): DialoguePlugin { return ArtCriticJacquesDialogue(player) }
	override fun getIds(): IntArray { return intArrayOf(5930) }
}