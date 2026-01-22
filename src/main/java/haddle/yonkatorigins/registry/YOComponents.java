package haddle.yonkatorigins.registry;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import haddle.yonkatorigins.YonkatOrigins;
import haddle.yonkatorigins.components.FishComponent;
import haddle.yonkatorigins.components.PlayerFishComponent;
import net.minecraft.util.Identifier;

public class YOComponents implements EntityComponentInitializer {
    public static final ComponentKey<FishComponent> FISH = ComponentRegistry.getOrCreate(Identifier.of(YonkatOrigins.MOD_ID, "fish_component"), FishComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry entityComponentFactoryRegistry) {
        entityComponentFactoryRegistry.registerForPlayers(FISH,player -> new PlayerFishComponent(player), RespawnCopyStrategy.ALWAYS_COPY);
        YonkatOrigins.LOGGER.info("Components Registered");
    }
}
