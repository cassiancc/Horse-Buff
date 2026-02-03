package net.F53.HorseBuff.mixin.client;

import net.F53.HorseBuff.render.entity.state.ExtendedRideableEntityRenderState;
import net.minecraft.client.renderer.entity.state.CamelRenderState;
import net.minecraft.client.renderer.entity.state.EquineRenderState;
import net.minecraft.client.renderer.entity.state.LlamaRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

// Additional fields for:
// horses, donkeys, mules, undead horses, skeleton horses (LivingHorseEntityRenderState)
// llamas and camels
@Mixin(value = {EquineRenderState.class, LlamaRenderState.class, CamelRenderState.class}, priority = 960)
public abstract class RideableEntityRenderStateMixin implements ExtendedRideableEntityRenderState {

    @Unique
    private int horsebuff$id;

    @Unique
    private boolean horsebuff$playerPassenger;

    @Override
    public void horsebuff$setId(int id) {
        this.horsebuff$id = id;
    }

    @Override
    public int horsebuff$getId() {
        return this.horsebuff$id;
    }

    @Override
    public void horsebuff$setPlayerPassenger(boolean playerPassenger) {
        this.horsebuff$playerPassenger = playerPassenger;
    }

    @Override
    public boolean horsebuff$isPlayerPassenger() {
        return horsebuff$playerPassenger;
    }
}
