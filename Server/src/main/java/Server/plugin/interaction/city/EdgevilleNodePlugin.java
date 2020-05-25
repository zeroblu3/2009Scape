package plugin.interaction.city;

import core.cache.def.impl.ObjectDefinition;
import core.game.content.global.action.ClimbActionHandler;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.object.GameObject;
import core.game.world.map.Location;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the plugin used to handle edgeville related interactions.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class EdgevilleNodePlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(9262).getConfigurations().put("option:take-seed", this);
		ObjectDefinition.forId(9261).getConfigurations().put("option:take-seed", this);
		ObjectDefinition.forId(30806).getConfigurations().put("option:take-seed", this);
		ObjectDefinition.forId(12265).getConfigurations().put("option:climb", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		int id = ((GameObject) node).getId();
		switch (id) {
		case 9262:
		case 9261:
		case 30806:
			player.getPacketDispatch().sendMessage("There doesn't seem to be any seeds on this rosebush.");
			break;
		case 12265:
			ClimbActionHandler.climb(player, null, Location.create(3078, 3493, 0));
			break;
		}
		return true;
	}

}
