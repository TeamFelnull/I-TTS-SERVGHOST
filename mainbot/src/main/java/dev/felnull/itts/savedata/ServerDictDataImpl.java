package dev.felnull.itts.savedata;

import dev.felnull.itts.core.savedata.DictData;
import dev.felnull.itts.savedata.db.ServerDictDataEntry;
import org.jetbrains.annotations.NotNull;

public record ServerDictDataImpl(ServerDictDataEntry dictDataEntry) implements DictData {
    @Override
    public @NotNull String getTarget() {
        return dictDataEntry.getTargetWord();
    }

    @Override
    public @NotNull String getRead() {
        return dictDataEntry.getReadWord();
    }
}
