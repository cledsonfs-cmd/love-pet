package br.com.lovepet.statemachine.service;

import br.com.lovepet.enums.UsuarioEvents;
import br.com.lovepet.enums.UsuarioStates;
import br.com.lovepet.model.entity.Usuario;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.statemachine.StateMachine;

public interface StateMachineEventService {

    StateMachine<UsuarioStates, UsuarioEvents> buildSM(Usuario usuario);
    void sendEvent(Usuario usuario, UsuarioEvents event);
    void sendEvent(Usuario usuario, UsuarioEvents event, MessageHeaderAccessor messageHeaderAccessor);

}
