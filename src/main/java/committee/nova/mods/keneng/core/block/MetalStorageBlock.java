package committee.nova.mods.keneng.core.block;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.LootParams;
import committee.nova.mods.keneng.init.template.DefBlock;

import java.util.List;

public class MetalStorageBlock extends DefBlock
{

    public MetalStorageBlock(double hs)
    {

        super(build(hs, hs, MapColor.METAL, SoundType.METAL, 0, true));

    }

    @Override
    public List<ItemStack> getDrops(BlockState p_287732_, LootParams.Builder p_287596_)
    {
        return List.of(asItem().getDefaultInstance());
    }

}
