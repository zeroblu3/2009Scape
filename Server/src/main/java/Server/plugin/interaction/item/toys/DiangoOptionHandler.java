package plugin.interaction.item.toys;

import org.crandor.cache.def.impl.NPCDefinition;
import org.crandor.game.interaction.OptionHandler;
import org.crandor.game.node.Node;
import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

@InitializablePlugin
public class DiangoOptionHandler extends OptionHandler {
    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        NPCDefinition.forId(970).getConfigurations().put("option:holiday-items",this);
        return this;
    }

    @Override
    public boolean handle(Player player, Node node, String option) {
        DiangoReclaimInterface.open(player);
        return true;
    }
}