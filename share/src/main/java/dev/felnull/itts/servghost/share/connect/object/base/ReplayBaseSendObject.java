package dev.felnull.itts.servghost.share.connect.object.base;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.UUID;

/**
 * 返信するためのオブジェクト
 *
 * @author MORIMORI0317
 */
public class ReplayBaseSendObject implements Serializable {
    /**
     * 返信ID
     */
    private final UUID replayId;

    /**
     * コンストラクタ
     *
     * @param replayId 返信ID
     */
    public ReplayBaseSendObject(@NotNull UUID replayId) {
        this.replayId = replayId;
    }

    @NotNull
    public UUID getReplayId() {
        return replayId;
    }
}
