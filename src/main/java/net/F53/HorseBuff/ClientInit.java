package net.F53.HorseBuff;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class ClientInit implements ClientModInitializer {
    public static KeyMapping horsePlayerInventory = KeyMappingHelper.registerKeyMapping(new KeyMapping(
            "text.HorseBuff.keybinding.horsePlayerInventory",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_LEFT_ALT,
            KeyMapping.Category.register(Identifier.fromNamespaceAndPath("horsebuff", "keybinding"))
    ));

    @Override
    public void onInitializeClient() {}
}
