package com.enigmacamp.enigmacoop.service.impl;

import com.enigmacamp.enigmacoop.constant.SavingType;
import com.enigmacamp.enigmacoop.entity.Nasabah;
import com.enigmacamp.enigmacoop.entity.Saving;
import com.enigmacamp.enigmacoop.entity.TrxSaving;
import com.enigmacamp.enigmacoop.repository.TrxSavingRepository;
import com.enigmacamp.enigmacoop.service.NasabahService;
import com.enigmacamp.enigmacoop.service.SavingService;
import com.enigmacamp.enigmacoop.service.TrxSavingService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TrxSavingServiceImpl implements TrxSavingService {

    private final TrxSavingRepository trxSavingRepository;
    private final SavingService savingService;
    private final NasabahService nasabahService;

    // Register nasabah - data saving
    @Override
    @Transactional(rollbackOn = Exception.class)
    public TrxSaving createTrxSaving(TrxSaving trxSaving) {
        // cek saving apakah ada
        Saving saving = savingService.getSavingById(trxSaving.getSaving().getId());
        // Cek apakah topup atau penarikan saldo
        if (trxSaving.getSavingType().equals(SavingType.DEBIT)){
           saving.setBalance(saving.getBalance()+trxSaving.getAmount());
        }else{
            if (trxSaving.getAmount() < saving.getBalance()) {
                saving.setBalance(saving.getBalance()-trxSaving.getAmount());
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Saldo Tidak cukup");
            }
        }
        return trxSavingRepository.saveAndFlush(trxSaving);
    }

    @Override
    public List<TrxSaving> getListTrxSaving() {
        return trxSavingRepository.findAll();
    }
}