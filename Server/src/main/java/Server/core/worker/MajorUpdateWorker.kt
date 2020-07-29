package core.worker

import core.game.system.SystemLogger
import core.game.world.GameWorld
import core.game.world.repository.Repository
import core.game.world.update.UpdateSequence
import plugin.CorePluginTypes.Managers
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService

class MajorUpdateWorker : Runnable {
    val sequence = UpdateSequence()
    val executor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
    var started = false

    fun start(){
        if(started)
            return
        started = true
        //executor.scheduleAtFixedRate(this,1200,600,TimeUnit.MILLISECONDS)
        Executors.newSingleThreadExecutor().execute { run() }
    }

    override fun run() {
        Thread.currentThread().name = "Major Update Worker"
        while(true) {
            try {
                GameWorld.Pulser.Runner().run()
            } catch (e: Exception){

            }
            sequence.start()
            sequence.run()
            sequence.end()
            GameWorld.pulse()
            Repository.getDisconnectionQueue().update()
            Managers.tick()
            Thread.sleep(600)
        }
    }
}