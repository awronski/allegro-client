package com.apwglobal.allegro.client.auctionbuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public enum Invoice {

    NO(1),
    YES(32);

    private int type;
    Invoice(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static final Map<Integer, Invoice> VALUES;
    static {
        VALUES = Collections.unmodifiableMap(
                Arrays.stream(Invoice.values())
                        .collect(Collectors.toMap((Invoice v) -> v.type, v -> v))
        );
    }

}
