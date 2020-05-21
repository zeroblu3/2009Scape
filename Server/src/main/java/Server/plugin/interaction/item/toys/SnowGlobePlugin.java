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
public class SnowGlobePlugin extends OptionHandler {
    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        ItemDefinition.forId(11949).getConfigurations().put("option:shake",this);
        return this;
    }

    @Override
    public boolean handle(Player player, Node node, String option) {
        player.getLocks().lockInteractions(2);
        player.getAnimator().animate(new Animation(2926));
        int freeSlots = player.getInventory().freeSlots();
        if(freeSlots == 0){
            return true;
        }
        player.getInventory().add(new Item(11951,freeSlots));
        return true;
    }
}
