package plugin.npc.bosses.kraken;

import core.game.node.entity.Entity;
import core.game.node.entity.combat.BattleState;
import core.game.node.entity.combat.CombatPulse;
import core.game.node.entity.combat.CombatStyle;
import core.game.node.entity.npc.AbstractNPC;
import core.game.node.entity.npc.IdleAbstractNPC;
import core.game.node.entity.player.Player;
import core.game.node.item.GroundItemManager;
import core.game.node.item.Item;
import core.game.system.task.Pulse;
import core.game.world.map.Location;
import core.plugin.InitializablePlugin;
import core.tools.RandomFunction;

/**
 * Handles the Cave kraken.
 * @author Emperor
 *
 */
@InitializablePlugin
public class CaveKrakenNPC extends IdleAbstractNPC {
	
	/**
	 * Constructs a new {@code CaveKrakenNPC} {@code Object}.
	 */
	public CaveKrakenNPC() {
		this(8619, 8618, null);
	}
	
	/**
	 * Constructs a new {@code CaveKrakenNPC} {@code Object}.
	 * @param id The active NPC id.
	 * @param location The location.
	 */
	public CaveKrakenNPC(int id, Location location) {
		this(8619, id, location);
	}
	
	/**
	 * Constructs a new {@code CaveKrakenNPC} {@code Object}.
	 * @param idleId The idle NPC id.
	 * @param id The active NPC id.
	 * @param location The location.
	 */
	public CaveKrakenNPC(int idleId, int activeId, Location location) {
		super(idleId, activeId, location);
	}
	
	@Override
	public boolean inDisturbingRange(final Entity disturber) {
		if (!super.inDisturbingRange(disturber)) {
			return false;
		}
		BattleState state = CombatPulse.taskSwing(disturber, this, true, new Pulse(0) {
			@Override
			public boolean pulse() {
				disturb(disturber);
				return true;
			}
		});
		if (state != null) {
			state.setEstimatedHit(1);
		}
		return true;
	}
	
	@Override
	public void disturb(Entity disturber) {
        super.disturb(disturber);
	}
	
	@Override
	public boolean canDisturb(Entity disturber) {
		return false;
	}
	
	@Override 
	public void finalizeDeath(Entity killer) {
		if (RandomFunction.getRandom(1200) == 10) {
			GroundItemManager.create(new Item(14750), killer.getLocation(), killer.asPlayer());
		} else if (RandomFunction.getRandom(256) == 10) {
			GroundItemManager.create(new Item(14667), killer.getLocation(), killer.asPlayer());
		}
		super.finalizeDeath(killer);
	}
	
	@Override
	public void checkImpact(BattleState state) {
		super.checkImpact(state);
		if (state.getStyle() != CombatStyle.MAGIC) {
			if (state.getEstimatedHit() > 0) {
				state.setEstimatedHit(state.getEstimatedHit() / 2);
			}
			if (state.getSecondaryHit() > 0) {
				state.setSecondaryHit(state.getSecondaryHit() / 2);
			}
			if (state.getAttacker() instanceof Player) {
				((Player) state.getAttacker()).sendMessage("Your " + (state.getStyle() == CombatStyle.RANGE ? "ranged" : "melee") + " attack has very little effect on the cave kraken.");
			}
		}
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new CaveKrakenNPC(id, location);
	}

	@Override
	public int[] getIds() {
		return new int[] { 8618, 8619 };
	}

}
