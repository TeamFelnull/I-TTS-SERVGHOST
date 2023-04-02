package dev.felnull.itts.savedata;

import java.util.function.Function;

public abstract class DBInfoSaveDataManage<E, K extends Record & InfoSaveDataKey, S extends DBSaveDataBase<?>> extends DBSaveDataManage<K, S> {
    private final InfoSaveService<E> infoSaveService;

    public DBInfoSaveDataManage(InfoSaveService<E> infoSaveService, Function<K, S> newSaveDataFactory) {
        super(newSaveDataFactory);
        this.infoSaveService = infoSaveService;
    }

    @Override
    public S get(K key) {
        infoSaveService.checkAndGetInfo(key.getInfoId(), () -> createInitInfo(key));
        return super.get(key);
    }

    abstract protected E createInitInfo(K id);
}
