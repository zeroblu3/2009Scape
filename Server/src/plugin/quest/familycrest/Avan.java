package plugin.quest.familycrest;
import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.InitializablePlugin;

/**
 * @author Austin
 * NPC: Avan ID: 663
 * Quest: Family Crest
 * TODO: Rewrite when Family Crest is functional
 */

@InitializablePlugin
public class Avan extends DialoguePlugin {
    public Avan() {
        /**
         * Empty
         */
    }
    public Avan(Player player){ super(player);}
    @Override
    public DialoguePlugin newInstance(Player player){return new Avan(player);}

    @Override
    public boolean open(Object... args){
        player("Hello!");
        return true;
    }

    /**
     * User can have unlimited Cooking Gauntlets
     *
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
    public int[] getIds() {return new int[] {663};}
}
