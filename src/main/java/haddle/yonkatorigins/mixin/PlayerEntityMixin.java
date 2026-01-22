package haddle.yonkatorigins.mixin;

import haddle.yonkatorigins.util.SeadriveKillbindHolder;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Inject(
            method = "dropInventory",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onDropInventory(CallbackInfo ci) {
        if ((Object) this instanceof SeadriveKillbindHolder holder) {
            if (holder.seadrive$consumeKillbind()) {
                System.out.println("inventory drop cancelled");
                ci.cancel();
            }
        }
    }
}
