package core.worker

import core.game.system.task.Pulse
import core.game.world.GameWorld
import core.game.world.repository.Repository
import core.game.world.update.UpdateSequence
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import plugin.CorePluginTypes.Managers

/**
 * Handles the running of pulses and writing of masks, etc
 * @author Ceikry
 */
class MajorUpdateWorker {
    var started = false
    val sequence = UpdateSequence()
    fun start() = GlobalScope.launch {
        started = true
        while(true){
            delay(600L)
            launch {
                val rmlist = ArrayList<Pulse>()
                val list = ArrayList(GameWorld.Pulser.TASKS)
                for(pulse in list){
                    try {
                        if (pulse.update()) rmlist.add(pulse)
                    } catch (e: Exception){
                        e.printStackTrace()
                        rmlist.add(pulse)
                    }
                }
                GameWorld.Pulser.TASKS.removeAll(rmlist)
                sequence.start()
                sequence.run()
                sequence.end()
            }
            launch {
                GameWorld.pulse()
                Repository.getDisconnectionQueue().update()
            }
            Managers.tick()
        }
    }
}