package committee.nova.mods.keneng.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import committee.nova.mods.keneng.Const;
import committee.nova.mods.keneng.core.machine.cable.CableTile;
import committee.nova.mods.keneng.core.machine.cable.CableTileClr;
import committee.nova.mods.keneng.core.machine.cable.CableTileQtz;
import committee.nova.mods.keneng.core.machine.cable.CableTileStar;
import committee.nova.mods.keneng.core.machine.cell.CellTile;
import committee.nova.mods.keneng.core.machine.channel.ChannelEnergyTile;
import committee.nova.mods.keneng.core.machine.channel.ChannelFluidTile;
import committee.nova.mods.keneng.core.machine.channel.ChannelItemTile;
import committee.nova.mods.keneng.core.machine.engine.biomass.BiomassTile;
import committee.nova.mods.keneng.core.machine.engine.extractor.ExtractorTile;
import committee.nova.mods.keneng.core.machine.engine.metalizer.MetalizerTile;
import committee.nova.mods.keneng.core.machine.engine.solar.SolarTile;
import committee.nova.mods.keneng.core.machine.pipe.PipeBlacklistTile;
import committee.nova.mods.keneng.core.machine.pipe.PipeTile;
import committee.nova.mods.keneng.core.machine.pipe.PipeWhitelistTile;
import committee.nova.mods.keneng.core.machine.useenergy.beacon.BeaconTile;
import committee.nova.mods.keneng.core.machine.useenergy.compressor.CompressorTile;
import committee.nova.mods.keneng.core.machine.useenergy.condenser.CondenserTile;
import committee.nova.mods.keneng.core.machine.useenergy.encflu.EncfluTile;
import committee.nova.mods.keneng.core.machine.useenergy.farm.FarmTile;
import committee.nova.mods.keneng.core.machine.useenergy.indfur.IndfurTile;
import committee.nova.mods.keneng.core.machine.useenergy.mobrip.MobRipTile;
import committee.nova.mods.keneng.core.machine.useenergy.psionicant.PsionicantTile;
import committee.nova.mods.keneng.core.machine.useenergy.pulverizer.PulverizerTile;
import committee.nova.mods.keneng.core.machine.useenergy.quarry.QuarryTile;
import committee.nova.mods.keneng.core.machine.useenergy.refiner.RefinerTile;
import committee.nova.mods.keneng.core.machine.useenergy.smelter.FurnaceTile;
import committee.nova.mods.keneng.lib.tile.mac.CmTileEntity;

import java.util.HashMap;
import java.util.Map;

public class TileInit
{

    static Map<String, RegistryObject<BlockEntityType<? extends CmTileEntity>>> regs = new HashMap<>();
    public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Const.modid);

    public static void regAll()
    {
        regTile("channel_energy", ChannelEnergyTile::new);
        regTile("channel_item", ChannelItemTile::new);
        regTile("channel_fluid", ChannelFluidTile::new);

        regTile("engine_extraction", ExtractorTile::new);
        regTile("engine_metal", MetalizerTile::new);
        regTile("engine_biomass", BiomassTile::new);
        regTile("engine_solar", SolarTile::new);

        regTile("machine_smelter", FurnaceTile::new);
        regTile("machine_farm_manager", FarmTile::new);
        regTile("machine_pulverizer", PulverizerTile::new);
        regTile("machine_compressor", CompressorTile::new);
        regTile("machine_beacon_simulator", BeaconTile::new);
        regTile("machine_mob_ripper", MobRipTile::new);
        regTile("machine_quarry", QuarryTile::new);
        regTile("machine_psionicant", PsionicantTile::new);
        regTile("machine_induction_furnace", IndfurTile::new);
        regTile("machine_enchantment_flusher", EncfluTile::new);
        regTile("machine_refiner", RefinerTile::new);
        regTile("machine_matter_condenser", CondenserTile::new);

        regTile("cable", CableTile::new);
        regTile("cable_quartz", CableTileQtz::new);
        regTile("cable_azure", CableTileClr::new);
        regTile("cable_star", CableTileStar::new);
        regTile("pipe", PipeTile::new);
        regTile("pipe_white", PipeWhitelistTile::new);
        regTile("pipe_black", PipeBlacklistTile::new);
        regTile("cell", CellTile::new);
        //regTile("pole", PoleTile::new);
    }

    public static void regTile(String id, BlockEntityType.BlockEntitySupplier<CmTileEntity> im)
    {

        RegistryObject<BlockEntityType<? extends CmTileEntity>> reg = TILES.register(id, () ->
                BlockEntityType.Builder.of(im, BlockInit.getBlock(id)).build(null));
        regs.put(id, reg);

    }

    public static BlockEntityType<? extends CmTileEntity> getType(String id)
    {

        return regs.get(id).get();

    }

}
