package plugin.skill.construction.decoration.costume;


import core.cache.def.impl.ObjectDefinition;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Handles the Toy Box POH
 * ToyBoxPlugin.java
 * @author Lee
 * @date 10/2/2017
 */
@InitializablePlugin
public class ToyBoxPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(18802).getConfigurations().put("option:open", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
