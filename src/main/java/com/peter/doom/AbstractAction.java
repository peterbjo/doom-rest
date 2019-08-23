package com.peter.doom;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAction implements Action {
    private final List<PlayerActionListener> playerActionListeners = new ArrayList<>();

    public void addPlayerActionListener(PlayerActionListener playerActionListener) {
        playerActionListeners.add(playerActionListener);
    }

    @Override
    public void perform() {
        playerActionListeners.forEach(playerActionListener -> playerActionListener.onPlayerAction(id()));
    }
}
