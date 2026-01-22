package haddle.yonkatorigins.registry;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import haddle.yonkatorigins.YonkatOrigins;
import haddle.yonkatorigins.components.FishComponent;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.EnumArgumentType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

// getString(ctx, "string")
import static com.mojang.brigadier.arguments.StringArgumentType.getString;
// word()
import static com.mojang.brigadier.arguments.StringArgumentType.word;
// literal("foo")
import static net.minecraft.server.command.CommandManager.literal;
// argument("bar", word())
import static net.minecraft.server.command.CommandManager.argument;

import static net.minecraft.server.command.CommandManager.*;

public class YOCommands {

    enum operations {
        SET,
        CHANGE,
        GET
    }

    public static void init(){
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
            dispatcher.register(literal("fish")
                .requires(source -> source.hasPermissionLevel(2))
                .then(literal("get")
                    .executes(context -> {
                        FishComponent fish = YOComponents.FISH.get(context.getSource());
                        context.getSource().sendFeedback(
                            () -> Text.literal("You have " + fish.getFish()),
                            false
                        );
                        return 1;
                    })
                )
                .then(literal("add")
                    .then(argument("value", DoubleArgumentType.doubleArg())
                        .executes(context -> {
                            double value = DoubleArgumentType.getDouble(context, "value");
                            FishComponent fish = YOComponents.FISH.get(context.getSource());

                            fish.changeFish(value);
                            context.getSource().sendFeedback(
                                () -> Text.literal("Fish set to " + fish.getFish()),
                                false
                            );
                            return 1;
                        })
                    )
                )
            )
        );
    }
}
