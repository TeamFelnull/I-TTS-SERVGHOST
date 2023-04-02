package dev.felnull.itts.savedata.db;

import dev.felnull.itts.savedata.db.entry.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SaveDataDAO {
    private final String url;
    private final String user;
    private final String passsword;

    public SaveDataDAO(String url, String user, String passsword) {
        this.url = url;
        this.user = user;
        this.passsword = passsword;
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, passsword);
    }

    public Optional<ServerDataEntry> selectServerData(Connection con, long serverId) throws SQLException {
        var sql = "select * from server_data where server_id=? limit 1";
        var ps = con.prepareCall(sql);
        ps.setLong(1, serverId);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next())
                return Optional.of(new ServerDataEntry(rs.getLong("server_id"), rs.getString("default_voice_type"),
                        rs.getString("ignore_regex"), rs.getBoolean("need_join"), rs.getBoolean("overwrite_aloud"),
                        rs.getBoolean("notify_move"), rs.getInt("read_limit"), rs.getInt("name_read_limit")));
        }
        return Optional.empty();
    }

    public void insertServerData(Connection con, ServerDataEntry serverData) throws SQLException {
        var sql = """
                insert into server_data(server_id, default_voice_type, ignore_regex, need_join, overwrite_aloud, notify_move, read_limit, name_read_limit)
                values (?, ?, ?, ?, ?, ?, ?, ?)
                """;
        var ps = con.prepareCall(sql);
        ps.setLong(1, serverData.getServerId());
        ps.setString(2, serverData.getDefaultVoiceType());
        ps.setString(3, serverData.getIgnoreRegex());
        ps.setBoolean(4, serverData.isNeedJoin());
        ps.setBoolean(5, serverData.isOverwriteAloud());
        ps.setBoolean(6, serverData.isNotifyMove());
        ps.setInt(7, serverData.getReadLimit());
        ps.setInt(8, serverData.getNameReadLimit());

        ps.execute();
    }

    public void updateServerData(Connection con, ServerDataEntry serverData) throws SQLException {
        var sql = """
                update server_data
                set default_voice_type=?,
                    ignore_regex=?,
                    need_join=?,
                    overwrite_aloud=?,
                    notify_move=?,
                    read_limit=?,
                    name_read_limit=?
                where server_id = ?
                """;
        var ps = con.prepareCall(sql);
        ps.setString(1, serverData.getDefaultVoiceType());
        ps.setString(2, serverData.getIgnoreRegex());
        ps.setBoolean(3, serverData.isNeedJoin());
        ps.setBoolean(4, serverData.isOverwriteAloud());
        ps.setBoolean(5, serverData.isNotifyMove());
        ps.setInt(6, serverData.getReadLimit());
        ps.setInt(7, serverData.getNameReadLimit());
        ps.setLong(8, serverData.getServerId());

        ps.execute();
    }

    public Optional<ServerUserDataEntry> selectServerUserData(Connection con, long serverId, long userId) throws SQLException {
        var sql = "select * from server_user_data where server_id=? and user_id=? limit 1";
        var ps = con.prepareCall(sql);
        ps.setLong(1, serverId);
        ps.setLong(2, userId);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next())
                return Optional.of(new ServerUserDataEntry(rs.getLong("server_id"), rs.getLong("user_id"),
                        rs.getString("voice_type"), rs.getBoolean("deny"), rs.getString("nick_name")));
        }
        return Optional.empty();
    }

    public void insertServerUserData(Connection con, ServerUserDataEntry serverUserData) throws SQLException {
        var sql = """
                insert into server_user_data(server_id, user_id, voice_type, deny, nick_name)
                values (?, ?, ?, ?, ?)
                """;

        var ps = con.prepareCall(sql);
        ps.setLong(1, serverUserData.getServerId());
        ps.setLong(2, serverUserData.getUserId());
        ps.setString(3, serverUserData.getVoiceType());
        ps.setBoolean(4, serverUserData.isDeny());
        ps.setString(5, serverUserData.getNickName());

        ps.execute();
    }

    public void updateServerUserData(Connection con, ServerUserDataEntry serverUserData) throws SQLException {
        var sql = """
                update server_user_data
                set voice_type=?,
                    deny=?,
                    nick_name=?
                where server_id = ?
                  and user_id = ?
                """;

        var ps = con.prepareCall(sql);
        ps.setString(1, serverUserData.getVoiceType());
        ps.setBoolean(2, serverUserData.isDeny());
        ps.setString(3, serverUserData.getNickName());
        ps.setLong(4, serverUserData.getServerId());
        ps.setLong(5, serverUserData.getUserId());

        ps.execute();
    }

    public Optional<BotStateDataEntry> selectBotStateData(Connection con, long serverId, long botId) throws SQLException {
        var sql = "select * from bot_state_data where server_id=? and bot_id=? limit 1";
        var ps = con.prepareCall(sql);
        ps.setLong(1, serverId);
        ps.setLong(2, botId);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next())
                return Optional.of(new BotStateDataEntry(rs.getLong("server_id"), rs.getLong("bot_id"),
                        rs.getLong("connected_audio_channel"), rs.getLong("read_around_text_channel")));
        }
        return Optional.empty();
    }

    public void insertBotStateData(Connection con, BotStateDataEntry botStateData) throws SQLException {
        var sql = """
                insert into bot_state_data(server_id, bot_id, connected_audio_channel, read_around_text_channel)
                values (?, ?, ?, ?)
                """;

        var ps = con.prepareCall(sql);
        ps.setLong(1, botStateData.getServerId());
        ps.setLong(2, botStateData.getBotId());
        ps.setLong(3, botStateData.getConnectedAudioChannel());
        ps.setLong(4, botStateData.getReadAroundTextChannel());

        ps.execute();
    }

    public void updateBotStateData(Connection con, BotStateDataEntry botStateData) throws SQLException {
        var sql = """
                update bot_state_data
                set connected_audio_channel=?,
                    read_around_text_channel=?
                where server_id = ?
                  and bot_id = ?
                """;

        var ps = con.prepareCall(sql);
        ps.setLong(1, botStateData.getConnectedAudioChannel());
        ps.setLong(2, botStateData.getReadAroundTextChannel());
        ps.setLong(3, botStateData.getServerId());
        ps.setLong(4, botStateData.getBotId());

        ps.execute();
    }

    public List<BotStateDataEntry> selectBotStateData(Connection con, long botId) throws SQLException {
        var sql = "select * from bot_state_data where bot_id=?";

        var ps = con.prepareCall(sql);
        ps.setLong(1, botId);

        List<BotStateDataEntry> ret = new ArrayList<>();

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next())
                ret.add(new BotStateDataEntry(rs.getLong("server_id"), rs.getLong("bot_id"),
                        rs.getLong("connected_audio_channel"), rs.getLong("read_around_text_channel")));
        }

        return ret;
    }

    public Optional<DictUseDataEntry> selectDictUseData(Connection con, long serverId, String dictId) throws SQLException {
        var sql = "select * from dict_use_data where server_id =? and dict_id=? limit 1";
        var ps = con.prepareCall(sql);
        ps.setLong(1, serverId);
        ps.setString(2, dictId);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next())
                return Optional.of(new DictUseDataEntry(rs.getLong("server_id"), rs.getString("dict_id"),
                        rs.getInt("priority")));
        }

        return Optional.empty();
    }

    public void insertDictUseData(Connection con, DictUseDataEntry dictUseData) throws SQLException {
        var sql = """
                insert into dict_use_data(server_id, dict_id, priority)
                values (?, ?, ?)
                """;

        var ps = con.prepareCall(sql);
        ps.setLong(1, dictUseData.getServerId());
        ps.setString(2, dictUseData.getDictId());
        ps.setInt(3, dictUseData.getPriority());

        ps.execute();
    }

    public void updateDictUseData(Connection con, DictUseDataEntry dictUseData) throws SQLException {
        var sql = """
                update dict_use_data
                set priority=?
                where server_id = ?
                  and dict_id = ?
                """;

        var ps = con.prepareCall(sql);
        ps.setInt(1, dictUseData.getPriority());
        ps.setLong(2, dictUseData.getServerId());
        ps.setString(3, dictUseData.getDictId());

        ps.execute();
    }

    public List<DictUseDataEntry> selectDictUseData(Connection con, long serverId) throws SQLException {
        var sql = "select * from dict_use_data where server_id=?";

        var ps = con.prepareCall(sql);
        ps.setLong(1, serverId);

        List<DictUseDataEntry> ret = new ArrayList<>();

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next())
                ret.add(new DictUseDataEntry(rs.getLong("server_id"), rs.getString("dict_id"), rs.getInt("priority")));
        }

        return ret;
    }

    public void deleteDictUseData(Connection con, long serverId, String dictId) throws SQLException {
        var sql = "delete from dict_use_data where server_id=? and dict_id=?";
        var ps = con.prepareCall(sql);
        ps.setLong(1, serverId);
        ps.setString(2, dictId);

        ps.execute();
    }

    public List<ServerDictDataEntry> selectServerDictData(Connection con, long serverId) throws SQLException {
        var sql = "select * from server_dict_data where server_id=?";
        var ps = con.prepareCall(sql);
        ps.setLong(1, serverId);

        List<ServerDictDataEntry> ret = new ArrayList<>();

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next())
                ret.add(new ServerDictDataEntry(rs.getLong("server_id"), rs.getString("target_word"), rs.getString("read_word")));
        }

        return ret;
    }

    public Optional<ServerDictDataEntry> selectServerDictData(Connection con, long serverId, String targetWord) throws SQLException {
        var sql = "select * from server_dict_data where server_id =? and target_word=? limit 1";
        var ps = con.prepareCall(sql);
        ps.setLong(1, serverId);
        ps.setString(2, targetWord);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next())
                return Optional.of(new ServerDictDataEntry(rs.getLong("server_id"), rs.getString("target_word"), rs.getString("read_word")));
        }

        return Optional.empty();
    }

    public void insertServerDictData(Connection con, ServerDictDataEntry dictDataEntry) throws SQLException {
        var sql = """
                insert into server_dict_data(server_id, target_word, read_word)
                values (?, ?, ?, ?)
                """;
        var ps = con.prepareCall(sql);

        ps.setLong(1, dictDataEntry.getServerId());
        ps.setString(2, dictDataEntry.getTargetWord());
        ps.setString(3, dictDataEntry.getReadWord());

        ps.execute();
    }

    public void deleteServerDictData(Connection con, long serverId, String targetWord) throws SQLException {
        var sql = "delete from server_dict_data where server_id=? and target_word=?";
        var ps = con.prepareCall(sql);
        ps.setLong(1, serverId);
        ps.setString(2, targetWord);

        ps.execute();
    }

    public List<GlobalDictDataEntry> selectGlobalDictData(Connection con) throws SQLException {
        var sql = "select * from global_dict_data";
        var ps = con.prepareCall(sql);

        List<GlobalDictDataEntry> ret = new ArrayList<>();

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next())
                ret.add(new GlobalDictDataEntry(rs.getString("target_word"), rs.getString("read_word")));
        }

        return ret;
    }

    public Optional<GlobalDictDataEntry> selectGlobalDictData(Connection con, String targetWord) throws SQLException {
        var sql = "select * from global_dict_data where target_word=? limit 1";
        var ps = con.prepareCall(sql);
        ps.setString(1, targetWord);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next())
                return Optional.of(new GlobalDictDataEntry(rs.getString("target_word"), rs.getString("read_word")));
        }

        return Optional.empty();
    }

    public void insertGlobalDictData(Connection con, GlobalDictDataEntry dictDataEntry) throws SQLException {
        var sql = """
                insert into global_dict_data(target_word, read_word)
                values (?, ?)
                """;
        var ps = con.prepareCall(sql);

        ps.setString(1, dictDataEntry.getTargetWord());
        ps.setString(2, dictDataEntry.getReadWord());

        ps.execute();
    }

    public void deleteGlobalDictData(Connection con, String targetWord) throws SQLException {
        var sql = "delete from global_dict_data where target_word=?";
        var ps = con.prepareCall(sql);
        ps.setString(1, targetWord);

        ps.execute();
    }

    public List<Long> selectAllDenyUser(Connection con, long serverId) throws SQLException {
        var sql = "select user_id from server_user_data where server_id=? and deny";
        var ps = con.prepareCall(sql);
        ps.setLong(1, serverId);

        List<Long> ret = new ArrayList<>();

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next())
                ret.add(rs.getLong("user_id"));
        }

        return ret;
    }

    public Optional<BotInfoEntry> selectBotInfo(Connection con, long botId) throws SQLException {
        var sql = "select * from bot_info where bot_id=? limit 1";
        var ps = con.prepareCall(sql);
        ps.setLong(1, botId);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next())
                return Optional.of(new BotInfoEntry(rs.getLong("bot_id"), rs.getString("bot_name")));
        }

        return Optional.empty();
    }

    public void insertBotInfo(Connection con, BotInfoEntry botInfo) throws SQLException {
        var sql = "insert into bot_info(bot_id, bot_name) value (?, ?)";
        var ps = con.prepareCall(sql);
        ps.setLong(1, botInfo.getBotId());
        ps.setString(2, botInfo.getBotName());

        ps.execute();
    }

    public void updateBotInfo(Connection con, BotInfoEntry botInfo) throws SQLException {
        var sql = """
                update bot_info
                set bot_name=?
                where bot_id = ?
                """;

        var ps = con.prepareCall(sql);
        ps.setString(1, botInfo.getBotName());
        ps.setLong(2, botInfo.getBotId());

        ps.execute();
    }

    public void deleteBotInfo(Connection con, long botId) throws SQLException {
        var sql = """
                delete from bot_info
                where bot_id=?
                """;

        var ps = con.prepareCall(sql);
        ps.setLong(1, botId);

        ps.execute();
    }

    public Optional<ServerInfoEntry> selectServerInfo(Connection con, long serverId) throws SQLException {
        var sql = "select * from server_info where server_id=? limit 1";
        var ps = con.prepareCall(sql);
        ps.setLong(1, serverId);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next())
                return Optional.of(new ServerInfoEntry(rs.getLong("server_id"), rs.getString("server_name")));
        }

        return Optional.empty();
    }

    public void insertServerInfo(Connection con, ServerInfoEntry serverInfo) throws SQLException {
        var sql = "insert into server_info(server_id, server_name) values (?, ?)";
        var ps = con.prepareCall(sql);
        ps.setLong(1, serverInfo.getServerId());
        ps.setString(2, serverInfo.getServerName());

        ps.execute();
    }

    public void updateServerInfo(Connection con, ServerInfoEntry serverInfo) throws SQLException {
        var sql = """
                update server_info
                set server_name=?
                where server_id = ?
                """;

        var ps = con.prepareCall(sql);
        ps.setString(1, serverInfo.getServerName());
        ps.setLong(2, serverInfo.getServerId());

        ps.execute();
    }

    public void deleteServerInfo(Connection con, long serverId) throws SQLException {
        var sql = """
                delete from server_info
                where server_id=?
                """;

        var ps = con.prepareCall(sql);
        ps.setLong(1, serverId);

        ps.execute();
    }
}
