package com.empleados.sistemabancario.services;

import com.empleados.sistemabancario.entities.CuentaBancaria;
import com.empleados.sistemabancario.exceptions.CustomException;
import com.empleados.sistemabancario.models.SaldoModel;
import com.empleados.sistemabancario.models.TransferenciaModel;
import com.empleados.sistemabancario.repositories.CuentaBancariaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CuentaBancariaService {
    @Autowired
    private CuentaBancariaRepository cuentaBancariaRepository;

    private CuentaBancaria getAll(String cuenta){
        CuentaBancaria cuentaBancaria = cuentaBancariaRepository.findByNumeroCuenta(cuenta);
        if(cuentaBancaria == null){
            throw new CustomException("No existe el numero de cuenta!");
        }
        return cuentaBancaria;
    }

    private CuentaBancaria findById(String cuenta){
        CuentaBancaria cuentaBancaria = cuentaBancariaRepository.findByNumeroCuenta(cuenta);
        if(cuentaBancaria != null){
            throw new CustomException("Ya existe un numero de cuenta");
        }
        return cuentaBancaria;
    }

    public ResponseEntity<SaldoModel> getSaldo(String cuenta){
        CuentaBancaria cuentaBancaria = getAll(cuenta);

        SaldoModel saldoModel = new SaldoModel();
        saldoModel.setNumeroCuenta(cuenta);
        saldoModel.setSaldo(cuentaBancaria.getSaldo());
        return new ResponseEntity<>(saldoModel, HttpStatus.OK);
    }

    public ResponseEntity<CuentaBancaria> create(CuentaBancaria cuentaBancaria){
        findById(cuentaBancaria.getNumeroCuenta());

        System.out.println(cuentaBancaria);
        cuentaBancaria.setSaldo(0L);
        cuentaBancariaRepository.save(cuentaBancaria);
        return new ResponseEntity<>(cuentaBancaria, HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<SaldoModel> updateSaldo(String cuenta, Long monto){
        valdidateMonto(monto);
        CuentaBancaria cuentaBancaria = getAll(cuenta);
        SaldoModel saldoModel = new SaldoModel();

        cuentaBancaria.setSaldo(cuentaBancaria.getSaldo() + monto);
        cuentaBancariaRepository.save(cuentaBancaria);

        saldoModel.setNumeroCuenta(cuenta);
        saldoModel.setSaldo(cuentaBancaria.getSaldo());
        return new ResponseEntity<>(saldoModel, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<SaldoModel> retiroSaldo(String cuenta, Long monto){
        valdidateMonto(monto);
        CuentaBancaria cuentaBancaria = getAll(cuenta);
        SaldoModel saldoModel = new SaldoModel();

        cuentaBancaria.setSaldo(cuentaBancaria.getSaldo() - monto);
        cuentaBancariaRepository.save(cuentaBancaria);

        saldoModel.setNumeroCuenta(cuenta);
        saldoModel.setSaldo(cuentaBancaria.getSaldo());
        return new ResponseEntity<>(saldoModel, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<TransferenciaModel> transferencia(String cuenta1, String cuenta2, Long monto){
        valdidateMonto(monto);
        CuentaBancaria cuentaBancaria1 = getAll(cuenta1);
        CuentaBancaria cuentaBancaria2 = getAll(cuenta2);

        if(cuentaBancaria1.getSaldo() < monto){
            throw new CustomException("Saldo insuficiente!");
        }

        cuentaBancaria1.setSaldo(cuentaBancaria1.getSaldo() - monto);
        cuentaBancaria2.setSaldo(cuentaBancaria2.getSaldo() + monto);
        cuentaBancariaRepository.save(cuentaBancaria1);
        cuentaBancariaRepository.save(cuentaBancaria2);

        TransferenciaModel transferenciaModel = new TransferenciaModel();
        transferenciaModel.setNumeroCuenta1(cuentaBancaria1.getNumeroCuenta());
        transferenciaModel.setSaldo1(cuentaBancaria1.getSaldo());
        transferenciaModel.setNumeroCuenta2(cuentaBancaria2.getNumeroCuenta());
        transferenciaModel.setSaldo2(cuentaBancaria2.getSaldo());

        return new ResponseEntity<>(transferenciaModel, HttpStatus.OK);
    }

    private void valdidateMonto(Long monto){
        if (monto < 0){
            throw new CustomException("Ingresa una cantidad valida!");
        }
    }
}
