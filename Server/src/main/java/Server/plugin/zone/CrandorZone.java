package plugin.zone;

import core.game.node.entity.Entity;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.diary.DiaryType;
import core.game.world.map.zone.MapZone;
import core.game.world.map.zone.ZoneBorders;
import core.game.world.map.zone.ZoneBuilder;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the crandor zone.
 * @author Vexia
 */
@InitializablePlugin
public class CrandorZone extends MapZone implements Plugin<Object> {

	/**
	 * Constructs a new {@code CrandorZone} {@code Object}
	 */
	public CrandorZone() {
		super("crandor", true);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ZoneBuilder.configure(this);
		return this;
	}

	@Override
	public boolean enter(Entity entity) {
		if (entity.isPlayer()) {
			Player player = entity.asPlayer();
			if (!player.getAchievementDiaryManager().getDiary(DiaryType.KARAMJA).isComplete(1, 2)) {
				player.getAchievementDiaryManager().getDiary(DiaryType.KARAMJA).updateTask(player, 1, 2, true);
			}
		}
		return super.enter(entity);
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

	@Override
	public void configure() {
		register(new ZoneBorders(2810, 3223, 2864, 3312));
	}

}
