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
    MAN_AND_THIEF(new int[]{1,2,3,4,5,6,8}, 0, 0, 0, 0, 0, 0, 23, 0,
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
            }),
    FARMER(new int[]{7},0,0,0,0,27,0,11,0,
            new Item[]{new Item(ItemNames.BONES_2530)},
            new ChanceItem[]{},
            new WeightedChanceItem[]{
                    new WeightedChanceItem(ItemNames.EARTH_RUNE,4,2),
                    new WeightedChanceItem(ItemNames.FIRE_RUNE,6,2),
                    new WeightedChanceItem(ItemNames.MIND_RUNE,9,2),
                    new WeightedChanceItem(ItemNames.CHAOS_RUNE,2,1),
                    new WeightedChanceItem(ItemNames.COINS,3,38),
                    new WeightedChanceItem(ItemNames.COINS,25,1),
                    new WeightedChanceItem(ItemNames.FISHING_BAIT,1,5),
                    new WeightedChanceItem(ItemNames.RAKE_5341,1,3),
                    new WeightedChanceItem(ItemNames.EARTH_TALISMAN,1,2),
                    new WeightedChanceItem(ItemNames.GARDENING_BOOTS_5345,1,2),
                    new WeightedChanceItem(ItemNames.SEED_DIBBER_5343,1,2),
                    new WeightedChanceItem(ItemNames.SECATEURS_5329,1,1),
                    new WeightedChanceItem(ItemNames.WATERING_CAN8_5340,1,1)
            }),
    GUARD(new int[]{9,32,206,296,297,298,299,344,345,346,368},0,18,0,0,0,0,0,0,
            new Item[]{new Item(ItemNames.BONES_2530)},
            new ChanceItem[]{},
            new WeightedChanceItem[]{
                    new WeightedChanceItem(ItemNames.IRON_BOLTS_9140,2,12,10),
                    new WeightedChanceItem(ItemNames.STEEL_ARROW,1,4),
                    new WeightedChanceItem(ItemNames.BRONZE_ARROW,1,3),
                    new WeightedChanceItem(ItemNames.AIR_RUNE,6,2),
                    new WeightedChanceItem(ItemNames.EARTH_RUNE,3,2),
                    new WeightedChanceItem(ItemNames.FIRE_RUNE,2,2),
                    new WeightedChanceItem(ItemNames.BLOOD_RUNE,1,1),
                    new WeightedChanceItem(ItemNames.CHAOS_RUNE,1,1),
                    new WeightedChanceItem(ItemNames.NATURE_RUNE,1,1),
                    new WeightedChanceItem(ItemNames.STEEL_ARROW,5,1),
                    new WeightedChanceItem(ItemNames.COINS,1,19),
                    new WeightedChanceItem(ItemNames.COINS,7,16),
                    new WeightedChanceItem(ItemNames.COINS,12,9),
                    new WeightedChanceItem(ItemNames.COINS,4,8),
                    new WeightedChanceItem(ItemNames.COINS,25,4),
                    new WeightedChanceItem(ItemNames.COINS,17,4),
                    new WeightedChanceItem(ItemNames.COINS,30,2),
                    new WeightedChanceItem(ItemNames.IRON_DAGGER,1,6),
                    new WeightedChanceItem(ItemNames.BODY_TALISMAN,1, 3),
                    new WeightedChanceItem(ItemNames.GRAIN_1947,1,1),
                    new WeightedChanceItem(ItemNames.IRON_ORE,1,1)
            }),
    BARBARIAN(new int[] {12,3246,3247,3248,3249,3250,3251,3252,3253,3255,3256,3257,3258,3259,3260,3261,3262,3263,3264},0,0,0,0,0,0,0,0,
            new Item[]{new Item(ItemNames.BONES_2530)},
            new ChanceItem[]{},
            new WeightedChanceItem[]{
                    new WeightedChanceItem(ItemNames.IRON_AXE,1,6),
                    new WeightedChanceItem(ItemNames.BRONZE_BATTLEAXE,1,4),
                    new WeightedChanceItem(ItemNames.IRON_MACE,1,1),
                    new WeightedChanceItem(ItemNames.BRONZE_ARROW,10,4),
                    new WeightedChanceItem(ItemNames.CHAOS_RUNE,3,4),
                    new WeightedChanceItem(ItemNames.IRON_ARROW,8,3),
                    new WeightedChanceItem(ItemNames.EARTH_RUNE,5,3),
                    new WeightedChanceItem(ItemNames.MIND_RUNE,10,2),
                    new WeightedChanceItem(ItemNames.FIRE_RUNE,8,2),
                    new WeightedChanceItem(ItemNames.LAW_RUNE,2,1),
                    new WeightedChanceItem(ItemNames.COINS,8,42),
                    new WeightedChanceItem(ItemNames.COINS,12,9),
                    new WeightedChanceItem(ItemNames.COINS,25,5),
                    new WeightedChanceItem(ItemNames.COINS,32,3),
                    new WeightedChanceItem(ItemNames.COOKED_MEAT,1,1),
                    new WeightedChanceItem(ItemNames.AMULET_MOULD_1595,1,1),
                    new WeightedChanceItem(ItemNames.BEER,1,1),
                    new WeightedChanceItem(ItemNames.BEAR_FUR_948,1,1),
                    new WeightedChanceItem(ItemNames.FLIER_956,1,1),
                    new WeightedChanceItem(RareDropTable.SLOT_ITEM_ID,1,1)
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
