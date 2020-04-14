package plugin.quest.ptreasure;

import org.crandor.game.component.Component;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.NeoQuest;
import org.crandor.game.node.entity.player.link.quest.NeoQuestRepository;
import org.crandor.game.node.entity.player.link.quest.Quest;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.PluginManager;

/**
 * Represents the pirates treasure quest.
 * @author Ceikry
 * 
 */
@InitializablePlugin
public final class PiratesTreasure extends NeoQuest {

	/**
	 * Represents the message component for pirates treasure quest.
	 */
	public static final Component MESSAGE_COMPONENT = new Component(222);

	/**
	 * Represents the cakset rewards (pirates treasure).
	 */
	public static final Item[] CASKET_REWARDS = new Item[] { new Item(995, 450), new Item(1635), new Item(1605) };

	/**
	 * Represents the karamjan rum item.
	 */
	public static final Item KARAMJAN_RUM = new Item(431);

	/**
	 * Represents the chest key item.
	 */
	public static final Item KEY = new Item(432);

	/**
	 * Represents the pirate message item.
	 */
	public static final Item PIRATE_MESSAGE = new Item(433);

	/**
	 * Represents the casket item.
	 */
	public static final Item CASKET = new Item(7956);
	
	/**
	 * Constructs a new {@Code PiratesTreasure} {@Code Object}
	 */
	public PiratesTreasure() { super(71,22,"Pirate's Treasure",2,4);}
	private boolean hasRum,hasNote,hasKey,hasRead,rumStashed,employed,rumSmuggled;

	@Override
	public NeoQuest newInstance(Object object) {
		NeoQuestRepository.register(buttonId,this);
		PluginManager.definePlugin(new PiratesTreasurePlugin());
		return this;
	}

	@Override
	public void setLines(Player player) {
		super.setLines(player);
		int line = 10;
		int stage = player.getNeoQuestRepository().getStage("Pirate's Treasure");
		boolean started = stage > 0;
		if(!started){
			journal.addLine("I can start this quest by speaking to !!Redbeard Frank?? who",++line,false);
			journal.addLine("is at !!Port Sarim??.",++line,false);
			journal.addLine("There aren't any requirements for this quest.",++line,false);
		} else {
			if (stage == 10) {
				journal.addLine("I have spoken to !!Redbeard Frank??. He has agreed to tell me",++line,false);
				journal.addLine("the location of some !!treasure?? for some !!Karamja rum??.",++line,false);
				if (hasRum && !rumStashed && !employed) {
					journal.addLine("I have the !!Karamja rum??. I should take it to !!Redbeard Frank??.",++line,false);
				}
				if(!rumStashed && employed){
						journal.addLine("I have taken employment on the !!banana plantation?? as the", ++line, false);
						journal.addLine("!!customs officers?? might not notice the !!rum?? if it is covered", ++line, false);
						journal.addLine("in !!bananas??.", ++line, false);
				}
				if(rumStashed){
					journal.addLine("I have stashed some rum in the crate.",++line,true);
					journal.addLine("I should finish filling the !!crate?? and report to !!Luthas??.",++line,false);
				}
				if(rumSmuggled){
					journal.addLine("I have hidden some rum in the banana crate.",++line,true);
					journal.addLine("I should go retrieve it from !!Wydin's Shop??.",++line,false);
				}
			}
			if(stage == 20) {
				journal.addLine("I have smuggled some rum off Karamja, and retrieved it", ++line, true);
				journal.addLine("from the back room of Wydin's shop.", ++line, true);
				journal.addLine("I have given the rum to !!Redbreak Frank.?? He has told me", ++line, false);
				journal.addLine("that the !!treasure?? is hidden in the chest in the upstairs", ++line, false);
				journal.addLine("room of the !!Blue Moon Inn?? in !!Varrock??.", ++line, false);

				if (!hasNote) {
					if(!hasKey) {
						journal.addLine("I have lost the key that !!Redbeard Frank?? gave me.",++line,false);
						journal.addLine("I should see if he has another.",++line,false);
					} else {
						journal.addLine("I have a !!key?? that can be used to unlock the chest",++line,false);
						journal.addLine("that holds the treasure.",++line,false);
					}
				} else {
					if (!hasRead) {
						journal.addLine("I have opened the chest in the !!Blue Moon Inn?? and found a", ++line, false);
						journal.addLine("!!note?? inside. I think it will tell me where to dig.", ++line, false);
					}
				}
				if (hasRead) {
					journal.addLine("The note reads !!Visit the city of the White Knights. In the", ++line, false);
					journal.addLine("!!park, Saradomin points to the X which marks the spot.", ++line, false);
					if (!hasNote) {
						journal.addLine("It's a good job I remembered that, as I have lost the !!note", ++line, false);
					}
				}
			}
			if(stage == 100) {
				journal.addLine("The note reads 'Visit the city of the White Knights. In the",++line,true);
				journal.addLine("park, Saradomin points to the X which marks the spot.",++line,true);
				journal.addLine("!!QUEST COMPLETE??!",++line,false);
				journal.addLine("I've found a treasure, gained 2 Quest Points and gained",++line,false);
				journal.addLine("access to the Pay-fare option to travel to and from",++line,false);
				journal.addLine("Karamja!",++line,false);
			}
		}
	}

	@Override
	public void updateConditionals(Player player) {
		hasNote = player.getInventory().containsItem(PIRATE_MESSAGE);
		hasKey = player.getInventory().containsItem(KEY);
		hasRum = player.getInventory().containsItem(KARAMJAN_RUM);
		hasRead = player.getAttribute("pirate-read",false);
		rumStashed = player.getAttribute("stashed-rum",false);
		rumSmuggled = player.getAttribute("wydin-rum",false);
		employed = player.getAttribute("ptreasure-employed",false);
	}

	@Override
	public int getConfig(Player player) {
		int stage = player.getNeoQuestRepository().getStage("Pirate's Treasure");
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
		rewards.addLine("2 Quest Points",line++);
		rewards.addLine("One-Eyed Hector's Treasure",line++);
		rewards.addLine("Chest",line++);
		rewards.addLine("You can also use the Pay-",line++);
		rewards.addLine("fare option to go to and from",line++);
		rewards.addLine("Karamja",line++);
		rewards.setInterfaceItem(7956);
		rewards.addRewardItem(CASKET);
		rewards.draw(player);
		player.removeAttribute("gardener-attack");
		player.removeAttribute("pirate-read");
		player.removeAttribute("rum-stashed");
		player.removeAttribute("ptreasure-employed");
	}
}
