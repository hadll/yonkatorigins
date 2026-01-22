package haddle.yonkatorigins.registry;

import haddle.yonkatorigins.YonkatOrigins;
import io.github.apace100.apoli.Apoli;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.apoli.registry.ApoliRegistries;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.entity.Entity;
import net.minecraft.registry.Registry;

public class YOActionTypes {

    private static void register(ActionFactory<Entity> actionFactory) {
        Registry.register(ApoliRegistries.ENTITY_ACTION, actionFactory.getSerializerId(), actionFactory);
    }

    public static void init(){
        register(new ActionFactory<>(Apoli.identifier("add_fish"), new SerializableData().add("amount", SerializableDataTypes.DOUBLE),
            (data, entity) -> {
                YOComponents.FISH.get(entity).changeFish(data.getDouble("amount"));
            }));
    }
}
