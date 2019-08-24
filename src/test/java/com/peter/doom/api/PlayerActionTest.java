package com.peter.doom.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class PlayerActionTest {

    @Test
    public void thatReturnsCorrectType(){
        PlayerAction action = PlayerAction.valueOf("test");
        assertEquals("test", action.getType());
    }

    @Test
    public void thatCanJsonDeSerialize() throws IOException {
        String json = "{\"type\":\"shoot\"}";
        ObjectMapper mapper = new ObjectMapper();
        PlayerAction playerAction = mapper.readValue(json, PlayerAction.class);
        assertEquals("shoot", playerAction.getType());
    }

    @Test
    public void thatCanJsonSerialize() throws IOException {
        String json = "{\"type\":\"shoot\"}";
        PlayerAction playerAction = PlayerAction.valueOf("shoot");
        ObjectMapper mapper = new ObjectMapper();
        String valueAsString = mapper.writeValueAsString(playerAction);
        assertEquals(json, valueAsString);
    }
}