package plugin.command;

import core.cache.Cache;
import core.cache.def.impl.ItemDefinition;
import core.game.system.config.ItemConfigParser;
import plugin.ge.GrandExchangeDatabase;
import plugin.ge.GrandExchangeEntry;
import plugin.ge.ResourceManager;
import core.game.node.entity.player.Player;
import core.game.system.SystemManager;
import core.game.system.SystemState;
import core.game.system.command.CommandPlugin;
import core.game.system.command.CommandSet;
import core.game.world.repository.Repository;
import core.net.amsc.WorldCommunicator;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents commands related to system updating.
 * @author Vexia
 */
@InitializablePlugin
public final class SystemCommand extends CommandPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		link(CommandSet.DEVELOPER);
		return this;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean parse(Player player, String name, String[] args) {
		switch (name) {
		case "cms":// Re-connects to the management server.
			for (Player pl : Repository.getPlayers()) {
				try {
					pl.removeAttribute("combat-time");
					pl.getPacketDispatch().sendLogout();
					pl.clear();
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			WorldCommunicator.terminate();
			WorldCommunicator.connect();
			return true;
		case "update":
			if (args.length > 1) {
				SystemManager.getUpdater().setCountdown(Integer.parseInt(args[1]));
			}
			SystemManager.flag(SystemState.UPDATING);
			return true;
		case "cancel_update":
		case "cancelupdate":
		case "cancel":
			SystemManager.getUpdater().cancel();
			return true;
		case "clear_resource":
			ResourceManager.clearResource(toInteger(args[1]));
			System.out.println("Cleared resource " + args[1] + "!");
			return true;
		case "add_resource":
			boolean sell = !(args.length > 3 && args[3].equals("false"));
			ResourceManager.addResource(toInteger(args[1]), toInteger(args[2]), sell);
			System.out.println("Added " + (sell ? "selling" : "buying") + " resource " + args[1] + ", " + args[2] + "!");
			return true;
		case "resetrm":
			ResourceManager.getStock().clear();
			System.out.println("Fully reset resource manager!");
			return true;
		case "setdefaultge":
			int changes = 0;
			for (int id = 0; id < Cache.getItemDefinitionsSize(); id++) {
				GrandExchangeEntry entry = GrandExchangeDatabase.getDatabase().get(id);
				if (entry == null) {
					continue;
				}
				ItemDefinition def = ItemDefinition.forId(id);
				int value = entry.getValue();
				if (value < def.getAlchemyValue(true)) {
					value = def.getAlchemyValue(true);
				}
				if (value < def.getAlchemyValue(false)) {
					value = def.getAlchemyValue(false);
				}
				if (value < def.getValue()) {
					value = def.getValue();
				}
				if (value < def.getConfiguration(ItemConfigParser.GE_PRICE, 0)) {
					value = def.getConfiguration(ItemConfigParser.GE_PRICE, 0);
				}
				if (value < def.getConfiguration(ItemConfigParser.SHOP_PRICE, 0)) {
					value = def.getConfiguration(ItemConfigParser.SHOP_PRICE, 0);
				}
				if (value != entry.getValue()) {
					changes++;
				}
				entry.setValue(value);
			}
			player.getPacketDispatch().sendMessage("Set default G.E prices - " + changes + " changes made!");
			return true;
		case "sgc":
		case "systemgc":
			System.gc();
			return true;
		}
		return false;
	}

}
