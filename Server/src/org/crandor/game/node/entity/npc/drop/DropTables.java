package org.crandor.game.node.entity.npc.drop;

import org.crandor.game.content.ItemNames;
import org.crandor.game.node.item.ChanceItem;
import org.crandor.game.node.item.Item;
import org.crandor.game.node.item.WeightedChanceItem;
import org.crandor.tools.RandomFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public enum DropTables {
    //FORMAT NPC(int[] ids, int.... shared table weights, new Item[] always drops, new ChanceItem[] charms, new WeightedChanceItem[] main drop table)
    MAN(new int[]{1,2,3,4,5,6}, 0, 0, 0, 0, 0, 0, 23, 0,
            new Item[]{new Item(ItemNames.BONES_2530)}, //always drops
            new ChanceItem[]{}, // charms
            new WeightedChanceItem[]{ //main drop table v
                    new WeightedChanceItem(ItemNames.BRONZE_MED_HELM,1,2),
                    new WeightedChanceItem(ItemNames.IRON_DAGGER,1,1),
                    new WeightedChanceItem(ItemNames.BRONZE_BOLTS,2,12,22),
                    new WeightedChanceItem(ItemNames.BRONZE_ARROW,7,3),
                    new WeightedChanceItem(ItemNames.EARTH_RUNE,4,2),
                    new WeightedChanceItem(ItemNames.FIRE_RUNE,6,2),
                    new WeightedChanceItem(ItemNames.MIND_RUNE,9,2),
                    new WeightedChanceItem(ItemNames.CHAOS_RUNE,2,1),
                    new WeightedChanceItem(ItemNames.COINS,3,38),
                    new WeightedChanceItem(ItemNames.COINS,5,9),
                    new WeightedChanceItem(ItemNames.COINS,15,4),
                    new WeightedChanceItem(ItemNames.COINS,25,1),
                    new WeightedChanceItem(ItemNames.FISHING_BAIT,1,5),
                    new WeightedChanceItem(ItemNames.COPPER_ORE,1,2),
                    new WeightedChanceItem(ItemNames.EARTH_TALISMAN,1,2),
                    new WeightedChanceItem(ItemNames.CABBAGE,1,1)
            });

    //map our npc ids to their drop table
    public static HashMap<Integer,DropTables> dropTableMap = new HashMap<>();
    static{
        for(DropTables table : DropTables.values()){
            for(int npc_id : table.ids){
                dropTableMap.putIfAbsent(npc_id,table);
            }
        }
    }

    public int[] ids;
    public WeightedChanceItem[] table;
    public Item[] always;
    public ChanceItem[] charms;
    int[] sharedTableWeights;
    DropTables(int[] ids, int commonSeed, int fixedAllot, int rareSeed, int treeHerb, int varAllot, int hops, int herbs, int usefulHerbs, Item[] always, ChanceItem[] charms, WeightedChanceItem[] table){
        this.ids = ids;
        this.table = table;
        this.sharedTableWeights = new int[] {commonSeed,fixedAllot,rareSeed,treeHerb,varAllot,hops,herbs,usefulHerbs};
        this.always = always;
        this.charms = charms;
    }

    public static DropTables forId(int id){
        return dropTableMap.get(id);
    }

    public List<Item> getDrops(){
        //roll all of our shared drop tables that this npc can roll on.
        List<WeightedChanceItem> sharedTablesRolls = new ArrayList<>();
        for(int i = 0; i < sharedTableWeights.length; i++){
            if(sharedTableWeights[i] == 0){
                continue;
            }
            //roll on the table and add a WeightedChanceItem with the table's weight to the list
            Item temp = RandomFunction.rollWeightedChanceTable(SharedTables.values()[i].table);
            sharedTablesRolls.add(new WeightedChanceItem(temp.getId(),temp.getAmount(),sharedTableWeights[i]));
        }
        //make a copy of our table and add shared table rolls with their weights into it
        List<WeightedChanceItem> tempCopy = new ArrayList<>(Arrays.asList(table));
        tempCopy.addAll(sharedTablesRolls);

        //build our rewards list
        List<Item> rewards = new ArrayList<>();
        rewards.addAll(Arrays.asList(always));
        rewards.add(RandomFunction.rollWeightedChanceTable(tempCopy));
        rewards.addAll(RandomFunction.rollChanceTable(false,charms));

        tempCopy.clear();
        return rewards;
    }
}
