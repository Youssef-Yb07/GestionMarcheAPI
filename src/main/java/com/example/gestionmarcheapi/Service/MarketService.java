package com.example.gestionmarcheapi.Service;

import com.example.gestionmarcheapi.Entity.Market;
import com.example.gestionmarcheapi.Repository.MarketRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class MarketService {

    private final MarketRepository marketRepository;

    public Market CreateMarket(Market market){
        return marketRepository.save(market);
    }
}
