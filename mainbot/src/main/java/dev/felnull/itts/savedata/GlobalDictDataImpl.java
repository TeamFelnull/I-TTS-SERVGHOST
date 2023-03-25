package dev.felnull.itts.savedata;

import dev.felnull.itts.core.savedata.DictData;
import dev.felnull.itts.savedata.db.GlobalDictDataEntry;
import org.jetbrains.annotations.NotNull;

public record GlobalDictDataImpl(GlobalDictDataEntry dictDataEntry) implements DictData {
    @Override
    public @NotNull String getTarget() {
        return dictDataEntry.getTargetWord();
    }

    @Override
    public @NotNull String getRead() {
        return dictDataEntry.getReadWord();
    }
}
