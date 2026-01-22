package haddle.yonkatorigins.mixin;

import haddle.yonkatorigins.registry.YODamageSources;
import haddle.yonkatorigins.util.SeadriveKillbindHolder;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin implements SeadriveKillbindHolder {

    @Unique
    private boolean seadriveKillbind = false;
    @Unique
    private DefaultedList<ItemStack> seadrive$storedInventory;

    @Inject(
            method = "onDeath(Lnet/minecraft/entity/damage/DamageSource;)V",
            at = @At("HEAD")
    )
    private void onDeath(DamageSource source, CallbackInfo ci) {
        if (source.isOf(YODamageSources.KILLBIND)) {
            seadriveKillbind = true;
            seadrive$storeInventory();
        }
    }

    @Override
    public boolean seadrive$consumeKillbind() {
        boolean value = seadriveKillbind;
        seadriveKillbind = false;
        return value;
    }

    @Override
    public void seadrive$storeInventory() {
        ServerPlayerEntity player = (ServerPlayerEntity)(Object)this;

        seadrive$storedInventory = DefaultedList.ofSize(
                player.getInventory().size(),
                ItemStack.EMPTY
        );

        for (int i = 0; i < player.getInventory().size(); i++) {
            seadrive$storedInventory.set(
                    i,
                    player.getInventory().getStack(i).copy()
            );
        }
    }
    @Inject(
            method = "copyFrom",
            at = @At("TAIL")
    )
    private void onCopyFrom(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo ci) {
        if (oldPlayer instanceof SeadriveKillbindHolder holder) {
            holder.seadrive$restoreInventory((ServerPlayerEntity)(Object)this);
        }
    }

    @Override
    public void seadrive$restoreInventory(ServerPlayerEntity newPlayer) {
        if (seadrive$storedInventory == null) return;

        newPlayer.getInventory().clear();

        for (int i = 0; i < seadrive$storedInventory.size(); i++) {
            newPlayer.getInventory().setStack(
                    i,
                    seadrive$storedInventory.get(i)
            );
        }

        seadrive$storedInventory = null;
    }
}

