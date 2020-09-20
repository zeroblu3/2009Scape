package plugin.worldevents

import core.plugin.InitializablePlugin
import core.plugin.Plugin
import io.github.classgraph.ClassGraph
import io.github.classgraph.ClassInfo
import java.util.function.Consumer

/**
 * Responsible for initializing world events
 * @author Ceikry
 */
@InitializablePlugin
class WorldEventInitializer : Plugin<Any>{
    override fun newInstance(arg: Any?): Plugin<Any> {
        /**
         * Here I used ClassGraph to scan the plugin.worldevents package for subclasses of the WorldEvent class and
         * then initialize them if their active setting evaluates to true. This makes it so we don't have to manually add them to
         * a list. It also prevents unnecessary plugins from being loaded if an event isn't currently active.
         */

        val result = ClassGraph().enableClassInfo().whitelistPackages("plugin.worldevents").scan()
        result.getSubclasses("plugin.worldevents.WorldEvent").forEach(Consumer { p: ClassInfo ->
            val c = p.loadClass().newInstance() as WorldEvent
            if(c.checkActive()) c.initialize().also { WorldEvents.add(c) }
        })
        return this
    }

    override fun fireEvent(identifier: String?, vararg args: Any?): Any {
        return Unit
    }

}