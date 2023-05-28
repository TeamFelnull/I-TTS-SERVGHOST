package dev.felnull.itts.servghost.share.connect.object.base;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.UUID;

/**
 * 返信を要求するオブジェクト
 *
 * @author MORIMORI0317
 */
public abstract class ReplyableBaseSendObject implements Serializable {

    /**
     * 返信時にどのメッセージか識別するためのID
     */
    private final UUID replayId;

    /**
     * コンストラクタ
     *
     * @param replayId 返信に利用するID
     */
    protected ReplyableBaseSendObject(@NotNull UUID replayId) {
        this.replayId = replayId;
    }

    @NotNull
    public UUID getReplayId() {
        return replayId;
    }
}
