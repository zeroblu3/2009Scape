package plugin.quest;

import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.NeoQuest;
import org.crandor.game.node.entity.player.link.quest.NeoQuestRepository;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.object.GameObject;
import org.crandor.game.node.object.ObjectBuilder;
import org.crandor.game.world.map.Location;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.world.map.RegionManager;
import plugin.skill.crafting.AmuletStringingPlugin;

/**
 * Represents the imp catcher quest.
 * @author Ceikry
 * 
 */
@InitializablePlugin
public class ImpCatcher extends NeoQuest {

	/**
	 * Represents the black bead item.
	 */
	private static final Item BLACK_BEAD = new Item(1474);

	/**
	 * Represents the red bead item.
	 */
	private static final Item RED_BEAD = new Item(1470);

	/**
	 * Represents the white bead item.
	 */
	private static final Item WHITE_BEAD = new Item(1476);

	/**
	 * Represents the yellow bead item.
	 */
	private static final Item YELLOW_BEAD = new Item(1472);

	/**
	 * Represents the amulet item.
	 */
	private static final Item AMULET = new Item(1478);
	
	/**
	 * Constructs a new {@Code ImpCatcher} {@Code Object}
	 */
	public ImpCatcher() { super(160,20,"Imp Catcher",1,2);}
	private boolean hasAllBeads,hasBlack,hasRed,hasWhite,hasYellow;


	@Override
	public NeoQuest newInstance(Object object) {
		NeoQuestRepository.register(buttonId,this);
		return this;
	}

	@Override
	public void setLines(Player player) {
		super.setLines(player);
		int line = 10;
		int stage = player.getNeoQuestRepository().getStage("Imp Catcher");
		boolean started = stage > 0;
		if (!started) {
			journal.addLine("I can start this quest by speaking to !!Wizard Mizgog?? who is",++line,false);
			journal.addLine("on the top floor of the !!Wizard's Tower??",++line,false);
			journal.addLine("There are no requirements for this quest.",++line,false);
		} else {
			journal.addLine("I have spoken to Wizard Mizgog",++line,true);
			if(stage == 10) {
				journal.addLine("I need to collect some items by killing !!imps??:", ++line, false);
				if (!hasAllBeads) {
					journal.addLine("A black bead.", ++line, hasBlack);
					journal.addLine("A red bead.", ++line, hasRed);
					journal.addLine("A white bead.", ++line, hasWhite);
					journal.addLine("A yellow bead.", ++line, hasYellow);
				} else {
					journal.addLine("I have collected all the missing beads and need to return", ++line, false);
					journal.addLine("them to !!Wizard Mizgog", ++line, false);
				}
			} else {
				journal.addLine("I collected all the beads for Wizard Mizgog, and he",++line,true);
				journal.addLine("rewarded me with an amulet of accuracy.",++line,true);
				journal.addLine("!!QUEST COMPLETE!??",++line,false);
			}
		}
	}

	@Override
	public void updateConditionals(Player player) {
		hasRed = player.getInventory().containsItem(RED_BEAD);
		hasBlack = player.getInventory().containsItem(BLACK_BEAD);
		hasYellow = player.getInventory().containsItem(YELLOW_BEAD);
		hasWhite = player.getInventory().containsItem(WHITE_BEAD);
		hasAllBeads = hasBlack && hasWhite && hasYellow && hasRed;
	}

	@Override
	public int getConfig(Player player) {
		int stage = player.getNeoQuestRepository().getStage("Imp Catcher");
		if(stage >= 100){
			return configs;
		}
		if (stage > 0){
			return 1;
		}
		return 0;
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.unlock();
		int line = 10;
		rewards.addLine("1 Quest Point",line++);
		rewards.addLine("875 Magic XP",line++);
		rewards.addLine("Amulet of Accuracy",line++);
		rewards.setInterfaceItem(AMULET.getId());
		rewards.addRewardItem(AMULET);
		rewards.addRewardXP(Skills.MAGIC, 875);
		rewards.draw(player);
		GameObject table = RegionManager.getObject(Location.create(3102, 3163, 2));
		if (table.getId() != 16170) {
			ObjectBuilder.replace(table, table.transform(16170), 80);
		}
	}
}
