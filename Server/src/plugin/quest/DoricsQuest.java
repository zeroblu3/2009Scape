package plugin.quest;

import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.NeoQuest;
import org.crandor.game.node.entity.player.link.quest.NeoQuestRepository;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.InitializablePlugin;

/**
 * Represents the Doric's Quest
 * @author Ceikry
 * 
 */
@InitializablePlugin
public class DoricsQuest extends NeoQuest {

	/**
	 * Constructs a new {@Code DoricsQuest} {@Code Object}
	 */
	public DoricsQuest() {super(31,16,"Doric's Quest",1,100);}

	private boolean hasClay, hasCopper, hasIron;
	
	@Override
	public NeoQuest newInstance(Object object) {
		NeoQuestRepository.register(buttonId,this);
		return this;
	}

	@Override
	public void setLines(Player player) {
		super.setLines(player);
		int stage = player.getNeoQuestRepository().getStage("Doric's Quest");
		boolean started = stage > 0;
		int line = 10;
		if(!started){
			journal.addLine("I can start this quest by speaking to !!Doric?? who is !!North of",++line,false);
			journal.addLine("!!Falador.",++line,false);
			journal.addLine("There aren't any requirements, but !!Level 15 Mining?? will help.",++line,false);
		} else {
			journal.addLine("I have spoken to !!Doric??", ++line, true);
			if(stage != 100) {
				journal.addLine("I need to collect some items and bring them to !!Doric??:", ++line, false);
				journal.addLine("6 Clay", ++line, hasClay);
				journal.addLine("4 Copper", ++line, hasCopper);
				journal.addLine("2 Iron", ++line, hasIron);
			} else {
				journal.addLine("I collected some clay, copper and iron for Doric.",++line,true);
				journal.addLine("Doric rewarded me by allowing me to use his anvils",++line,true);
				journal.addLine("whenever I want.",++line,true);
				journal.addLine("!!QUEST COMPLETE??.",++line,false);
			}
		}
	}

	@Override
	public void updateConditionals(Player player) {
		hasClay = player.getInventory().contains(434,6);
		hasCopper = player.getInventory().contains(436,4);
		hasIron = player.getInventory().contains(440,2);
	}

	@Override
	public int getConfig(Player player) {
		int stage = player.getNeoQuestRepository().getStage("Doric's Quest");
		if(stage == 100){
			return configs;
		}
		if(stage > 0){
			return 1;
		}
		return 0;
	}


	@Override
	public void finish(Player player) {
		super.finish(player);
		int line = 10;
		rewards.addLine("1 Quest Point",line++);
		rewards.addLine("1300 Mining XP",line++);
		rewards.addLine("180 coins",line++);
		rewards.addLine("Use of Doric's Anvils",line++);
		rewards.addRewardItem(new Item(995,180));
		rewards.addRewardXP(Skills.MINING,1300);
		rewards.setInterfaceItem(1269);
		rewards.draw(player);
	}

}
