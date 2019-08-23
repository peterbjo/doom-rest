package com.peter.doom;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


public class KeyInput implements KeyListener, UserInput {

    private List<InputListener> inputListeners = new ArrayList<>();

    private boolean readUserInput = false;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (readUserInput) {
            System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
            inputListeners.forEach(inputListener -> inputListener.onInput(e.getKeyCode()));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void addInputListener(InputListener inputListener) {
        inputListeners.add(inputListener);
    }

    @Override
    public void readUserInput() {
        readUserInput = true;
    }

}
