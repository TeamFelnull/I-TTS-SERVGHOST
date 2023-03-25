package dev.felnull.itts.savedata;

import dev.felnull.itts.core.savedata.DictUseData;
import dev.felnull.itts.savedata.db.DictUseDataEntry;
import dev.felnull.itts.savedata.db.SaveDataDAO;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class DictUseDataImpl extends DBEntrySaveData<DictUseDataEntry> implements DictUseData {
    private final long serverId;
    private final String dictId;

    public DictUseDataImpl(long serverId, String dictId, SaveDataDAO dao) {
        super(dao, new DictUseDataEntry(serverId, dictId, DictUseData.initPriority(dictId)));
        this.serverId = serverId;
        this.dictId = dictId;
    }

    @Override
    protected Optional<DictUseDataEntry> selectData(Connection connection) throws SQLException {
        return dao.selectDictUseData(connection, serverId, dictId);
    }

    @Override
    protected void insertData(Connection connection, DictUseDataEntry data) throws SQLException {
        dao.insertDictUseData(connection, data);
    }

    @Override
    protected void updateData(Connection connection, DictUseDataEntry data) throws SQLException {
        dao.updateDictUseData(connection, data);
    }

    @Override
    public @NotNull String getDictId() {
        return dictId;
    }

    @Override
    public int getPriority() {
        return currentData().getPriority();
    }

    @Override
    public void setPriority(int i) {
        insertOrUpdateData(data -> {
            data.setPriority(i);
            return data;
        });
    }
}
