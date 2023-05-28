package dev.felnull.itts.servghost.control;

import dev.felnull.itts.servghost.control.connect.ServerSession;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * クライアントごとを管理するためのインスタンス
 */
public class ClientInstance {
    /**
     * クライアントを識別するためのID
     */
    private final UUID id;

    /**
     * クライアントとの接続セッション
     */
    private final ServerSession serverSession;

    /**
     * コンストラクタ
     *
     * @param id            識別ID
     * @param serverSession セッション
     */
    public ClientInstance(@NotNull UUID id, @NotNull ServerSession serverSession) {
        this.id = id;
        this.serverSession = serverSession;
    }

    public ServerSession getServerSession() {
        return serverSession;
    }

    public UUID getId() {
        return id;
    }

    /**
     * セッションが開かれた際に呼び出し
     */
    public void onOpenSession() {
        Main.CONTROL_SERVER.addClient(this);
    }

    /**
     * セッションが閉じた際に呼び出し
     */
    public void onCloseSession() {
        Main.CONTROL_SERVER.removeClient(this.id);
    }
}
