package plugin.interaction.item.toys;

import org.crandor.cache.def.impl.ItemDefinition;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

@InitializablePlugin
public class YoyoPlugin extends OptionHandler {
    final static Animation PLAY = new Animation(1457);
    final static Animation LOOP = new Animation(1458);
    final static Animation WALK = new Animation(1459);
    final static Animation CRAZY = new Animation(1460);
    final static Item YOYO = new Item(4079);

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        ItemDefinition def = ItemDefinition.forId(YOYO.getId());
        def.getConfigurations().put("option:play",this);
        def.getConfigurations().put("option:loop",this);
        def.getConfigurations().put("option:walk",this);
        def.getConfigurations().put("option:crazy",this);
        return this;
    }

    @Override
    public boolean handle(Player player, Node node, String option) {

        switch(option){
            case "play":
                player.getLocks().lockInteractions(2);
                player.getAnimator().animate(PLAY);
                break;
            case "loop":
                player.getLocks().lockInteractions(2);
                player.getAnimator().animate(LOOP);
                break;
            case "walk":
                player.getLocks().lockInteractions(2);
                player.getAnimator().animate(WALK);
                break;
            case "crazy":
                player.getLocks().lockInteractions(2);
                player.getAnimator().animate(CRAZY);
                break;
        }
        return true;
    }
}
