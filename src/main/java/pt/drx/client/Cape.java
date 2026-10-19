package pt.drx.client;

import net.minecraft.util.Identifier;

public enum Cape {
    NONE("Padrão", null),
    DRX("DRX Vermelha", "textures/cape/drx.png"),
    NEON("DRX Neon", "textures/cape/neon.png"),
    DARK("DRX Dark", "textures/cape/dark.png");

    public final String label;
    public final Identifier texture;

    Cape(String label, String path) {
        this.label = label;
        this.texture = path == null ? null : new Identifier("drxclient", path);
    }

    public static Cape current() {
        Cape[] v = values();
        return v[Math.floorMod(DrxConfig.I.cape, v.length)];
    }
}
