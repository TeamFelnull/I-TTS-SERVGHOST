-- BOT情報
create table bot_info
(
    bot_id   BIGINT NOT NULL PRIMARY KEY,-- BOTのユーザーID
    bot_name NVARCHAR(100) NOT NULL      -- BOTの名前
);

-- サーバー情報
create table server_info
(
    server_id   BIGINT NOT NULL PRIMARY KEY, -- サーバーID
    server_name NVARCHAR(100) NOT NULL       -- サーバー名
);

-- サーバーごとのデータ
create table server_data
(
    server_id          BIGINT NOT NULL PRIMARY KEY, -- サーバーのID
    default_voice_type VARCHAR(100),                -- デフォルトの声タイプ
    ignore_regex       NVARCHAR(100),               -- 読み上げを無視する正規表現
    need_join          BIT    NOT NULL,             -- VCに参加時のみに読み上げるかどうか
    overwrite_aloud    BIT    NOT NULL,             -- 読み上げを上書きするかどうか
    notify_move        BIT    NOT NULL,             -- VC参加時に読み上げるかどうか
    read_limit         INT    NOT NULL,             -- 最大読み上げ文字数
    name_read_limit    INT    NOT NULL,             -- 名前の最大読み上げ文字数
    foreign key (server_id) references server_info (server_id)
);

-- サーバーごとのユーザーデータ
create table server_user_data
(
    server_id  BIGINT NOT NULL, -- サーバーID
    user_id    BIGINT NOT NULL, -- ユーザーID
    voice_type VARCHAR(100),    -- 読み上げタイプ
    deny       BIT    NOT NULL, -- 読み上げ拒否されているかどうか
    nick_name  NVARCHAR(100),   -- ニックネーム
    PRIMARY KEY (server_id, user_id),
    foreign key (server_id) references server_info (server_id)
);

-- サーバーごとのBOT状態データ
create table bot_state_data
(
    server_id                BIGINT NOT NULL,-- サーバーID
    bot_id                   BIGINT NOT NULL,-- BOTのID
    connected_audio_channel  BIGINT,         -- 接続中のオーディオチャンネル
    read_around_text_channel BIGINT,         -- 読み上げ対象のテキストチャンネル
    PRIMARY KEY (server_id, bot_id),
    foreign key (server_id) references server_info (server_id),
    foreign key (bot_id) references bot_info (bot_id)
);

-- 辞書の利用データ
create table dict_use_data
(
    server_id BIGINT       NOT NULL,-- サーバーID
    dict_id   VARCHAR(100) NOT NULL,-- 辞書ID(文字列で指定)
    priority  INT          NOT NULL,-- 優先度(-1の場合無効)
    PRIMARY KEY (server_id, dict_id),
    foreign key (server_id) references server_info (server_id)
);

-- サーバー辞書データ
create table server_dict_data
(
    server_id   BIGINT NOT NULL,-- サーバーID
    target_word NVARCHAR(100) NOT NULL,-- 置き換え対象の文字
    read_word   NVARCHAR(100) NOT NULL,-- 実際に読み上げる文字
    PRIMARY KEY (server_id, target_word),
    foreign key (server_id) references server_info (server_id)
);

-- 共通辞書データ
create table global_dict_data
(
    target_word NVARCHAR(100) NOT NULL PRIMARY KEY, -- 置き換え対象の文字
    read_word   NVARCHAR(100) NOT NULL              -- 実際に読み上げる文字
);

