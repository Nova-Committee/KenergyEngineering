package committee.nova.mods.keneng.core.block;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.LootContext;
import committee.nova.mods.keneng.init.template.DefBlock;

import java.util.List;

public class RawStorageBlock extends DefBlock
{

    public RawStorageBlock(double hs)
    {

        super(build(hs, hs, MapColor.STONE, SoundType.STONE, 0, true));

    }

    public List<ItemStack> getDrops(BlockState p_60537_, LootContext.Builder p_60538_)
    {
        return List.of(asItem().getDefaultInstance());
    }

}
