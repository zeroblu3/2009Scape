package plugin.quest.familycrest;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.plugin.InitializablePlugin;

/**
 * @author Austin
 * NPC: Dimintheis ID: 8172
 * Quest: Family Crest
 *
 */

@InitializablePlugin
public class Dimintheis extends DialoguePlugin {
        /**
         * The quest.
         */
        private Quest quest;

        /**
         * Constructs a new {@code Dimintheis} {@code Object}.
         */
	public Dimintheis() {
            /**
             * empty.
             */
    }
    public Dimintheis(Player player){ super(player);}

    @Override
    public DialoguePlugin newInstance(Player player){return new Dimintheis(player);}

    @Override
    public boolean open(Object... args){
        player("Hello!");
        return true;
    }

    /**
     * @param componentId
     * @param buttonId
     * @return
     */

    @Override
    public boolean handle(int componentId, int buttonId) {
        switch (stage) {
            case 0:
                npc("Can't you see I'm busy?");
                stage++;
                break;

            case 1:
                end();
                break;
        }
        return true;
    }

        @Override
        public int[] getIds() {return new int[] {8172};}
}
