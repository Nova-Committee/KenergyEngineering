package committee.nova.mods.keneng.core.world;

import net.minecraftforge.fml.common.Mod;
import committee.nova.mods.keneng.Const;

@Mod.EventBusSubscriber(modid = Const.modid, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OreFeatureGen
{

    /*
    @SubscribeEvent
    public void gatherData(GatherDataEvent event)
    {

        DataGenerator gen = event.getGenerator();
        RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, RegistryAccess.builtinCopy());

        Map<ResourceLocation, PlacedFeature> pmap = new HashMap<>();
        Map<ResourceLocation, ConfiguredFeature<?, ?>> cmap = new HashMap<>();
        List<Holder<PlacedFeature>> worldFeatures = new ArrayList<>();

        FeatureCm tin = FeatureCm.builder()
                .id("tin_ore")
                .addTarget(STONE_ORE_REPLACEABLES, "tin_ore")
                .addTarget(DEEPSLATE_ORE_REPLACEABLES, "deep_tin_ore")

                .setRarity(12)
                .toPlace(10, -80, 54)
                .build();
        FeatureCm nickel = FeatureCm.builder()
                .id("nickel_ore")
                .addTarget(STONE_ORE_REPLACEABLES, "nickel_ore")
                .addTarget(DEEPSLATE_ORE_REPLACEABLES, "deep_nickel_ore")

                .setRarity(9)
                .toPlace(9, -80, 16)
                .build();

        cmap.put(tin.asRL(), tin.oreCfg().value());
        pmap.put(tin.asRL(), tin.orePlace().value());
        worldFeatures.add(ops.registryAccess.registryOrThrow(Registry.PLACED_FEATURE_REGISTRY)
                                  .getOrCreateHolderOrThrow(ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, tin.asRL())));
        cmap.put(nickel.asRL(), nickel.oreCfg().value());
        pmap.put(nickel.asRL(), nickel.orePlace().value());
        worldFeatures.add(ops.registryAccess.registryOrThrow(Registry.PLACED_FEATURE_REGISTRY)
                                  .getOrCreateHolderOrThrow(ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, nickel.asRL())));

        BiomeModifier modifier = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), BiomeTags.IS_OVERWORLD),
                HolderSet.direct(worldFeatures),
                GenerationStep.Decoration.UNDERGROUND_ORES
        );

        DataProvider providerCfg = JsonCodecProvider.forDatapackRegistry(
                gen, event.getExistingFileHelper(),
                TConst.modid, ops, Registry.CONFIGURED_FEATURE_REGISTRY,
                cmap
        );
        DataProvider providerPfg = JsonCodecProvider.forDatapackRegistry(
                gen, event.getExistingFileHelper(),
                TConst.modid, ops, Registry.PLACED_FEATURE_REGISTRY,
                pmap
        );
        DataProvider providerModify = JsonCodecProvider.forDatapackRegistry(
                gen, event.getExistingFileHelper(),
                TConst.modid, ops, ForgeRegistries.Keys.BIOME_MODIFIERS,
                ImmutableMap.of(
                        TConst.asRes("overworld_ores"), modifier
                )
        );

        gen.addProvider(true, providerCfg);
        gen.addProvider(true, providerPfg);
        gen.addProvider(true, providerModify);
    }
    */

}
