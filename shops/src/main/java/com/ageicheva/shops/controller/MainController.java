package com.ageicheva.shops.controller;

import com.ageicheva.common.dto.BuyDTO;
import com.ageicheva.common.dto.BuyDtoRow;
import com.ageicheva.shops.entity.Position;
import com.ageicheva.shops.entity.PositionHistory;
import com.ageicheva.shops.entity.ReceiptHistory;
import com.ageicheva.shops.entity.Shop;
import com.ageicheva.shops.repository.PositionHistoryRepository;
import com.ageicheva.shops.repository.PositionRepository;
import com.ageicheva.shops.repository.ReceiptHistoryRepository;
import com.ageicheva.shops.repository.ShopRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
public class MainController {
    ShopRepository shopRepository;
    PositionRepository positionRepository;
    PositionHistoryRepository positionHistoryRepository;
    ReceiptHistoryRepository receiptHistoryRepository;

    @PostMapping(value ="/addShop")
    public ResponseEntity<String> addShop(@RequestParam String name, @RequestParam String address, @RequestParam String phoneNumber) {
        if (!shopRepository.existsByName(name)) {
            return new ResponseEntity<>(String.valueOf(shopRepository.save(Shop.builder().name(name).address(address).phoneNumber(phoneNumber).build()).getId()), HttpStatus.OK);
        }
        return ResponseEntity.status(400).body("Магазин с таким именем уже есть");
    }

    @PostMapping(value ="/addPositions")
    public void addPositions(@RequestBody List<Position> positions) {
        positionRepository.saveAll(positions);

    }

    @GetMapping(value ="/getShopPositions")
    public List<Position> getShopPosition(@RequestParam Integer shopId) {
        return positionRepository.findAllByShopId(shopId);

    }

    @PostMapping("/buy")
    public ResponseEntity<String> buy(@RequestBody BuyDTO dto) {
        Gson gson = new GsonBuilder().create();
        List<PositionHistory> positionHistories = new ArrayList<>();


        Set<String> set = new HashSet<>();
        var builder = ReceiptHistory.builder().paymentMethod(dto.getPaymentMethod()).userName(dto.getUserName()).shopId(dto.getShopId());
        if (dto.getCategory().equals(" ")) {
            for (BuyDtoRow row : dto.getPositionsHistory()) {
                val position = positionRepository.findByPositionNameAndShopId(row.getPositionName(), dto.getShopId());
                set.add(position.getCategory());
            }
            if (set.size() > 1) {

                builder = builder.category("mixed");
            } else {
                builder = builder.category(set.stream().iterator().next());

            }
        } else {
            builder = builder.category(dto.getCategory());

        }


        for (BuyDtoRow row : dto.getPositionsHistory()) {
            val position = positionRepository.findByPositionNameAndShopId(row.getPositionName(), dto.getShopId());
            if (position.getQuantity() < row.getQuantity()) {
                return ResponseEntity.status(204).build();
            }
            position.setQuantity(position.getQuantity() - row.getQuantity());
            positionRepository.save(position);
            var builderPositionHistory = PositionHistory.builder().positionName(position.getPositionName()).price(position.getPrice()).quantity(row.getQuantity()).build();
            positionHistories.add(builderPositionHistory);


        }

        String payloadStr = gson.toJson(receiptHistoryRepository.save(builder.positionsHistory(positionHistories).build()));

        return new ResponseEntity<String>(payloadStr, HttpStatus.OK); //ResponseEntity.ok().build();
    }


}
