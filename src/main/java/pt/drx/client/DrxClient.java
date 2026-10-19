package pt.drx.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class DrxClient implements ClientModInitializer {
    public static KeyBinding MENU_KEY;

    @Override
    public void onInitializeClient() {
        DrxConfig.load();

        MENU_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.drxclient.menu", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, "category.drxclient"));

        // Carrega os modelos das facas para podermos trocá-los em runtime
        ModelLoadingPlugin.register(ctx -> {
            for (Knife k : Knife.values()) if (k.model != null) ctx.addModels(k.model);
        });

        ClientTickEvents.END_CLIENT_TICK.register(mc -> {
            while (MENU_KEY.wasPressed()) mc.setScreen(new DrxScreen());
        });
    }
}
