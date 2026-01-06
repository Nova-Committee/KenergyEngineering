package committee.nova.mods.keneng.core.world;

public class FeatureCm
{

    /*
    public static class FeatureCmBuilder {

        String id;
        Holder<ConfiguredFeature<OreConfiguration, ?>> cfg;
        Holder<PlacedFeature> pfg;
        List<OreConfiguration.TargetBlockState> list = new ArrayList<>();

        public FeatureCmBuilder id(String i)
        {
            id = i;
            return this;
        }

        public FeatureCmBuilder addTarget(RuleTest ruleTest, String block)
        {
            list.add(OreConfiguration.target(ruleTest, BlockInit.getBlock(block).defaultBlockState()));
            return this;
        }

        public FeatureCmBuilder setRarity(int r)
        {
            cfg = FeatureUtils.register(id, Feature.ORE, new OreConfiguration(list, r));
            return this;
        }

        public FeatureCmBuilder toPlace(int count, int min, int max)
        {
            var list
                    = List.of(
                            CountPlacement.of(count),
                            InSquarePlacement.spread(),
                            HeightRangePlacement.triangle(
                                       VerticalAnchor.aboveBottom(min),
                                       VerticalAnchor.belowTop(max)
                            ),
                            BiomeFilter.biome()
            );
            pfg = PlacementUtils.register(id, cfg, list);
            return this;
        }

        public FeatureCm build()
        {
            return new FeatureCm(this);
        }

    }

    public static FeatureCmBuilder builder()
    {
        return new FeatureCmBuilder();
    }

    FeatureCmBuilder builder;

    public FeatureCm(FeatureCmBuilder bd)
    {
        builder = bd;
    }

    public Holder<ConfiguredFeature<OreConfiguration, ?>> oreCfg()
    {
        return builder.cfg;
    }

    public Holder<PlacedFeature> orePlace()
    {
        return builder.pfg;
    }

    public String id()
    {
        return builder.id;
    }

    public ResourceLocation asRL()
    {
        return TConst.asRes(id());
    }
    */

}
