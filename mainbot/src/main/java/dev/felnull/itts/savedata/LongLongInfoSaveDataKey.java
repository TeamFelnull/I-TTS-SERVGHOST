package dev.felnull.itts.savedata;

public record LongLongInfoSaveDataKey(long id, long secondaryId) implements InfoSaveDataKey {
    @Override
    public long getInfoId() {
        return id;
    }
}
