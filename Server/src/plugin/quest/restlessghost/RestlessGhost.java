package plugin.quest.restlessghost;

import org.crandor.game.content.skill.Skills;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.NeoQuest;
import org.crandor.game.node.entity.player.link.quest.NeoQuestRepository;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.PluginManager;

/**
 * Represents the restless ghost quest.
 * @author Ceikry
 * 
 */
@InitializablePlugin
public class RestlessGhost extends NeoQuest {
	
	/**
	 * The ghost speak amulet.
	 */
	public static final Item AMULET = new Item(552);
	
	/**
	 * Constructs a new {@Code RestlessGhost} {@Code Object}
	 */
	public RestlessGhost() { super(107,24,"The Restless Ghost",1,5);}

	@Override
	public NeoQuest newInstance(Object object) {
		NeoQuestRepository.register(buttonId,this);
		PluginManager.definePlugin(new OldCronDialogue());
		return this;
	}

	@Override
	public void setLines(Player player) {
		super.setLines(player);
		int line = 10;
		int stage = player.getNeoQuestRepository().getStage("The Restless Ghost");
		boolean started = stage > 0;
		if(!started){
			journal.addLine("I can start this quest by speaking to !!Father Aereck?? in the",++line,false);
			journal.addLine("!!church?? next to !!Lumbridge Castle??.",++line,false);
			journal.addLine("I must be unafraid of a !!level 13 skeleton??.",++line,false);
		} else {
			if(stage >= 10){
				journal.addLine("Father Aereck asked me to help hime deal with the ghost in",++line,true);
				journal.addLine("the graveyard next to the church.",++line,true);
			}
			if(stage == 10) {
				journal.addLine("I should find !!Father Urhney?? who is an expert on !!ghosts",++line,false);
				journal.addLine("He lives in a !!shack?? in !!Lumbridge swamp.??",++line,false);
			}
			if(stage >= 20) {
				journal.addLine("I found Father Urhney in the swamp south of Lumbridge. He",++line,true);
				journal.addLine("gave me an Amulet of Ghostspeak to talk to the ghost.",++line,true);
			}
			if(stage == 20) {
				journal.addLine("I should talk to the !!ghost?? to find out why it is haunting the",++line,false);
				journal.addLine("graveyard crypt.",++line,false);
			}
			if(stage >= 30) {
				journal.addLine("I spoke to the ghost and he told me he could not rest in",++line,true);
				journal.addLine("peace because an evil wizard has stolen his skull.",++line,true);
			}
			if(stage == 30) {
				journal.addLine("I should go and search the !!Wizard's Tower?? south west of",++line,false);
				journal.addLine("!!Lumbridge?? for the !!ghost's skull??.",++line,false);
			}
			if(stage == 40) {
				journal.addLine("I have recovered the !!ghost's skull??. I should bring it to him.",++line,false);
			}
			if(stage >= 100) {
				journal.addLine("I found the ghost's skull in the basement of the Wizards'",++line,true);
				journal.addLine("Tower. It was guarded by a skeleton, but I took it anyways.",++line,true);
				journal.addLine("I placed the skull in the ghost's coffin, and allowed it to",++line,true);
				journal.addLine("rest in peace once more, with gratitude for my help.",++line,true);
				journal.addLine("!!QUEST COMPLETE??!",++line,false);
			}
		}
	}

	@Override
	public int getConfig(Player player) {
		int stage = player.getNeoQuestRepository().getStage("The Restless Ghost");
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
		rewards.addLine("1125 Prayer XP",line++);
		rewards.addRewardXP(Skills.PRAYER,1125);
		rewards.setInterfaceItem(964);
		rewards.draw(player);
		player.getInterfaceManager().closeChatbox();
		player.getConfigManager().set(728, 31, true);
		player.getGameAttributes().removeAttribute("restless-ghost:urhney");
	}
}
