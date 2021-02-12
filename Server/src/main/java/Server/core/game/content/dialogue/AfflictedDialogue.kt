package core.game.content.dialogue

import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.plugin.Initializable
import core.tools.RandomFunction

/**
 * @author qmqz
 */
@Initializable
class AfflictedDialogue : DialoguePlugin {
    var amt = 0
    var sentence = ""
    var word = ""

    companion object {
        private val chats = arrayOf(
            arrayOf("ughugh"),
            arrayOf("knows'is"),
            arrayOf("nots"),
            arrayOf("pirsl"),
            arrayOf("wot's"),
            arrayOf("zurgle"),
            arrayOf("gurghl"),
            arrayOf("mee's"),
            arrayOf("seysyi"),
            arrayOf("sfriess"),
            arrayOf("says")
        )
    }

    override fun open(vararg args: Any): Boolean {
        amt = RandomFunction.random(1, 6)
        for (i in 0 until amt) {
            word = chats[RandomFunction.random(0, chats.size)].contentToString()
            sentence += "$word "
        }
        npc = args[0] as NPC
        interpreter.sendDialogues(npc, FacialExpression.ASKING, sentence
                .replace("[", "")
                .replace("]", "")
                .replace("(", "")
                .replace(")", "")
        )
        return true
    }

    constructor() {}
    constructor(player: Player?) : super(player) {}

    override fun getIds(): IntArray {
        return intArrayOf(1257, 1258, 1261, 1262)
    }

    override fun handle(interfaceId: Int, buttonId: Int): Boolean {
        end()
        return true
    }

    override fun newInstance(player: Player): DialoguePlugin {
        return AfflictedDialogue(player)
    }
}