package com.apwglobal.allegro.client.auctionbuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public enum SellType {

    AUCTION(0),
    SHOP(1);

    private int type;
    SellType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static final Map<Integer, SellType> VALUES;
    static {
        VALUES = Collections.unmodifiableMap(
                Arrays.stream(SellType.values())
                        .collect(Collectors.toMap((SellType v) -> v.type, v -> v))
        );
    }
}
