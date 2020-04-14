package plugin.quest;

import org.crandor.game.node.entity.npc.AbstractNPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.NeoQuest;
import org.crandor.game.node.entity.player.link.quest.NeoQuestRepository;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.game.world.map.Location;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.PluginManager;

/**
 * Represents the ernest the chicken quest.
 * @author Ceikry
 */
@InitializablePlugin
public final class ErnestTheChicken extends NeoQuest {

	/**
	 * Represents the oil can item.
	 */
	private static final Item OIL_CAN = new Item(277);

	/**
	 * Represents the pressure guage item.
	 */
	private static final Item PRESSURE_GAUGE = new Item(271);

	/**
	 * Represents the rubber tube item.
	 */
	private static final Item RUBBER_TUBE = new Item(276);

	/**
	 * Represents the coins item.
	 */
	private static final Item COINS = new Item(995, 300);

	/**
	 * Constructs a new {@code ErnestTheChicken} {@code Object}.
	 * @param player the player.
	 */
	public ErnestTheChicken() { super(32,18,"Ernest the Chicken",4,3);}

	private boolean hasCan,hasGauge,hasTube;

	@Override
	public NeoQuest newInstance(Object object) {
		NeoQuestRepository.register(buttonId,this);
		PluginManager.definePlugin(new ErnestNPC(), new ErnestChickenNPC());
		return this;
	}

	@Override
	public void setLines(Player player) {
		super.setLines(player);
		int line = 10;
		int stage = player.getNeoQuestRepository().getStage("Ernest the Chicken");
		boolean started = stage > 0;
		if(!started){
			journal.addLine("I can start this quest by speaking to !!Veronica??, who is",++line,false);
			journal.addLine("outside !!Draynor Manor??.",++line,false);
			journal.addLine("There aren't any requirements for this quest.",++line,false);
		} else {
			journal.addLine("I have spoken to Veronica",++line,true);
			if(stage == 10) {
				journal.addLine("I need to find !!Ernest??. He went into !!Draynor Manor?? and hasn't returned.", ++line, false);
			}
			if(stage == 20){
				journal.addLine("I've spoken to Dr Oddenstein, and discovered Ernest is a",++line,true);
				journal.addLine("chicken.",++line,true);
				journal.addLine("I need to bring !!Dr Oddenstein?? parts for his machine:",++line,false);
				journal.addLine("An oil can.",++line,hasCan);
				journal.addLine("A pressure gauge.",++line,hasGauge);
				journal.addLine("A rubber tube",++line,hasTube);
			}
			if(stage == 100){
				journal.addLine("I have collected all the parts for the machine.",++line,true);
				journal.addLine("Dr Oddenstein thanked me for helping fix his machine.",++line,true);
				journal.addLine("We turned Ernest back to normal and he rewarded me",++line,true);
				journal.addLine("!!QUEST COMPLETE!??",++line,false);
			}
		}
	}

	@Override
	public void updateConditionals(Player player) {
		hasCan = player.getInventory().containsItem(OIL_CAN);
		hasTube = player.getInventory().containsItem(RUBBER_TUBE);
		hasGauge = player.getInventory().containsItem(PRESSURE_GAUGE);
	}

	@Override
	public int getConfig(Player player) {
		int stage = player.getNeoQuestRepository().getStage("Ernest the Chicken");
		if(stage >= 100){
			return configs;
		}
		if(stage > 0){
			return 1;
		}
		return 0;
	}

	@Override
	public void finish(Player player) {
		player.unlock();
		player.getPacketDispatch().sendMessage("Ernest hands you 300 coins.");
		super.finish(player);
		int line = 10;
		rewards.addLine("4 Quest Points",line++);
		rewards.addLine("300 coins",line++);
		rewards.addRewardItem(COINS);
		rewards.setInterfaceItem(314);
		rewards.draw(player);
		player.getGameAttributes().removeAttribute("piranhas-killed");
		player.getGameAttributes().removeAttribute("pressure-gauge");
	}
	
	/**
	 * Represents the abstact npc class to handle ernest the chicken.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final static class ErnestChickenNPC extends AbstractNPC {

		/**
		 * The NPC ids of NPCs using this plugin.
		 */
		private static final int[] ID = { 288 };

		/**
		 * Constructs a new {@code ErnestChickenNPC} {@code Object}.
		 */
		public ErnestChickenNPC() {
			super(0, null, false);
		}

		/**
		 * Constructs a new {@code ErnestChickenNPC} {@code Object}.
		 * @param id The NPC id.
		 * @param location The location.
		 */
		private ErnestChickenNPC(int id, Location location) {
			super(id, location, false);
		}

		@Override
		public AbstractNPC construct(int id, Location location, Object... objects) {
			return new ErnestChickenNPC(id, location);
		}

		@Override
		public boolean isHidden(final Player player) {
			return player.getNeoQuestRepository().getStage("Ernest the Chicken") == 100 || player.getAttribute("ernest-hide", false);
		}

		@Override
		public int[] getIds() {
			return ID;
		}

	}
	
	/**
	 * Represents the abstact npc class to handle ernest the chicken.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final static class ErnestNPC extends AbstractNPC {

		/**
		 * The NPC ids of NPCs using this plugin.
		 */
		private static final int[] ID = { 287 };

		/**
		 * Constructs a new {@code ErnestChickenNPC} {@code Object}.
		 */
		public ErnestNPC() {
			super(0, null, false);
		}

		/**
		 * Constructs a new {@code ErnestChickenNPC} {@code Object}.
		 * @param id The NPC id.
		 * @param location The location.
		 */
		private ErnestNPC(int id, Location location) {
			super(id, location, false);
		}

		@Override
		public AbstractNPC construct(int id, Location location, Object... objects) {
			return new ErnestNPC(id, location);
		}

		@Override
		public boolean isHidden(final Player player) {
			Player target = getAttribute("target", null);
			if (target != null && target.getNeoQuestRepository().getStage("Ernest the Chicken") == 100) {
				clear();
				return super.isHidden(player);
			}
			return target == null ? true : player != target;
		}

		@Override
		public int[] getIds() {
			return ID;
		}
	}
}
