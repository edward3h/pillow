package net.ethelred.pillow.neoforge;

import net.ethelred.pillow.ModContent;
import net.ethelred.pillow.PillowItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod("pillow")
public class PillowNeoForge {

    public PillowNeoForge(IEventBus modEventBus) {
        DeferredRegister<Item> items = DeferredRegister.create(Registries.ITEM, "pillow");

        ModContent.PILLOW = items.register("pillow",
            () -> new PillowItem(new Item.Properties().stacksTo(1))
        );

        modEventBus.addListener((BuildCreativeModeTabContentsEvent event) -> {
            if (event.getTabKey() == CreativeModeTabs.COMBAT) {
                event.accept(ModContent.PILLOW.get());
            }
        });

        items.register(modEventBus);
    }
}
