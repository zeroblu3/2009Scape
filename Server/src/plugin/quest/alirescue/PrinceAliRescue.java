package plugin.quest.alirescue;

import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.NeoQuest;
import org.crandor.game.node.entity.player.link.quest.NeoQuestRepository;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.PluginManager;

/**
 * Represents the prince ali rescue quest.
 * @author Ceikry
 * 
 */
@InitializablePlugin
public class PrinceAliRescue extends NeoQuest {

	/**
	 * Represents the rope item.
	 */
	private static final Item ROPE = new Item(954);

	/**
	 * Represents the pink skirt item.
	 */
	private static final Item SKIRT = new Item(1013);

	/**
	 * Represents the yellow wig item.
	 */
	private static final Item YELLOW_WIG = new Item(2419);

	/**
	 * Represents the skin paste item.
	 */
	private static final Item PASTE = new Item(2424);

	/**
	 * Represents the coins item.
	 */
	private static final Item COINS = new Item(995, 700);
	
	/**
	 * Constructs a new {@Code PrinceAliRescue} {@Code Object}
	 */
	public PrinceAliRescue() { super(273,23,"Prince Ali Rescue",3,110);}
	private boolean hasRope,hasWig,hasSkirt,hasPaste,guardDrunk;

	@Override
	public NeoQuest newInstance(Object object) {
		NeoQuestRepository.register(buttonId,this);
		PluginManager.definePlugin(new LadyKeliDialogue(), new LadyKeliNPC(), new PrinceAliRescuePlugin(), new WigDyePlugin());
		return this;
	}

	@Override
	public void setLines(Player player) {
		super.setLines(player);
		int line = 10;
		int stage = player.getNeoQuestRepository().getStage("Prince Ali Rescue");
		boolean started = stage > 0;
		if(!started){
			journal.addLine("I can start this quest by speaking to !!Hassan?? at the palace",++line,false);
			journal.addLine("in !!Al-Kharid.??",++line,false);
		} else {
			journal.addLine("I started this quest by speaking to Hassan in Al-Kharid",++line,true);
			journal.addLine("Palace. He told me I should speak to Osman the spymaster.",++line,true);
			journal.addLine("I should go and speak to !!Osman?? for details on the quest.",++line,stage >= 20);
			if(stage >= 20) {
				journal.addLine("!!Prince Ali?? has been !!kidnapped?? but luckily the spy",++line,false);
				journal.addLine("found he is being held near !!Draynor village??. I will need to",++line,false);
				journal.addLine("!!Disguise?? the !!prince?? and !!tie?? up his !!captor?? to !!free?? him from",++line,false);
				journal.addLine("their !!clutches??.",++line,false);
				if(stage == 20) {
					journal.addLine("To do this I should:-", ++line, false);
					journal.addLine("Talk to !!Leela?? near !!Draynor village?? for advice.", ++line, false);

					journal.addLine(stage >= 30 ? "I have duplicated a key. I need to get it from !!Leela??." :
									"Get a !!duplicate?? of the !!key?? that is !!imprisoning?? the prince.",
							++line, false);
					journal.addLine(!hasRope ? "Get some !!rope?? to tie up the Prince's !!kidnapper.??" :
									"I have some rope with me.",
							++line, hasRope);
					journal.addLine(!hasPaste ? "Get something to !!colour?? the !!Prince's skin?? as a disguise." :
									"I have some skin paste suitable for disguise with me.",
							++line, hasPaste);
					journal.addLine(!hasSkirt ? "Get a !!skirt?? similar to his !!kidnapper?? as a disguise." :
									"I have a skirt suitable for disguise with me.",
							++line, hasSkirt);
					journal.addLine(!hasWig ? "Get a !!wig?? suitable to disguise the !!Prince??." :
									"I have a wig suitable for a disguise with me.",
							++line, hasWig);
				}
			}
			if(stage >= 40) {
				if(guardDrunk) {
					journal.addLine("I also had to prevent the guard from seeing what I was up", ++line, true);
					journal.addLine("to, by getting him drunk.", ++line, true);
				}
				if(stage == 40 && guardDrunk) {
					journal.addLine("With the guard out of the way, all I have to do now is",++line,false);
					journal.addLine("use the !!Skin potion??, !!pink skirt??, !!rope??, !!blonde wig?? and !!cell key??", ++line, false);
					journal.addLine("to free !!Prince Ali?? from his cell somehow.", ++line, false);
				} else if(stage == 40){
					journal.addLine("Do something to prevent !!Joe the Guard?? seeing the", ++line, false);
					journal.addLine("escape.", ++line, false);
				}
			}
			if(stage >= 50) {
				journal.addLine("With the guard disposed of, I used my rope to tie up Lady",++line,true);
				journal.addLine("Keli in a cupboard, so I could disguise the Prince.",++line,true);
				if(stage == 50) {
					journal.addLine("I need to !!unlock the cell door?? and then give the Prince the",++line,false);
					journal.addLine("!!pink skirt??, the !!skin paste??, and the !!blonde wig?? so that the",++line,false);
					journal.addLine("Prince can safely !!escape?? disguised as !!Lady Keli.",++line,false);
				}
			}
			if(stage >= 60) {
				journal.addLine("I then used a wig and some skin skin paste to make the",++line,true);
				journal.addLine("prince look like Lady Keli so he could escape to his",++line,true);
				journal.addLine("freedom with Leela after unlocking his cell door.",++line,true);
				if(stage == 60) {
					journal.addLine("I should return to !!Hassan?? to claim my reward.",++line,false);
				}
			}
			if(stage == 100) {
				journal.addLine("Hassan the chancellor rewarded me for all of my help.",++line,true);
				journal.addLine("I am now a friend of Al-Kharid and may pass through the",++line,true);
				journal.addLine("gate leading between Lumbridge and Al-Kharid for free.",++line,true);
				journal.addLine("!!QUEST COMPLETE??!",++line,false);
			}
		}
	}

	@Override
	public void updateConditionals(Player player) {
		hasRope = player.getInventory().containsItem(ROPE);
		hasWig = player.getInventory().containsItem(YELLOW_WIG);
		hasSkirt = player.getInventory().containsItem(SKIRT);
		hasPaste = player.getInventory().containsItem(PASTE);
		guardDrunk = player.getAttribute("guard-drunk",false);
	}

	@Override
	public int getConfig(Player player) {
		int stage = player.getNeoQuestRepository().getStage("Prince Ali Rescue");
		if(stage >= 100){
			return configs;
		}
		if(stage > 0){
			return 1;
		}
		return 0;
	}

	/**
	 * Ceck if the player has the item. //Vexia, why the fuck did you write a function that calls a function that does what you
	 * 									  wanted your function to do to begin with????????? Leaving this here for historical purpose.
	 * @param player the player.
	 * @param item the item.
	 * @return true or false.
	 */
	public static boolean hasItem(final Player player, final Item item) {
		return player.getInventory().containsItem(item);
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		int line = 10;
		rewards.addLine("3 Quest Points",line++);
		rewards.addLine("700 coins",line++);
		rewards.setInterfaceItem(995);
		rewards.addRewardItem(COINS);
		rewards.draw(player);
		player.getPacketDispatch().sendMessage("The chancellor pays you 700 coins.");
		player.removeAttribute("guard-drunk");
	}


}
