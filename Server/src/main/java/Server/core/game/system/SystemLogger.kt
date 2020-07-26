package core.game.system

import core.game.world.GameWorld
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Printing log messages class.
 * @author Apache Ah64
 */
object SystemLogger {
    /**
     * The date format string.
     */
    private val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    /**
     * Print a log message.
     * @param message
     */
    @JvmStatic
    fun log(message: String?) {
        dateFormat.timeZone = TimeZone.getDefault()
        if (message == null) {
            return
        }
        println("[" + dateFormat.format(Date()) + "][" + GameWorld.getName() + "]: " + message)
    }

    /**
     * Print a log message with class name.
     * @param thread
     * @param message
     */
    fun log(thread: Class<*>?, message: String?) {
        if (message == null) {
            return
        }
        println("[" + dateFormat.format(Date()) + "][" + Class::class.java.simpleName + "]: " + message)
    }

    /**
     * Print a log message with class name.
     * @param thread
     * @param message
     */
    fun log(className: String, message: String?) {
        if (message == null) {
            return
        }
        println("[" + dateFormat.format(Date()) + "][" + className + "]: " + message)
    }

    /**
     * Print a error message.
     * @param message
     */
    @JvmStatic
    fun error(message: String?) {
        if (message == null) {
            return
        }
        System.err.println("[" + dateFormat.format(Date()) + "][" + GameWorld.getName() + "]: " + message)
    }

    /**
     * Print a error message with class name.
     * @param thread
     * @param message
     */
    @JvmStatic
    fun error(thread: Class<*>?, message: String?) {
        if (message == null) {
            return
        }
        System.err.println("[" + dateFormat.format(Date()) + "][" + Class::class.java.simpleName + "]: " + message)
    }

    /**
     * Print a error message with class name.
     * @param thread
     * @param message
     */
    @JvmStatic
    fun error(className: String, message: String?) {
        if (message == null) {
            return
        }
        System.err.println("[" + dateFormat.format(Date()) + "][" + className + "]: " + message)
    }
}