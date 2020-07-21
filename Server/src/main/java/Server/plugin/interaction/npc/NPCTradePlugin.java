package plugin.interaction.npc;

import core.cache.def.impl.NPCDefinition;
import plugin.shops.FOGShop;
import plugin.skill.crafting.TanningProduct;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.npc.NPC;
import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the plugin used for an npc with the trade option.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class NPCTradePlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.setOptionHandler("trade", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final NPC npc = (NPC) node;
		if (npc.getId() == 2824) {
			TanningProduct.open(player, 2824);
			return true;
		}
		if(npc.getId() == 7601){
			new FOGShop().open(player);
			return true;
		}
		return node.asNpc().openShop(player);
	}

}
