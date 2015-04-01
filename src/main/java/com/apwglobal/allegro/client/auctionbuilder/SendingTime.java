package com.apwglobal.allegro.client.auctionbuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public enum SendingTime {

    IN24H(1),
    AFTER24H(24),
    AFTER48H(48),
    AFTER72H(72),
    AFTER96H(96),
    AFTER120H(120),
    AFTER168H(168),
    AFTER240H(240),
    AFTER336H(336),
    AFTER504H(504);

    private int type;
    SendingTime(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static final Map<Integer, SendingTime> VALUES;
    static {
        VALUES = Collections.unmodifiableMap(
                Arrays.stream(SendingTime.values())
                        .collect(Collectors.toMap((SendingTime v) -> v.type, v -> v))
        );
    }

}
