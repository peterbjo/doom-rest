package com.peter.doom;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class EndGameAction extends AbstractAction {
    private static Logger logger = Logger.getLogger(EndGameAction.class.getName());

    private final List<EndGameListener> endGameListeners = new ArrayList<>();

    private final Integer id;

    public EndGameAction(Integer id) {
        this.id = id;
    }

    @Override
    public Integer id() {
        return id;
    }

    @Override
    public void perform() {
        logger.info("Exiting");
        super.perform();
        exitGame();
    }

    public void addEndGameListener(EndGameListener endGameListener) {
        endGameListeners.add(endGameListener);
    }

    private void exitGame() {
        endGameListeners.forEach(EndGameListener::onEndGame);
    }
}
