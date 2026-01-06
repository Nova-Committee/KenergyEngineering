package committee.nova.mods.keneng.plugin.jei;

import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import committee.nova.mods.keneng.TConst;
import committee.nova.mods.keneng.util.ComponentHelper;

public abstract class JeiBaseCategory<T extends Recipe<Container>> implements IRecipeCategory<T> {

    protected RecipeType<T> type;
    protected ProcessDraw drawer;
    protected IDrawable icon;

    public JeiBaseCategory(RecipeType<T> name, int u, int v, int w, int h)
    {
        type = name;
        drawer = new ProcessDraw(u, v, w, h, name.getUid().getPath());
    }

    @Override
    public Component getTitle()
    {
        return ComponentHelper.translated(TConst.modid + ".machine_" + type.getUid().getPath());
    }

    @Override
    public IDrawable getBackground()
    {
        return drawer;
    }

    @Override
    public IDrawable getIcon()
    {
        return icon;
    }

}
