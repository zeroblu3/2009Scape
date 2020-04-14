package org.crandor.game.node.entity.player.link.quest;

import org.crandor.game.node.entity.player.Player;
import java.util.HashMap;

public class NeoQuestRepository {
    public Player player;
    public int points = 32;
    public static int maxPoints;
    public static HashMap<Integer,NeoQuest> buttonMap = new HashMap<>();
    public static HashMap<String,NeoQuest> nameMap = new HashMap<>();
    public HashMap<String,Integer> stageMap = new HashMap<>();

    public void start(String name){
        stageMap.putIfAbsent(name,10);
        syncTab();
    }

    public void finish(String name){
        nameMap.get(name).finish(player);
        stageMap.replace(name,100);
        syncTab();
    }

    public boolean hasStarted(String name){
        if(stageMap.get(name) == null){
            return false;
        }
        return stageMap.get(name) > 0;
    }

    public boolean hasFinished(String name){
        if(stageMap.get(name) == null){
            return false;
        }
        return stageMap.get(name) == 100;
    }

    public static void register(int buttonId, NeoQuest quest){
        buttonMap.putIfAbsent(buttonId,quest);
        nameMap.putIfAbsent(quest.name,quest);
        maxPoints += quest.points;
    }

    public void syncTab(){
        player.getConfigManager().set(101,points);
        Object[] quests = nameMap.values().toArray();
        int questsNum = quests.length;
        for(int i = 0; i < questsNum; i++){
            NeoQuest quest = (NeoQuest) quests[i];
            player.getConfigManager().set(quest.id,quest.getConfig(player));
        }
    }

    public NeoQuestRepository(Player player){
        this.player = player;
    }

    public NeoQuest forButton(int id){
        return buttonMap.get(id);
    }

    public NeoQuest getQuest(String name){
        return nameMap.get(name);
    }

    public int getStage(String name){
        return stageMap.get(name) == null ? 0 : stageMap.get(name);
    }

    public void setStage(String name, int stage){
        stageMap.replace(name,stage);
    }

    public boolean hasMaxPoints() { return points == maxPoints;}

    public void addPoints(int points){
        this.points += points;
    }

    public int getPoints(){
        return points;
    }
}
