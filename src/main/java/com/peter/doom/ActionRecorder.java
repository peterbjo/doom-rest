package com.peter.doom;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ActionRecorder implements PlayerActionListener, EndGameListener {


    private long lastInput;

    private final StringBuilder stringBuilder = new StringBuilder();
    private final Path path;

    public ActionRecorder(String userInputFile) {
        path = Paths.get(userInputFile);
        init();
    }


    @Override
    public void onPlayerAction(Integer actionId) {
        long now = System.currentTimeMillis();
        String line = "" + actionId + " " + (now - lastInput) + System.lineSeparator();
        lastInput = now;
        stringBuilder.append(line);
    }

    private void writeToFile() {
        byte[] strToBytes = stringBuilder.toString().getBytes();
        try {
            Files.write(path, strToBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void init() {
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
            lastInput = System.currentTimeMillis();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onEndGame() {
        writeToFile();
    }
}
