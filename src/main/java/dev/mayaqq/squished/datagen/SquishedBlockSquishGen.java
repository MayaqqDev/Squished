package dev.mayaqq.squished.datagen;

import dev.mayaqq.squished.api.datagen.BlockSquishAmountProvider;
import dev.mayaqq.squished.resources.BlockSquishAmountData;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Blocks;

public class SquishedBlockSquishGen extends BlockSquishAmountProvider {
    protected SquishedBlockSquishGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public BlockSquishAmountData addBlockSquishAmounts(BlockSquishAmountBuilder builder) {
        builder.add(Blocks.ANVIL, 5.0F);
        builder.add(Blocks.CHIPPED_ANVIL, 4.0F);
        builder.add(Blocks.DAMAGED_ANVIL, 3.0F);
        builder.add(Blocks.SAND, 0.3F);
        builder.add(Blocks.RED_SAND, 0.3F);
        builder.add(Blocks.GRAVEL, 0.5F);
        builder.add(Blocks.SUSPICIOUS_SAND, 0.4F);
        builder.add(Blocks.SUSPICIOUS_GRAVEL, 0.6F);
        return builder.build();
    }
}
