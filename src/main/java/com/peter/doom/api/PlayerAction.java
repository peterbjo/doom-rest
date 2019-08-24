package com.peter.doom.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerAction {

    private final String type;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PlayerAction(@JsonProperty("type") String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static PlayerAction valueOf(String type) {
        return new PlayerAction(type);
    }
}
