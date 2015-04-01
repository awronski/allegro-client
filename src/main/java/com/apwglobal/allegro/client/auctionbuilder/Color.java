package com.apwglobal.allegro.client.auctionbuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public enum Color {

    WHITE(1),
    BLACK(2),
    YELLOW(4),
    ORANGE_RED(8),
    PINK(16),
    VIOLET(32),
    BLUE(64),
    GREEN(128),
    GRAY(256),
    BROWN(512),
    SILVER(1024),
    GOLD(2048),
    OTHER(4096),
    MULTI(8192);

    private int type;
    Color(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static final Map<Integer, Color> VALUES;
    static {
        VALUES = Collections.unmodifiableMap(
                Arrays.stream(Color.values())
                        .collect(Collectors.toMap((Color v) -> v.type, v -> v))
        );
    }
}
