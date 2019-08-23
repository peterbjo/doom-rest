package com.peter.doom;

import com.peter.doom.api.PlayerAction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RestAction extends AbstractAction {

    private static Logger logger = Logger.getLogger(RestAction.class.getName());

    private final Integer id;
    private final String url;
    private final PlayerAction playerAction;
    private final RestTemplate restTemplate = new RestTemplate();

    public RestAction(Integer id, String url, PlayerAction playerAction) {
        this.id = id;
        this.url = url;
        this.playerAction = playerAction;
    }

    @Override
    public Integer id() {
        return id;
    }

    @Override
    public void perform() {
        logger.info("Performing action : " + playerAction.getType());
        ResponseEntity<String> entity = restTemplate.postForEntity(url, playerAction, String.class);
        if (!entity.getStatusCode().equals(HttpStatus.CREATED)) {
            logger.log(Level.WARNING, "Failed to post action: " + playerAction.getType());
        }
        super.perform();
    }
}
