package br.com.lovepet.statemachine.interceptors;

import br.com.lovepet.enums.UsuarioEvents;
import br.com.lovepet.enums.UsuarioStates;

import br.com.lovepet.model.entity.Usuario;
import br.com.lovepet.model.entity.UsuarioHistorico;
import br.com.lovepet.repository.UsuarioRepository;
import br.com.lovepet.service.HistoricoUsuarioService;
import br.com.lovepet.statemachine.service.StateMachineErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class StatusMedicaoChangeInterceptor extends StateMachineInterceptorAdapter<UsuarioStates, UsuarioEvents> {

    private final UsuarioRepository medicaoRepository;
    private final StateMachineErrorService errorService;
    private final HistoricoUsuarioService historicoService;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void preStateChange(State<UsuarioStates, UsuarioEvents> state, Message<UsuarioEvents> message, Transition<UsuarioStates, UsuarioEvents> transition, StateMachine<UsuarioStates, UsuarioEvents> stateMachine, StateMachine<UsuarioStates, UsuarioEvents> rootStateMachine) {
        List<String> errors = errorService.getErrors(stateMachine.getExtendedState());
        if (errors.isEmpty()) {
            Optional.ofNullable(message).flatMap(msg -> Optional.ofNullable((Long) msg.getHeaders().getOrDefault(message.getPayload().getUsuario().getId(), -1L)))
                    .ifPresent(idMedicao -> {
                        Usuario usuario = message.getPayload().getUsuario();
                        usuario.setIdStatus(transition.getTarget().getId().getId());

                        usuario = Objects.requireNonNull(message).getPayload().getUsuario();
                        UsuarioHistorico medicaoHistorico = UsuarioHistorico.builder()
                                .usuario(usuario)
                                .dataRegistro(LocalDateTime.now())
                                .descricao(message.getPayload().getUsuarioHistorico().getDescricao())
                                .build();
                        historicoService.salvar(medicaoHistorico);
                    });
        }
    }

}
