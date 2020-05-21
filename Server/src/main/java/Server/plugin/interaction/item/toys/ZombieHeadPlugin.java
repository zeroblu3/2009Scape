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
public class ZombieHeadPlugin extends OptionHandler {
    static final Item ZOMBIE_HEAD = new Item(6722);
    static final Animation TALK_AT = new Animation(2840);
    static final Animation DISPLAY = new Animation(2844);
    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        ItemDefinition def = ItemDefinition.forId(ZOMBIE_HEAD.getId());
        def.getConfigurations().put("option:talk-at",this);
        def.getConfigurations().put("option:display",this);
        def.getConfigurations().put("option:question",this);
        return this;
    }

    @Override
    public boolean handle(Player player, Node node, String option) {
        switch(option){
            case "talk-at":
                player.getLocks().lockInteractions(2);
                player.getAnimator().animate(TALK_AT);
                player.sendChat("Alas!");
                break;
            case "display":
                player.getLocks().lockInteractions(2);
                player.getAnimator().animate(DISPLAY);
                player.sendChat("MWAHHAHAHAHAHAHA");
                break;
            case "question":
                //player.getDialogueInterpreter().open()
        }
        return true;
    }
}
