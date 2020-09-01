package plugin.command

import core.ServerConstants
import core.cache.Cache
import core.cache.def.impl.ItemDefinition
import core.cache.def.impl.NPCDefinition
import core.game.node.entity.npc.NPC
import core.game.node.entity.player.Player
import core.game.node.entity.player.info.Rights
import core.game.node.entity.player.link.TeleportManager.TeleportType
import core.game.node.entity.player.link.quest.Quest
import core.game.node.entity.player.link.quest.QuestRepository
import core.game.node.item.Item
import core.game.system.SystemLogger.log
import core.game.system.command.CommandPlugin
import core.game.system.command.CommandSet
import core.game.world.map.Location
import core.game.world.map.RegionManager
import core.game.world.map.build.DynamicRegion
import core.game.world.repository.Repository
import core.plugin.InitializablePlugin
import core.plugin.Plugin
import core.tools.StringUtils
import core.tools.npc.TestStats
import plugin.ai.resource.ResourceAIPManager
import plugin.ge.GrandExchangeDatabase
import plugin.skill.Skills
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

/**
 * The commands available during beta stage.
 *
 * @author Emperor
 */
@InitializablePlugin
class DebugCommandPlugin : CommandPlugin() {
    var sqlLog = ""
    override fun parse(player: Player?, name: String?, args: Array<String?>?): Boolean {
        var name = name
        var id: Int
        var amount: Int
        val p: Player?
        when (name) {
            "rights" -> {
                player!!.details.rights = Rights.forId(args!![1]!!.toInt())
                player.sendMessage("Set rights to " + Rights.forId(args[1]!!.toInt()).name)
            }
            "lo" -> {
                var index = 0
                var i = 8349
                while (i < 8367) {
                    val n = NPC.create(i, player!!.location.transform(0, -index, 0))
                    n.init()
                    index += 2
                    i++
                }
                player!!.sendMessage("Lol")
            }
            "resetquest", "reset_quest" -> {
                if (args!!.size < 2) {
                    player!!.debug("syntax error: name")
                    return true
                }
                name = ""
                var i = 1
                while (i < args.size) {
                    name += (if (i == 1) "" else " ") + args[i]
                    i++
                }
                name = StringUtils.formatDisplayName(name)
                if (player!!.questRepository.getQuest(name) == null) {
                    player.debug("err or: invalid quest - $name")
                    return true
                }
                player.questRepository.getQuest(name).setStage(player, 0)
                player.questRepository.dockPoints(player.questRepository.getQuest(name).questPoints)
                player.questRepository.syncronizeTab(player)
                return true
            }
            "allquest" -> {
                for (quest in QuestRepository.getQuests().values) {
                    quest.finish(player)
                }
                return true
            }
            "pos", "position", "coords", "loc" -> {
                val l = player!!.location
                val r = player.viewport.region
                player.packetDispatch.sendMessage("Absolute: " + l + ", regional: [" + l.localX + ", " + l.localY + "], chunk: [" + l.chunkOffsetX + ", " + l.chunkOffsetY + "], flag: [" + RegionManager.isTeleportPermitted(l) + ", " + RegionManager.getClippingFlag(l) + ", " + RegionManager.isLandscape(l) + "].")
                player.packetDispatch.sendMessage("Region: [id=" + l.regionId + ", active=" + r.isActive + ", instanced=" + (r is DynamicRegion) + "], obj=" + RegionManager.getObject(l) + ".")
                player.packetDispatch.sendMessage("Object: " + RegionManager.getObject(l) + ".")
                log("Viewport: " + l.getSceneX(player.playerFlags.lastSceneGraph) + "," + l.getSceneY(player.playerFlags.lastSceneGraph))
                val loc = "Location.create(" + l.x + ", " + l.y + ", " + l.z + ")"
                log(loc + "; " + player.playerFlags.lastSceneGraph + ", " + l.localX + ", " + l.localY)
                val stringSelection = StringSelection(loc)
                val clpbrd = Toolkit.getDefaultToolkit().systemClipboard
                clpbrd.setContents(stringSelection, null)
                return true
            }
            "cursong" -> {
                player!!.sendMessage(player.musicPlayer.currentMusicId.toString() + "")
                return true
            }
            "setquest", "setoquest" -> {
                if (args!!.size < 2) {
                    player!!.debug("syntax error: name")
                    return true
                }
                val m = if (name == "setoquest") getTarget(args[args.size - 1]) else player
                if (m == null || !m.isActive) {
                    player!!.sendMessage("Error! " + args[3] + " in invalid.")
                    return true
                }
                name = ""
                var i = 1
                while (i < args.size - 1) {
                    name += (if (i == 1) "" else " ") + args[i]
                    i++
                }
                var quest: Quest? = null
                for (q in QuestRepository.getQuests().values) {
                    if (q.name.toLowerCase() == name.toLowerCase()) {
                        quest = q
                        break
                    }
                }
                if (quest == null) {
                    player!!.debug("error: invalid quest - $name")
                    return true
                }
                val stage = toInteger(args[args.size - 1]!!)
                quest.setStage(player, stage)
                m.packetDispatch.sendMessage("quest=$name, new stage=$stage")
                m.questRepository.syncronizeTab(player)
            }
            "empty" -> {
                player!!.inventory.clear()
                return true
            }
            "firerandom" -> {
                if (args!!.size < 2) {
                    player!!.sendMessage("You need to specify what event.")
                }
                name = ""
                var i = 1
                while (i < args.size) {
                    name += (if (i == 1) "" else " ") + args[i]
                    i++
                }
                player!!.antiMacroHandler.fireEvent(name)
                return true
            }
            "setvalue" -> {
                val itemId = toInteger(args!![1]!!)
                val value = toInteger(args[2]!!)
                val item = Item(itemId)
                val entry = GrandExchangeDatabase.getDatabase()[itemId]
                if (entry == null) {
                    player!!.packetDispatch.sendMessage("Could not find G.E entry for item [id=" + itemId + ", name=" + item.name + "]!")
                } else {
                    entry.value = value
                    player!!.packetDispatch.sendMessage("Set Grand Exchange value for item [id=" + itemId + ", name=" + item.name + "] to " + value + "gp!")
                }
            }
            "npc" -> {
                if (args!!.size < 2) {
                    player!!.debug("syntax error: id (optional) direction")
                    return true
                }
                val npc = NPC.create(toInteger(args[1]!!), player!!.location)
                npc.setAttribute("spawned:npc", true)
                npc.isRespawn = false
                npc.direction = player.direction
                npc.init()
                npc.isWalks = if (args.size > 2) true else false
                val npcString = "{" + npc.location.x + "," + npc.location.y + "," + npc.location.z + "," + (if (npc.isWalks) "1" else "0") + "," + npc.direction.ordinal + "}"
                val clpbrd = Toolkit.getDefaultToolkit().systemClipboard
                clpbrd.setContents(StringSelection(npcString), null)
                println(npcString)
                return true
            }
            "npcn" -> {
                if (args!!.size < 2) {
                    player!!.debug("syntax error: npc-name (optional) amount")
                    return true
                }
                var parameters: String? = ""
                run {
                    var i = 1
                    while (i < args.size) {
                        parameters += if (i == args.size - 1) args[i] else args[i].toString() + " "
                        i++
                    }
                }
                var i = 0
                while (i < NPCDefinition.getDefinitions().size) {
                    val npcDefinition = NPCDefinition.forId(i)
                    if (npcDefinition != null && npcDefinition.name.equals(parameters!!.toLowerCase(), ignoreCase = true)) {
                        val n = NPC.create(npcDefinition.id, player!!.location)
                        n.setAttribute("spawned:npcDefinition", true)
                        n.isRespawn = false
                        n.direction = player.direction
                        n.init()
                        n.isWalks = if (args.size > 2) true else false
                        val npcs = "{" + n.location.x + "," + n.location.y + "," + n.location.z + "," + (if (n.isWalks) "1" else "0") + "," + n.direction.ordinal + "}"
                        println(npcs)
                        break
                    }
                    i++
                }
                return true
            }
            "item" -> {
                if (args!!.size < 2) {
                    player!!.sendMessage("You must specify an item ID")
                    return false
                }
                id = toInteger(args[1]!!)
                amount = if (args.size > 2) toInteger(args[2]!!) else 1
                if (id > Cache.getItemDefinitionsSize()) {
                    player!!.sendMessage("Item ID '$id' out of range.")
                    return true
                }
                val item = Item(id, amount)
                val max = player!!.inventory.getMaximumAdd(item)
                if (amount > max) {
                    amount = max
                }
                item.setAmount(amount)
                player.inventory.add(item)
                return true
            }
            "give", "giveitem" -> {
                if (args!!.size < 3) {
                    player!!.sendMessage("Syntax: ::give id amt playername")
                }
                var n: String? = ""
                var i = 3
                while (i < args.size) {
                    if (i == 3) {
                        n += args[i]
                        i++
                        continue
                    }
                    n += " " + args[i]
                    i++
                }
                player!!.sendMessage("Giving $n item..")
                val recipient = Repository.getPlayer(n)
                if (recipient == null) {
                    player.debug("syntax error: name")
                    return true
                }
                val item = Item(toInteger(args[1]!!), toInteger(args[2]!!))
                item.setAmount(toInteger(args[2]!!))
                recipient.inventory.add(item)
                return true
            }
            "logsql" -> {
                val lsql = player!!.location
                player.packetDispatch.sendMessage("Absolute: $lsql")
                sqlLog += "{" + lsql.x + "," + lsql.y + "," + lsql.z + ",0,0}"
                return true
            }
            "sqldone" -> {
                val stringSelection = StringSelection(sqlLog)
                val clpbrd = Toolkit.getDefaultToolkit().systemClipboard
                clpbrd.setContents(stringSelection, null)
                sqlLog = ""
                return true
            }
            "sqloc", "sqlloc", "locsql" -> {
                val lsqlatom = player!!.location
                player.packetDispatch.sendMessage("Absolute: $lsqlatom")
                val locql = "{" + lsqlatom.x + "," + lsqlatom.y + "," + lsqlatom.z + ",0,0}"
                val stringSelection = StringSelection(locql)
                val clpbrd = Toolkit.getDefaultToolkit().systemClipboard
                clpbrd.setContents(stringSelection, null)
                return true
            }
            "sqlocgs", "sqllocgs", "locsqlgspawn" -> {
                val lsqlgs = player!!.location
                player.packetDispatch.sendMessage("Absolute: $lsqlgs")
                val lsqlgst = "{1," + lsqlgs.x + "," + lsqlgs.y + "," + lsqlgs.z + ",196610}"
                val stringSelection = StringSelection(lsqlgst)
                val clpbrd = Toolkit.getDefaultToolkit().systemClipboard
                clpbrd.setContents(stringSelection, null)
                return true
            }
            "itemn" -> {
                if (args!!.size < 2) {
                    player!!.sendMessage("You must specify an item name")
                    return true
                }
                var itemName: String? = ""
                run {
                    var i = 1
                    while (i < args.size) {
                        itemName += if (i == args.size - 1) args[i] else args[i].toString() + " "
                        i++
                    }
                }
                var foundItem = false
                var i = 0
                while (i < ItemDefinition.getDefinitions().size) {
                    val def1 = ItemDefinition.forId(i)
                    if (def1 != null && def1.name.equals(itemName!!.toLowerCase(), ignoreCase = true)) {
                        player!!.inventory.add(Item(i, 1))
                        player.sendMessage("Added " + def1.name + "[" + def1.id + "] to inventory")
                        foundItem = true
                        break
                    }
                    i++
                }
                if (!foundItem) {
                    player!!.sendMessage("<col=ff0000>Unable to find item: $itemName</col>")
                }
                return true
            }
            "task" -> ResourceAIPManager.get().runTask(player, "Willow Logs")
            "master", "max" -> {
                if (player!!.details.rights !== Rights.ADMINISTRATOR) {
                    if (player!!.inCombat() || player.locks.isInteractionLocked || player.skullManager.isWilderness) {
                        player.packetDispatch.sendMessage("You can't do that right now.")
                        return true
                    }
                }
                var i = 0
                while (i < Skills.SKILL_NAME.size) {
                    player!!.skills.setLevel(i, 99)
                    player.skills.setStaticLevel(i, 99)
                    i++
                }
                player!!.skills.updateCombatLevel()
                player.appearance.sync()
                return true
            }
            "runes" -> {
                var i = 554
                while (i < 567) {
                    player!!.inventory.add(Item(i, 50000))
                    i++
                }
                player!!.inventory.add(Item(9075, 50000))
                return true
            }
            "skill", "oskill" -> {
                if (player!!.details.rights !== Rights.ADMINISTRATOR) {
                    if (player!!.inCombat() || player.locks.isInteractionLocked || player.skullManager.isWilderness) {
                        player.packetDispatch.sendMessage("You can't do that right now.")
                        return true
                    }
                }
                if (args!!.size < 3) {
                    player!!.packetDispatch.sendMessage("Use as ::skill skillname/id level.")
                    return true
                }
                var skillId = -1
                if (Character.isDigit(args[1]!![0])) {
                    skillId = toInteger(args[1]!!)
                } else {
                    id = 0
                    while (id < Skills.SKILL_NAME.size) {
                        val skill = Skills.SKILL_NAME[id]
                        if (args[1] == skill.toLowerCase()) {
                            skillId = id
                            break
                        }
                        id++
                    }
                }
                if (skillId < 0) {
                    player!!.packetDispatch.sendMessage("Use as ::skill skillname/id level.")
                    return true
                }
                var level = Math.abs(toInteger(args[2]!!))
                if (level > 99) {
                    level = 99
                }
                p = if (name == "oskill" && args.size > 3) Repository.getPlayer(args[3]) else player
                if (p == null) {
                    player!!.packetDispatch.sendMessage("Unable to set level for " + args[3] + ".")
                    return true
                }
                p.skills.setLevel(skillId, level)
                p.skills.setStaticLevel(skillId, level)
                p.skills.updateCombatLevel()
                p.appearance.sync()
                player!!.packetDispatch.sendMessage("Set " + p.name + "'s " + Skills.SKILL_NAME[skillId] + " level to " + args[2] + ".")
                return true
            }
            "copy" -> {
                val target = Repository.getPlayer(args!![1])
                if (target != null) {
                    player!!.inventory.copy(target.inventory)
                    player.inventory.refresh()
                    player.skills.copy(target.skills)
                    player.skills.configure()
                    player.equipment.copy(target.equipment)
                    player.equipment.refresh()
                    player.appearance.sync()
                }
                return true
            }
            "to" -> {
                if (player!!.details.rights !== Rights.ADMINISTRATOR) {
                    if (player!!.inCombat() || player.locks.isTeleportLocked) {
                        player.packetDispatch.sendMessage("You can't teleport right now.")
                        return true
                    }
                }
                var destination: Location? = null
                val place = getArgumentLine(args!!)
                for (destinations in ServerConstants.TELEPORT_DESTINATIONS) {
                    var i = 1
                    while (i < destinations.size) {
                        if (place == destinations[i]) {
                            destination = destinations[0] as Location
                            break
                        }
                        i++
                    }
                }
                if (destination != null) {
                    player!!.teleporter.send(destination, TeleportType.NORMAL)
                } else {
                    player!!.packetDispatch.sendMessage("Could not locate teleport destination [name=$place]!")
                }
                return true
            }
            "teleports", "destinations" -> {
                player!!.interfaceManager.close()
                player.packetDispatch.sendString("<u>Teleport destinations</u>", 239, 1)
                val sb = StringBuilder()
                var i = 0
                while (i < ServerConstants.TELEPORT_DESTINATIONS.size) {
                    sb.append(ServerConstants.TELEPORT_DESTINATIONS[i][1])
                    if (i != ServerConstants.TELEPORT_DESTINATIONS.size - 1) {
                        sb.append(", ")
                    }
                    i++
                }
                player.packetDispatch.sendString("<br>$sb", 239, 2)
                player.packetDispatch.sendString("", 239, 3)
                player.packetDispatch.sendString("", 239, 4)
                player.packetDispatch.sendString("", 239, 5)
                player.interfaceManager.openComponent(239)
                return true
            }
            "maxmag" -> {
                TestStats.setMaxedMagicAcc(player)
                return true
            }
            "maxstr" -> {
                TestStats.setMaxedMeleeStr(player)
                return true
            }
        }
        return false
    }

    @Throws(Throwable::class)
    override fun newInstance(arg: Any?): Plugin<Any?>? {
        link(CommandSet.BETA, CommandSet.ADMINISTRATOR)
        return this
    }
}