package core.game.node.entity.player.link.diary;

import core.game.content.ItemNames;
import core.game.node.item.Item;

/**
 * An achievement diary type.
 *
 * @author Vexia
 */
public enum DiaryType {
    KARAMJA("Karamja", 11,
            new String[]{"Easy", "Medium", "Hard"},
            new String[][]{
                    {
                            "Pick 5 bananas from the plantation located east of the <br><br>volcano.",
                            "Use the rope swing to travel to the small island north-west<br><br>of Karamja, where the moss giants are.",
                            "Mine some gold from the rocks on the north-west<br><br>peninsula of Karamja.",
                            "Travel to Port Sarim via the dock, east of Musa Point.",
                            "Travel to Ardougne via the port near Brimhaven.",
                            "Explore Cairn Island to the west of Karamja.",
                            "Use the Fishing spots north of the banana plantation.",
                            "Collect 5 seaweed from anywhere on Karamja.",
                            "Attempt the TzHaar Fight Pits or Fight Cave.",
                            "Kill a Jogre in the Pothole dungeon."
                    },
                    {
                            "Claim a ticket from the Agility arena in Brimhaven.",
                            "Discover hidden wall in the dungeon below the volcano.",
                            "Visit the Isle of Crandor via the dungeon below the volcano.",
                            "Use Vigroy and Hajedy's cart service.",
                            "Cut a log from a teak tree.", "Cut a log from a mahogany tree.",
                            "Use the gnome glider to travel to Karamja.",
                            "Grow a healthy fruit tree in the patch near Brimhaven.",
                            "Chop the vines to gain deeper access to Brimhaven Dungeon.",
                            "Cross the lava using the stepping stones within Brimhaven Dungeon.",
                            "Climb the stairs within Brimhaven Dungeon.",
                            "Mine a red topaz from a gem rock."
                    },
                    {
                            "Become the Champion of the Fight Pits.",
                            "Successfully kill a Ket-Zek in the Fight Caves.",
                            "Craft some nature runes.",
                            "Be assigned a Slayer task by Duradel in Shilo Village.",
                            "Kill a metal dragon in Brimhaven Dungeon."
                    }
            },
            new Item[][]{
                    {new Item(ItemNames.KARAMJA_GLOVES_1_11136), new Item(ItemNames.ANTIQUE_LAMP_11137)},
                    {new Item(ItemNames.KARAMJA_GLOVES_2_11138), new Item(ItemNames.ANTIQUE_LAMP_11139)},
                    {new Item(ItemNames.KARAMJA_GLOVES_3_11140), new Item(ItemNames.ANTIQUE_LAMP_11141)}
            },
            "To start marking off tasks in your journal, speak to Pirate<br><br>Jackie the Fruit in Brimhaven, Kaleb Paramay in Shilo<br><br>Village or one of the Jungle Foresters north of the<br><br>Kharazi Jungle.",
            new int[]{1055, 512, 401}
    ),
    VARROCK("Varrock", 15,
            new String[]{"Easy", "Medium", "Hard"},
            new String[][]{
                    {
                            "Have Thessalia show you what outfits you can wear.",
                            "Have Aubury teleport you to the essence mine.",
                            "Mine some Iron in the south east mining patch near Varrock.",
                            "Make a plank at the sawmill.",
                            "Enter the second level of the Stronghold of Security.",
                            "Jump over the fence south of Varrock.",
                            "Chop down a dying tree in the Lumber Yard.",
                            "Buy a newspaper.", "Give a dog a bone!",
                            "Spin a bowl on the pottery wheel and fire it in the oven in <br><br>Barbarian Village.",
                            "Craft some Earth runes.",
                            "Catch some trout in the river Lum at Barbarian Village.",
                            "Steal from the Tea stall in Varrock."
                    },
                    {
                            "Have the Apothecary in Varrock make you a strength potion.",
                            "Enter the Champions Guild.",
                            "Use the spirit tree north of Varrock",
                            "Perform the 4 emotes from the Stronghold of Security.",
                            "Cast the teleport to Varrock spell.",
                            "Get a Slayer task from Vannaka",
                            "Make 20 Mahogany Planks in one go."
                    },
                    {
                            "Make a Waka Canoe near Edgeville.",
                            "Teleport to Barbarian Village with a skull sceptre.",
                            "Chop some yew logs in Varrock and burn them at the top of<br><br> the Varrock church.",
                            "Collect at least 2 yew roots from the Tree patch in Varrock Palace.",
                            "Pray at the altar in Varrock palace with Smite active.",
                            "Squeeze through the obstacle pipe in Edgeville dungeon."
                    }
            },
            new Item[][]{
                    {new Item(11756), new Item(11185)},
                    {new Item(11757), new Item(11186)},
                    {new Item(11758), new Item(11187)}
            },
            "",
            new int[]{5833, 2660, 1597}
    ),
    LUMBRIDGE("Lumbridge", 2,
            new String[]{"Beginner", "Easy", "Medium"},
            new String[][]{
                    {
                            "Slay a cave bug in the Lumbridge Swamp Caves",
                            "Have Sedridor teleport you to the Rune essence mine",
                            "Craft some water runes",
                            "Pickpocket a man or woman in Lumbridge",
                            "Chop and burn some oak logs in Lumbridge", // TODO make this require actually chopping the logs in Lumbridge
                            "Kill a zombie in the Draynor Sewers (level 13 only)",
                            "Catch some anchovies in Al-Kharid",
                            "Bake some bread on the Lumbridge kitchen range",
                            "Mine some iron ore at the Al-Kharid mine",
                            "Enter the H.A.M. Hideout"
                    },
                    {
                            "Purchase an upgraded device from Ava",
                            "Travel to the Wizards' Tower by fairy ring",
                            "Cast the Lumbridge Teleport spell",
                            "Catch some salmon in Lumbridge",
                            "Craft a coif in the Lumbridge cow pen",
                            "Chop some willow logs in Draynor Village",
                            "Pickpocket Martin the Master Gardener",
                            "Get a slayer task from Chaeldar",
                            "Catch an essence or electic impling in Puro-Puro",
                            "Craft some lava runes at the Fire altar in Al Kharid"
                    },
                    {
                            "Smith a mithril platebody on the anvil in the Draynor Sewers",
                            "Craft 56 cosmic runes simultaneously",
                            "Travel from Lumbridge to Edgeville on a waka canoe",
                            "Purchase some barrows gloves from the Culinaromancer's Chest",
                            "Pick some belladonna from the farming patch at Draynor manor"
                    }
            },
            new Item[][]{
                    {new Item(13560), new Item(11185)},
                    {new Item(13561), new Item(11186)},
                    {new Item(13562), new Item(11187)}},
            "",
            new int[]{7969, 519, 743}
    ),
    // https://runescape.wiki/w/Falador_achievements?oldid=900390
    FALADOR("Falador", 23,
            new String[]{"Easy", "Medium", "Hard"},
            new String[][]{
                    {
                            "Buy a Farming amulet from Sarah on the farm north of Port Sarim.",
                            "Buy a stat-boosting beer from a waitress in the Rising Sun Tavern.",
                            "Buy a black chainbody from Wayne's Chains, and<br><br>try it on in the shop.",
                            "Climb to the top of the White Knights' Castle.", // TODO
                            "Discover your family crest from Sir Renitee.", // TODO
                            "Enter the mole's lair under Falador Park.", // TODO
                            "Feed Ridgeley, the hairdresser's pet.",
                            "Fill a bucket from the pump north of the west Falador bank.",
                            "Heal an elemental wizard by casting an appropriate<br><br>elemental spell on him (Air, Water, Earth, Fire).",
                            "Kill a duck in Falador Park.",
                            "Kill a highwayman on the road south of Falador.",
                            "Make an air tiara.",
                            "Pop a party balloon.", // TODO
                            "Recharge your Prayer points at the altar south-west of Port Sarim.",
                            "Take the boat to Entrana."
                    },
                    {
                            "Craft a Fruit basket using the loom at the farm north of Port Sarim.",
                            "Crawl under Falador's south wall.",
                            "Grapple up, and then jump off the north Falador wall.",
                            "Increase your reputation with the White Knights<br><br>by killing a black knight.",
                            "Kill an ice giant in the Asgarnian Ice Dungeon.",
                            "Light a bullseye lantern in the chemist's.",
                            "Pickpocket a Falador guard.",
                            "Place a scarecrow to protect your sweetcorn as it<br><br>grows in the patch north of Port Sarim.",  // TODO make this actually rely on corn
                            "Salute Sir Tiffy Cashien while wearing full initiate armour.",
                            "Smith blurite crossbow limbs on Thurgo's anvil.",
                            "Travel from Port Sarim to Musa Point for<br><br>free (with a little help from Charos).",
                            "Visit the Port Sarim rat pits." // TODO
                    },
                    {
                            "Ascend the Dark Wizards' Tower while wearing full proselyte armour.", // TODO
                            "Change your family crest to the Saradomin symbol.", // TODO
                            "Craft 196 or more air runes simultaneously.", // TODO
                            "Cut down a Yew tree or Magic tree that you grew in Falador Park.", // TODO
                            "Dial to the fairy ring on Mudskipper Point.", // TODO
                            "Dye a cape pink with Pink dye from Betty in Port Sarim.", // TODO
                            "Enter the Mining Guild.", // TODO
                            "Kill a mogre at Mudskipper Point.", // TODO
                            "Kill a skeletal wyvern in the Asgarnian Ice Dungeon.", // TODO
                            "Summon an Ibis in the Port Sarim fish store." // TODO
                    }
            },
            // Falador Shields 1-3
            // 1k XP, 5k XP, 10k XP
            new Item[][]{
                    {new Item(14577), new Item(14580)},
                    {new Item(14578), new Item(14581)},
                    {new Item(14579), new Item(14582)}
            },
            "",
            // Redbeard Frank, Chemist, Squire
            new int[]{375, 367, 606}
    ),
    // https://runescape.wiki/w/Fremennik_achievements?oldid=877418
    FREMENNIK("Fremennik", 19,
            new String[]{"Easy", "Medium", "Hard"},
            new String[][]{
                    {
                            "Kill a cave crawler in the Fremennik Slayer Dungeon", // TODO
                            "Kill five rock crabs on the shore near Rellekka or on Waterbirth Island", // TODO
                            "Find the highest tree on the Fremennik mainland", // TODO
                            "View the rewards in the Barbarian Assault tutorial", // TODO
                            "Speak to Otto Godblessed about barbarian training", // TODO
                            "Collect three seaweed from the shore north-east of Rellekka", // TODO
                            "Find the Hunting Expert on the northern ice plains", // TODO
                            "Catch a fish off one of Rellekka's piers", // TODO
                            "Recharge your Summoning points near Rellekka's gate", // TODO
                            "Kill an adult black unicorn" // TODO
                    },
                    {
                            "Learn of the history of the Fremennik and the Outerlanders<br><br>from Chieftain Brundt", // TODO
                            "Watch a shouting match between Fremennik Isles tower guards<br><br>(the guards can be found between Jatizso and Neitiznot in one of the towers)", // TODO
                            "Interact with a Pet rock", // TODO
                            "Make three vials in the furnace building at Rellekka", // TODO
                            "Charm the Fossegrimen into accepting a raw bass", // TODO
                            "Wear yak-hide armour and kill an ice troll", // TODO
                            "Make cheese in the dairy churn in Rellekka", // TODO
                            "Use a fairy ring to appear on a mountaintop, near the windswept tree", // TODO
                            "Look at Yrsa's options for recolouring your boots in her<br><br>clothes shop in Rellekka", // TODO
                            "Successfully hunt a sabre-toothed kyatt", // TODO
                            "Steal a fish from the fishing stall in the Rellekka marketplace" // TODO
                    },
                    {
                            "Kill three dagannoths in the first layer of the<br><br>Waterbirth Island Dungeon", // TODO
                            "Wear rockshell, spined or skeletal armour and have the<br><br>locals use an honorific with your Fremennik name", // TODO
                            "Complete the Barbarian Outpost Agility Course", // TODO
                            "Mine pure essence on Lunar Isle", // TODO
                            "Make a barbarian pyre ship from arctic pine", // TODO
                            "Catch a tuna without a harpoon", // TODO
                            "Bake a pie using Magic", // TODO
                            "Kill a Mithril dragon", // TODO
                            "Get mahogany from your Etceterian subjects" // TODO
                    }
            },
            // Fremennik sea boots 1-3
            // 5k, 10k, 15k xp
            new Item[][]{
                    {new Item(14571), new Item(14574)},
                    {new Item(14572), new Item(14575)},
                    {new Item(14573), new Item(14576)}
            },
            "",
            // Council Workman, Yrsa, Advisor Ghrim
            new int[]{1287, 1301, 1375}
    ),
    // https://runescape.wiki/w/Seers%27_Village_achievements?oldid=900527
    SEERS_VILLAGE("Seers' Village", 27,
            new String[]{"Easy", "Medium", "Hard"},
            new String[][]{
                    {
                            "Pick 5 flax.", // TODO
                            "Walk clockwise around the Mysterious statue.", // TODO
                            "Have Sir Galahad make you a cup of tea.", // TODO
                            "Take the poison chalice to King Arthur.", // TODO
                            "Spin 5 bow strings.", // TODO
                            "Fill five pots with flour from Sinclair Mansion.", // TODO
                            "Give five locals a glass of cider in the Foresters' Arms.", // TODO
                            "Plant jute seeds.", // TODO
                            "Use the churn in Sinclair Mansion garden.", // TODO
                            "Buy a candle from the candle maker.", // TODO
                            "Pray at Seers' Village altar.", // TODO
                            "Catch a mackerel." // TODO
                    },
                    {
                            "Use the Sinclair Mansion to Fremennik agility shortcut.", // TODO
                            "Talk to Thormac the Sorcerer about making mystic staves.", // TODO
                            "Transport a full load of coal to Seers' Village.", // TODO
                            "Find the highest point in Seers' Village.", // TODO
                            "Defeat each type of elemental in the Elemental Workshop.", // TODO
                            "Teleport to Camelot.", // TODO
                            "Kill one guard on each tower of the Ranging Guild using a longbow.", // TODO
                            "Have the Ranging Guild competition judge congratulate you<br><br>for acquiring over 1,000 Archery tickets.", // TODO
                            "Buy something from the ticket exchange in the Ranging Guild.", // TODO
                            "Use a familiar to make a fire from maple logs within Seers' Village.", // TODO
                            "Get a pet fish from Harry.", // TODO
                            "Catch and cook a bass in Catherby." // TODO
                    },
                    {
                            "Teleport to the Ranging Guild.", // TODO
                            "Cut five sets of yew logs.", // TODO
                            "Fletch a magic shortbow in the Seers' Village Bank.", // TODO
                            "Enter the Seers' Village courthouse with your Piety prayer turned on.", // TODO
                            "Use the fairy ring in McGrubor's Wood.", // TODO
                            "Burn a magic log in Seers' Village.", // TODO
                            "High Alch a magic shortbow in the Seers' Village bank.", // TODO
                            "Catch five sharks in Catherby.", // TODO
                            "Cook 5 sharks in Catherby using the Cooking gauntlets.", // TODO
                            "Charge five water orbs in one go.", // TODO
                            "Use the grapple shortcut to get from the water obelisk<br><br>island to Catherby shore." // TODO
                    }
            },
            // Seers Headbad 1-3
            // 1k, 5k, 10k xp lamps
            new Item[][]{
                    {new Item(14631), new Item(14633)},
                    {new Item(14631), new Item(14634)},
                    {new Item(14631), new Item(14635)}
            },
            "",
            // Seer, Stankers, Sir Kay
            new int[]{388, 383, 241}
    );

