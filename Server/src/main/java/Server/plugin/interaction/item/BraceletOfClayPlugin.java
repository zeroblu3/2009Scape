package plugin.interaction.item;

import core.cache.def.impl.ItemDefinition;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Handles the bracelet of clay item.
 * @author Vexia
 */
@InitializablePlugin
public final class BraceletOfClayPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.forId(11074).getConfigurations().put("option:operate", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		player.sendMessage("You have " + (28 - player.getSavedData().getGlobalData().getBraceletClayUses()) + " uses left.");
		return true;
	}

	@Override
	public boolean isWalk() {
		return false;
	}
}
