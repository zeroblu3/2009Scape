package plugin.interaction.item.toys;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.game.world.update.flag.context.Graphics;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@InitializablePlugin
public class MarionettePlugin extends OptionHandler {
    public final static List<Item> MARIONETTES = Arrays.asList(new Item(6865), new Item(6866), new Item(6867));
    private final Animation JUMP = new Animation(3003);
    private final Animation WALK = new Animation(3004);
    private final Animation BOW = new Animation(3005);
    private final Animation DANCE = new Animation(3006);
    private final int[][] GFX = new int[][] {{507,508,509,510}, {511,512,513,514}, {515,516,517,518}};

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        for(Item item : MARIONETTES) {
            ItemDefinition def = item.getDefinition();
            def.getConfigurations().put("option:jump",this);
            def.getConfigurations().put("option:walk",this);
            def.getConfigurations().put("option:bow",this);
            def.getConfigurations().put("option:dance",this);
        }
        return this;
    }

    @Override
    public boolean handle(Player player, Node node, String option) {
        int index = 0;
        switch(node.getId()){
            case 6865:
                index = 1;
                break;
            case 6866:
                index = 2;
                break;
            case 6867:
                index = 0;
                break;
        }
        switch(option){
            case "jump":
                player.getLocks().lockInteractions(2);
                player.getAnimator().animate(JUMP, new Graphics(GFX[index][0]));
                break;
            case "walk":
                player.getLocks().lockInteractions(2);
                player.getAnimator().animate(WALK, new Graphics(GFX[index][1]));
                break;
            case "bow":
                player.getLocks().lockInteractions(2);
                player.getAnimator().animate(BOW, new Graphics(GFX[index][2]));
                break;
            case "dance":
                player.getLocks().lockInteractions(2);
                player.getAnimator().animate(DANCE, new Graphics(GFX[index][3]));
                break;
        }
        return false;
    }
}
