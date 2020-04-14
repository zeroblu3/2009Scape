package plugin.quest;

import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.NeoQuest;
import org.crandor.game.node.entity.player.link.quest.NeoQuestRepository;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;

/**
 * Represents the cooks assistants quest
 * @author Ceikry
 * 
 */
@InitializablePlugin
public final class CooksAssistant extends NeoQuest {
	int stage;
	private boolean hasEgg,hasMilk,hasFlour,gaveMilk,gaveEgg,gaveFlour;
	public CooksAssistant(){super(29,14,"Cook's Assistant",1,2);}

	@Override
	public void setLines(Player player){
		super.setLines(player);
		stage = player.getNeoQuestRepository().getStage("Cook's Assistant");
		boolean started = stage > 0;
		journal.setTitle("Cook's Assistant");
		int line = 10;
		if(!started) {
			journal.addLine("I can start this quest by speaking to the !!Cook?? in the ", ++line, false);
			journal.addLine("!!Kitchen?? on the ground floor of !!Lumbridge Castle??.",++line,false);
		} else {
			if(stage == 10){
				journal.addLine("It's the !!Duke of Lumbridge's?? birthday and I have to help",++line,false);
				journal.addLine("his !!Cook?? make him a !!birthday cake??. To do this I need to",++line,false);
				journal.addLine("bring him the following ingredients: ",++line,false);

				if(!hasMilk && !gaveMilk){
					journal.addLine("I need to find a !!bucket of milk??.",++line,false);
				} else if(gaveMilk) {
					journal.addLine("I have given the cook a bucket of milk.", ++line, true);
				} else {
					journal.addLine("I have found him a !!bucket of milk??.",++line,false);
				}

				if(!hasFlour && !gaveFlour){
					journal.addLine("I need to find a !!pot of flour??.",++line,false);
				} else if(gaveFlour) {
					journal.addLine("I have given the cook a pot of flour.", ++line, true);
				} else {
					journal.addLine("I have found him a !!pot of flour??.",++line,false);
				}

				if(!hasEgg && !gaveEgg){
					journal.addLine("I need to find an !!egg??.",++line,false);
				} else if(gaveEgg) {
					journal.addLine("I have given the cook an egg.", ++line, true);
				} else {
					journal.addLine("I have found him an !!egg??.",++line,false);
				}
			}
			if(stage == 100){
				journal.addLine("It was the Duke of Lumbridge's birthday,  but his cook had",++line,true);
				journal.addLine("forgotten to buy the ingredients he needed to make him a",++line,true);
				journal.addLine("cake. I brought the cook an egg, some flour and some milk",++line,true);
				journal.addLine("and then cook made a delicious looking cake with them.",++line,true);
				journal.addLine("As a reward he now lets me use his high quality range",++line,true);
				journal.addLine("which lets me burn things less whenever I wish to cook",++line,true);
				journal.addLine("there.",++line,true);
				journal.addLine("!!QUEST COMPLETE??",++line,false);
			}
		}
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NeoQuestRepository.register(14,this);
		return this;
	}

	@Override
	public int getConfig(Player player){
		int stage = player.getNeoQuestRepository().getStage("Cook's Assistant");
		if(stage == 100){
			return configs;
		}
		if(stage > 0){
			return 1;
		}
		return 0;
	}

	@Override
	public void updateConditionals(Player player){
		if(player.getInventory().contains(1927,1)){
			hasMilk = true;
		}
		if(player.getInventory().contains(1944,1)){
			hasEgg = true;
		}
		if(player.getInventory().contains(1933,1)){
			hasFlour = true;
		}
		gaveMilk = player.getSavedData().getQuestData().getCookAssist("milk");
		gaveFlour = player.getSavedData().getQuestData().getCookAssist("flour");
		gaveEgg = player.getSavedData().getQuestData().getCookAssist("egg");
	}

	@Override
	public void finish(Player player) {
		int line = 10;
		super.finish(player);
		rewards.addLine("1 Quest Point",line++);
		rewards.addLine("300 Cooking XP",line++);
		rewards.addRewardXP(Skills.COOKING,300);
		rewards.setInterfaceItem(1891);
		rewards.draw(player);
	}
}