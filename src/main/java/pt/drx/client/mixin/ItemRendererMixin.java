package pt.drx.client.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pt.drx.client.Knife;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    // Qualquer espada passa a ser renderizada como a faca escolhida (só visual, client-side)
    @Inject(method = "getModel", at = @At("RETURN"), cancellable = true)
    private void drx$knife(ItemStack stack, @Nullable World world, @Nullable LivingEntity entity, int seed,
                           CallbackInfoReturnable<BakedModel> cir) {
        Knife k = Knife.current();
        if (k.model != null && stack.getItem() instanceof SwordItem) {
            BakedModel m = MinecraftClient.getInstance().getBakedModelManager().getModel(k.model);
            if (m != null) cir.setReturnValue(m);
        }
    }
}
