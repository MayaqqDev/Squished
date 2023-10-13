package dev.mayaqq.squished;

import dev.mayaqq.squished.resources.BlockSquishAmount;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Squished implements ModInitializer {
    public static final String MOD_ID = "squished";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        BlockSquishAmount.register();
    }
}
