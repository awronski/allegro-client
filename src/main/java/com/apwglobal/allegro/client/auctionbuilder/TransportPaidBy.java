package com.apwglobal.allegro.client.auctionbuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public enum TransportPaidBy {

    SELLER(0),
    BUYER(1);

    private int type;
    TransportPaidBy(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static final Map<Integer, TransportPaidBy> VALUES;
    static {
        VALUES = Collections.unmodifiableMap(
                Arrays.stream(TransportPaidBy.values())
                        .collect(Collectors.toMap((TransportPaidBy v) -> v.type, v -> v))
        );
    }
}
