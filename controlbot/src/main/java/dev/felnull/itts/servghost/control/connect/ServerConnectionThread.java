package dev.felnull.itts.servghost.control.connect;

import dev.felnull.itts.servghost.control.Main;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * クライアントとの通信を行うスレッド
 */
public class ServerConnectionThread extends Thread {

    /**
     * クライアント接続インスタンス
     */
    private final ConnectionInstance connectionInstance;

    /**
     * コンストラクタ
     *
     * @param connectionInstance クライアント接続インスタンス
     */
    public ServerConnectionThread(ConnectionInstance connectionInstance) {
        this.connectionInstance = connectionInstance;
    }

    @Override
    public void run() {
        Main.LOGGER.info("Connection start");


        try {
            ObjectInputStream inputStream = new ObjectInputStream(this.connectionInstance.getSocket().getInputStream());

            while (!this.connectionInstance.getSocket().isClosed()) {
                try {
                    this.connectionInstance.onReceiveObject(inputStream.readObject());
                } catch (ClassNotFoundException e) {
                    Main.LOGGER.error("Object receive error", e);
                }
            }
        } catch (IOException e) {
            Main.LOGGER.error("Connection error", e);
        }

        Main.LOGGER.info("Connection end");
    }
}
