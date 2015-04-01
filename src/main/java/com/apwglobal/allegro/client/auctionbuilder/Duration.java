package com.apwglobal.allegro.client.auctionbuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public enum Duration {

    THREE(0),
    FIVE(1),
    SEVEN(2),
    TEN(3),
    FOURTEEN(4),
    THIRTY(5),
    UNLIMITED(99);

    private int type;
    Duration(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static final Map<Integer, Duration> VALUES;
    static {
        VALUES = Collections.unmodifiableMap(
                Arrays.stream(Duration.values())
                        .collect(Collectors.toMap((Duration v) -> v.type, v -> v))
        );
    }
}
