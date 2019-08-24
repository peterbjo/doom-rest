package com.peter.doom;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class GamePlayTest {
    private Player player = mock(Player.class);
    private UserInput userInput = mock(UserInput.class);
    private GamePlay gamePlay = new GamePlay(player, userInput);

    @Test
    public void isAddedAsInputListener() {
        gamePlay.afterPropertiesSet();
        verify(userInput).addInputListener(gamePlay);
    }

    @Test
    public void userInputIsRead() {
        gamePlay.afterPropertiesSet();
        verify(userInput).readUserInput();
    }

    @Test
    public void onInputTriggersPlayerPerformAction() {
        gamePlay.onInput(1);
        verify(player).performAction(1);
    }
}