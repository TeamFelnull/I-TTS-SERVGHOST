package dev.felnull.itts.servghost.share.connect.object;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.UUID;

/**
 * 接続開始時サーバーからクライアントに送信するオブジェクト
 *
 * @param clientId           クライアントID
 * @param version            通信のバージョン
 * @param overwriteTTSConfig クライアントのコンフィグを上書きするJson
 */
public record S2CStartSendObject(@NotNull UUID clientId, int version, String overwriteTTSConfig, String overwriteDBConfig) implements Serializable {
}
