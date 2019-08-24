package com.peter.doom;

import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PlayerTest {

    private Player player = new Player();

    @Test
    public void thatActionGetsAdded() {
        Action action = mock(Action.class);
        when(action.id()).thenReturn(1);
        player.addAction(action);
        Collection<Action> playerActions = player.getPlayerActions();
        assertEquals(1, playerActions.size());
        assertTrue(playerActions.contains(action));
    }

    @Test
    public void thatActionGetsPerformed(){
        Action action = mock(Action.class);
        when(action.id()).thenReturn(1);
        player.addAction(action);
        player.performAction(1);
        verify(action).perform();
    }

    @Test
    public void thatActionIsNotPerformedWithWrongId(){
        Action action = mock(Action.class);
        when(action.id()).thenReturn(1);
        player.addAction(action);
        player.performAction(2);
        verify(action, never()).perform();
    }

}