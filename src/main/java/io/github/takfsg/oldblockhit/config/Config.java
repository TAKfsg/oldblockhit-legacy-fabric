package io.github.takfsg.oldblockhit.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;

public class Config {
    public static final Path configPath = FabricLoader.getInstance().getConfigDir().resolve("./oldblockhit.json");

    public static boolean old3rdPersonBlock;
    public static boolean oldBow;
    public static boolean oldConsume;
    public static boolean oldItemHeld;
    public static boolean oldPotionOffset;
    public static boolean oldRod;
    public static boolean oldSneak;
    public static boolean redArmor;
    public static boolean useSwing;

    public static void init() {
        JsonObject config = new JsonObject();
        if (!configPath.toFile().exists()) {
            config.addProperty("old3rdPersonBlock", true);
            config.addProperty("oldBow", true);
            config.addProperty("oldItemHeld", true);
            config.addProperty("oldConsume", true);
            config.addProperty("oldPotionOffset", true);
            config.addProperty("oldRod", true);
            config.addProperty("oldSneak", true);
            config.addProperty("redArmor", true);
            config.addProperty("useSwing", true);
        } else {
            try {
                JsonParser parser = new JsonParser();
                config = parser.parse(new FileReader(configPath.toFile())).getAsJsonObject();
            } catch (Exception e) {
                System.out.println("[OldBlockHit] Failed to load config file!");
            }
        }

        try {
            try {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String jsonString = gson.toJson(config);
                FileWriter writer = new FileWriter(configPath.toFile());
                writer.write(jsonString);
                writer.close();
            } catch (Exception e) {
                System.out.println("[OldBlockHit] Failed to create config file!");
            }

            old3rdPersonBlock = config.get("old3rdPersonBlock").getAsBoolean();
            oldBow = config.get("oldBow").getAsBoolean();
            oldItemHeld = config.get("oldItemHeld").getAsBoolean();
            oldConsume = config.get("oldConsume").getAsBoolean();
            oldPotionOffset = config.get("oldPotionOffset").getAsBoolean();
            oldRod = config.get("oldRod").getAsBoolean();
            oldSneak = config.get("oldSneak").getAsBoolean();
            redArmor = config.get("redArmor").getAsBoolean();
            useSwing = config.get("useSwing").getAsBoolean();
        } catch (Exception e) {
            System.out.println("[OldBlockHit] Failed to load config file!");
        }
    }
}
