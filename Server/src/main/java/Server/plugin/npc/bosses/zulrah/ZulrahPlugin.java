package plugin.npc.bosses.zulrah;

import core.cache.def.impl.ObjectDefinition;
import plugin.activity.ActivityManager;
import plugin.dialogue.DialogueAction;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.world.GameWorld;
import core.plugin.Plugin;
import core.plugin.InitializablePlugin;
import core.plugin.PluginManager;

/**
 * Handles interactions related to Zulrah.
 * @author Vexia
 *
 */
@InitializablePlugin
public class ZulrahPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(10075).getConfigurations().put("option:board", this);
		PluginManager.definePlugin(new ZulrahCutscene(), new ZulrahNPC());
		return this;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		if (node.asObject().getId() == 10075 && GameWorld.getSettings().isDevMode()) {
			player.getDialogueInterpreter().sendOptions("Return to Zulrah's shrine?", "Yes", "No");
			player.getDialogueInterpreter().addAction(new DialogueAction() {
				@Override
				public void handle(Player p, int buttonId) {
					switch(buttonId) {
					case 2:
						ActivityManager.start(p, "zulrah cutscene", false);
						break;
					case 3:
						p.getDialogueInterpreter().close();
						break;
					}
					
				}
				
			});
			return true;
		}
		return true;
	}
	
}
