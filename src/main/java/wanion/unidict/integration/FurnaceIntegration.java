package wanion.unidict.integration;

/*
 * Created by WanionCane(https://github.com/WanionCane).
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 1.1. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/1.1/.
 */

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import wanion.unidict.UniDict;

import java.util.Map;

final class FurnaceIntegration extends AbstractIntegrationThread
{
    FurnaceIntegration()
    {
        super("Furnace");
    }

    @Override
    public String call()
    {
        try {
            optimizeFurnaceRecipes();
        } catch (Exception e) { UniDict.getLogger().error(threadName + e); }
        return threadName + "Some things that you smelted appear to be different now.";
    }

    private void optimizeFurnaceRecipes()
    {
        for (final Map.Entry<ItemStack, ItemStack> furnaceRecipe : FurnaceRecipes.instance().getSmeltingList().entrySet())
            furnaceRecipe.setValue(resourceHandler.getMainItemStack(furnaceRecipe.getValue()));
    }
}