package haddle.yonkatorigins.components;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerFishComponent implements AutoSyncedComponent, FishComponent {

    private double fish = 0.0;
    private double maxFish = 100.0;

    private final PlayerEntity player;

    public PlayerFishComponent(PlayerEntity player){
        this.player = player;
    }

    @Override
    public boolean shouldSyncWith(ServerPlayerEntity player) {
        return AutoSyncedComponent.super.shouldSyncWith(player);
    }

    @Override
    public void writeSyncPacket(PacketByteBuf buf, ServerPlayerEntity recipient) {
        AutoSyncedComponent.super.writeSyncPacket(buf, recipient);
    }

    @Override
    public void applySyncPacket(PacketByteBuf buf) {
        AutoSyncedComponent.super.applySyncPacket(buf);
    }

    @Override
    public void readFromNbt(NbtCompound nbtCompound) {
        this.fish = nbtCompound.getDouble("fish");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {
        nbtCompound.putDouble("fish", this.fish);
    }

    @Override
    public void setMaxFish(double max) {
        this.maxFish = max;
    }

    @Override
    public void changeFish(double amount) {
        fish = Math.min(Math.max(fish+amount, 0),maxFish);
    }

    @Override
    public double getMaxFish() {
        return maxFish;
    }

    @Override
    public double getFish() {
        return fish;
    }
}
