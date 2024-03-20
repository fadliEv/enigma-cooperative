package com.enigmacamp.enigmacoop.service;

import com.enigmacamp.enigmacoop.entity.TrxSaving;

import java.util.List;

public interface TrxSavingService {
    TrxSaving createTrxSaving(TrxSaving trxSaving);
    List<TrxSaving> getListTrxSaving();
}
