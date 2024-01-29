package com.empleados.sistemabancario.services;

import com.empleados.sistemabancario.entities.CuentaBancaria;
import com.empleados.sistemabancario.entities.Titular;
import com.empleados.sistemabancario.models.SaldoModel;
import com.empleados.sistemabancario.repositories.CuentaBancariaRepository;
import com.empleados.sistemabancario.repositories.TitularRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CuentaBancariaServiceTest {

    @Mock
    private CuentaBancariaRepository cuentaBancariaRepository;
    @Mock
    private TitularRepository titularRepository;

    @InjectMocks
    private CuentaBancariaService cuentaBancariaService;

    private Titular titular1;
    private Titular titular2;
    private Titular titular3;
    private CuentaBancaria cuenta1;
    private CuentaBancaria cuenta2;
    private CuentaBancaria cuenta3;

    @BeforeEach
    void setUp() {
        titular();
        data();
    }

    @Test
    void getSaldo() {
        when(cuentaBancariaRepository.findByNumeroCuenta(anyString())).thenReturn(cuenta1);
        SaldoModel cuenta = cuentaBancariaService.getSaldo("15023").getBody();

        assertNotNull(cuenta);

        assertEquals("15023", cuenta.getNumeroCuenta());
        assertEquals(0, cuenta.getSaldo());
    }

    @Test
    void create() {
        when(cuentaBancariaRepository.save(any(CuentaBancaria.class))).thenReturn(cuenta1);
        CuentaBancaria cuenta = cuentaBancariaService.create(cuenta1).getBody();

        assertNotNull(cuenta);

        assertEquals("Tenyo", cuenta.getTitular().getName());
        assertEquals(0, cuenta.getSaldo());
        assertEquals('1', cuenta.getStatus());
    }

//    @Transactional
//    @Test
//    void updateSaldo() {
//        when(cuentaBancariaRepository.findByNumeroCuenta(anyString())).thenReturn(cuenta1);
//        SaldoModel cuenta = cuentaBancariaService.getSaldo("15023").getBody();
//        System.out.println(cuenta);
//        assertNotNull(cuenta);
//        when(cuentaBancariaRepository.save(any(CuentaBancaria.class))).thenAnswer(res -> {
//            CuentaBancaria cuentaBancaria = res.getArgument(0);
//
//            System.out.println(cuentaBancaria);
//            SaldoModel saldoModel = new SaldoModel();
//            saldoModel.setNumeroCuenta(cuentaBancaria.getNumeroCuenta());
//            saldoModel.setSaldo(cuentaBancaria.getSaldo());
//            return saldoModel;
//        });
//
//        SaldoModel cuenta1 = cuentaBancariaService.updateSaldo("15023", 50L).getBody();
//        System.out.println(cuenta1);
//
//        assertNotNull(cuenta1);
//
//        assertEquals("15023", cuenta1.getNumeroCuenta());
//        assertEquals(50, cuenta1.getSaldo());
//    }

    private void titular(){
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
    private void data(){
        cuenta1 = new CuentaBancaria();
        cuenta1.setNumeroCuenta("15023");
        cuenta1.setTitular(titular1);
        cuenta1.setSaldo(0L);
        cuenta1.setStatus('1');

        cuenta2 = new CuentaBancaria();
        cuenta2.setNumeroCuenta("160345");
        cuenta2.setTitular(titular2);
        cuenta2.setSaldo(0L);
        cuenta2.setStatus('1');

        cuenta3 = new CuentaBancaria();
        cuenta3.setNumeroCuenta("78565012");
        cuenta3.setTitular(titular3);
        cuenta3.setSaldo(0L);
        cuenta3.setStatus('1');

        cuentaBancariaRepository.saveAll(Arrays.asList(cuenta1, cuenta2, cuenta3));
    }
}