package org.crandor.game.content.skill.free.gather.woodcutting;

import org.crandor.ServerConstants;
import org.crandor.game.world.repository.Repository;

import java.util.HashMap;

/**
 * Woodcutting nodes
 * @author ceik
 */
public enum WoodcuttingNode {
    //standard trees
    STANDARD(new int[]{1276,1277,1278,1279,1280,1330,1331,1332,2409,3033,3034,3035,3036,3879,3881,3882,3883,10041,14308,14309,16264,16265,30132,30133,37477,37478,37652},
             new int[]{1342,1343,1342,1345,1343,1341,1341,1341,1342,1345,1345,1347,1351,3880,3880,3880,3884,1342,1342,1342,1342,1342,1342,1342,1342,37653,37653}),

    DEAD    (new int[]{1282,1283,1284,1285,1286,1289,1290,1291,1365,1383,1384,5902,5903,5904,32294,37841,37842,37843,24168},
             new int[]{1347,1347,1348,1349,1351,1353,1354,23054,1352,1358,1359,1347,1353,1353,1353,1347,1351,1358,24169}),

    EVERGREEN(new int[]{1315,1316,1318,1319},
              new int[]{1342,1355,1355,1355}),

    JUNGLE_TREE(new int[]{2887,2889,2890,4818,4820},
                new int[]{0,0,0,0,0}),

    JUNGLE_BUSH(new int[]{2892,2893},
                new int[]{2894,2895}),

    ACHEY(new int[]{2023},
          new int[]{3371}),

    OAK(new int[]{1281,3037,37479},
        new int[]{1356,1357,1356}),

    OAK_FARMING(new int[]{8467},
                new int[]{1356}),

    WILLOW(new int[]{1308,5551,5552,5553,37480},
           new int[]{7399,5554,5554,5554,7399}),

    WILLOW_FARMING(new int[]{8488},
                   new int[]{7399}),


    TEAK(new int[]{9036,15062},
         new int[]{9037,9037}),

    MAPLE(new int[]{1307,4674},
          new int[]{7400,7400}),

    MAPLE_FARMING(new int[]{8444}, new int[]{7400}),

    HOLLOW(new int[]{2289,4060},
           new int[]{2310,4061}),

    MAHOGANY(new int[]{9034}, new int[]{9035}),


    //Arctic pine
    ARCTIC_PINE(new int[]{21273},new int[]{21274}),

    EUCALYPTUS(new int[]{28951,28952,28953},
               new int[]{28954,28955,28956}),

    YEW(new int[]{1309,8513},
        new int[]{7402,7402}),

    YEW_FARMING(new int[]{8513},new int[]{7402}),

    MAGIC(new int[]{1306,37823,8409},
          new int[]{7401,37824,37824}),

    MAGIC_FARMING(new int[]{8409},new int[]{37824}),

    CURSED_MAGIC_TREE(new int[]{37821},new int[]{37822}),

    DRAMEN_TREE(new int[]{1292}, new int[]{-1});



