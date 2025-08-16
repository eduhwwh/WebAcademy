package br.ufac.sgcmapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.ufac.sgcmapi.model.Atendimento;
import br.ufac.sgcmapi.model.EStatus;
import br.ufac.sgcmapi.repository.AtendimentoRepository;


@ExtendWith(MockitoExtension.class)
public class AtendimentoServiceTest {
    
    @Mock
    private AtendimentoRepository repo;

    @InjectMocks
    private AtendimentoService servico;

    private Atendimento atendimento;
    private List<Atendimento> atendimentos;

    @BeforeEach
    void setUp(){
        atendimento = new Atendimento();
        atendimento.setId(1L);

        var atendimento1 = new Atendimento();
        atendimento1.setId(1L);

        var atendimento2 = new Atendimento();
        atendimento2.setId(2L);

        atendimentos = new ArrayList<>();
        atendimentos.add(atendimento);
        atendimentos.add(atendimento2);

    }

    @Test
    void testAtendimentoConsultarTodos(){
        Mockito.when(repo.consultar("", null))
            .thenReturn(atendimentos);
        var resultado = servico.consultar("");
        assertEquals(2, resultado.size());

    }

    @Test
    void testAtendimentoAtualizarTodos(){
        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(atendimento));
        Mockito.when(repo.save(atendimento))
            .thenReturn(atendimento);

        var resultado = servico.atualizarStatus(1L);
        assertNotNull(resultado);
        assertEquals(EStatus.CONFIRMADO, resultado.getStatus());

        
    }
    

}
