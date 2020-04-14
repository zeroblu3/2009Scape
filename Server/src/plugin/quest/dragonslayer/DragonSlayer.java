package plugin.quest.dragonslayer;

import org.crandor.game.component.Component;
import org.crandor.game.content.skill.Skills;
import org.crandor.game.content.skill.member.agility.AgilityHandler;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.NeoQuest;
import org.crandor.game.node.entity.player.link.quest.NeoQuestRepository;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.system.task.Pulse;
import org.crandor.game.world.GameWorld;
import org.crandor.game.world.map.Location;
import org.crandor.game.world.map.RegionManager;
import org.crandor.game.world.update.flag.context.Animation;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.PluginManager;

/**
 * Represents the dragon slayer quest.
 * @author Vexia
 * 
 */
@InitializablePlugin
public final class DragonSlayer extends NeoQuest {

	/**
	 * Represents the maze key given by the guildmaster.
	 */
	public static final Item MAZE_KEY = new Item(1542);

	/**
	 * Represents the red key item.
	 */
	public static final Item RED_KEY = new Item(1543);

	/**
	 * Represents the orange key item.
	 */
	public static final Item ORANGE_KEY = new Item(1544);

	/**
	 * Represents the yellow key item.
	 */
	public static final Item YELLOW_KEY = new Item(1545);

	/**
	 * Represents the blue key item.
	 */
	public static final Item BLUE_KEY = new Item(1546);

	/**
	 * Represents the purple key item.
	 */
	public static final Item PURPLE_KEY = new Item(1547);

	/**
	 * Represents the green key item.
	 */
	public static final Item GREEN_KEY = new Item(1548);

	/**
	 * Represents the maze map piece.
	 */
	public static final Item MAZE_PIECE = new Item(1535);

	/**
	 * Represents the magic door map piece.
	 */
	public static final Item MAGIC_PIECE = new Item(1537);

	/**
	 * Represents the wormbrain piece.
	 */
	public static final Item WORMBRAIN_PIECE = new Item(1536);

	/**
	 * Represents the anti dragon fire shield.
	 */
	public static final Item SHIELD = new Item(1540);

	/**
	 * Represents the crandor map item.
	 */
	public static final Item CRANDOR_MAP = new Item(1538);

	/**
	 * Represents the map component interface.
	 */
	public static final Component MAP_COMPONENT = new Component(547);

	/**
	 * Represents the nails item.
	 */
	public static final Item NAILS = new Item(1539, 30);

	/**
	 * Represents the plank item.
	 */
	public static final Item PLANK = new Item(960);

	/**
	 * Represents the hammer item.
	 */
	public static final Item HAMMER = new Item(2347);

	/**
	 * Represents the elvarg head item.
	 */
	public static final Item ELVARG_HEAD = new Item(11279);

	/**
	 * Constructs a new {@Code DragonSlayer} {@Code Object}
	 */
	public DragonSlayer() { super(176,17,"Dragon Slayer",2, 10);}

	private boolean hasNeededQP,hasMelzar,hasOracle,hasWormbrain,hasShield,hasShip,shipRepaired,elvargSlain,hiddenPassage,hasFullMap;

	@Override
	public NeoQuest newInstance(Object object) {
		NeoQuestRepository.register(buttonId,this);
		PluginManager.definePlugin(new CrandorMapPlugin(), new DragonSlayerPlugin(), new DSMagicDoorPlugin(), new DragonSlayerCutscene(), new MazeDemonNPC(), new MazeGhostNPC(), new MazeSkeletonNPC(), new MazeZombieNPC(), new MeldarMadNPC(), new WormbrainNPC(), new ZombieRatNPC(), new DSChestDialogue(), new GuildmasterDialogue(), new ElvargNPC(), new WormbrainDialogue(), new OziachDialogue(), new NedDialogue(), new DukeHoracioDialogue());
		return this;
	}

