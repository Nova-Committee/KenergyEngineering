package committee.nova.mods.keneng.lib.recipe;

import net.minecraft.resources.ResourceLocation;

import java.util.List;

public interface IFactoryCm<T extends FormsCombinedRecipe>
{

    T create(ResourceLocation regName, ResourceLocation serializerType,
             List<FormsCombinedIngredient> ip,
             List<FormsCombinedIngredient> op, int cookTimeIn);

}
