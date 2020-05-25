package plugin.consumable.food;

import plugin.consumable.Consumables;
import plugin.consumable.Food;
import core.game.node.entity.combat.ImpactHandler.HitsplatType;
import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import core.game.node.item.Item;

/**
 * Handles the rock cake plugin.
 * @author Vexia
 */
@InitializablePlugin
public class RockCakePlugin extends Food {

	@Override
	public RockCakePlugin newInstance(final Object object) {
		Consumables.add(this);
		return this;
	}

	/**
	 * Constructs a new {@Code RockCakePlugin} {@Code Object}
	 */
	public RockCakePlugin() {
		super(2379, 0);
	}

	@Override
	public void consume(final Item item, final Player player) {
		int lp = player.getSkills().getLifepoints();
		int hit = 1;
		player.animate(ANIMATION);
		if (lp - 1 < 30) {
			hit = 0;
		}
		player.getLocks().unlock("eat", player);
		player.getImpactHandler().manualHit(player, hit, hit == 0 ? HitsplatType.MISS : HitsplatType.NORMAL);
		player.sendChat("Ow! I nearly broke a tooth!");
		player.sendMessage("The rock cake resists all attempts to eat it.");
	}
}
