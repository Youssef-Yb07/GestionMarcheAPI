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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("markets")
@RequiredArgsConstructor
@Tag(name = "Marchés", description = "Gestion des marchés")
public class MarketController {

    private final MarketService marketService;

    @Operation(summary = "Create Market")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Marché created successfully"),
            @ApiResponse(responseCode = "400",description = "Invalid request")
    })
    @PostMapping("/create")
    public ResponseEntity<Market> CreateMarket(@RequestBody Market market){
        try{
            return new ResponseEntity<>(marketService.CreateMarket(market), HttpStatus.CREATED);
        }catch(Exception e){
         e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Market>> GetMarket(){
        return new ResponseEntity<>(marketService.GetMarketList(), HttpStatus.OK);
    }

}
