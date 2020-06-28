package plugin.interaction.npc;

import core.cache.def.impl.NPCDefinition;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the plugin used for the hairdresser.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class HairCutPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.forId(598).getConfigurations().put("option:hair-cut", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		player.getDialogueInterpreter().open(598, ((NPC) node), true);
		return true;
	}

}
