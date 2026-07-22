package net.ethelred.pillow.fabric;

import net.ethelred.pillow.ModContent;
import net.ethelred.pillow.PillowItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

public class PillowFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        PillowItem item = new PillowItem(new Item.Properties().stacksTo(1));

        Registry.register(
            BuiltInRegistries.ITEM,
            ResourceLocation.fromNamespaceAndPath("pillow", "pillow"),
            item
        );
        ModContent.PILLOW = () -> item;

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT)
            .register(entries -> entries.accept(item));
    }
}
