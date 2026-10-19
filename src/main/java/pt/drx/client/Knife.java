package pt.drx.client;

import net.minecraft.util.Identifier;

public enum Knife {
    NONE("Desligado", null),
    BAYONET("Bayonet", "item/bayonet"),
    KARAMBIT("Karambit", "item/karambit"),
    BUTTERFLY("Butterfly", "item/butterfly");

    public final String label;
    public final Identifier model;

    Knife(String label, String path) {
        this.label = label;
        this.model = path == null ? null : new Identifier("drxclient", path);
    }

    public static Knife current() {
        Knife[] v = values();
        int i = DrxConfig.I.knife;
        return v[Math.floorMod(i, v.length)];
    }
}
