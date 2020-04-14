package plugin.quest;

import org.crandor.game.node.entity.npc.NPC;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.NeoQuest;
import org.crandor.game.node.entity.player.link.quest.NeoQuestRepository;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.game.node.entity.player.link.quest.Quest;

/**
 * Represents the romeo and juliet quest.
 * @author ceikry
 */
@InitializablePlugin
public class RomeoJuliet extends NeoQuest {

	/**
	 * Constructs a new {@code RomeoJuliet} {@code Object}.
	 * @param player The player to construct the class for.
	 */
	public RomeoJuliet() { super(144,25,"Romeo & Juliet",5,100);}

	@Override
	public void setLines(Player player) {
		super.setLines(player);
		int stage = player.getNeoQuestRepository().getStage("Romeo & Juliet");
		boolean started = stage > 0;
		int line = 10;
		if(!started){
			journal.addLine("I can start this quest by speaking to !!Romeo?? in !!Varrock??",++line,false);
			journal.addLine("central square by the !!fountain??.",++line,false);
		} else {
			if(stage >= 10){
				journal.addLine("I have agreed to find Juliet for Romeo and tell her how he",++line,true);
				journal.addLine("feels. For some reason he can't just do this himself",++line,true);
			}
			if(stage >= 20){
				journal.addLine("I found Juliet on the western edge of Varrock, and told",++line,true);
				journal.addLine("her about Romeo. She gave me a message to take back.",++line,true);
			}
			if(stage >= 30) {
				journal.addLine("I delivered the message to Romeo, and he was sad to hear",++line,true);
				journal.addLine("that Juliet's father opposed their marriage. However, he",++line,true);
				journal.addLine("said that Father Lawrence might be able to overcome this.",++line,true);
			}
			if(stage >= 40) {
				journal.addLine("I found Father Lawrence and he suggested the use of a",++line,true);
				journal.addLine("potion to fool Juliet's Father that she is dead so that",++line,true);
				journal.addLine("Romeo and Juliet can be together in peace.",++line,true);
			}
			if(stage >= 50) {
				journal.addLine("I went to the Apothecary regarding making this cadava",++line,true);
				journal.addLine("potion, and he told me to bring him some cadava berries.",++line,true);
			}
			if(stage >= 70) {
				journal.addLine("After the apothecary made the potion, I delivered it to",++line,true);
				journal.addLine("Juliet. She asked me to tell Romeo the plan.",++line,true);
			}
			if(stage >= 100) {
				journal.addLine("I told Romeo what was going to happen, but I'm not exactly",++line,true);
				journal.addLine("sure he understood what was happening. Ah well, I was",++line,true);
				journal.addLine("rewarded for all my help regardless.",++line,true);
				journal.addLine("!!QUEST COMPLETE??!",++line,false);
			}
			switch(stage) {
				case 10:
					journal.addLine("All I need to do now is find !!Juliet??.", ++line, false);
					break;
				case 20:
					journal.addLine("I should take the !!message?? from !!Juliet?? to !!Romeo??.", ++line, false);
					break;
				case 30:
					journal.addLine("I should find !!Father Lawrence?? and see how we can help.", ++line, false);
					break;
				case 40:
					journal.addLine("I need to find the !!apothecary?? to make a !!cadava potion??.", ++line, false);
					break;
				case 50:
					if (!player.getInventory().contains(753, 1)) {
						journal.addLine("I will have to find some !!cadava berries?? somewhere!", ++line, false);
					} else {
						journal.addLine("I should take these !!cadava berries?? to the !!apothecary??.", ++line, false);
					}
					break;
				case 60:
					journal.addLine("I should take this !!cadava potion?? to !!Juliet??.",++line,false);
					break;
				case 70:
					journal.addLine("I have to find !!Romeo?? and tell him what's happened.",++line,false);
					break;
			}
		}
	}

	@Override
	public int getConfig(Player player) {
		int stage = player.getNeoQuestRepository().getStage("Romeo & Juliet");
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
		rewards.addLine("5 Quest Points",line++);
		rewards.setInterfaceItem(756);
		rewards.draw(player);
	}

	@Override
	public NeoQuest newInstance(Object object) {
		NeoQuestRepository.register(buttonId,this);
		return this;
	}
}
