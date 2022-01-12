package net.F53.HorseBuff.mixin.PortalHorse;


import net.F53.HorseBuff.config.ModConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Entity.class)
public abstract class TickNether {

    @Shadow protected boolean inNetherPortal;

    @Shadow protected int netherPortalTime;

    @Shadow protected abstract void tickNetherPortalCooldown();

    @Shadow public abstract boolean hasVehicle();

    @Inject(method = "tickNetherPortal()V", at = @At("HEAD"))
    public void riderTravel(CallbackInfo ci){
        Entity thisEntity = (Entity)(Object)this;
        if (thisEntity.world instanceof ServerWorld && thisEntity instanceof PlayerEntity){
            if (thisEntity.hasVehicle()){
                int maxPortalTime = thisEntity.getMaxNetherPortalTime();
                if (inNetherPortal) {
                    MinecraftServer minecraftServer = ((ServerWorld)thisEntity.world).getServer();
                    ServerWorld serverWorld2 = minecraftServer.getWorld(thisEntity.world.getRegistryKey() == World.NETHER ? World.OVERWORLD : World.NETHER);

                    if (serverWorld2 != null && minecraftServer.isNetherAllowed() && netherPortalTime++ >= maxPortalTime) {
                        // Get Vehicle
                        Entity oldVehicle = thisEntity.getVehicle();
                        assert oldVehicle != null;

                        // Change Entity Dim
                        netherPortalTime = maxPortalTime;
                        thisEntity.resetNetherPortalCooldown();
                        thisEntity.moveToWorld(serverWorld2);

                        // Change Vehicle Dim
                        Entity newVehicle = oldVehicle.getType().create(serverWorld2);
                        assert newVehicle != null;
                        newVehicle.copyFrom(oldVehicle);
                        newVehicle.refreshPositionAndAngles(thisEntity.getX(), thisEntity.getY()-.8, thisEntity.getZ(), thisEntity.getYaw(), newVehicle.getPitch());
                        newVehicle.setVelocity(oldVehicle.getVelocity());
                        serverWorld2.onDimensionChanged(newVehicle);
                        oldVehicle.setRemoved(Entity.RemovalReason.CHANGED_DIMENSION);

                        // Make Entity remount Vehicle
                        thisEntity.startRiding(newVehicle, true);
                    }
                    inNetherPortal = false;
                }
                else {
                    if (this.netherPortalTime > 0) {
                        this.netherPortalTime -= 4;
                    }
                    if (this.netherPortalTime < 0) {
                        this.netherPortalTime = 0;
                    }
                }
                tickNetherPortalCooldown();
            }
        }
    }

    // elsewhere, we allow vehicles to be marked as in nether portal, so we have to deny them teleporting
    @Redirect(method = "tickNetherPortal()V", at = @At(value = "INVOKE", target = "net/minecraft/entity/Entity.hasVehicle ()Z"))
    public boolean denyVehicleTravel(Entity instance){
        // if portalPatch, deny travel
        if (instance.hasPassengers() && ModConfig.getInstance().portalPatch){
            return true;
        }
        return instance.hasVehicle();
    }

    @ModifyConstant(method = "tickNetherPortal()V", constant = @Constant(intValue = 4))
    public int netherPortalTime(int constant){
        if (this.hasVehicle()){
            return 0;
        }
        return constant;
    }
}