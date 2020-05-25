package plugin.npc.familiar;

import plugin.skill.SkillBonus;
import plugin.skill.Skills;
import plugin.skill.fishing.Fish;
import plugin.skill.summoning.familiar.Familiar;
import plugin.skill.summoning.familiar.FamiliarSpecial;
import plugin.skill.summoning.familiar.Forager;
import core.game.node.entity.Entity;
import core.game.node.entity.impl.Projectile;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.world.update.flag.context.Animation;
import core.game.world.update.flag.context.Graphics;
import core.plugin.InitializablePlugin;
import core.tools.RandomFunction;

/**
 * Represents the Granite Lobster familiar.
 * @author Aero
 */
@InitializablePlugin
public class GraniteLobsterNPC extends Forager {

	/**
	 * The fish.
	 */
	private static final Item[] FISH = new Item[] { new Item(383), new Item(377) };

	/**
	 * Constructs a new {@code GraniteLobsterNPC} {@code Object}.
	 */
	public GraniteLobsterNPC() {
		this(null, 6849);
	}

	/**
	 * Constructs a new {@code GraniteLobsterNPC} {@code Object}.
	 * @param owner The owner.
	 * @param id The id.
	 */
	public GraniteLobsterNPC(Player owner, int id) {
		super(owner, id, 4700, 12069, 6);
		boosts.add(new SkillBonus(Skills.FISHING, 4));
	}

	@Override
	public Familiar construct(Player owner, int id) {
		return new GraniteLobsterNPC(owner, id);
	}

	@Override
	protected boolean specialMove(FamiliarSpecial special) {
		Entity target = special.getTarget();
		if (!canCombatSpecial(target)) {
			return false;
		}
		animate(new Animation(8118));
		graphics(Graphics.create(1351));
		Projectile.ranged(this, target, 1352, 60, 40, 1, 45).send();
		sendFamiliarHit(target, 14);
		return true;
	}

	@Override
	public void handlePassiveAction() {
		if (RandomFunction.random(4) == 1) {
			final Item item = FISH[RandomFunction.random(FISH.length)];
			animate(Animation.create(8107));
			Fish fish = Fish.forItem(item);
			owner.getSkills().addExperience(Skills.FISHING, fish.getExperience() * 0.10);
			produceItem(item);
		}
	}

	@Override
	public int[] getIds() {
		return new int[] { 6849, 6850 };
	}

}
