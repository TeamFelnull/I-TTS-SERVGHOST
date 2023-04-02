package dev.felnull.itts.savedata;

import dev.felnull.itts.Main;
import dev.felnull.itts.savedata.db.entry.ServerInfoEntry;
import net.dv8tion.jda.api.entities.Guild;

import java.util.function.Function;

public class ServerInfoSaveDataManage<S extends DBSaveDataBase<?>> extends DBInfoSaveDataManage<ServerInfoEntry, LongInfoSaveDataKey, S> {
    public ServerInfoSaveDataManage(InfoSaveService<ServerInfoEntry> infoSaveService, Function<LongInfoSaveDataKey, S> newSaveDataFactory) {
        super(infoSaveService, newSaveDataFactory);
    }

    @Override
    protected ServerInfoEntry createInitInfo(LongInfoSaveDataKey id) {
        Guild guild = Main.RUNTIME.getBot().getJDA().getGuildById(id.getInfoId());
        if (guild == null)
            throw new RuntimeException("Guild does not exist");
        return new ServerInfoEntry(id.getInfoId(), guild.getName());
    }
}
