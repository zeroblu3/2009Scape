package core.game.system

import core.ServerConstants
import core.game.world.GameWorld
import java.io.BufferedWriter
import java.io.File
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
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
    private val fileNameFormat: DateFormat = SimpleDateFormat("yyy-MM-dd")
    private var logWriter : BufferedWriter? = null

    init {
        if(ServerConstants.WRITE_LOGS){
            val logDir = Paths.get(ServerConstants.LOGS_PATH ?: ".")
            if(Files.notExists(logDir)) {
                Files.createDirectory(logDir)
            }
            val filePath = Paths.get(logDir.toString() + File.separator + fileNameFormat.format(Date()) + ".txt")
            System.out.println("Using path " + filePath)
            logWriter = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8, StandardOpenOption.APPEND, StandardOpenOption.CREATE)
        }
    }

    private fun writeLog(message: String){
        try {
            logWriter?.write(message)
            logWriter?.flush()
        } catch (e: IOException){
            e.printStackTrace()
        }
    }

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
        val msg = "[" + dateFormat.format(Date()) + "][" + GameWorld.settings?.name + "]: " + message
        writeLog(msg + "\n")
        println(msg)
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
        val msg = "[" + dateFormat.format(Date()) + "][" + Class::class.java.simpleName + "]: " + message
        writeLog(msg + "\n")
        println(msg)
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
        val msg = "[" + dateFormat.format(Date()) + "][" + className + "]: " + message
        writeLog(msg + "\n")
        println(msg)
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
        val msg = "[" + dateFormat.format(Date()) + "][" + GameWorld.settings?.name + "]: " + message
        System.err.println(msg)
        writeLog(msg + "\n")
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
        val msg = "[" + dateFormat.format(Date()) + "][" + Class::class.java.simpleName + "]: " + message
        writeLog(msg + "\n")
        System.err.println(msg)
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
        val msg ="[" + dateFormat.format(Date()) + "][" + className + "]: " + message
        writeLog(msg + "\n")
        System.err.println(msg)
    }
}