	@Override
	public void setLines(Player player) {
		super.setLines(player);
		int line = 10;
		int stage = player.getNeoQuestRepository().getStage("Dragon Slayer");
		boolean started = stage > 0;
		if(!started){
			journal.addLine("I can start this quest by speaking to the !!Guildmaster?? in",++line,false);
			journal.addLine("the !!Champions' Guild??, south-west of Varrock.",++line,false);
			journal.addLine("I will need to be able to defeat a !!level 83 dragon.??",++line,false);
			journal.addLine("To enter the champion's guild I need !!32 Quest Points??.",++line,hasNeededQP);
		} else {
			if(stage >= 10){
				journal.addLine("The Guildmaster of the Champions' Guild said I could earn",++line,true);
				journal.addLine("the right to wear rune armour if I went on a quest for",++line,true);
				journal.addLine("Oziach, who makes the armor.",++line,true);
				journal.addLine(stage < 15 ? "I should speak to !!Oziach??, who lives by the cliffs to the":
						                      "I spoke to Oziach in Edgeville. He told me to slay the",
						        ++line,stage >= 15);
				journal.addLine(stage < 15 ? "west of !!Edgeville??.":
											  "dragon of Crandor island.",
						        ++line,stage >= 15);
			}
			if(stage >= 15){
				journal.addLine(stage <= 20 ? "I should return to the !!Champions' Guild Guildmaster?? for":
											  "The Champions' Guild Guildmaster gave me more detailed",
								 ++line,stage >= 20);
				journal.addLine(stage <= 20 ? "more detailed instructions.":
											  "instructions.",
						        ++line,stage >= 20);
			}
			if(stage >= 20 && stage < 40){
				journal.addLine("To defeat the dragon I will need to find a !!map?? to !!Crandor??, a",++line,false);
				journal.addLine("!!ship??, a !!captain?? to take me there and some kind of",++line,false);
				journal.addLine("!!protecion?? against dragon breath.",++line,false);

				if(!hasFullMap) {
					journal.addLine(!hasMelzar ? "One-third of the map is in !!Melzar's Maze?? near" :
									"I found the piece of the map that was hidden in Melzar's",
							++line, hasMelzar);
					journal.addLine(!hasMelzar ? "Rimmington." :
									"Maze.",
							++line, hasMelzar);

					journal.addLine(!hasOracle ? "One-third of the map is hidden and only the !!Oracle?? on" :
									"I found the map piece that was hidden beneath Ice",
							++line, hasOracle);
					journal.addLine(!hasOracle ? "!!Ice Mountain?? will know where it is." :
									"Mountain.",
							++line, hasOracle);

					journal.addLine(!hasWormbrain ? "One-third of the map was stolen by a !!goblin?? from the" :
									"I found the piece of the map that the goblin, Wormbrain,",
							++line, hasWormbrain);
					journal.addLine(!hasWormbrain ? "!!Goblin Village??." :
									"stole.",
							++line, hasWormbrain);
				} else {
					journal.addLine("I've restored the map of Crandor.",++line,true);
				}
				if(stage < 40 || !hiddenPassage) {
					journal.addLine(!hasShield ? "I should ask the !!Duke of Lumbridge?? for an !!anti-" :
									"The Duke of Lumbridge gave me an anti-dragonbreath",
							++line, hasShield);
					journal.addLine(!hasShield ? "!!dragonbreath shield." :
									"shield",
							++line, hasShield);

					journal.addLine(!hasShip ? "I should see if there is a !!ship?? for sale in !!Port Sarim" :
									"I bought a ship in Port Sarim called the Lady Lumbridge.",
							++line, hasShip);
					if (hasShip) {
						journal.addLine(!shipRepaired ? "I need to repair the hole in bottom of the ship." :
										"I have repaired my ship using wooden planks and nails.",
								++line, shipRepaired);
					}
				  }
				}
			if(stage == 30){
				journal.addLine("Captain Ned from Draynor has agreed to sail the", ++line, true);
				journal.addLine("ship to Crandor for me.",++line,true);
				journal.addLine("Now I should go to my ship in !!Port Sarim?? and set sail for",++line,false);
				journal.addLine("!!Crandor??!",++line,false);
			}
			if(stage >= 40){
				if (hiddenPassage) {
					journal.addLine("I have found a secret passage leading from Karamaja to",++line,true);
					journal.addLine("Crandor, so I no longer need to worry about finding a",++line,true);
					journal.addLine("seaworthy ship and captain to take me there.",++line,true);
				}
				if(stage == 40) {
					journal.addLine(!elvargSlain ? "Now all I need to do is slay the dragon!" :
									"I have slain the dragon! Now I just need to tell !!Oziach??.",
							++line, false);
				}
			}
			if(stage == 100){
				journal.addLine("I sailed to Crandor and killed the dragon, I am now a true",++line,true);
				journal.addLine("champion and have proved myself worthy to wear rune platemail!",++line,true);
				journal.addLine("!!QUEST COMPLETE.??",++line,false);
				journal.addLine("I gained 2 Quest Points, 18,650 Strength XP, 18,650",++line,false);
				journal.addLine("Defence XP, and the right to wear rune platebodies!",++line,false);
			}
		}
	}

