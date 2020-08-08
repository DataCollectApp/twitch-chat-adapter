package app.datacollect.twitchchatadapter.twitchconnection

import app.datacollect.twitchchatadapter.user.User
import org.slf4j.LoggerFactory
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.net.InetSocketAddress
import java.net.Socket

class TwitchConnection(host: String, port: Int, username: String, token: String) {

    private val inputReader: BufferedReader
    private val outputStream: DataOutputStream
    private val botUser: User = User(username, token)

    fun login() {
        if (botUser.isLoggedIn) {
            logger.error("Failed to login: A user is already logged in")
            return
        }
        send("PASS " + botUser.token)
        send("NICK " + botUser.username)
    }

    fun send(message: String) {
        try {
            outputStream.writeBytes(message + "\n")
            logOutput(message)
        } catch (ex: IOException) {
            logger.error("Exception occurred while writing to socket", ex)
        }
    }

    fun read(): String? {
        return try {
            inputReader.readLine()
        } catch (ex: IOException) {
            logger.error("Exception occurred while reading from socket", ex)
            null
        }
    }

    private fun logOutput(output: String) {
        logger.info("TO TWITCH << $output")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(TwitchConnection::class.java)
    }

    init {
        val socket = Socket()
        socket.connect(InetSocketAddress(host, port))
        inputReader = BufferedReader(InputStreamReader(socket.getInputStream()))
        outputStream = DataOutputStream(socket.getOutputStream())
    }
}
