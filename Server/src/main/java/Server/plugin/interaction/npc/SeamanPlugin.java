package plugin.interaction.npc;

import core.cache.def.impl.NPCDefinition;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents a plugin used to handle the pay fare option.
 * @author 'Vexia
 */
@InitializablePlugin
public class SeamanPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.setOptionHandler("pay-fare", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final NPC npc = ((NPC) node);
		player.getDialogueInterpreter().open(npc.getId(), npc, true);
		return true;
	}

}
