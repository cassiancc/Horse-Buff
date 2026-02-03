package net.F53.HorseBuff.mixin.client;

import net.F53.HorseBuff.render.entity.model.ExtendedRideableEquippableEntityModel;
import net.minecraft.client.model.animal.equine.HorseModel;
import net.minecraft.client.model.animal.llama.LlamaModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

// Horses can equip armor and llamas can equip carpets
@Mixin(value = {HorseModel.class, LlamaModel.class}, priority = 960)
public abstract class RideableEquippableEntityModelMixin implements ExtendedRideableEquippableEntityModel {

    @Unique
    private boolean horsebuff$playerPassenger;

    @Override
    public void horsebuff$setPlayerPassenger(boolean playerPassenger) {
        this.horsebuff$playerPassenger = playerPassenger;
    }

    @Override
    public boolean horsebuff$isPlayerPassenger() {
        return this.horsebuff$playerPassenger;
    }
}
