package haddle.yonkatorigins.powers.seadrive;

import haddle.yonkatorigins.components.FishComponent;
import haddle.yonkatorigins.registry.YOComponents;
import haddle.yonkatorigins.registry.YODamageSources;
import io.github.apace100.apoli.power.ActiveCooldownPower;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.util.HudRender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.function.Consumer;

public class KillbindPower extends ActiveCooldownPower {

    private final double powerCost = 25;

    public KillbindPower(PowerType<?> type, LivingEntity entity, int cooldownDuration, HudRender hudRender, Consumer<Entity> activeFunction) {
        super(type, entity, cooldownDuration, hudRender, activeFunction);
    }

    @Override
    public void onUse() {
        PlayerEntity player = (PlayerEntity) entity;

        FishComponent fish = YOComponents.FISH.get(entity);

        if (player.getHealth() >= player.getMaxHealth()*0.9 && fish.getFish() >= powerCost) {
            fish.changeFish(-powerCost);

            entity.damage(YODamageSources.of(entity.getWorld(), YODamageSources.KILLBIND), Float.MAX_VALUE);
        }
    }



}