    int reward,respawnRate,level,rewardAmount;
    int[] full;
    int[] empty;
    double experience,rate;
    boolean farming;
    WoodcuttingNode(int[] full, int[] empty){
        this.full = full;
        this.empty = empty;
        this.rewardAmount = 1;
        this.farming = false;
        switch(ordinal()){
            case 0:
            case 1:
            case 2:
            case 3:
                reward = 1511;
                respawnRate = 50 | 100 << 16;
                rate = 0.05;
                experience = 25.0;
                level = 1;
                break;
            case 4:
                reward = 1511;
                respawnRate = 50 | 100 << 16;
                rate = 0.15;
                experience = 100;
                level = 1;
                this.rewardAmount = 2;
                break;
            case 5:
                reward = 2862;
                respawnRate = 50 | 100 << 16;
                rate = 0.05;
                experience = 25.0;
                level = 1;
                break;
            case 6:
                reward = 1521;
                respawnRate = 14 | 22 << 16;
                rate = 0.15;
                experience = 37.5;
                level = 15;
                rewardAmount = 10;
                break;
            case 7:
                reward = 1521;
                respawnRate = 14 | 22 << 16;
                rate = 0.15;
                experience = 37.5;
                level = 15;
                rewardAmount = 10;
                farming = true;
                break;
            case 8:
                reward = 1519;
                respawnRate = 14 | 22 << 16;
                rate = 0.3;
                experience = 67.8;
                level = 30;
                rewardAmount = 20;
                break;
            case 9:
                reward = 1519;
                respawnRate = 14 | 22 << 16;
                rate = 0.3;
                experience = 67.8;
                level = 30;
                rewardAmount = 20;
                farming = true;
                break;
            case 10:
                reward = 6333;
                respawnRate = 35 | 60 << 16;
                rate = 0.7;
                experience = 85.0;
                level = 35;
                rewardAmount = 25;
                break;
            case 11:
                reward = 1517;
                respawnRate = 58 | 100 << 16;
                rate = 0.65;
                experience = 100.0;
                level = 45;
                rewardAmount = 30;
                break;
            case 12:
                reward = 1517;
                respawnRate = 58 | 100 << 16;
                rate = 0.65;
                experience = 100.0;
                level = 45;
                rewardAmount = 30;
                farming = true;
                break;
            case 13:
                reward = 3239;
                respawnRate = 58 | 100 << 16;
                rate = 0.6;
                experience = 82.5;
                level = 45;
                rewardAmount = 30;
                break;
            case 14:
                reward = 6332;
                respawnRate = 62 | 115 << 16;
                rate = 0.7;
                experience = 125.0;
                level = 50;
                rewardAmount = 35;
                break;
            case 15:
                reward = 10810;
                respawnRate = 75 | 130 << 16;
                rate = 0.73;
                experience = 140.2;
                level = 54;
                rewardAmount = 35;
                break;
            case 16:
                reward = 12581;
                respawnRate = 80 | 140 << 16;
                rate = 0.77;
                experience = 165.0;
                level = 58;
                rewardAmount = 35;
                break;
            case 17:
                reward = 1515;
                respawnRate = 100 | 162 << 16;
                rate = 0.8;
                experience = 175.0;
                level = 60;
                rewardAmount = 40;
                break;
            case 18:
                reward = 1515;
                respawnRate = 100 | 162 << 16;
                rate = 0.8;
                experience = 175.0;
                level = 60;
                rewardAmount = 40;
                farming = true;
                break;
            case 19:
                reward = 1513;
                respawnRate = 200 | 317 << 16;
                rate = 0.9;
                experience = 250.0;
                level = 75;
                rewardAmount = 50;
                break;
            case 20:
                reward = 1513;
                respawnRate = 200 | 317 << 16;
                rate = 0.9;
                experience = 250.0;
                level = 75;
                rewardAmount = 50;
                farming = true;
                break;
            case 21:
                reward = 1513;
                respawnRate = 200 | 317 << 16;
                rate = 0.95;
                experience = 275.0;
                level = 82;
                rewardAmount = 50;
                break;
            case 22:
                reward = 771;
                respawnRate = -1;
                rate = 0.05;
                experience = 25.0;
                level = 1;
                rewardAmount = Integer.MAX_VALUE;
                break;
        }

    }
    private static HashMap<Integer, WoodcuttingNode> NODE_MAP = new HashMap<>();
    private static HashMap<Integer, Integer> EMPTY_MAP = new HashMap<>();
    static{
        WoodcuttingNode[] nodeArray = values();
        int length = nodeArray.length;
        for(int i = 0; i < length; i++){
            WoodcuttingNode node = nodeArray[i];
            int childLength = node.full.length;
            for(int j = 0; j < childLength; j++) {
                try {
                    NODE_MAP.putIfAbsent(node.full[j], node);
                    EMPTY_MAP.putIfAbsent(node.empty[j], node.full[j]);
                } catch(Exception e){
                    System.out.println("Error at ordinal " + node.ordinal());
                    System.out.println("Empty length: " + node.empty.length + " Full length: " + node.full.length);
                }
            }
        }
    }

    public static WoodcuttingNode forId(int id){
        return NODE_MAP.get(id);
    }

    public static boolean isEmpty(int id){
        return EMPTY_MAP.get(id) != null;
    }

    public int getRewardAmount() {
        return rewardAmount;
    }

    public int getEmptyId(int i){
        return EMPTY_MAP.get(i);
    }

    public int getReward() {
        return reward;
    }

    public double getExperience() {
        return experience;
    }

    public int getRespawnRate() {
        return respawnRate;
    }

    public double getRate() {
        return rate;
    }

    public int getLevel() {
        return level;
    }

    public int getMinimumRespawn() {
        return respawnRate & 0xFFFF;
    }

    public int getMaximumRespawn() {
        return (respawnRate >> 16) & 0xFFFF;
    }

    public boolean isFarming(){ return farming;}

    public int getRespawnDuration() {
        int minimum = respawnRate & 0xFFFF;
        int maximum = (respawnRate >> 16) & 0xFFFF;
        double playerRatio = ServerConstants.MAX_PLAYERS / Repository.getPlayers().size();
        return (int) (minimum + ((maximum - minimum) / playerRatio));
    }
}
