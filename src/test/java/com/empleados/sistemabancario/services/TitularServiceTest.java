package com.empleados.sistemabancario.services;

import com.empleados.sistemabancario.entities.Titular;
import com.empleados.sistemabancario.repositories.TitularRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TitularServiceTest {

    @Mock
    private TitularRepository titularRepository;

    @InjectMocks
    private TitularService titularService;

    private Titular titular1;
    private Titular titular2;
    private Titular titular3;

    @BeforeEach
    void setUp() {
        data();
    }

    @Test
    void getTitular() {
        when(titularRepository.findByName(anyString())).thenReturn(titular1);
        Titular titular = titularService.getTitular("Tenyo").getBody();

        assertNotNull(titular);
        assertEquals(1L, titular.getId());
        assertEquals("Tenyo", titular.getName());
        assertEquals("Pineda", titular.getApellido());
        assertEquals('1', titular.getStatus());
    }

    @Test
    void create() {
        when(titularRepository.save(any(Titular.class))).thenReturn(titular2);
        titularService.create(titular1);
        Titular titular = titularService.create(titular2).getBody();

        assertNotNull(titular);

        assertEquals(2L, titular.getId());
        assertEquals("Ale", titular.getName());
        assertEquals("Almaraz", titular.getApellido());
        assertEquals('1', titular.getStatus());
    }

    private void data(){
        titular1 = new Titular();
        titular1.setId(1L);
        titular1.setName("Tenyo");
        titular1.setApellido("Pineda");
        titular1.setStatus('1');

        titular2 = new Titular();
        titular2.setId(2L);
        titular2.setName("Ale");
        titular2.setApellido("Almaraz");
        titular2.setStatus('1');

        titular3 = new Titular();
        titular3.setId(3L);
        titular3.setName("Uriel");
        titular3.setApellido("Cruz");
        titular3.setStatus('1');

        titularRepository.saveAll(Arrays.asList(titular1, titular2, titular3));
    }
}