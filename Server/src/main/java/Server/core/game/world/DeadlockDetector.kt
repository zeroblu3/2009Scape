package core.game.world

import java.lang.management.ManagementFactory
import java.lang.management.ThreadMXBean

class DeadlockDetector : Runnable {
    override fun run() {
        val mbean = ManagementFactory.getThreadMXBean();
        val deadLockedThreads = mbean.findDeadlockedThreads()




        fun `(┛◉Д◉)┛彡┻━┻`() {
            val infos = mbean.getThreadInfo(deadLockedThreads)
            infos.forEach { threadInfo ->
                if (threadInfo != null) {
                    for (thread in Thread.getAllStackTraces().keys) {
                        if (thread.id == threadInfo.threadId) {
                            System.err.println(threadInfo.toString().trim())
                            for (ste in thread.stackTrace) {
                                System.err.println("\t" + ste.toString().trim { it <= ' ' })
                            }
                        }
                    }
                }
            }
        }


        if(deadLockedThreads != null){
            `(┛◉Д◉)┛彡┻━┻`()
        }

    }
}