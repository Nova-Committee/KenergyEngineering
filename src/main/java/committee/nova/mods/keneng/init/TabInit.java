package committee.nova.mods.keneng.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import committee.nova.mods.keneng.Const;
import committee.nova.mods.keneng.core.item.ICanFillGroup;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public class TabInit
{

    public static Set<Supplier<ItemStack>> ITEMS = new HashSet<>();
    public static Set<Supplier<ItemStack>> BLOCKS = new HashSet<>();
    public static Set<Supplier<ItemStack>> MACHINES = new HashSet<>();
    public static Set<Supplier<ItemStack>> TOOLS = new HashSet<>();

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Const.modid);

    public static void fillItems()
    {
        ITEMS.clear();
        BLOCKS.clear();
        MACHINES.clear();
        TOOLS.clear();
        for(RegistryObject<Item> itemObj : ItemInit.regs.values()) {
            ((ICanFillGroup) itemObj.get()).fillGroup();
        }
    }

    public static void doRegistry()
    {
        TABS.register("item", () -> {
            return CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.ten3.item"))
                    .icon(() -> new ItemStack(ItemInit.getItem("tin_ingot")))
                    .displayItems((p, out) -> {
                        fillItems();
                        for(Supplier<ItemStack> s : ITEMS) {
                            out.accept(s.get());
                        }
                    })
                    .build();
        });
        TABS.register("block", () -> {
            return CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.ten3.block"))
                    .icon(() -> new ItemStack(ItemInit.getItem("tin_ore")))
                    .displayItems((p, out) -> {
                        fillItems();
                        for(Supplier<ItemStack> s : BLOCKS) {
                            out.accept(s.get());
                        }
                    })
                    .build();
        });
        TABS.register("machine", () -> {
            return CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.ten3.machine"))
                    .icon(() -> new ItemStack(ItemInit.getItem("machine_smelter")))
                    .displayItems((p, out) -> {
                        fillItems();
                        for(Supplier<ItemStack> s : MACHINES) {
                            out.accept(s.get());
                        }
                    })
                    .build();
        });
        TABS.register("tool", () -> {
            return CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.ten3.tool"))
                    .icon(() -> new ItemStack(ItemInit.getItem("photosyn_levelup")))
                    .displayItems((p, out) -> {
                        fillItems();
                        for(Supplier<ItemStack> s : TOOLS) {
                            out.accept(s.get());
                        }
                    })
                    .build();
        });
    }

}
