package plugin.quest.gdiplomacy;

import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.NeoQuest;
import org.crandor.game.node.entity.player.link.quest.NeoQuestRepository;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.PluginManager;

/**
 * Represents the demon slayer quest.
 * @author Vexia
 * 
 */
@InitializablePlugin
public class GoblinDiplomacy extends NeoQuest {

	/**
	 * The name of the quest.
	 */
	public static final String NAME = "Goblin Diplomacy";

	/**
	 * Repreresents the orange goblin mail.
	 */
	private static final Item ORANGE_MAIL = new Item(286);

	/**
	 * Represents the blue goblin mail.
	 */
	private static final Item BLUE_MAIL = new Item(287);

	/**
	 * Represents the goblin mail.
	 */
	private static final Item GOBLIN_MAIL = new Item(288);

	/**
	 * Represents the gold bar item.
	 */
	private static final Item GOLD_BAR = new Item(2357);
	
	/**
	 * Constructs a new {@Code GoblinDiplomacy} {@Code Object}
	 */
	public GoblinDiplomacy() { super(62,19,"Goblin Diplomacy",5,6);}
	private boolean hasOrange, hasBlue, hasBrown;
	
	@Override
	public NeoQuest newInstance(Object object) {
		NeoQuestRepository.register(buttonId,this);
		PluginManager.definePlugin(new GDiplomacyCutscene(), new GoblinDiplomacyPlugin(), new GrubfootDialogue());
		return this;
	}

	@Override
	public void setLines(Player player) {
		super.setLines(player);
		int line = 10;
		int stage = player.getNeoQuestRepository().getStage("Goblin Diplomacy");
		boolean started = stage > 0;
		if(!started){
			journal.addLine("I can start thiss quest by speaking to !!Generals Wartface and",++line,false);
			journal.addLine("!!Bentnoze?? in the Goblin Village.",++line,false);
			journal.addLine("There are no requirements for this quest.",++line,false);
		} else {
			journal.addLine("I spoke to Generals Wartface and Bentnoze in the Goblin",++line,true);
			journal.addLine("Village and found that the goblins were on the brink of civil",++line,true);
			journal.addLine("war over the colour of their armour. I offered to help the",++line,true);
			journal.addLine("generals by finding another colour that they both like.",++line,true);
			if(stage == 10){
				journal.addLine(!hasOrange ? "I should bring the goblins some !!orange goblin armour.??":
						                     "I have some !!orange goblin armour??. I should show it to the",
						        ++line,false);
				journal.addLine(!hasOrange ? "Maybe the generals will know where to get some.":
						                     "generals.",
						        ++line,false);
			}
			if(stage >= 20) {
				journal.addLine("I brought the generals some orange goblin armour, but they", ++line, true);
				journal.addLine("didn't like it.",++line,true);
			}
			if(stage == 20){
				journal.addLine(!hasBlue ? "I should bring the goblins some !!blue goblin armour.??":
						                   "I have some !!blue goblin armour??. I should show it to the",
						        ++line,false);
				journal.addLine(!hasBlue ? "Maybe the generals will know where to get some.":
								           "generals.",
						        ++line,false);
			}
			if(stage >= 30){
				journal.addLine("I brought the generals some blue goblin armour, but they", ++line, true);
				journal.addLine("didn't like it.",++line,true);
			}
			if(stage == 30){
				journal.addLine(!hasBrown ? "I should bring the goblins some !!brown goblin armour.??":
								"I have some !!brown goblin armour??. I should show it to the",
						++line,false);
				journal.addLine(!hasBrown ? "Maybe the generals will know where to get some.":
								"generals.",
						++line,false);
			}
			if(stage == 100){
				journal.addLine("Unfortunately, the goblins were very stupid, and it turned out",++line,true);
				journal.addLine("that they liked the original colour the most. That's goblins",++line,true);
				journal.addLine("for you I guess.",++line,true);
				journal.addLine("!!QUEST COMPLETE!??",++line,false);
			}
		}
	}

	@Override
	public void updateConditionals(Player player) {
		hasBlue = player.getInventory().containsItem(BLUE_MAIL);
		hasOrange = player.getInventory().containsItem(ORANGE_MAIL);
		hasBrown = player.getInventory().containsItem(GOBLIN_MAIL);
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		int line = 10;
		rewards.addLine("5 Quest Points",line++);
		rewards.addLine("200 Crafting XP",line++);
		rewards.addLine("A gold bar",line++);
		rewards.addRewardItem(GOLD_BAR);
		rewards.addRewardXP(Skills.CRAFTING,200);
		rewards.setInterfaceItem(288);
		rewards.draw(player);
	}

	@Override
	public int getConfig(Player player) {
		int stage = player.getNeoQuestRepository().getStage("Goblin Diplomacy");
		if (stage == 100) {
			return configs;
		}
		if (stage > 0) {
			return 1;
		}
		return 0;
	}

}
