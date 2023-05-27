package dev.felnull.itts.servghost.control.connect;

import dev.felnull.itts.servghost.control.Main;

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
     * 現在接続中のセッション
     */
    private final List<ServerSession> sessions = new LinkedList<>();

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
        this.serverSocket.bind(new InetSocketAddress("localhost", 8765));

        this.waitThread.start();
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    /**
     * スレッドセーフでセッションを追加する
     *
     * @param serverSession セッション
     */
    protected void addSession(ServerSession serverSession) {
        synchronized (this.sessions) {
            this.sessions.add(serverSession);
        }
    }

    /**
     * スレッドセーフでセッションを削除する
     *
     * @param serverSession セッション
     */
    protected void removeSession(ServerSession serverSession) {
        synchronized (this.sessions) {
            this.sessions.remove(serverSession);
        }
    }
}
