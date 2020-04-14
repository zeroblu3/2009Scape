package plugin.quest.familycrest;

import org.crandor.game.content.dialogue.DialoguePlugin;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.InitializablePlugin;

/**
 * @author Austin
 * NPC: Caleb ID: 666
 * Quest: Family Crest
 * TODO: Rewrite when Family Crest is functional
 */

@InitializablePlugin
public class Caleb extends DialoguePlugin {
    public Caleb() {
        /**
         * Empty
         */
    }
    public Caleb(Player player){ super(player);}
    @Override
    public DialoguePlugin newInstance(Player player){return new Caleb(player);}

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
                npc("Who are you? What are you after?");
                stage++;
                break;

            case 1:
                interpreter.sendOptions("Choose one","Nothing I will be on my way","I see you are a chef, will you cook me anything?.");
                stage++;
                break;

            case 2:
                switch(buttonId){
                    case 1:
                        player("Nothing I will be on my way");
                        stage = 6;
                        break;
                    case 2:
                        player("I see you are a chef.");
                        stage = 3;
                        break;
                }
                break;

            case 3:
                player("Will you cook me anything?");
                stage++;
                break;

            case 4:
                npc("I would, but I am very busy.", "Trying to prepare my special fish salad");
                stage++;
                break;

            case 5:
                npc("Which I hope will significantly increase my renown", "as a master chef.");
                stage++;
                break;

            case 6:
                end();
                break;
        }
        return true;
    }

    @Override
    public int[] getIds() {return new int[] {666};}
}