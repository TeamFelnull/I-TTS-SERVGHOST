package dev.felnull.itts.servghost.share.connect.object;

import dev.felnull.itts.servghost.share.connect.object.base.ReplyableBaseSendObject;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class TestSendObject extends ReplyableBaseSendObject {
    private final String text;

    /**
     * コンストラクタ
     *
     * @param replayId 返信に利用するID
     * @param text     TEST
     */
    public TestSendObject(@Nullable UUID replayId, String text) {
        super(replayId);
        this.text = text;
    }
}
