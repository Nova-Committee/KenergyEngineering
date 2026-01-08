package committee.nova.mods.keneng.init;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import committee.nova.mods.keneng.Const;
import committee.nova.mods.keneng.core.client.IntMap;
import committee.nova.mods.keneng.core.machine.cell.CellScreen;
import committee.nova.mods.keneng.core.machine.channel.ChannelScreenEnergy;
import committee.nova.mods.keneng.core.machine.channel.ChannelScreenFluid;
import committee.nova.mods.keneng.core.machine.channel.ChannelScreenItem;
import committee.nova.mods.keneng.core.machine.engine.EngineScreen;
import committee.nova.mods.keneng.core.machine.engine.solar.SolarScreen;
import committee.nova.mods.keneng.core.machine.pipe.PipeScreen;
import committee.nova.mods.keneng.core.machine.useenergy.beacon.BeaconScreen;
import committee.nova.mods.keneng.core.machine.useenergy.compressor.CompressorScreen;
import committee.nova.mods.keneng.core.machine.useenergy.condenser.CondenserScreen;
import committee.nova.mods.keneng.core.machine.useenergy.encflu.EncfluScreen;
import committee.nova.mods.keneng.core.machine.useenergy.farm.FarmScreen;
import committee.nova.mods.keneng.core.machine.useenergy.indfur.IndfurScreen;
import committee.nova.mods.keneng.core.machine.useenergy.mobrip.MobRipScreen;
import committee.nova.mods.keneng.core.machine.useenergy.psionicant.PsionicantScreen;
import committee.nova.mods.keneng.core.machine.useenergy.pulverizer.PulverizerScreen;
import committee.nova.mods.keneng.core.machine.useenergy.quarry.QuarryScreen;
import committee.nova.mods.keneng.core.machine.useenergy.refiner.RefinerScreen;
import committee.nova.mods.keneng.core.machine.useenergy.smelter.FurnaceScreen;
import committee.nova.mods.keneng.lib.tile.CmContainerMachine;
import committee.nova.mods.keneng.lib.tile.mac.CmTileMachine;
import committee.nova.mods.keneng.lib.wrapper.IntArrayCm;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ContInit
{

    public static IntMap<String> containerInvOffset = new IntMap<>();
    static Map<String, RegistryObject<MenuType<?>>> regs = new HashMap<>();
    public static final DeferredRegister<MenuType<?>> CONS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Const.modid);

    public static void regAll()
    {
        regCont("channel_energy");
        regCont("channel_item");
        regCont("channel_fluid");

        regCont("engine_extraction");
        regCont("engine_metal");
        regCont("engine_biomass");
        regCont("engine_solar");

        regCont("machine_smelter");
        regCont("machine_farm_manager");
        regCont("machine_pulverizer");
        regCont("machine_compressor");
        regCont("machine_beacon_simulator");
        regCont("machine_mob_ripper");
        regCont("machine_quarry");
        regCont("machine_psionicant");
        regCont("machine_induction_furnace");
        regCont("machine_enchantment_flusher");
        regCont("machine_refiner");
        regCont("machine_matter_condenser");

        regCont("cell");
        regCont("pipe_white");
        regCont("pipe_black");
    }

    public static IntArrayCm createDefaultIntArr()
    {
        return new IntArrayCm(40);
    }

    public static void regCont(String id)
    {

        RegistryObject<MenuType<?>> reg = CONS.register(id, () ->
                IForgeMenuType.create((windowId, inv, data) -> {
                    BlockPos pos = data.readBlockPos();
                    return new CmContainerMachine(windowId, id,
                                                  (CmTileMachine) TileInit.getType(id).create(pos, inv.player.level().getBlockState(pos)),
                                                  inv, pos
                    );
                }));
        regs.put(id, reg);

    }

    public static MenuType<?> getType(String id)
    {

        return regs.get(id).get();

    }

    public static boolean hasType(String id)
    {

        return regs.containsKey(id);

    }

    @SubscribeEvent
    @SuppressWarnings("all")
    public static void doBinding(FMLClientSetupEvent e)
    {

        bindScr("channel_energy", ChannelScreenEnergy::new);
        bindScr("channel_item", ChannelScreenItem::new);
        bindScr("channel_fluid", ChannelScreenFluid::new);

        bindScr("engine_metal", EngineScreen::new);
        bindScr("engine_extraction", EngineScreen::new);
        bindScr("engine_biomass", EngineScreen::new);
        bindScr("engine_solar", SolarScreen::new);

        bindScr("machine_smelter", FurnaceScreen::new);
        bindScr("machine_farm_manager", FarmScreen::new);
        bindScr("machine_pulverizer", PulverizerScreen::new);
        bindScr("machine_compressor", CompressorScreen::new);
        bindScr("machine_beacon_simulator", BeaconScreen::new);
        bindScr("machine_mob_ripper", MobRipScreen::new);
        bindScr("machine_quarry", QuarryScreen::new);
        bindScr("machine_psionicant", PsionicantScreen::new);
        bindScr("machine_induction_furnace", IndfurScreen::new);
        bindScr("machine_enchantment_flusher", EncfluScreen::new);
        bindScr("machine_refiner", RefinerScreen::new);
        bindScr("machine_matter_condenser", CondenserScreen::new);

        bindScr("cell", CellScreen::new);
        bindScr("pipe_white", PipeScreen::new);
        bindScr("pipe_black", PipeScreen::new);

    }

    @SuppressWarnings("all")
    private static <M extends AbstractContainerMenu, U extends Screen & MenuAccess<M>> void bindScr(String s, MenuScreens.ScreenConstructor<M, U> fac)
    {
        MenuScreens.register((MenuType<? extends M>) getType(s), fac);
    }

}
