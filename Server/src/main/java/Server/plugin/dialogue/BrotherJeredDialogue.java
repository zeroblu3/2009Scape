package plugin.dialogue;

import core.game.content.global.Skillcape;
import core.game.node.item.Item;
import core.tools.ItemNames;
import plugin.skill.Skills;
import core.game.node.entity.npc.NPC;
import core.plugin.InitializablePlugin;
import core.game.node.entity.player.Player;

/**
 * Handles the BrotherJeredDialogue dialogue.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class BrotherJeredDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code BrotherJeredDialogue} {@code Object}.
	 */
	public BrotherJeredDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code BrotherJeredDialogue} {@code Object}.
	 * @param player the player.
	 */
	public BrotherJeredDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new BrotherJeredDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		if(player.getInventory().contains(ItemNames.UNBLESSED_SYMBOL_1716,1)){
			player("Can you bless this symbol for me?");
			stage = 10;
			return true;
		}
		if (Skillcape.isMaster(player, Skills.PRAYER)) {
			player("Can I buy a Skillcape of Prayer?");
			stage = 2;
		} else {
			player("Praise to Saradomin!");
			stage = 0;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			npc("Yes! Praise he who brings life to this world.");
			stage = 1;
			break;
		case 1:
			case 12:
				end();
			break;
		case 2:
			npc("Certainly! Right when you give me 99000 coins.");
			stage = 3;
			break;
		case 3:
			options("Okay, here you go.", "No");
			stage = 4;
			break;
		case 4:
			switch (buttonId) {
			case 1:
				player("Okay, here you go.");
				stage = 5;
				break;
			case 2:
				end();
				break;
			}
			break;
		case 5:
			if (Skillcape.purchase(player, Skills.PRAYER)) {
				npc("There you go! Enjoy.");
			}
			stage = 6;
			break;
		case 6:
			end();
			break;
		case 10:
			npc("Sure thing! Just give me a moment, here...");
			stage++;
			break;
		case 11:
			npc("There we go! I have laid the blessing of","Saradomin upon it.");
			player.getInventory().remove(new Item(ItemNames.UNBLESSED_SYMBOL_1716));
			player.getInventory().add(new Item(ItemNames.HOLY_SYMBOL_1718));
			stage++;
			break;
		}

		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 802 };
	}
}
