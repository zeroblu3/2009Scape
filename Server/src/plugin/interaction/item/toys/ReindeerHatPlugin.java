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
public class ReindeerHatPlugin extends OptionHandler {
    public final static Item ReindeerHat = new Item(10507);

    @Override
    public Plugin newInstance(Object arg) throws Throwable {
        ReindeerHat.getDefinition().getConfigurations().put("option:operate",this);
        return null;
    }

    @Override
    public boolean handle(Player player, Node node, String option) {
        player.getLocks().lockInteractions(2);
        player.getAnimator().animate(new Animation(5059));
        return true;
    }
}
