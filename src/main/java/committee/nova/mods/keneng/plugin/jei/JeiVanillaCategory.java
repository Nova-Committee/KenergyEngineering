package committee.nova.mods.keneng.plugin.jei;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;

public abstract class JeiVanillaCategory<T extends Recipe<Container>> extends JeiCategory<T>
{

    public JeiVanillaCategory(RecipeType<T> name, int u, int v, int w, int h)
    {
        super(name, u, v, w, h);
    }

    public void input(int x, int y, int i, T recipe, IRecipeLayoutBuilder bd)
    {
        bd.addSlot(RecipeIngredientRole.INPUT, x + 1, y + 1)
                .addIngredients(recipe.getIngredients().get(i));
    }

    public void output(int x, int y, int i, T recipe, IRecipeLayoutBuilder bd)
    {
        Minecraft minecraft = Minecraft.getInstance();
        ClientLevel level = minecraft.level;
        if (level == null) {
            throw new NullPointerException("level must not be null.");
        }
        RegistryAccess registryAccess = level.registryAccess();
        bd.addSlot(RecipeIngredientRole.OUTPUT, x + 1, y + 1)
                .addItemStack(recipe.getResultItem(registryAccess).copy());
    }

}
