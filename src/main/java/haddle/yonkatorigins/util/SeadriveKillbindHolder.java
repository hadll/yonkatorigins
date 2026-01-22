package haddle.yonkatorigins.util;

import net.minecraft.server.network.ServerPlayerEntity;

public interface SeadriveKillbindHolder {
    boolean seadrive$consumeKillbind();
    void seadrive$storeInventory();
    void seadrive$restoreInventory(ServerPlayerEntity newPlayer);
}
