package committee.nova.mods.keneng.core.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import committee.nova.mods.keneng.core.block.mac.Machine4;
import committee.nova.mods.keneng.core.item.Spanner;
import committee.nova.mods.keneng.core.machine.IHasMachineTile;
import committee.nova.mods.keneng.core.machine.MachineClick;
import committee.nova.mods.keneng.core.machine.pole.PoleTile;
import committee.nova.mods.keneng.core.network.Network;
import committee.nova.mods.keneng.core.network.packets.PTCBindPack;
import committee.nova.mods.keneng.util.ItemNBTHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Pole extends Machine4 implements EntityBlock, IHasMachineTile
{

    VoxelShape shape = Block.box(4, 0, 4, 12, 16, 12);
    ;

    public Pole(MapColor m, SoundType s, String n)
    {

        super(m, s, n, false);

    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_)
    {
        return shape;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState p_60572_, BlockGetter p_60573_, BlockPos p_60574_, CollisionContext p_60575_)
    {
        return shape;
    }

    static Map<UUID, BlockPos> poses = new HashMap<>();
    static Map<UUID, PoleTile> poles = new HashMap<>();

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit)
    {
        if(handIn == InteractionHand.MAIN_HAND && !worldIn.isClientSide()) {
            MachineClick.clickMachineEvent(worldIn, pos, player, hit);
            ItemStack stack = player.getMainHandItem();
            PoleTile tile = (PoleTile) worldIn.getBlockEntity(pos);
            if(stack.getItem() instanceof Spanner && tile != null) {
                UUID uuid = player.getUUID();
                if(ItemNBTHelper.getTag(stack, "mode") == Spanner.Modes.ENERGY.getIndex()) {
                    BlockPos lastPos = poses.get(uuid);
                    if(lastPos == null) {
                        poles.put(uuid, tile);
                        poses.put(uuid, pos);
                    }
                    else {
                        tile.bind = lastPos;//this tile
                        Network.sendToClient(new PTCBindPack(lastPos, pos));
                        poles.get(uuid).bind = pos;//last tile
                        Network.sendToClient(new PTCBindPack(pos, lastPos));
                        poles.remove(uuid);
                        poses.remove(uuid);
                    }
                }
            }
        }

        return InteractionResult.SUCCESS;
    }

    public List<ItemStack> getDrops(BlockState p_287732_, LootParams.Builder p_287596_)
    {
        return List.of(asItem().getDefaultInstance());
    }

}
