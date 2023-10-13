package dev.mayaqq.squished.api.datagen;

import com.google.gson.JsonObject;
import dev.mayaqq.squished.resources.BlockSquishAmountData;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.data.DataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

public abstract class BlockSquishAmountProvider implements DataProvider {
    protected final FabricDataOutput output;
    protected final DataOutput.PathResolver pathResolver;

    protected BlockSquishAmountProvider(FabricDataOutput output) {
        this.output = output;
        this.pathResolver = output.getResolver(DataOutput.OutputType.DATA_PACK, "block_squish_amount");
    }

    public abstract BlockSquishAmountData addBlockSquishAmounts(BlockSquishAmountBuilder builder);

    @Override
    public CompletableFuture<?> run(DataWriter writer) {
        HashMap<String, Float> blockSquishAmounts = addBlockSquishAmounts(new BlockSquishAmountBuilder()).blockSquishAmount;

        JsonObject entryJson = new JsonObject();


        for (Map.Entry<String, Float> entry : blockSquishAmounts.entrySet()) {
            entryJson.addProperty(entry.getKey(), entry.getValue());
        }

        return DataProvider.writeToPath(writer, entryJson, this.pathResolver.resolveJson(new Identifier(this.output.getModId(), "block_squish_amount")));
    }

    @Override
    public String getName() {
        return "Block Squish Amount";
    }

    public static class BlockSquishAmountBuilder {

        TreeMap<String, Float> blockSquishAmounts;

        public BlockSquishAmountBuilder() {
            this.blockSquishAmounts = new TreeMap<>();
        }

        public void add(Block block, float squishAmount) {
            String blockTranslationKey = block.getTranslationKey();
            Identifier blockId = new Identifier(blockTranslationKey.split("\\.")[1], blockTranslationKey.split("\\.")[2]);
            this.add(blockId, squishAmount);
        }

        public void add(Identifier blockId, float squishAmount) {
            this.add(blockId.toString(), squishAmount);
        }

        public void add(String blockId, float squishAmount) {
            this.blockSquishAmounts.put(blockId, squishAmount);
        }

        public BlockSquishAmountData build() {
            BlockSquishAmountData blockSquishAmountData = new BlockSquishAmountData();
            blockSquishAmountData.blockSquishAmount.putAll(this.blockSquishAmounts);
            return blockSquishAmountData;
        }
    }
}
