package dev.felnull.itts.savedata;

public record LongInfoSaveDataKey(long id) implements InfoSaveDataKey {
    @Override
    public long getInfoId() {
        return id;
    }
}
