package com.apwglobal.allegro.client.auctionbuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public enum Units {

    PCS(0),
    SET(1),
    PAIR(2);

    private int type;
    Units(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static final Map<Integer, Units> VALUES;
    static {
        VALUES = Collections.unmodifiableMap(
                Arrays.stream(Units.values())
                        .collect(Collectors.toMap((Units v) -> v.type, v -> v))
        );
    }
}
