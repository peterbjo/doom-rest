package com.peter.doom;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Player {

    private int actionCount = 1;

    private final Map<Integer, Action> playerActions = new HashMap<>();

    public void addAction(Action action) {
        playerActions.put(action.id(), action);
    }

    public void performAction(Integer actionId) {
        Action action = playerActions.get(actionId);
        if (action == null) {
            return;
        }
        System.out.print("Action " + actionCount++ + " - ");
        action.perform();
    }

    public Collection<Action> getPlayerActions() {
        return playerActions.values();
    }
}
