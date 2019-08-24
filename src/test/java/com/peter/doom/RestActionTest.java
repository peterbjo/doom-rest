package com.peter.doom;

import com.peter.doom.api.PlayerAction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RestActionTest {

    private PlayerAction playerAction = mock(PlayerAction.class);
    private RestTemplate restTemplate = mock(RestTemplate.class);
    private PlayerActionListener playerActionListener = mock(PlayerActionListener.class);
    private ResponseEntity responseEntity = mock(ResponseEntity.class);

    private RestAction restAction = new RestAction(1, "url", playerAction, restTemplate);

    @Before
    public void setup(){
        when(playerAction.getType()).thenReturn("shoot");
    }

    @Test
    public void thatReturnsId() {
        assertEquals(Integer.valueOf(1), restAction.id());
    }

    @Test
    public void playerActionListenerGetsNotifiedOnSuccessfulRequest() {
        when(responseEntity.getStatusCode()).thenReturn(HttpStatus.CREATED);
        when(restTemplate.postForEntity(eq("url"), any(PlayerAction.class), any())).thenReturn(responseEntity);
        restAction.addPlayerActionListener(playerActionListener);
        restAction.perform();
        verify(playerActionListener).onPlayerAction(1);
    }

    @Test
    public void playerActionListenerDontGetsNotifiedOnErrorResponse() {
        when(responseEntity.getStatusCode()).thenReturn(HttpStatus.NOT_FOUND);
        when(restTemplate.postForEntity(eq("url"), any(PlayerAction.class), any())).thenReturn(responseEntity);
        restAction.addPlayerActionListener(playerActionListener);
        restAction.perform();
        verify(playerActionListener, never()).onPlayerAction(1);
    }

}