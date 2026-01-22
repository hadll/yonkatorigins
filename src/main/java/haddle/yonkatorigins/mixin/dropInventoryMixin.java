package haddle.yonkatorigins.mixin;

import haddle.yonkatorigins.registry.YODamageSources;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class dropInventoryMixin {
    @Inject(
            method = "dropInventory",
            at = @At("HEAD"),
            cancellable = true
    )
    private void dropInventory(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity)(Object)this;
        DamageSource lastSource = player.getRecentDamageSource();
        if (lastSource != null && lastSource.isOf(YODamageSources.KILLBIND)) {
            ci.cancel();
        }
    }
}
