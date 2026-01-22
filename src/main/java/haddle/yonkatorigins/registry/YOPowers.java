package haddle.yonkatorigins.registry;

import haddle.yonkatorigins.YonkatOrigins;
import haddle.yonkatorigins.powers.seadrive.KillbindPower;
import io.github.apace100.apoli.data.ApoliDataTypes;
import io.github.apace100.apoli.power.Active;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.apoli.registry.ApoliRegistries;
import io.github.apace100.apoli.util.HudRender;
import io.github.apace100.calio.data.SerializableData;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class YOPowers {

    public static final PowerFactory<Power> KILLBIND = new PowerFactory<>(new Identifier(YonkatOrigins.MOD_ID, "killbind"), new SerializableData().add("key", ApoliDataTypes.BACKWARDS_COMPATIBLE_KEY, new Active.Key()), data -> (type, entity) -> {
        KillbindPower power = new KillbindPower(type, entity, 20, HudRender.DONT_RENDER, null);
        power.setKey((Active.Key) data.get("key"));
        return power;
    }).allowCondition();
    private static void register(PowerFactory<?> powerFactory) {
        Registry.register(ApoliRegistries.POWER_FACTORY, powerFactory.getSerializerId(), powerFactory);
    }

    public static void init(){
        Registry.register(ApoliRegistries.POWER_FACTORY, KILLBIND.getSerializerId(), KILLBIND);
        YonkatOrigins.LOGGER.info("Powers Registered");
    }
}
