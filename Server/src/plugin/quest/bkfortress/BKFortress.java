package plugin.quest.bkfortress;

import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.entity.player.link.quest.NeoQuest;
import org.crandor.game.node.entity.player.link.quest.NeoQuestRepository;
import org.crandor.game.node.item.Item;
import org.crandor.plugin.InitializablePlugin;
import org.crandor.plugin.Plugin;
import org.crandor.plugin.PluginManager;

/**
 * Black Knights Fortress quest
 * @author ceik
 */
@InitializablePlugin
public class BKFortress extends NeoQuest {
    public static final Item DOSSIER = new Item(9589);
    public BKFortress(){super(130,13,"Black Knights' Fortress",3,4);}

    public String name = "Black Knights' Fortress";
    public boolean hasRequiredQP;

    @Override
    public void setLines(Player player) {
        super.setLines(player);
        int stage = player.getNeoQuestRepository().getStage("Black Knights' Fortress");
        boolean started = stage > 0;
        int line = 10;
        if(!started){
            journal.addLine("I can start this quest by speaking to !!Sir Amik Varze?? at the",++line,false);
            journal.addLine("!!White Knights' Castle?? in !!Falador.??",++line,false);
            journal.addLine("I need a total of at least 12 Quest Points.",++line,hasRequiredQP);
            journal.addLine("I would have an advantage if I could fight !!Level 33 knights??",++line,false);
            journal.addLine("and if I had a !!smithing?? level of !!26??",++line,false);
        } else {
            journal.addLine("!!Sir Amik Varze?? has asked me to investigate the Black Knights'",++line,stage > 10);

            if(stage == 10){//having to adapt to the retarded way Vexia wrote the quest :I
                journal.addLine("Fortress which is located on !!Ice Mountain??.",++line,false);
                journal.addLine("I need to disguise myself to gain entry to the !!Black??",++line,false);
                journal.addLine("!!Knights' Fortress.??",++line,false);
            } else {
                journal.addLine("Fortress. I sneaked inside disguised as a guard.",++line,true);
            }

            if(stage >= 20) {
                journal.addLine("I eavesdropped on a witch and the Black Knight Captain",++line,stage >= 30);
                journal.addLine("and discovered that their invincibility potion can be",++line,stage >= 30);
                journal.addLine("destroyed with a normal !!cabbage.??",++line,stage >= 30);
            }

            if(stage >= 30){
                journal.addSwitchingLine("Now that I have sabotaged the witch's potion, I can claim",
                                         "I found a cabbage, and used it to destroy the potion, then",
                                          ++line,stage >= 100,stage >= 100);

                journal.addSwitchingLine("my !!reward?? from !!Sir Amik Varze?? in !!Falador castle??.",
                                         "claimed my reward for a job well done",
                                         ++line, stage >= 100, stage >= 100);
            }

            if(stage >= 100){
                journal.addLine("!!QUEST COMPLETE!??",++line,false);
                journal.addLine("Reward:",++line,false);
                journal.addLine("3 Quest Points",++line,false);
                journal.addLine("2500 gp",++line,false);
            }

        }
    }

    @Override
    public void finish(Player player) {
        super.finish(player);
        int line = 10;
        rewards.addLine("3 Quest Points",line++);
        rewards.addLine("2500 coins",line++);
        rewards.addRewardItem(new Item(995,2500));
        rewards.setInterfaceItem(9591);
        rewards.draw(player);
    }

    @Override
    public void updateConditionals(Player player) {
        hasRequiredQP = player.getNeoQuestRepository().getPoints() >= 12;
    }

    @Override
    public int getConfig(Player player) {
        int stage = player.getNeoQuestRepository().getStage("Black Knights' Fortress");
        if(stage >= 100){
            return configs;
        }
        if(stage > 0){
            return 1;
        }
        return 0;
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        NeoQuestRepository.register(13,this);
        PluginManager.definePlugin(new BKCabbagePlugin(), new BKFortressPlugin(), new SirAmikVarzeDialogue());
        return this;
    }
}
