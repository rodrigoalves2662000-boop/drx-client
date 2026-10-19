package pt.drx.client.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pt.drx.client.Cape;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class CapeMixin {
    @Inject(method = "getCapeTexture", at = @At("HEAD"), cancellable = true)
    private void drx$cape(CallbackInfoReturnable<Identifier> cir) {
        Cape cape = Cape.current();
        if (cape.texture != null && (Object) this == MinecraftClient.getInstance().player) {
            cir.setReturnValue(cape.texture);
        }
    }
}