    /**
     * The name of the diary.
     */
    private final String name;

    /**
     * The child id.
     */
    private final int child;

    /**
     * Level names
     */
    private final String[] levelNames;

    /**
     * The achievement descriptions.
     */
    private final String[][] achievements;

    /**
     * The item rewards.
     */
    private final Item[][] rewards;

    /**
     * The info to start the diary.
     */
    private final String info;

    /**
     * The npcs for the task levels.
     */
    private final int[] npcs;

    /**
     * Constructs a new {@code DiaryType} {@code Object}
     *
     * @param name         the name of the diary.
     * @param child        the child id.
     * @param achievements the achievements.
     * @param rewards      the rewards.
     * @param info         the info about the diary.
     * @param npcs         the npcs for the task levels.
     */
    DiaryType(String name, int child, String[] levelNames, String[][] achievements, Item[][] rewards, String info, int[] npcs) {
        this.name = name;
        this.child = child;
        this.levelNames = levelNames;
        this.achievements = achievements;
        this.rewards = rewards;
        this.info = info;
        this.npcs = npcs;
    }

    /**
     * Gets the diary type for the child id.
     *
     * @param child the child.
     * @return the diary type.
     */
    public static DiaryType forChild(int child) {
        for (DiaryType type : values()) {
            for (int i = 0; i < 4; i++) {
                if (child == type.getChild() + i) {
                    return type;
                }
            }
        }
        return null;
    }

    /**
     * Gets the npc for the level.
     *
     * @param level the level.
     * @return the npc id.
     */
    public int getNpc(int level) {
        return npcs[level];
    }

    /**
     * Gets the rewards for the task level.
     *
     * @param level the level.
     * @return the rewards.
     */
    public Item[] getRewards(int level) {
        return rewards[level];
    }

    /**
     * Gets the achievements for the task level.
     *
     * @param level the level.
     * @return the achievements.
     */
    public String[] getAchievements(int level) {
        return achievements[level];
    }

    /**
     * Gets the achievements.
     *
     * @return the achievements
     */
    public String[][] getAchievements() {
        return achievements;
    }

    /**
     * Gets the rewards.
     *
     * @return the rewards
     */
    public Item[][] getRewards() {
        return rewards;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the level names
     *
     * @return the level names
     */
    public String[] getLevelNames() {
        return levelNames;
    }

    /**
     * Gets the info.
     *
     * @return the info
     */
    public String getInfo() {
        return info;
    }

    /**
     * Gets the child.
     *
     * @return the child
     */
    public int getChild() {
        return child;
    }

    /**
     * Gets the npcs.
     *
     * @return the npcs
     */
    public int[] getNpcs() {
        return npcs;
    }
}