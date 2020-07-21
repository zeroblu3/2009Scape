package plugin.quest.fremtrials

import core.game.node.entity.player.Player
import core.plugin.InitializablePlugin
import plugin.dialogue.DialoguePlugin

@InitializablePlugin
class LalliDialogue(player: Player? = null) : DialoguePlugin(player){
    override fun open(vararg args: Any?): Boolean {
        player?.let { if(it.questRepository.getStage("Fremennik Trials") > 0) {
                player("Hello there.").also { stage = 0; return true }
            }
        }
        return true
    }

    override fun newInstance(player: Player?): DialoguePlugin {
        return LalliDialogue(player)
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        when(stage){
            0 -> npc("Bah! Puny humans always try steal Lalli's golden","apples! You go away now!").also { stage++ }
            1 -> player("Actually, I'm not after your golden apples. I was","wondering if I could have some golden wool, I need it","to string a lyre.").also { stage++ }
            2 -> npc("Ha! You not fool me human! Me am smart! Other","trolls so jealous of how brainy I are, they kick me out","of camp and make me live down here in cave! But me","have last funny!").also { stage++ }
            3 -> npc("Me find golden apples on tree and me build wall to stop","anyone who not Lalli eating lovely golden apples! Did","me not tell you I are smart?").also { stage++ }
            4 -> player("Yes, yes, you are incredibly clever. Now please can I","have some golden wool?").also { stage++ }
            5 -> npc("Hmm, me think you not really think I are clever. Me","think you is trying to trick Lalli. Me not like you as much","as other human. He give me present. I give him wool.").also { stage++ }
            6 -> options("Other human?","No, honest, you're REALLY clever.","Can I give you a present?").also { stage++ }
            7 -> when(buttonId){
                1 -> player("Other human? You mean someone else has been here","and you gave them wool?").also { stage = 10 }
                2 -> player("No, honest, you're REALLY clever!").also { stage = 20 }
                3 -> player("Can I give you a present.").also { stage = 30 }
            }

                //Other human?
                10 -> npc("Human call itself Askeladden! It not trick Lalli. Lalli do","good deal with human! Stupid human get some dumb","wool, but did not get golden apples!").also { stage++ }
                11 -> player("I see... okay, well, bye!").also { stage++ }

                //Honest, you're clever
                20 -> npc("Me no believe you tell truth. Go away.").also { stage = 1000 }

                //Can I give you a present
                30 -> npc("Me think about that.").also { stage = 1000 }
        }
        return true
    }

    override fun getIds(): IntArray {
        return intArrayOf(1270)
    }

}