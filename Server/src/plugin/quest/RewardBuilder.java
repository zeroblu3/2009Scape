package plugin.quest;

import org.crandor.game.component.Component;
import org.crandor.game.node.entity.player.Player;
import org.crandor.game.node.item.GroundItemManager;
import org.crandor.game.node.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//used for building the rewards for a quest
//author ceikry
public class RewardBuilder {
    public List<rewardLine> lines = new ArrayList<>();
    public List<Item> itemRewards = new ArrayList<>();
    public HashMap<Integer,Double> experienceRewards = new HashMap<>();
    private int interfaceItem;
    public static final int REWARD_COMPONENT = 277;


    public void draw(Player player){
        Object[] linesArray = lines.toArray();
        int length = linesArray.length;
        for(int i = 10; i <= 18; i++){
            player.getPacketDispatch().sendString("",REWARD_COMPONENT,i);
        }
        for(int i = 0; i < length; i++){
            rewardLine line = (rewardLine) linesArray[i];
            player.getPacketDispatch().sendString(line.message,REWARD_COMPONENT,line.line);
        }
        player.getPacketDispatch().sendItemZoomOnInterface(interfaceItem, 240, 277, 5);

        if(!experienceRewards.isEmpty()){
            Object[] skills = experienceRewards.keySet().toArray();
            int num = skills.length;
            for(int i = 0; i < num; i++){
                int skill = (int) skills[i];
                player.getSkills().addExperience(skill,experienceRewards.get(skill));
            }
        }
        if(!itemRewards.isEmpty()){
            Object[] items = itemRewards.toArray();
            int num = items.length;
            for(int i = 0; i < num; i++){
                Item item = (Item) items[i];
                if(!player.getInventory().add(item)){
                    GroundItemManager.create(item,player);
                }
            }
        }
        player.getInterfaceManager().open(new Component(REWARD_COMPONENT));
    }

    public void addLine(String message, int line){
        lines.add(new rewardLine(message,line));
    }

    public void addRewardItem(Item item){
        itemRewards.add(item);
    }

    public void addRewardXP(int skill, double amount){
        experienceRewards.put(skill,amount);
    }

    public void setInterfaceItem(int id){
        interfaceItem = id;
    }

    public class rewardLine{
        public String message;
        public int line;
        public rewardLine(String message, int line){
            this.message = message;
            this.line = line;
        }
    }
}
