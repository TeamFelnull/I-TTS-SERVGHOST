package dev.felnull.itts.servghost.control.connect;

import dev.felnull.itts.servghost.control.Main;

import java.io.IOException;
import java.net.Socket;

/**
 * クライアントとの接続を待つスレッド
 *
 * @author MORIMORI0317
 */
public class ConnectionWaitThread extends Thread {
    /**
     * エラーが発生した際の待機時間
     */
    private static final long ERROR_WAIT_TIME = 1000;

    @Override
    public void run() {
        Main.LOGGER.info("Connection wait start");

        while (!Main.CONTROL_SERVER.getServerSocket().isClosed()) {
            try {
                connectWait();
            } catch (Exception ex) {
                Main.LOGGER.error("Connection wait error", ex);

                try {
                    Thread.sleep(ERROR_WAIT_TIME);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }

        Main.LOGGER.info("Connection wait end");
    }

    /**
     * 接続待ちを行い、接続された場合は通信を開始する。
     *
     * @throws IOException 接続エラー
     */
    private void connectWait() throws IOException {
        Socket socket = Main.CONTROL_SERVER.getServerSocket().accept();

        ConnectionInstance connectionInstance = new ConnectionInstance(socket);
        connectionInstance.startConnection();
    }
}
