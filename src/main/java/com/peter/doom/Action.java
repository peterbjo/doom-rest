package com.peter.doom;

public interface Action {

    Integer id();

    void perform();

    void addPlayerActionListener(PlayerActionListener playerActionListener);
}
