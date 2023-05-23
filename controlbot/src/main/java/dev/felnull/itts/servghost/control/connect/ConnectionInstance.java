package dev.felnull.itts.servghost.control.connect;

import dev.felnull.itts.servghost.control.Main;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * クライアントごとの接続
 */
public class ConnectionInstance {

    /**
     * クライアントとの通信を行うスレッド
     */
    private final ServerConnectionThread connectionThread = new ServerConnectionThread(this);

    /**
     * クライアントと接続中のソケット
     */
    private final Socket socket;

    /**
     * クライアントへ送信用アウトプットストリーム
     */
    private ObjectOutputStream outputStream;

    /**
     * コンストラクタ
     *
     * @param socket ソケット
     */
    public ConnectionInstance(Socket socket) {
        this.socket = socket;
    }

    /**
     * 接続開始
     */
    public void startConnection() {
        Main.CONTROL_SERVER.addConnectionInstances(this);

        try {
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            connectionThread.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 接続終了
     */
    public void stopConnection() {
        Main.CONTROL_SERVER.removeConnectionInstances(this);

        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    /**
     * クライアントから送られてきたオブジェクトを処理する
     *
     * @param object 受信オブジェクト
     */
    protected void onReceiveObject(Object object) {
        //TODO 受信処理
        System.out.println(object);
    }
}
