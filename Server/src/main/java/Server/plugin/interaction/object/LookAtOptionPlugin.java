package plugin.interaction.object;

import core.cache.def.impl.ObjectDefinition;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

@InitializablePlugin
public class LookAtOptionPlugin extends OptionHandler {
    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        for (int i = 18877; i <= 18900; i++)
        {
            ObjectDefinition.forId(i).getConfigurations().put("option:look at", this);
        }
        return this;
    }

    @Override
    public boolean handle(Player player, Node node, String option) {
        player.getPacketDispatch().sendMessage("The " + node.getName().toLowerCase() + " seem to be going south-west.");
        return true;

    }
}
