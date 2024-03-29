package com.peter.doom;

import com.peter.doom.api.PlayerAction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;

@SpringBootApplication
public class DoomApplication implements CommandLineRunner, EndGameListener{

    @Value("${doom.api.host}")
    private String host;

    @Value("${doom.api.port}")
    private String port;

    @Value("${doom.key.forward}")
    private Integer forward;

    @Value("${doom.key.backward}")
    private Integer backward;

    @Value("${doom.key.turn-left}")
    private Integer turnLeft;

    @Value("${doom.key.turn-right}")
    private Integer turnRight;

    @Value("${doom.key.shoot}")
    private Integer shoot;

    @Value("${doom.key.use}")
    private Integer use;

    @Value("${doom.key.end}")
    private Integer end;

    @Value("${doom.user.input}")
    private String userInputFile;

    private static boolean recordInput = false;

    private KeyInput keyInput = new KeyInput();

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("recordInput")) {
            recordInput = true;
        }
        SpringApplication app = new SpringApplication(DoomApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.setHeadless(false);
        app.run(args);

    }

    @Override
    public void run(String... args) {
        if (recordInput) {
            JFrame frame = new JFrame("Doom");
            frame.addKeyListener(keyInput);
            frame.setVisible(true);
        }
    }

    @Bean
    UserInput userInput() {
        if (recordInput) {
            return keyInput;
        }
        return new FileInput(userInputFile);
    }

    @Bean
    Player player() {
        Player player = new Player();
        EndGameAction endGameAction = new EndGameAction(end);
        endGameAction.addEndGameListener(this);
        String url = "http://" + host + ":" + port + "/api/player/actions";
        player.addAction(new RestAction(forward, url, PlayerAction.valueOf("forward"), restTemplate()));
        player.addAction(new RestAction(backward, url, PlayerAction.valueOf("backward"), restTemplate()));
        player.addAction(new RestAction(turnLeft, url, PlayerAction.valueOf("turn-left"), restTemplate()));
        player.addAction(new RestAction(turnRight, url, PlayerAction.valueOf("turn-right"), restTemplate()));
        player.addAction(new RestAction(shoot, url, PlayerAction.valueOf("shoot"), restTemplate()));
        player.addAction(new RestAction(use, url, PlayerAction.valueOf("use"), restTemplate()));
        player.addAction(endGameAction);

        if (recordInput) {
            ActionRecorder actionRecorder = new ActionRecorder(userInputFile);
            player.getPlayerActions().forEach(action -> action.addPlayerActionListener(actionRecorder));
            endGameAction.addEndGameListener(actionRecorder);
        }
        return player;
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void onEndGame() {
        System.exit(0);
    }
}
