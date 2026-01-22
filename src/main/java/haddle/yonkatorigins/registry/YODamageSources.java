package haddle.yonkatorigins.registry;

import haddle.yonkatorigins.YonkatOrigins;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class YODamageSources {
    public static final RegistryKey<DamageType> KILLBIND = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(YonkatOrigins.MOD_ID, "killbind_damage"));

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }
}
