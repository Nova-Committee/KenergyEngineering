package committee.nova.mods.keneng.util;

import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.LegacyRandomSource;

import java.util.Collection;

public class SafeOperationHelper
{

    public static String regNameOf(Item i)
    {
        return BuiltInRegistries.ITEM.getKey(i).getPath();
    }

    public static String regNameOf(Block i)
    {
        return BuiltInRegistries.BLOCK.getKey(i).getPath();
    }

    public static String regNameOf(BlockEntityType<?> i)
    {
        return BuiltInRegistries.BLOCK_ENTITY_TYPE.getKey(i).getPath();
    }

    static RandomSource random = new LegacyRandomSource(1214891);

    @SuppressWarnings("all")
    public static <T> T randomInCollection(Collection<T> col)
    {

        if(col == null) {
            return null;
        }
        if(col.size() == 0) {
            return null;
        }

        Object[] items = col.toArray();
        Object j = Util.getRandom(items, random);
        return (T) j;

    }

    public static int safeInt(Integer i)
    {

        if(i == null) {
            return 0;
        }

        return i;

    }

}
