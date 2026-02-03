package net.F53.HorseBuff.config;


import folk.sisby.kaleido.api.WrappedConfig;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.IntegerRange;
import net.F53.HorseBuff.HorseBuffInit;
import net.fabricmc.loader.api.FabricLoader;

public class ModConfig extends WrappedConfig {

    public ClientConfig client = new ClientConfig();
    public static class ClientConfig implements Section {
        public boolean noBuck = true;
        @IntegerRange(min = 0, max = 45)
        public int horseHeadAngleOffset = 0;
        public boolean jeb_Horses = true;
    }

    public ServerConfig server = new ServerConfig();
    public static class ServerConfig implements Section {
        public boolean stepHeight = true;
        public boolean noWander = true;
        public boolean breakSpeed = true;
    }


    public FadeConfig pitchFade = new FadeConfig();
    public static class FadeConfig implements Section {
        public boolean enabled = true;

        @IntegerRange(min = 0, max = 90)
        public int startAngle = 30;
        
        @IntegerRange(min = 0, max = 90)
        public int endAngle = 50;
        
        @IntegerRange(min = 50, max = 100)
        public int maxTransparency = 90;
    }

    public static ModConfig init() {
        return ModConfig.createToml(FabricLoader.getInstance().getConfigDir(), "", HorseBuffInit.MOD_ID, ModConfig.class);
    }

    public static ModConfig getInstance() {
        return HorseBuffInit.CONFIG;
    }
}
