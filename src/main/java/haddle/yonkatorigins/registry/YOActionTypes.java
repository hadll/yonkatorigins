package haddle.yonkatorigins.registry;

import haddle.yonkatorigins.YonkatOrigins;
import io.github.apace100.apoli.Apoli;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.apoli.power.factory.condition.ConditionFactory;
import io.github.apace100.apoli.registry.ApoliRegistries;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.entity.Entity;
import net.minecraft.registry.Registry;

public class YOActionTypes {

    private static void register(ActionFactory<Entity> actionFactory) {
        Registry.register(ApoliRegistries.ENTITY_ACTION, actionFactory.getSerializerId(), actionFactory);
    }

    private static void register(ConditionFactory<Entity> conditionFactory) {
        Registry.register(ApoliRegistries.ENTITY_CONDITION, conditionFactory.getSerializerId(), conditionFactory);
    }

    public static void init(){
        register(new ActionFactory<>(Apoli.identifier("add_fish"), new SerializableData().add("value", SerializableDataTypes.DOUBLE),
            (data, entity) -> {
                YOComponents.FISH.get(entity).changeFish(data.getDouble("amount"));
            }));
        register(new ConditionFactory<>(Apoli.identifier("has_fish"), new SerializableData().add("value",  SerializableDataTypes.DOUBLE),
                (data, entity) -> {
                    return (YOComponents.FISH.get(entity).getFish() >= (double) data.get("amount"));
                }));
    }
}