	@Override
	public void updateConditionals(Player player) {
		hasNeededQP = player.getNeoQuestRepository().points >= 32;
		hasMelzar = player.getInventory().containsItem(MAZE_PIECE) || player.getBank().containsItem(MAZE_PIECE);
		hasOracle = player.getInventory().containsItem(MAGIC_PIECE) || player.getBank().containsItem(MAGIC_PIECE);
		hasWormbrain = player.getInventory().containsItem(WORMBRAIN_PIECE) || player.getBank().containsItem(WORMBRAIN_PIECE);
		hasShield = player.getInventory().containsItem(SHIELD) || player.getBank().containsItem(SHIELD);
		hasShip = player.getSavedData().getQuestData().getDragonSlayerAttribute("ship");
		shipRepaired = player.getSavedData().getQuestData().getDragonSlayerAttribute("repaired");
		hiddenPassage = player.getAttribute("demon-slayer:memorize", false);
		elvargSlain = player.getInventory().containsItem(ELVARG_HEAD);
		hasFullMap = player.getInventory().containsItem(CRANDOR_MAP);
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		int line = 10;
		rewards.addLine("2 Quest Points",line++);
		rewards.addLine("Ability to wear rune platebody",line++);
		rewards.addLine("18,650 Strength XP",line++);
		rewards.addLine("18,650 Defence XP",line++);
		rewards.setInterfaceItem(ELVARG_HEAD.getId());
		rewards.addRewardXP(Skills.STRENGTH,18650);
		rewards.addRewardXP(Skills.DEFENCE,18650);
		rewards.draw(player);
	}

	@Override
	public int getConfig(Player player) {
		int stage = player.getNeoQuestRepository().getStage("Dragon Slayer");
			if(stage >= 100){
				return configs;
			}
			if(stage > 0){
				return 1;
			}
			return 0;
	}

	/**
	 * Method used to handle going through the magic door.
	 * @param player the player.
	 * @param interaction the interaction.
	 * @return <code>True</code> if so.
	 */
	public static boolean handleMagicDoor(final Player player, boolean interaction) {
		if (!player.getSavedData().getQuestData().getDragonSlayerItem("lobster") || !player.getSavedData().getQuestData().getDragonSlayerItem("bowl") || !player.getSavedData().getQuestData().getDragonSlayerItem("silk") || !player.getSavedData().getQuestData().getDragonSlayerItem("wizard")) {
			if (interaction) {
				player.getPacketDispatch().sendMessage("You can't see any way to open the door.");
			}
			return true;
		}
		player.getPacketDispatch().sendMessage("The door opens...");
		final GameObject object = RegionManager.getObject(new Location(3050, 9839, 0));
		player.faceLocation(object.getLocation());
		player.getPacketDispatch().sendObjectAnimation(object, new Animation(6636));
		GameWorld.submit(new Pulse(1, player) {
			int counter = 0;

			@Override
			public boolean pulse() {
				switch (counter++) {
				case 4:
					AgilityHandler.walk(player, 0, player.getLocation(), player.getLocation().getX() == 3051 ? Location.create(3049, 9840, 0) : Location.create(3051, 9840, 0), null, 0, null);
					break;
				case 5:
					player.getPacketDispatch().sendObjectAnimation(object, new Animation(6637));
					break;
				case 6:
					player.getPacketDispatch().sendObjectAnimation(object, new Animation(6635));
					return true;
				}
				return false;
			}
		});
		return true;
	}
	
}
