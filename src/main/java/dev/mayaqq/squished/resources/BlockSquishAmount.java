package dev.mayaqq.squished.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dev.mayaqq.squished.Squished;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

public class BlockSquishAmount {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static final BlockSquishAmountData BLOCK_SQUISH_AMOUNT = new BlockSquishAmountData();
    public static void register() {
            ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
                @Override
                public Identifier getFabricId() {
                    return new Identifier("squished", "block_squish_amount");
                }

                @Override
                public void reload(ResourceManager manager) {
                    manager.findResources("block_squish_amount", path -> path.toString().endsWith(".json")).forEach((id, resource) -> {
                        try {
                            JsonObject obj = GSON.fromJson(resource.getReader(), JsonObject.class);
                            obj.entrySet().forEach(entry -> {
                                BLOCK_SQUISH_AMOUNT.blockSquishAmount.put(entry.getKey(), entry.getValue().getAsFloat());
                            });
                        } catch (Exception e) {
                            Squished.LOGGER.error("Failed to load block squish amount from " + id.toString() + " as " + e.getMessage());
                        }
                    });
                }
            });
        }
}
