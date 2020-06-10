package plugin.dialogue;

import core.plugin.InitializablePlugin;
import core.game.node.entity.player.Player;

/**
 * Handles the SimpleEntityMessage dialogue.
 * @author 'Vexia
 */
@InitializablePlugin
public class SimpleEntityMessage extends DialoguePlugin {

	public SimpleEntityMessage() {

	}

	public SimpleEntityMessage(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 8000 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		end();
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new SimpleEntityMessage(player);
	}

	@Override
	public boolean open(Object... args) {
		String[] messages = new String[args.length];
		for (int i = 0; i < messages.length; i++)
			messages[i] = (String) args[i];
		interpreter.sendDialogues(player, FacialExpression.HALF_GUILTY, messages);
		return true;
	}
}
