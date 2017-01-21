package inc.a13xis.legacy.dendrology.content.crafting;

import inc.a13xis.legacy.dendrology.block.ModBlocks;
import inc.a13xis.legacy.dendrology.content.overworld.OverworldTreeSpecies;
import inc.a13xis.legacy.koresample.tree.DefinesLog;
import inc.a13xis.legacy.koresample.tree.DefinesSlab;
import inc.a13xis.legacy.koresample.tree.DefinesStairs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public final class Crafter
{
    public void writeRecipes()
    {
        initLogRecipes();
        initPlankRecipes();
        initSaplingRecipes();
    }

    private void initSaplingRecipes()
    {
        CraftingManager.getInstance().addShapelessRecipe(new ItemStack(Items.dye, 1, 9),
                new ItemStack(OverworldTreeSpecies.CERASU.saplingBlock(), 1,
                        OverworldTreeSpecies.CERASU.saplingSubBlockIndex()));

        CraftingManager.getInstance().addShapelessRecipe(new ItemStack(Items.dye, 1, 10),
                new ItemStack(OverworldTreeSpecies.EWCALY.saplingBlock(), 1,
                        OverworldTreeSpecies.EWCALY.saplingSubBlockIndex()));

        CraftingManager.getInstance().addShapelessRecipe(new ItemStack(Items.dye, 1, 5),
                new ItemStack(OverworldTreeSpecies.PORFFOR.saplingBlock(), 1,
                        OverworldTreeSpecies.PORFFOR.saplingSubBlockIndex()));

        CraftingManager.getInstance().addShapelessRecipe(new ItemStack(Items.dye, 1, 14),
                new ItemStack(OverworldTreeSpecies.ACEMUS.saplingBlock(), 1,
                        OverworldTreeSpecies.ACEMUS.saplingSubBlockIndex()));
    }

    private void initLogRecipes()
    {
        for (final DefinesLog definition : ModBlocks.logDefinitions())
            CraftingManager.getInstance()
                    .addRecipe(new ItemStack(definition.woodBlock(), 4, definition.woodSubBlockIndex()), "#", '#',
                            new ItemStack(definition.logBlock(), 1, definition.logSubBlockIndex()));
    }

    private void initPlankRecipes()
    {
        initWoodStairsRecipes();
        initWoodSlabRecipes();
    }

    @SuppressWarnings("ObjectAllocationInLoop")
    private void initWoodStairsRecipes()
    {
        for (final DefinesStairs definition : ModBlocks.stairsDefinitions())
            CraftingManager.getInstance()
                    .addRecipe(new ItemStack(definition.stairsBlock(), 4), "#  ", "## ", "###", '#',
                            new ItemStack(definition.stairsModelBlock(), 1, definition.stairsModelSubBlockIndex()));
    }

    @SuppressWarnings("ObjectAllocationInLoop")
    private void initWoodSlabRecipes()
    {
        for (final DefinesSlab definition : ModBlocks.slabDefinitions())
            CraftingManager.getInstance()
                    .addRecipe(new ItemStack(definition.singleSlabBlock(), 6, definition.slabSubBlockIndex()), "###",
                            '#', new ItemStack(definition.slabModelBlock(), 1, definition.slabSubBlockIndex()));
    }
}
