package plugin.interaction.npc;

import core.cache.def.impl.NPCDefinition;
import plugin.skill.crafting.TanningProduct;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * @author 'Vexia
 */
@InitializablePlugin
public class TanningNPC extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.forId(1041).getConfigurations().put("option:trade", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		TanningProduct.open(player, ((NPC) node).getId());
		return true;
	}

}
