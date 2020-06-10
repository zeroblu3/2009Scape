package plugin.interaction.item;

import core.cache.def.impl.ItemDefinition;
import core.game.content.global.LightSource;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.world.map.zone.impl.DarkZone;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the extinguish light plugin.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class ExtinguishLightPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.setOptionHandler("extinguish", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final LightSource source = LightSource.forProductId(((Item) node).getId());
		if (source == null) {
			System.err.println("Extinguish Light Plugin source not found - " + ((Item) node).getId() + ".");
			return true;
		}
		player.getInventory().replace(source.getRaw(), ((Item) node).getSlot());
		DarkZone.checkDarkArea(player);
		return true;
	}

	@Override
	public boolean isWalk() {
		return false;
	}
}
