package inc.a13xis.legacy.dendrology.world.gen.feature.cedrum;

import com.google.common.base.Objects;
import inc.a13xis.legacy.dendrology.world.gen.feature.AbstractTree;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import java.util.Random;

public class NormalCedrumTree extends AbstractTree
{
    @SuppressWarnings("PackageVisibleField")
    int logDirection = 0;

    public NormalCedrumTree(boolean fromSapling) { super(fromSapling); }

    @Override
    protected boolean canBeReplacedByLog(World world, BlockPos pos)
    {
        return super.canBeReplacedByLog(world, pos) || world.getBlockState(pos).getBlock().getMaterial().equals(Material.water);
    }

    @Override
    protected int getLogMetadata() {return super.getLogMetadata() | logDirection;}

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("logDirection", logDirection).toString();
    }

    @Override
    public boolean isReplaceable(World world, BlockPos pos)
    {
        return super.isReplaceable(world,pos) || world.getBlockState(pos).getBlock().getMaterial().equals(Material.water);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        final int height = rand.nextInt(10) + 9;
        if (!isPoorGrowthConditions(world, pos, height, getSaplingBlock())) {

        final Block block = world.getBlockState(pos.down()).getBlock();
        block.onPlantGrow(world, pos.down(),pos);

        for (int level = 0; level <= height; level++)
        {
            placeLog(world, pos.up(level));

            if (level == height) leafTop(world, pos.up(level));

            if (level > 5 && level < height)
            {
                if (level == height - 1)
                {
                    leafGen(world, 2, pos.up(level));
                }

                if (level == height - 4 || level == height - 7)
                {
                    for (int next = 1; next < 3; next++)
                    {
                        logDirection = 4;
                        placeLog(world, pos.add(next,level-2,0));
                        placeLog(world, pos.add(-next,level-2,0));
                        logDirection = 8;
                        placeLog(world,pos.add(0,level-2,next));
                        placeLog(world, pos.add(0,level-2,next));
                        logDirection = 0;
                    }
                    leafGen(world, level == height - 4 ? 3 : 4, pos.up(level));
                }

                if (level == height - 10 || level == height - 13)
                {
                    leafGen(world, level == height - 10 ? 3 : 2, pos.up(level));
                }
            }
        }
        return true;
    }
    return false;
    }

    @SuppressWarnings("MethodWithMultipleLoops")
    void leafTop(World world, BlockPos pos)
    {
        for (int dX = -2; dX <= 2; dX++)
            for (int dZ = -2; dZ <= 2; dZ++)
            {
                if (Math.abs(dX) + Math.abs(dZ) < 3) placeLeaves(world, pos.add(dX,0,dZ));

                if (Math.abs(dX) + Math.abs(dZ) < 2) placeLeaves(world, pos.add(dX,1,dZ));

                if (Math.abs(dX) == 0 && Math.abs(dZ) == 0) placeLeaves(world, pos.add(dX,2,dZ));
            }
    }

    void leafGen(World world, int size, BlockPos pos)
    {
        final int radius;
        final int limiter1;
        final int limiter2;
        final int limiter3;

        switch (size)
        {
            case 3:
                radius = 4;
                limiter1 = 3;
                limiter2 = 5;
                limiter3 = 7;
                break;
            case 4:
                radius = 5;
                limiter1 = 5;
                limiter2 = 7;
                limiter3 = 8;
                break;
            case 5:
                radius = 6;
                limiter1 = 7;
                limiter2 = 8;
                limiter3 = 9;
                break;
            default:
                radius = 3;
                limiter1 = 2;
                limiter2 = 3;
                limiter3 = 5;
        }
        doLeafGen(world, pos, radius, limiter1, limiter2, limiter3);
    }

    @SuppressWarnings("MethodWithMultipleLoops")
    private void doLeafGen(World world, BlockPos pos, int radius, int limiter1, int limiter2, int limiter3)
    {
        for (int dX = -radius; dX <= radius; dX++)
            for (int dZ = -radius; dZ <= radius; dZ++)
            {
                if (Math.abs(dX) + Math.abs(dZ) < limiter1) placeLeaves(world, pos.add(dX,0,dZ));

                if (Math.abs(dX) + Math.abs(dZ) < limiter2) placeLeaves(world, pos.add(dX,-1,dZ));

                if (Math.abs(dX) + Math.abs(dZ) < limiter3) placeLeaves(world, pos.add(dX,-2,dZ));
            }
    }
}
