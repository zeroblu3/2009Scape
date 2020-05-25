package plugin.interaction.item.toys;

import core.cache.def.impl.ItemDefinition;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

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
