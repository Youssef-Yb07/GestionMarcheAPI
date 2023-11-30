package com.example.gestionmarcheapi.Controller;

import com.example.gestionmarcheapi.Entity.Market;
import com.example.gestionmarcheapi.Service.MarketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("markets")
@RequiredArgsConstructor
@Tag(name = "Marché", description = "Gestion des marchés")
public class MarketController {

    private final MarketService marketService;

    @Operation(summary = "Create Market")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Marché created successfully"),
            @ApiResponse(responseCode = "400",description = "Invalid request")
    })
    @PostMapping
    public ResponseEntity<Market> CreateMarket(Market market){
        try{
            return new ResponseEntity<>(marketService.CreateMarket(market), HttpStatus.CREATED);
        }catch(Exception e){
         e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
