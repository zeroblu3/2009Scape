package plugin.interaction.inter;

import core.cache.def.impl.ItemDefinition;
import core.game.component.Component;
import core.game.component.ComponentDefinition;
import core.game.component.ComponentPlugin;
import plugin.skill.crafting.spinning.SpinningItem;
import plugin.skill.crafting.spinning.SpinningPulse;
import core.game.node.entity.player.Player;
import core.game.node.entity.player.link.RunScript;
import core.game.node.item.Item;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

@InitializablePlugin
public class SpinningInterface extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(459, this);
		return this;
	}

	@Override
	public boolean handle(final Player p, Component component, int opcode, int button, int slot, int itemId) {
		final SpinningItem spin = SpinningItem.forId(button);
		if (spin == null) {
			return true;
		}
		if (!p.getInventory().contains(spin.getNeed(), 1)) {
			p.getPacketDispatch().sendMessage("You need "+ ItemDefinition.forId(spin.getNeed()).getName().toLowerCase() + " to make this.");
			return true;
		}
		int amt = -1;
		switch (opcode) {
		case 155:
			amt = 1;
			break;
		case 196:
			amt = 5;
			break;
		case 124:
			amt = p.getInventory().getAmount(new Item(spin.getNeed()));
			break;
		case 199:
			p.setAttribute("runscript", new RunScript() {
				@Override
				public boolean handle() {
					int ammount = (int) value;
					p.getPulseManager().run(new SpinningPulse(p, new Item(spin.getNeed(), 1), ammount, spin));
					return true;
				}
			});
			p.getDialogueInterpreter().sendInput(false, "Enter the amount.");
			break;
		}
		if (opcode == 199) {
			return true;
		}
		p.getPulseManager().run(new SpinningPulse(p, new Item(spin.getNeed(), 1), amt, spin));
		return true;
	}
}
