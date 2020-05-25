package plugin.skill.construction.decoration;


import core.cache.def.impl.ObjectDefinition;
import plugin.skill.Skills;
import core.game.interaction.OptionHandler;
import core.game.node.Node;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.game.node.object.GameObject;
import core.game.node.object.ObjectBuilder;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.update.flag.context.Animation;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Handles the various fireplaces that you may light in Construction.
 * @author Splinter
 * @author Emperor
 */
@InitializablePlugin
public final class FireplacePlugin extends OptionHandler {

	/**
	 * The animation.
	 */
	private static final Animation ANIMATION = Animation.create(3658);

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(13609).getConfigurations().put("option:light", this);
		ObjectDefinition.forId(13611).getConfigurations().put("option:light", this);
		ObjectDefinition.forId(13613).getConfigurations().put("option:light", this);
		return this;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		if (!player.getInventory().contains(1511, 1) || !player.getInventory().contains(590, 1)) {
			player.sendMessage("You need some logs and a tinderbox in order to light the fireplace.");
			return true;
		}
		final GameObject obj = (GameObject) node.asObject();
		player.lock(2);
		player.animate(ANIMATION);
		GameWorld.Pulser.submit(new Pulse(2, player) {
			@Override
			public boolean pulse() {
				if (!obj.isActive()) {
					return true;
				}
				player.getInventory().remove(new Item(1511));
				player.getSkills().addExperience(Skills.FIREMAKING, 80);
				ObjectBuilder.replace(new GameObject(obj.getId(), obj.getLocation()), new GameObject(obj.getId() + 1, obj.getLocation(), obj.getRotation()), 1000);
				player.sendMessage("You light the fireplace.");
				return true;
			}
		});
		return true;
	}
}