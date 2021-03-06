package inc.a13xis.legacy.dendrology.block;

import inc.a13xis.legacy.dendrology.TheMod;
import inc.a13xis.legacy.koresample.common.block.DoorBlock;
import inc.a13xis.legacy.koresample.tree.DefinesDoor;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;

public final class ModDoorBlock extends DoorBlock
{
    public ModDoorBlock(DefinesDoor definition)
    {
        super(definition);
        setCreativeTab(TheMod.INSTANCE.creativeTab());
        setRegistryName("door_"+variant.name().toLowerCase());
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(OPEN, Boolean.valueOf(false)).withProperty(HINGE, BlockDoor.EnumHingePosition.LEFT).withProperty(POWERED, Boolean.valueOf(false)).withProperty(HALF, BlockDoor.EnumDoorHalf.LOWER));
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[]{HALF,FACING,OPEN,HINGE,POWERED});
    }

    @Override
    protected String resourcePrefix()
    {
        return TheMod.getResourcePrefix();
    }

    @Override
    public int damageDropped(IBlockState state) {
        return 0;
    }
}
