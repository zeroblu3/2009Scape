package plugin.dialogue;

import core.game.component.Component;
import plugin.skill.cooking.dairy.DairyChurnPulse;
import plugin.skill.cooking.dairy.DairyProduct;
import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import core.game.node.item.Item;

/**
 * Represents the dairy churn dialogue.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class DairyChurnDialogue extends DialoguePlugin {

	/**
	 * Represents the item array.
	 */
	private static final Item[] ITEMS = new Item[] { new Item(1927, 1), new Item(2130, 1), new Item(6697, 1) };

	/**
	 * Constructs a new {@code DairyChurnDialogue} {@code Object}.
	 */
	public DairyChurnDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code DairyChurnDialogue} {@code Object}.
	 * @param player the player.
	 */
	public DairyChurnDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new DairyChurnDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		player.getInterfaceManager().openChatbox(new Component(74));
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		DairyProduct product = null;
		int amount = 0;
		switch (buttonId) {
		case 7:
			product = DairyProduct.POT_OF_CREAM;
			amount = 1;
			break;
		case 6:
			product = DairyProduct.POT_OF_CREAM;
			amount = 5;
			break;
		case 5:
			product = DairyProduct.POT_OF_CREAM;
			amount = 10;
			break;
		case 10:
			product = DairyProduct.PAT_OF_BUTTER;
			amount = 1;
			break;
		case 9:
			product = DairyProduct.PAT_OF_BUTTER;
			amount = 5;
			break;
		case 8:
			product = DairyProduct.PAT_OF_BUTTER;
			amount = 10;
			break;
		case 13:
			product = DairyProduct.CHEESE;
			amount = 1;
			break;
		case 12:
			product = DairyProduct.CHEESE;
			amount = 5;
			break;
		case 11:
			product = DairyProduct.CHEESE;
			amount = 10;
			break;
		}
		Item item = null;
		if (player.getInventory().contains(1927, 1)) {
			item = ITEMS[0];
		} else if (player.getInventory().contains(2130, 1)) {
			item = ITEMS[1];
		} else if (player.getInventory().contains(6697, 1)) {
			item = ITEMS[2];
		}
		player.getPulseManager().run(new DairyChurnPulse(player, item, product, amount));
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 984374 };
	}

}
