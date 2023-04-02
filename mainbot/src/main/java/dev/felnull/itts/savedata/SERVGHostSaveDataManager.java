package dev.felnull.itts.savedata;

import dev.felnull.itts.Main;
import dev.felnull.itts.core.savedata.*;
import dev.felnull.itts.savedata.db.SaveDataDAO;
import dev.felnull.itts.savedata.db.entry.BotStateDataEntry;
import dev.felnull.itts.savedata.db.entry.GlobalDictDataEntry;
import dev.felnull.itts.savedata.db.entry.ServerDictDataEntry;
import dev.felnull.itts.savedata.impl.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SERVGHostSaveDataManager implements SaveDataAccess {
    private static final SERVGHostSaveDataManager INSTANCE = new SERVGHostSaveDataManager();
    private BotInfoSaveService botInfoSaveService;
    private ServerInfoSaveService serverInfoSaveService;
    private ServerInfoSaveDataManage<ServerDataImpl> serverData;
    private ServerInfoSaveDataManage<ServerUserDataImpl> serverUserData;
    private SaveDataDAO dao;

    public static SERVGHostSaveDataManager getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean init() {
        dao = new SaveDataDAO(Main.DB_CONFIG.getUrl(), Main.DB_CONFIG.getUser(), Main.DB_CONFIG.getPassword());
        botInfoSaveService = new BotInfoSaveService(dao);
        serverInfoSaveService = new ServerInfoSaveService(dao);

        serverData = new ServerInfoSaveDataManage<>(serverInfoSaveService, (id) -> new ServerDataImpl(id, dao));
        //serverUserData = new ServerInfoSaveDataManage<>(serverInfoSaveService, (id) -> new ServerUserDataImpl(id, dao));

        return true;
    }

    @Override
    public @NotNull ServerData getServerData(long serverId) {
        return serverData.get(serverId);
    }

    @Override
    public @NotNull ServerUserData getServerUserData(long serverId, long userId) {
        return new ServerUserDataImpl(serverId, userId, dao);
    }

    @Override
    public @NotNull DictUseData getDictUseData(long l, @NotNull String s) {
        return new DictUseDataImpl(l, s, dao);
    }

    @Override
    public @NotNull BotStateData getBotStateData(long l) {
        return new BotStateDataImpl(l, getSelfBotId(), dao);
    }

    @Override
    public @NotNull @Unmodifiable Map<Long, BotStateData> getAllBotStateData() {

        List<BotStateDataEntry> stateDataEntries;

        try (Connection con = dao.connect()) {
            stateDataEntries = dao.selectBotStateData(con, getSelfBotId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return stateDataEntries.stream()
                .map(it -> new BotStateDataImpl(it.getServerId(), it.getBotId(), dao))
                .collect(Collectors.toMap(BotStateDataImpl::getServerId, botStateData -> botStateData));
    }

    private long getSelfBotId() {
        return Main.RUNTIME.getBot().getJDA().getSelfUser().getIdLong();
    }

    @Override
    public @NotNull @Unmodifiable List<DictData> getAllServerDictData(long l) {
        List<ServerDictDataEntry> dictDataEntries;

        try (Connection con = dao.connect()) {
            dictDataEntries = dao.selectServerDictData(con, l);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return dictDataEntries.stream()
                .map(data -> (DictData) new ServerDictDataImpl(data))
                .toList();
    }

    @Override
    public @Nullable DictData getServerDictData(long l, @NotNull String s) {
        Optional<ServerDictDataEntry> dictDataEntry;

        try (Connection con = dao.connect()) {
            dictDataEntry = dao.selectServerDictData(con, l, s);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dictDataEntry
                .map(ServerDictDataImpl::new)
                .orElse(null);
    }

    @Override
    public void addServerDictData(long l, @NotNull String s, @NotNull String s1) {
        try (Connection con = dao.connect()) {
            DictData pre = getServerDictData(l, s);

            if (pre != null)
                removeServerDictData(l, s);

            dao.insertServerDictData(con, new ServerDictDataEntry(l, s, s1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeServerDictData(long l, @NotNull String s) {
        try (Connection con = dao.connect()) {
            dao.deleteServerDictData(con, l, s);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NotNull @Unmodifiable List<DictData> getAllGlobalDictData() {
        List<GlobalDictDataEntry> dictDataEntries;

        try (Connection con = dao.connect()) {
            dictDataEntries = dao.selectGlobalDictData(con);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dictDataEntries.stream()
                .map(data -> (DictData) new GlobalDictDataImpl(data))
                .toList();
    }

    @Override
    public @Nullable DictData getGlobalDictData(@NotNull String s) {
        Optional<GlobalDictDataEntry> dictDataEntry;

        try (Connection con = dao.connect()) {
            dictDataEntry = dao.selectGlobalDictData(con, s);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dictDataEntry
                .map(GlobalDictDataImpl::new)
                .orElse(null);
    }

    @Override
    public void addGlobalDictData(@NotNull String s, @NotNull String s1) {
        try (Connection con = dao.connect()) {
            DictData pre = getGlobalDictData(s);

            if (pre != null)
                removeGlobalDictData(s);

            dao.insertGlobalDictData(con, new GlobalDictDataEntry(s, s1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeGlobalDictData(@NotNull String s) {
        try (Connection con = dao.connect()) {
            dao.deleteGlobalDictData(con, s);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NotNull @Unmodifiable List<Long> getAllDenyUser(long l) {
        try (Connection con = dao.connect()) {
            return dao.selectAllDenyUser(con, l);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
