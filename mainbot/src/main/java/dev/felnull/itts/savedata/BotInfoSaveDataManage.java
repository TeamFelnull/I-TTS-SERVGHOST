package dev.felnull.itts.savedata;

import dev.felnull.itts.Main;
import dev.felnull.itts.savedata.db.entry.BotInfoEntry;

import java.util.function.Function;

public class BotInfoSaveDataManage<S extends DBSaveDataBase<?>> extends DBInfoSaveDataManage<BotInfoEntry, LongInfoSaveDataKey, S> {
    public BotInfoSaveDataManage(InfoSaveService<BotInfoEntry> infoSaveService, Function<LongInfoSaveDataKey, S> newSaveDataFactory) {
        super(infoSaveService, newSaveDataFactory);
    }

    @Override
    protected BotInfoEntry createInitInfo(LongInfoSaveDataKey id) {
        var self = Main.RUNTIME.getBot().getJDA().getSelfUser();
        if (self.getIdLong() != id.getInfoId())
            throw new RuntimeException("BOT other than self");
        return new BotInfoEntry(id.getInfoId(), self.getName());
    }
}
