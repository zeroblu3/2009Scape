package plugin.interaction.object;

import core.cache.def.impl.ObjectDefinition;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.world.map.Location;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the fishing contest gate plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class FishingContestGatePlugin extends OptionHandler {

	/**
	 * Represents the location to be near.
	 */
	private static final Location LOCATION = Location.create(2643, 3441, 0);

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(47).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(48).getConfigurations().put("option:open", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		if (player.getLocation().withinDistance(LOCATION, 50)) {
			player.getPacketDispatch().sendMessage("You need a fishing contest pass to go through here.");
			return true;
		}
		return true;
	}

}
