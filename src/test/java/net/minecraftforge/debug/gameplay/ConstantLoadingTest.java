/*
 * Minecraft Forge
 * Copyright (c) 2016-2018.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation version 2.1
 * of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package net.minecraftforge.debug.gameplay;

import java.io.IOException;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = ConstantLoadingTest.MODID, name = "ConstantLoadingTestMod", version = "1.0", acceptableRemoteVersions = "*")
@Mod.EventBusSubscriber
public class ConstantLoadingTest
{
    public static final String MODID = "constantloadingtest";
    private static final boolean ENABLED = false;

    private static final Logger LOGGER = LogManager.getLogger(MODID);

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        if (!ENABLED)
        {
            return;
        }
        LOGGER.info("Loading constant for recipe...");
        JsonContext ctx = null;
        try
        {
            ctx = CraftingHelper.loadContext("/stuff/someConstants.json");
        } catch (IOException e) {
            LOGGER.error("Something went wrong in loading the constant");
            return;
        }

        Ingredient flint = ctx.getConstant("FLINT");
        if (flint == null)
        {
            LOGGER.error("Ingredients not loaded correctly from json");
            return;
        }
        LOGGER.info("Ingredient matches correctly: {}", flint.apply(new ItemStack(Items.FLINT)));
    }
}
