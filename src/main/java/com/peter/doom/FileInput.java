package com.peter.doom;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileInput implements UserInput {


    private final Path path;

    private List<InputListener> inputListeners = new ArrayList<>();

    public FileInput(String userInputFile) {
        path = Paths.get(userInputFile);
    }

    @Override
    public void readUserInput() {
        try {

            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] split = line.split(" ");
                Thread.sleep(Long.valueOf(split[1]));
                inputListeners.forEach(inputListener -> inputListener.onInput(Integer.valueOf(split[0])));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addInputListener(InputListener inputListener) {
        inputListeners.add(inputListener);
    }

}
