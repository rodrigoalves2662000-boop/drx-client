package pt.drx.client.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pt.drx.client.DrxConfig;

@Mixin(EntityRenderDispatcher.class)
public abstract class EntityCullMixin {
    // Não desenha entidades (exceto jogadores) para lá da distância configurada
    @Inject(method = "shouldRender", at = @At("HEAD"), cancellable = true)
    private void drx$cull(Entity entity, Frustum frustum, double x, double y, double z,
                          CallbackInfoReturnable<Boolean> cir) {
        DrxConfig c = DrxConfig.I;
        if (!c.entityCull || entity instanceof PlayerEntity) return;
        double d = MinecraftClient.getInstance().gameRenderer.getCamera().getPos().squaredDistanceTo(x, y, z);
        if (d > (double) c.cullDistance * c.cullDistance) cir.setReturnValue(false);
    }
}
