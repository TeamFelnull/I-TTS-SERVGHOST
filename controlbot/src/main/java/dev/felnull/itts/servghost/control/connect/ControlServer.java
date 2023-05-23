package dev.felnull.itts.servghost.control.connect;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;

/**
 * 制御BOTとI-TTSを接続するためのサーバー
 *
 * @author MORIMORI0317
 */
public class ControlServer {
    /**
     * 接続待機スレッド
     */
    private final ConnectionWaitThread waitThread = new ConnectionWaitThread();

    /**
     * 現在接続中のクライアント接続インスタンス
     */
    private final List<ConnectionInstance> connectionInstances = new LinkedList<>();

    /**
     * サーバーソケット
     */
    private ServerSocket serverSocket;

    /**
     * サーバーを開始する
     *
     * @throws IOException 接続エラー
     */
    public void start() throws IOException {
        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("localhost", 8765));

        waitThread.start();
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    /**
     * スレッドセーフでクライアント接続インスタンスを追加する
     *
     * @param connectionInstance クライアント接続インスタンス
     */
    protected void addConnectionInstances(ConnectionInstance connectionInstance) {
        synchronized (connectionInstances) {
            connectionInstances.add(connectionInstance);
        }
    }

    /**
     * スレッドセーフでクライアント接続インスタンスを削除する
     *
     * @param connectionInstance クライアント接続インスタンス
     */
    protected void removeConnectionInstances(ConnectionInstance connectionInstance) {
        synchronized (connectionInstances) {
            connectionInstances.remove(connectionInstance);
        }
    }
}
