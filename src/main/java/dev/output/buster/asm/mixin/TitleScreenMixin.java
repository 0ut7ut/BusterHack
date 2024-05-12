package dev.output.buster.asm.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Administrator
 */
@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
    @Unique
    private static final Text COPYRIGHT = Text.literal("BusterHack Developed by Slmpc and Niuren");

    protected TitleScreenMixin(Text title) {
        super(title);
    }
    @Inject(method = "init", at = @At("HEAD"), cancellable = true, require = 0)
    private void init(CallbackInfo ci) {
        int i = this.textRenderer.getWidth(COPYRIGHT);
        this.addDrawableChild(new TextWidget(this.width - i - 2, this.height - 20, i, 10, COPYRIGHT, this.textRenderer));
    }
}

