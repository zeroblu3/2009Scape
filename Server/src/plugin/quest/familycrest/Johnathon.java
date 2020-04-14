package plugin.quest.familycrest;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.InitializablePlugin;

/**
 * @author Austin
 * NPC: Johnathon ID: 668
 * Quest: Family Crest
 * TODO: Rewrite when Family Crest is functional
 */

@InitializablePlugin
public class Johnathon extends DialoguePlugin {
    public Johnathon() {
        /**
         * Empty
         */
    }
    public Johnathon(Player player){ super(player);}
    @Override
    public DialoguePlugin newInstance(Player player){return new Johnathon(player);}

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
                npc("I am so very tired, leave me to rest.");
                stage++;
                break;

            case 1:
                end();
                break;
        }
        return true;
    }

    @Override
    public int[] getIds() {return new int[] {668};}
}