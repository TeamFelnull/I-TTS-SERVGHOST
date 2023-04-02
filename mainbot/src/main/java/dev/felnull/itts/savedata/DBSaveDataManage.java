package dev.felnull.itts.savedata;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class DBSaveDataManage<K extends Record, S extends DBSaveDataBase<?>> {
    private final Map<K, S> saveDataEntries = new ConcurrentHashMap<>();
    private final Function<K, S> newSaveDataFactory;

    public DBSaveDataManage(Function<K, S> newSaveDataFactory) {
        this.newSaveDataFactory = newSaveDataFactory;
    }

    public S get(K key) {
        return saveDataEntries.get(key);
    }
}
