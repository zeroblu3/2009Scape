package org.crandor.game.node.entity.player.link.quest;

import org.crandor.game.node.entity.player.Player;
import org.crandor.plugin.Plugin;
import plugin.quest.RewardBuilder;


public class NeoQuest implements Plugin<Object> {

    public String name;
    public int id,buttonId,points;
    public int configs;
    public boolean members = false;
    public boolean[] stageConditions;
    public JournalBuilder journal;
    public RewardBuilder rewards;

    public NeoQuest(int id, int buttonId, String name, int points, int configs){
        this.id = id;
        this.buttonId = buttonId;
        this.name = name;
        this.points = points;
        this.configs = configs;
        this.journal = new JournalBuilder();
        this.rewards = new RewardBuilder();
    }

    public void complete(){

    }

    public void setLines(Player player){
        player.getInterfaceManager().close();
        updateConditionals(player);
        journal.lines.clear();
        journal.addLine("!!"+name+"??",2,false);
    }

    public void updateConditionals(Player player){

    }

    public int getConfig(Player player){
        return 0;
    }

    @Override
    public Plugin<Object> newInstance(Object arg) throws Throwable {
        return null;
    }

    @Override
    public Object fireEvent(String identifier, Object... args) {
        return null;
    }

    public void finish(Player player){
        player.getNeoQuestRepository().addPoints(points);
        rewards.addLine("" + player.getNeoQuestRepository().getPoints() + "", 7);
        rewards.addLine("You have completed the " + name + " Quest!",  4);
        player.getPacketDispatch().sendMessage("Congratulations! Quest complete!");
        player.getPacketDispatch().sendTempMusic(152);
    }
}
