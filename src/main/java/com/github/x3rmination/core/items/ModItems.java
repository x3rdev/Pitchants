package com.github.x3rmination.core.items;

import com.github.x3rmination.common.items.TestChestplateItem;
import com.github.x3rmination.common.items.TestItem;
import com.github.x3rmination.common.items.TestSword;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {

    @GameRegistry.ObjectHolder("pitchants:testitem")
    public static TestItem testItem;
    @GameRegistry.ObjectHolder("pitchants:testchestplateitem")
    public static TestChestplateItem testChestplateItem;
    @GameRegistry.ObjectHolder("pitchants:firstitem")
    public static TestSword testSword;
}
