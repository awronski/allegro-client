package com.apwglobal.allegro.client.auctionbuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public enum State {

    DOLNOSLASKIE(1),
    KUJAWSKO_POMORSKIE(2),
    LUBELSKIE(3),
    LUBUSKIE(4),
    LODZKIE(5),
    MALOPOLSKIE(6),
    MAZOWIECKIE(7),
    OPOLSKIE(8),
    PODKARPACKIE(9),
    PODLASKIE(10),
    POMORSKIE(11),
    SLASKIE(12),
    SWIETOKRZYSKIE(13),
    WARMINSKO_MAZURSKIE(14),
    WIELKOPOLSKIE(15),
    ZACHODNIOPOMORSKIE(16);

    private int type;
    State(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static final Map<Integer, State> VALUES;
    static {
        VALUES = Collections.unmodifiableMap(
                Arrays.stream(State.values())
                        .collect(Collectors.toMap((State v) -> v.type, v -> v))
        );
    }
}
