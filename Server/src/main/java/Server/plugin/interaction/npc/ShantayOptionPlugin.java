package plugin.interaction.npc;

import core.cache.def.impl.NPCDefinition;
import plugin.dialogue.DialogueInterpreter;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the option plguin.
 * @author 'Vexia
 */
@InitializablePlugin
public class ShantayOptionPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.forId(836).getConfigurations().put("option:buy-pass", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		DialogueInterpreter interpreter = player.getDialogueInterpreter();
		if (player.getInventory().remove(new Item(995, 5))) {
			player.getInventory().add(new Item(1854));
			interpreter.sendItemMessage(1854, "You purchase a Shantay Pass.");
		} else {
			interpreter.sendDialogues(player, null, "Sorry, I don't seem to have enough money.");
		}
		return true;
	}

}
