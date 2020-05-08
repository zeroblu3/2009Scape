package org.crandor.game.content.skill.member.slayer;

import java.util.Arrays;
import java.util.HashMap;

/**
 * A non-garbage way of representing slayer masters
 * @author ceik
 */
public enum Master2 {
    TURAEL(8273, new int[]{15, 50}, new int[]{0, 0, 0}, Tasks2.BANSHEE, Tasks2.BASILISKS, Tasks2.BATS, Tasks2.BEARS, Tasks2.BIRDS, Tasks2.CAVE_SLIMES, Tasks2.COWS, Tasks2.CRAWLING_HAND, Tasks2.DESERT_LIZARDS, Tasks2.DOG, Tasks2.DWARF, Tasks2.GHOSTS, Tasks2.GOBLINS, Tasks2.ICE_FIENDS, Tasks2.MINOTAURS, Tasks2.MONKEYS, Tasks2.RATS, Tasks2.SCORPIONS, Tasks2.SKELETONS, Tasks2.SPIDERS, Tasks2.WOLVES, Tasks2.ZOMBIES),
    MAZCHNA(8274, new int[]{30, 70}, new int[]{2, 5, 15}, Tasks2.BANSHEE, Tasks2.BASILISKS, Tasks2.BATS, Tasks2.BEARS, Tasks2.CATABLEPONS, Tasks2.CAVE_CRAWLERS, Tasks2.CAVE_SLIMES, Tasks2.COCKATRICES, Tasks2.CRAWLING_HAND, Tasks2.CYCLOPES, Tasks2.DESERT_LIZARDS, Tasks2.DOG, Tasks2.FLESH_CRAWLERS, Tasks2.GHOSTS, Tasks2.GHOULS, Tasks2.HILL_GIANTS, Tasks2.HOBGOBLINS, Tasks2.ICE_WARRIOR, Tasks2.INFERNAL_MAGES, Tasks2.KALPHITES, Tasks2.PYREFIENDS, Tasks2.ROCK_SLUGS, Tasks2.SKELETONS, Tasks2.VAMPIRES, Tasks2.WATERFIENDS, Tasks2.WOLVES, Tasks2.ZOMBIES),
    VANNAKA(1597, new int[]{30, 80}, new int[]{4, 20, 60}, Tasks2.ABERRANT_SPECTRES, Tasks2.ANKOU, Tasks2.BANSHEE, Tasks2.BASILISKS, Tasks2.BLOODVELDS, Tasks2.BRINE_RATS, Tasks2.COCKATRICES, Tasks2.CROCODILES, Tasks2.DAGANNOTHS, Tasks2.DUST_DEVILS, Tasks2.EARTH_WARRIORS, Tasks2.GREEN_DRAGON, Tasks2.HARPIE_BUG_SWARMS, Tasks2.HILL_GIANTS, Tasks2.ICE_GIANTS, Tasks2.ICE_WARRIOR, Tasks2.INFERNAL_MAGES, Tasks2.JELLIES, Tasks2.JUNGLE_HORRORS, Tasks2.LESSER_DEMONS, Tasks2.MOSS_GIANTS, Tasks2.OGRES, Tasks2.OTHERWORDLY_BEING, Tasks2.PYREFIENDS, Tasks2.SHADE, Tasks2.VAMPIRES, Tasks2.WEREWOLFS),
    CHAELDAR(1598, new int[]{110, 170}, new int[]{10, 50, 150}, Tasks2.ABERRANT_SPECTRES, Tasks2.ABYSSAL_DEMONS, Tasks2.BANSHEE, Tasks2.BASILISKS, Tasks2.BLOODVELDS, Tasks2.BLUE_DRAGONS, Tasks2.BRINE_RATS, Tasks2.BRONZE_DRAGONS, Tasks2.CAVE_CRAWLERS, Tasks2.CAVE_HORRORS, Tasks2.CRAWLING_HAND, Tasks2.DUST_DEVILS, Tasks2.FIRE_GIANTS, Tasks2.GARGOYLES, Tasks2.GHOULS, Tasks2.GREATER_DEMONS, Tasks2.DAGANNOTHS, Tasks2.DUST_DEVILS, Tasks2.FIRE_GIANTS, Tasks2.HARPIE_BUG_SWARMS, Tasks2.INFERNAL_MAGES, Tasks2.JELLIES, Tasks2.JUNGLE_HORRORS, Tasks2.KALPHITES, Tasks2.KURASKS, Tasks2.LESSER_DEMONS, Tasks2.TROLLS, Tasks2.TUROTHS),
    DURADEL(8275, new int[]{50, 199}, new int[]{15, 75, 225}, Tasks2.ABERRANT_SPECTRES, Tasks2.ABYSSAL_DEMONS, Tasks2.BLACK_DEMONS, Tasks2.BLACK_DRAGONS, Tasks2.BLOODVELDS, Tasks2.DAGANNOTHS, Tasks2.DARK_BEASTS, Tasks2.DUST_DEVILS, Tasks2.FIRE_GIANTS, Tasks2.GARGOYLES, Tasks2.GORAKS, Tasks2.GREATER_DEMONS, Tasks2.HELLHOUNDS, Tasks2.IRON_DRAGONS, Tasks2.KALPHITES, Tasks2.MITHRIL_DRAGON, Tasks2.NECHRYAELS, Tasks2.SKELETAL_WYVERN, Tasks2.SPIRTUAL_MAGES, Tasks2.STEEL_DRAGONS, Tasks2.WATERFIENDS);


    private static HashMap<Integer,Master2> idMap = new HashMap<>();

    static{
        Arrays.stream(Master2.values()).forEach(m -> idMap.putIfAbsent(m.npc_id, m));
    }

    int npc_id;
    int[] assignment_range;
    int[] streakPoints;
    Tasks2[] tasks;

    Master2(int npc_id, int[] assignment_range, int[] streakPoints, Tasks2... tasks) {
        this.npc_id = npc_id;
        this.assignment_range = assignment_range;
        this.streakPoints = streakPoints;
        this.tasks = tasks;
    }

    public Master2 forId(int id){
        return idMap.get(id);
    }
}