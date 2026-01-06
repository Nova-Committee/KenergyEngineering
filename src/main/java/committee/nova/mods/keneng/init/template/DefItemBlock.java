package committee.nova.mods.keneng.init.template;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import committee.nova.mods.keneng.core.block.CableBased;
import committee.nova.mods.keneng.core.block.PipeBased;
import committee.nova.mods.keneng.core.item.ICanFillGroup;
import committee.nova.mods.keneng.init.TabInit;
import committee.nova.mods.keneng.util.ComponentHelper;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DefItemBlock extends BlockItem implements ICanFillGroup
{

    public DefItemBlock(Block b, Properties prp)
    {
        super(b, prp);
    }

    public void fillGroup()
    {
        if(getBlock() instanceof CableBased || getBlock() instanceof PipeBased) {
            TabInit.MACHINES.add(this::getDefaultInstance);
            return;
        }
        TabInit.BLOCKS.add(this::getDefaultInstance);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level p_41422_, List<Component> tooltip, TooltipFlag p_41424_)
    {
        List<Component> list = new ArrayList<>();

        for(int i = 0; true; i++) {
            //*getPATH!
            String k = "ten3." + BuiltInRegistries.ITEM.getKey(this).getPath() + "." + i;
            Component ttc = ComponentHelper.translated(ComponentHelper.GOLD, k);
            if(ttc.getString().equals(k)) {
                break;
            }

            list.add(ttc);
        }

        if(DefItem.shift()) {
            tooltip.addAll(list);
        }
        else if(list.size() > 0) {
            tooltip.add(ComponentHelper.translated(ComponentHelper.GOLD, "ten3.shift"));
        }
    }

    @Override
    public String getDescriptionId()
    {
        return ComponentHelper.getKey(BuiltInRegistries.ITEM.getKey(this).getPath());
    }

}
