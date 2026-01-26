package haddle.yonkatorigins.item;

import haddle.yonkatorigins.YonkatOrigins;
import haddle.yonkatorigins.item.papercraft.FishyPaperItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import javax.print.DocFlavor;


public class YOModItems {

    public static final Item Fishy_Paper = registerItem( "fishy_paper", new FishyPaperItem(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(YonkatOrigins.MOD_ID, name), item);
    }

    public static void init() {

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries ->{
            entries.add(Fishy_Paper);
        });
        YonkatOrigins.LOGGER.info("Registered Items");
    }
}
