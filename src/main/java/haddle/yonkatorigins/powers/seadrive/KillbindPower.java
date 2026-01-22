package haddle.yonkatorigins.powers.seadrive;

import haddle.yonkatorigins.registry.YODamageSources;
import io.github.apace100.apoli.power.ActiveCooldownPower;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.util.HudRender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.function.Consumer;

public class KillbindPower extends ActiveCooldownPower {

    public KillbindPower(PowerType<?> type, LivingEntity entity, int cooldownDuration, HudRender hudRender, Consumer<Entity> activeFunction) {
        super(type, entity, cooldownDuration, hudRender, activeFunction);
    }

    @Override
    public void onUse() {
        PlayerEntity player = (PlayerEntity) entity;
        entity.damage(YODamageSources.of(entity.getWorld(), YODamageSources.KILLBIND), Float.MAX_VALUE);
    }
}
