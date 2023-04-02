package dev.felnull.itts.savedata;

import dev.felnull.itts.savedata.db.SaveDataDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;

public abstract class DBSaveDataBase<T> {
    protected final SaveDataDAO dao;
    private final T initData;

    public DBSaveDataBase(SaveDataDAO dao, T initData) {
        this.dao = dao;
        this.initData = initData;
    }

    protected abstract Optional<T> selectData(Connection connection) throws SQLException;

    protected abstract void insertData(Connection connection, T data) throws SQLException;

    protected abstract void updateData(Connection connection, T data) throws SQLException;

    protected T currentData() {
        try (Connection con = dao.connect()) {
            return selectData(con).orElse(initData);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void insertOrUpdateData(Function<T, T> updateData) {
        try (Connection con = dao.connect()) {
            Optional<T> currentData = selectData(con);
            T newData = updateData.apply(currentData.orElse(initData));

            if (currentData.isPresent()) {
                updateData(con, newData);
            } else {
                insertData(con, newData);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
