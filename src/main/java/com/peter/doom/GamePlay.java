package com.peter.doom;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class GamePlay implements InputListener, InitializingBean {


    private final Player player;

    private final UserInput userInput;

    public GamePlay(Player player, UserInput userInput) {
        this.player = player;
        this.userInput = userInput;
    }

    @Override
    public void onInput(Integer input) {
        player.performAction(input);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        userInput.addInputListener(this);
        userInput.readUserInput();
    }
}
