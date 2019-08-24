package com.peter.doom;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EndGameActionTest {

    @Test
    public void thatReturnsId() {
        EndGameAction action = new EndGameAction(1);
        assertEquals(Integer.valueOf(1), action.id());
    }

    @Test
    public void thatPlayerActionListenerGetsNotified() {
        EndGameAction action = new EndGameAction(1);
        PlayerActionListener playerActionListener = mock(PlayerActionListener.class);
        action.addPlayerActionListener(playerActionListener);
        action.perform();
        verify(playerActionListener).onPlayerAction(1);
    }

    @Test
    public void thatPlayerEndGameListenerGetsNotified() {
        EndGameAction action = new EndGameAction(1);
        EndGameListener endGameListener = mock(EndGameListener.class);
        action.addEndGameListener(endGameListener);
        action.perform();
        verify(endGameListener).onEndGame();
    }

}