package dev.felnull.itts.servghost.share.connect;

import com.google.common.base.Preconditions;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * クライアント、サーバー間の接続を維持して送信受信を行う
 *
 * @author MORIMORI0317
 */
public abstract class BaseSession {

    /**
     * 受信を待機するスレッド
     */
    private final ReceiveThread receiveThread = new ReceiveThread();

    /**
     * 送信中にストリームをロックするためのオブジェクト
     */
    private final Object sendLock = new Object();

    /**
     * このセッションが停止済みかどうか
     */
    private final AtomicBoolean stopped = new AtomicBoolean();

    /**
     * 接続用ソケット
     */
    private final Socket socket;

    /**
     * 送信用ストリーム
     */
    private ObjectOutputStream sendStream;


    /**
     * コンストラクタ
     *
     * @param socket 接続用ソケット
     */
    public BaseSession(@NotNull Socket socket) {
        this.socket = socket;
    }

    /**
     * セッションを開始する
     */
    public final void start() {
        onStart();
        this.receiveThread.start();
    }

    /**
     * 接続を切断して、セッションを停止する、何度も呼ばれる可能性あり
     */
    public final void stop() {

        // すでに停止済みならば停止処理を行わない
        if (!this.stopped.compareAndSet(false, true))
            return;

        onStop();

        if (canKeepConnected())
            getLogger().info("Forced to disconnect from server");

        try {
            this.socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * セッションが開始された際に呼び出し
     */
    abstract protected void onStart();

    /**
     * セッションが終了した際に呼び出し
     */
    abstract protected void onStop();

    /**
     * スレッドセーフでオブジェクトを送信する
     *
     * @param object 送信するオブジェクト
     */
    public void send(@NotNull Object object) {
        Preconditions.checkNotNull(object, "Object is null");

        try {
            synchronized (this.sendLock) {
                if (this.sendStream == null)
                    this.sendStream = new ObjectOutputStream(socket.getOutputStream());

                this.sendStream.writeObject(object);
            }
        } catch (IOException e) {
            getLogger().error("Send failed", e);
            stop();
        }
    }

    /**
     * オブジェクトを受信した際に非同期で呼ばれる
     *
     * @param object 受信したオブジェクト
     */
    abstract protected void onReceive(@NotNull Object object);

    /**
     * 出力用ロガー
     *
     * @return ロガー
     */
    @NotNull
    abstract protected Logger getLogger();

    /**
     * ソケットとの接続を継続できるかどうか
     *
     * @return 継続できるかどうか
     */
    private boolean canKeepConnected() {
        return !socket.isClosed() && socket.isConnected() && !socket.isInputShutdown() && !socket.isOutputShutdown();
    }

    /**
     * 接続を行っている間、スレッドを待機する
     */
    public void join() {
        try {
            this.receiveThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 受信を待機するスレッド
     */
    private class ReceiveThread extends Thread {

        /**
         * 受信スレッドに採番するためのカウント
         */
        private static final AtomicInteger threadCounter = new AtomicInteger();

        public ReceiveThread() {
            this.setName("receive-thread-" + threadCounter.getAndIncrement());
            this.setDaemon(true);
        }

        @Override
        public void run() {
            getLogger().info("Started waiting to receive");


            try (ObjectInputStream receiveSteam = new ObjectInputStream(socket.getInputStream())) {

                while (canKeepConnected()) {
                    try {
                        // 接続を維持している間、受信待ちを行う
                        onReceive(receiveSteam.readObject());
                    } catch (ClassNotFoundException e) {
                        getLogger().error("Object receive error", e);
                    }
                }

            } catch (IOException e) {
                getLogger().error("An error occurred while receiving", e);
            }

            getLogger().info("Finished waiting to receive");

            BaseSession.this.stop();
        }
    }
}
