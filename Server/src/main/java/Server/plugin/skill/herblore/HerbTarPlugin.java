package plugin.skill.herblore;

import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the plugin used to create a herb tar.
 * @author 'Vexia
 * @version 1.0
 */
@InitializablePlugin
public final class HerbTarPlugin extends UseWithHandler {

	/**
	 * Constructs a new {@code HerbTarPlugin} {@code Object}.
	 */
	public HerbTarPlugin() {
		super(1939);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (Tars tar : Tars.values()) {
			addHandler(tar.getIngredient().getId(), ITEM_TYPE, this);
		}
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		event.getPlayer().getDialogueInterpreter().open(2827673, Tars.forItem(event.getUsedItem().getId() == 1939 ? event.getBaseItem() : event.getUsedItem()));
		return true;
	}

}
