package core.game.node.entity.npc.city.piratescove

import core.game.content.dialogue.DialoguePlugin
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable

/**
 * @author qmqz
 */

@Initializable
class PirateCovePirates(player: Player? = null) : DialoguePlugin(player){

	private val chats = arrayOf(0, 10, 20, 30, 40, 50, 60, 70, 80, 90)

	override fun open(vararg args: Any): Boolean {
		npc = args[0] as NPC
		chats.shuffle()
		player("Hello.").also { stage = chats[0] }
		return true
	}

	override fun handle(interfaceId: Int, buttonId: Int): Boolean {
		when (stage) {
			0 -> player("So how's life as a pirate?").also { stage++ }
			1 -> npc("What kind of question is that? ",
				"How's life as a... I dunno. ",
				"Whatever it is that you do for a living.").also { stage++ }
			2 -> player("I'm a freelance troubleshooter.").also { stage++ }
			3 -> npc("What does that entail then?").also { stage++ }
			4 -> player("Mostly killing things for money and delivering ","items around the planet for people.").also { stage++ }
			5 -> player("I collect stuff.").also { stage++ }
			6 -> npc("So how's that life?").also { stage++ }
			7 -> player("Can't complain, can't complain...").also { stage++ }
			8 -> npc("Well, there you go.").also { stage = 99 }

			10 -> player("Aren't you a little short for a pirate?").also { stage++ }
			11 -> npc("My mother was a gnome.", "Apparently it was a very painful birth.").also { stage++ }
			12 -> player("More info than I wanted, thanks!").also { stage = 99 }

			20 -> npc("Sorry, can't stop, the Captain will have my guts ",
				"for garters if he catches me slacking off ",
				"talking to the stowaway.").also { stage++ }
			21 -> player("I'm not a stowaway! I was invited aboard!").also { stage++ }
			22 -> npc("Yeah, whatever guy, it doesn't really matter ",
				"who you are I'll get in trouble!").also { stage = 99 }

			30 -> player("You know, I've always wondered what life as a", "pirate actually entails.").also { stage++ }
			31 -> npc("Well, at the moment it mostly involves being", "asked random questions by a stowaway.").also { stage++ }
			32 -> player("I'm not a stowaway!", "I was invited aboard! By Lokar!", "Ask him!").also { stage++ }
			33 -> npc("Hey, whatever pal. ",
				"Just make sure the captain doesn't catch you, ",
				"pirates don't like stowaways much.").also { stage = 99 }

			40 -> npc("Ah, good day to you " + gender() + "! ",
				"Your face is unfamiliar, did you perhaps join us ",
				"aboard the ship at Lunar Isle?").also { stage++ }
			41 -> player("No, Lokar offered me a lift in Rellekka actually.").also { stage++ }
			42 -> npc("Oh, really?", "You don't look like a Fremennik to me!").also { stage++ }
			43 -> player("Well... I kind of am, and I kind of aren't.", "It's a long story.").also { stage++ }
			44 -> npc("Sorry I don't have time to hear it then!",
				"See you around young fremennik-who-is-not-really-a- ", "fremennik!").also { stage++ }
			45 -> player("'Bye.").also { stage = 99 }

			50 -> player("Brrrr! Its cold up here!").also { stage++ }
			51 -> npc("You think this is cold? ",
				"Up by Acheron it gets so cold that when you talk",
				"you see the words freeze in the air in front of", "you!").also { stage++ }
			52 -> player("REALLY?").also { stage++ }
			53 -> npc("Nah, not really.",
				"I was exaggerating for humourous effect.",
				"It is very very cold though!").also { stage = 99 }

			60 -> npc("Hello to you too.").also { stage++ }
			61 -> player("Yar! We be pirates, yar!", "Avast, ye scurvy land-lubbing lychee!").also { stage++ }
			62 -> npc("Please don't talk like that, it is", "extremely irritating.").also { stage++ }
			63 -> npc("Also, please don't call me a lychee,", "whatever that may be.").also { stage++ }
			64 -> player("Oh. Okay. Sorry.").also { stage = 99 }


			70 -> npc("ARGH! SOUND THE ALARM!",
				"STOWAWAY ON BOARD!", "STOWAWAY ON BOARD!").also { stage++ }
			71 -> player("No!", "I'm not a stowaway! Honest!" ,"I was invited here!").also { stage++ }
			72 -> npc("Oh, sorry, my mistake then.").also { stage++ }
			73 -> npc("You must admit you do look a lot", "like a stowaway though.").also { stage++ }
			74 -> player("Why, what do they usually look like?").also { stage++ }
			75 -> npc("Erm... I've never actually met one...").also { stage++ }
			76 -> player("Okay then...").also { stage = 99 }

			80 -> npc("Hello.").also { stage++ }
			81 -> player("So... You're a pirate, huh?").also { stage++ }
			82 -> npc("It's what it says on my pay-packet at the end of", "the month.").also { stage++ }
			83 -> player("How's that working out for you?").also { stage++ }
			84 -> npc("Pretty good so far.",
				"All the grog and loot that we can plunder, plus",
				"full medical including dental.").also { stage++ }
			85 -> player("You mean you have insurance?").also { stage++ }
			86 -> npc("Not as such." ,"If any of us get sick we kidnap a doctor and don't ",
				"let him go until we're better.").also { stage++ }
			87 -> npc("You'd be surprised what an incentive for expert", "health care that is.").also { stage++ }
			88 -> player("I can imagine.").also { stage = 99 }

			90 -> npc("Hello there.", "So what brings you aboard the Lady Zay?").also { stage++ }
			91 -> player("Well, I was planning on visiting the Moon Clan,",
				"but I have to say your ship is very impressive.").also { stage++ }
			92 -> npc("Aye, she's a beauty alright!",
				"The Lady Zay has been my home for many hard months, ",
				"through storm and sun, and she always gets us", "to here we were headed!").also { stage++ }
			93 -> player("Yes, she's certainly one of the finest boats", "I've seen on my travels!").also { stage++ }
			94 -> npc("That she is lad, that she is.").also { stage = 99 }

			99 -> end()
		}
		return true
	}
	fun gender (male : String = "sirrah", female : String = "milady") = if (player.isMale) male else female
	override fun newInstance(player: Player): DialoguePlugin { return PirateCovePirates(player) }
	override fun getIds(): IntArray { return intArrayOf(4545, 4547, 4549, 4550, 4551, 4553, 4554, 4555, 4556) }
}