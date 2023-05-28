package dev.felnull.itts.control;

import dev.felnull.itts.Main;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

/**
 * サーバーへの接続を制御するスレッド
 *
 * @author MORIMORI0317
 */
public class ConnectionThread extends Thread {
    /**
     * 接続を再試行するまでの時間
     */
    private static final long RETRY_TIME = 1000 * 10;

    public ConnectionThread() {
        this.setName("connection-thread");
    }

    @Override
    public void run() {
        Main.LOGGER.info("Connection start");

        while (this.isAlive()) {

            try {
                connected();
            } catch (ConnectException e) {
                Main.LOGGER.error("Failed to connect to server: {}", e.getLocalizedMessage());
            } catch (Exception e) {
                Main.LOGGER.error("Lost connection with server", e);
            }

            Main.LOGGER.info("Retry connection to server after {} ms", RETRY_TIME);

            try {
                Thread.sleep(RETRY_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        Main.LOGGER.info("Connection end");
    }

    /**
     * 接続を行う、接続中はスレッドを待機する
     */
    private void connected() throws IOException {
        Main.LOGGER.info("Try to connect to the server");

        try (Socket socket = new Socket(Main.CONNECTION_CONFIG.getHostName(), Main.CONNECTION_CONFIG.getPort())) {
            Main.LOGGER.info("Start connection with server");

            ClientSession clientSession = new ClientSession(socket);
            clientSession.start();
            clientSession.join();
        }

        Main.LOGGER.info("Lost connection with server");
    }
}
