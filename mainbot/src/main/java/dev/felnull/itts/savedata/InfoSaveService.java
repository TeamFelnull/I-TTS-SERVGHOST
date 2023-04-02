package dev.felnull.itts.savedata;

import dev.felnull.itts.savedata.db.SaveDataDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public abstract class InfoSaveService<E> {
    private final Map<Long, E> infoEntries = new ConcurrentHashMap<>();
    protected final SaveDataDAO dao;

    public InfoSaveService(SaveDataDAO dao) {
        this.dao = dao;
    }

    public E checkAndGetInfo(long id, Supplier<E> initInfo) {
        return infoEntries.computeIfAbsent(id, it -> selectOrInsertInfo(id, initInfo));
    }

    private E selectOrInsertInfo(long id, Supplier<E> initInfo) {
        try (Connection con = dao.connect()) {
            Optional<E> info = selectInfo(con, id);

            if (info.isPresent())
                return info.get();

            E newInfo = initInfo.get();
            insertInfo(con, newInfo);

            return newInfo;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    abstract protected Optional<E> selectInfo(Connection connection, long id) throws SQLException;

    abstract protected void insertInfo(Connection connection, E info) throws SQLException;
}
