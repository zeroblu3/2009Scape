package plugin.consumable;

import plugin.skill.SkillBonus;
import plugin.skill.Skills;
import core.game.node.entity.player.Player;
import core.plugin.InitializablePlugin;
import core.game.node.item.Item;

/**
 * Represents the cup of tea plugin.
 * @author 'Veixa
 * @version 1.0
 */
@InitializablePlugin
public final class CupofTeaPlugin extends Drink {

	/**
	 * Represents the skill bonuses.
	 */
	private SkillBonus[] bonuses;

	/**
	 * Represents the force chat to use.
	 */
	private static final String CHAT = "Aaah, nothing like a nice cuppa tea!";

	@Override
	public CupofTeaPlugin newInstance(Object object) {
		Consumables.add(this);
		return this;
	}

	/**
	 * Constructs a new {@code CupofTeaPlugin} {@code Object}.
	 */
	public CupofTeaPlugin() {
		super(712, new ConsumableProperties(3, 1980));
		this.bonuses = new SkillBonus[1];
		this.bonuses[0] = new SkillBonus(Skills.ATTACK, 0, 3);
	}

	@Override
	public void consume(final Item item, final Player player) {
		player.sendChat(CHAT);
		if (this.bonuses != null) {
			for (SkillBonus b : bonuses) {
				addBonus(player, b);
			}
		}
		super.remove(player, item);
	}

}
