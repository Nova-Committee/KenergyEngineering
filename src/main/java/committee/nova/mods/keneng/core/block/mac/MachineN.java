package committee.nova.mods.keneng.core.block.mac;

import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;
import committee.nova.mods.keneng.core.item.energy.EnergyItemHelper;
import committee.nova.mods.keneng.core.machine.IHasMachineTile;
import committee.nova.mods.keneng.core.machine.MachineClick;
import committee.nova.mods.keneng.init.ContInit;
import committee.nova.mods.keneng.init.TileInit;
import committee.nova.mods.keneng.init.template.DefBlock;
import committee.nova.mods.keneng.lib.tile.mac.CmTileEntity;
import committee.nova.mods.keneng.lib.tile.mac.CmTileMachine;

import java.util.List;

public class MachineN extends DefBlock implements EntityBlock, IHasMachineTile
{

    public static BooleanProperty active = BooleanProperty.create("active");

    String tileName;

    public MachineN(String name)
    {

        this(MapColor.METAL, SoundType.STONE, name, true);
        tileName = name;

    }

    public MachineN(MapColor m, SoundType s, String name, boolean solid)
    {

        super(build(3, 5, m, s, (state) -> {
            if(state.hasProperty(active)) {
                return state.getValue(active) ? 6 : 0;
            }
            return 0;
        }, solid));
        tileName = name;

    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return TileInit.getType(tileName).create(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> p_153214_)
    {
        return (p1, p2, p3, p4) -> ((CmTileEntity) p4).serverTick();
    }

    @Override
    public List<ItemStack> getDrops(BlockState p_287732_, LootParams.Builder p_287596_)
    {
        CmTileMachine tile = ((CmTileMachine) p_287596_.getParameter(LootContextParams.BLOCK_ENTITY));

        ItemStack stack = EnergyItemHelper.fromMachine(tile, asItem().getDefaultInstance());

        List<ItemStack> ret = Lists.newArrayList(stack);

        return ret;
    }

    @Override
    public void onRemove(BlockState s1, Level level, BlockPos pos, BlockState s2, boolean p_60519_)
    {
        if(s1.is(s2.getBlock())) {
            return;
        }
        CmTileMachine tile = ((CmTileMachine) level.getBlockEntity(pos));

        if(tile != null) {
            for(ItemStack s : tile.drops()) {
                popResource(level, pos, s);
            }
        }
        super.onRemove(s1, level, pos, s2, p_60519_);
    }

    @Override
    public void setPlacedBy(Level worldIn, BlockPos pos, BlockState p_49849_, @Nullable LivingEntity p_49850_, ItemStack stack)
    {
        super.setPlacedBy(worldIn, pos, p_49849_, p_49850_, stack);
        CmTileMachine tile = (CmTileMachine) worldIn.getBlockEntity(pos);
        if(tile != null) {
            EnergyItemHelper.pushToTile(tile, stack);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(active);
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit)
    {
        if(handIn == InteractionHand.MAIN_HAND && !worldIn.isClientSide()) {
            if(MachineClick.clickMachineEvent(worldIn, pos, player, hit)) {
                CmTileMachine tile = (CmTileMachine) worldIn.getBlockEntity(pos);
                if(tile == null) {
                    return InteractionResult.PASS;
                }

                if(!ContInit.hasType(tileName)) {
                    return InteractionResult.PASS;
                }

                NetworkHooks.openScreen((ServerPlayer) player, tile, (FriendlyByteBuf packerBuffer) -> {
                    packerBuffer.writeBlockPos(tile.getBlockPos());
                });
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter level, BlockPos pos, @Nullable Direction direction)
    {
        return true;
    }

}
