package plugin.quest;

import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.NeoQuest;
import org.crandor.game.node.entity.player.link.quest.NeoQuestRepository;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.item.Item;

/**
 * Represents The Knight's Sword quest.
 * @author Ceikry
 * 
 */
@InitializablePlugin
public class TheKnightsSword extends NeoQuest {
	
	/**
	 * Represents the portrait item.
	 */
	private static final Item PORTRAIT = new Item(666);

	public TheKnightsSword() { super(122,21,"The Knight's Sword", 1, 7);}
	boolean hasMining,hasSword,hasPortrait;
	
	@Override
	public NeoQuest newInstance(Object object) {
		NeoQuestRepository.register(buttonId,this);
		return this;
	}

	@Override
	public void setLines(Player player) {
		super.setLines(player);
		int stage = player.getNeoQuestRepository().getStage("The Knight's Sword");
		boolean started = stage > 0;
		int line = 10;
		if(!started){
			journal.addLine("I can start this quest by speaking to the !!Squire?? in the",++line,false);
			journal.addLine("courtyard of the !!White Knights' Castle?? in !!southern Falador.??",++line,false);
			journal.addLine("To complete the quest I need:",++line,false);
			journal.addLine("Level 10 Mining",++line,hasMining);
			journal.addLine("and to be unafraid of !!level 57 Ice Warriors.??",++line,false);
		} else {
			if(stage < 100) {
				journal.addLine("I told the Squire I would help hin to replace the sword he", ++line, true);
				journal.addLine("has lost. It could only be made by an Imcando Dwarf.", ++line, true);
				if (stage == 10) {
					journal.addLine("The Squire suggests I speak to !!Reldo?? in the !!Varrock Palace??", ++line, false);
					journal.addLine("!!Library?? for information about the !!Imcando dwarves??.", ++line, false);
				}
				if (stage == 20) {
					journal.addLine("Reldo couldn't give much information about the", ++line, false);
					journal.addLine("!!Imcando??, except a few live on the !!southern peninsula?? of", ++line, false);
					journal.addLine("!!Asgarnia??, they dislike strangers, and LOVE !!redberry pies??.", ++line, false);
				}
				if (stage >= 30) {
					journal.addLine("I found an Imcando dwarf named Thurgo thanks to", ++line, true);
					journal.addLine("information provided by Reldo. He wasn't very talkative", ++line, true);
					journal.addLine("until I gave him a redberry pie, which he gobbled up.", ++line, true);
				}
				if (stage == 30) {
					journal.addLine("He will help me now that I have gained his trust through !!pie", ++line, false);
				}
				if (stage == 40) {
					journal.addLine("!!Thurgo?? needs a !!picture of the sword?? before he can help.", ++line, false);
					journal.addLine("I should probably ask the !!Squire?? about obtaining one.", ++line, false);
				}
				if (stage == 50) {
					journal.addLine(!hasPortrait ? "The squire told me about a !!portrait??" :
									"I now have a picture of the !!Knight's Sword?? - I should take it",
							++line, false);
					journal.addLine(!hasPortrait ? "which has a !!picture of the sword?? in !!Sir Vyvin??'s room." :
									"to !!Thurgo?? so that he can duplicate it.",
							++line, false);
				}
				if (stage >= 60) {
					journal.addLine("Thurgo needed a picture of the sword before he could", ++line, true);
					journal.addLine("start work on a replacement. I brought him a portrait.", ++line, true);
				}
				if (stage == 60) {
					if (!hasSword) {
						journal.addLine("According to !!Thurgo?? to make a !!replica sword?? he will need", ++line, false);
						journal.addLine("!!two Iron Bars?? and some !!Blurite Ore??. Blurite Ore can only be", ++line, false);
						journal.addLine("found !!deep in the caves below Thurgo's house", ++line, false);
					} else {
						journal.addLine("Thurgo has now smithed me a reploica of Sir Vyvin's sword", ++line, true);
						journal.addLine("I should return it to the !!Squire?? for my reward.", ++line, false);
					}
				}
			}
			if(stage >= 100){
				journal.addLine("Thurgo needed a picture of the sword before he could", ++line,true);
				journal.addLine("start work on a replacement. I took him a portrait of it.",++line, true);
				journal.addLine("After bringing Thurgo two iron bars and some blurite ore",++line,true);
				journal.addLine("he made me a fine replica of Sir Vyvin's Sword, which I",++line,true);
				journal.addLine("returned to the Squire for a reward.",++line,true);
				journal.addLine("!!QUEST COMPLETE!??",++line,false);
			}
		}
	}

	@Override
	public void updateConditionals(Player player) {
		hasPortrait = player.getInventory().containsItem(PORTRAIT);
		hasSword = player.getInventory().contains(667,1) || player.getEquipment().contains(667,1);
		hasMining = player.getSkills().getLevel(Skills.MINING) >= 10;
	}

	@Override
	public int getConfig(Player player) {
		int stage = player.getNeoQuestRepository().getStage("The Knight's Sword");
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
		super.finish(player);
		int line = 10;
		rewards.addLine("1 Quest Point",line++);
		rewards.addLine("12,725 Smithing XP",line++);
		rewards.setInterfaceItem(667);
		rewards.addRewardXP(Skills.SMITHING,12725);
		rewards.draw(player);
	}

}
