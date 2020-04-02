package plugin.activity.pyramidplunder;

import org.crandor.game.node.entity.player.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Manages what objects a specific player has interacted with in pyramid plunder.
 * @author ceik
 */

public class PlunderObjectManager{
    Player player;
    public PlunderObjectManager(Player player){
        this.player = player;
    }
    public final List<PlunderObject> objectList = new ArrayList<PlunderObject>();
    int originalIndex;
    public boolean resetObjectsFor(Player player){
        //Completely clear the list
    	objectList.clear();
        /*ListIterator oliter = ObjectList.listIterator();
        int i = 0;
        while(oliter.hasNext()){
            PlunderObject current = (PlunderObject) oliter.next();
            if(current.playerOpened){
                current.playerOpened = false;
            }
            if(current.snakeCharmed){
                current.snakeCharmed = false;
            }
        }*/
        return true;
    }

    public void register(PlunderObject object){
    	objectList.add(object);
    }
}
