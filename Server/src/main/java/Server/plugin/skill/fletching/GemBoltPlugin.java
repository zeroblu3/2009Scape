package plugin.skill.fletching;

import plugin.dialogue.SkillDialogueHandler;
import plugin.dialogue.SkillDialogueHandler.SkillDialogue;
import plugin.skill.fletching.items.gem.GemBoltCutPulse;
import plugin.skill.fletching.items.gem.GemBoltPulse;
import core.game.interaction.NodeUsageEvent;
import core.game.interaction.UseWithHandler;
import core.game.node.entity.player.Player;
import core.game.node.item.Item;
import core.net.packet.PacketRepository;
import core.net.packet.context.ChildPositionContext;
import core.net.packet.out.RepositionChild;
import core.plugin.InitializablePlugin;
import core.plugin.Plugin;

/**
 * Represents the gem bolt creating plugin.
 * @author Ceikry
 * @version 2.0
 */
@InitializablePlugin
public final class GemBoltPlugin extends UseWithHandler {
	Fletching.GemBolts bolt;

	/**
	 * Constructs a new {@code GemBoltPlugin} {@code Object}.
	 */
	public GemBoltPlugin() {
		super(45, 46, 9187, 9188, 9189, 9190, 9191, 9192, 9193, 9194, 411, 1609, 1611, 1613, 1607, 1605, 1603, 1601, 1615, 6573);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addHandler(1755, ITEM_TYPE, this);
		for(Fletching.GemBolts gem : Fletching.GemBolts.values()){
			addHandler(gem.base, ITEM_TYPE,this);
		}
		return this;
	}

	@Override
	public boolean handle(final NodeUsageEvent event) {
		final Player player = event.getPlayer();
		bolt = Fletching.tipMap.get(event.getUsedItem().getId());
		if(bolt == null){
			bolt = Fletching.tipMap.get(event.getUsedWith().getId());
		}
		if(bolt != null){
			SkillDialogueHandler handler = new SkillDialogueHandler(player, SkillDialogue.ONE_OPTION, new Item(bolt.product)) {
				@Override
				public void create(final int amount, int index) {
					player.getPulseManager().run(new GemBoltPulse(player, event.getUsedItem(), bolt, amount));
				}
				@Override
				public int getAll(int index) {
					return player.getInventory().getAmount(event.getUsedItem());
				}
			};
			handler.open();
			PacketRepository.send(RepositionChild.class, new ChildPositionContext(player, 309, 2, 210, 10));
			return true;
		} else {
			final Fletching.GemBolts gem = Fletching.gemMap.get(event.getUsedItem().getId() == 1755 ? event.getBaseItem().getId() : event.getUsedItem().getId());
			if(gem == null){
				return false;
			}
			new SkillDialogueHandler(player, SkillDialogue.ONE_OPTION, new Item(gem.gem)) {
				@Override
				public void create(final int amount, final int index) {
					player.getPulseManager().run(new GemBoltCutPulse(player, event.getUsedItem(), gem, amount));
				}

				@Override
				public int getAll(int index) {
					return player.getInventory().getAmount(gem.gem);
				}

			}.open();
			return true;
		}
	}
}
