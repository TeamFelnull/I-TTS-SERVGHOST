package dev.felnull.itts.servghost.control.connect;

import dev.felnull.itts.servghost.control.ClientInstance;
import dev.felnull.itts.servghost.control.Main;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

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
     * 現在接続中のクライアント
     */
    private final Map<UUID, ClientInstance> allClient = new ConcurrentHashMap<>();

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
        Main.LOGGER.info("Started the control server");
        this.serverSocket = new ServerSocket();
        this.serverSocket.bind(new InetSocketAddress(Main.CONNECTION_CONFIG.getHostName(), Main.CONNECTION_CONFIG.getPort()));

        this.waitThread.start();
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    /**
     * クライアントを追加
     *
     * @param clientInstance クライアントインスタンス
     */
    public void addClient(ClientInstance clientInstance) {
        this.allClient.put(clientInstance.getId(), clientInstance);
    }

    /**
     * クライアントを削除
     *
     * @param id クライアントID
     */
    public void removeClient(UUID id) {
        this.allClient.remove(id);
    }
}
