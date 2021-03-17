package com.ageicheva.asd.controller;

import com.ageicheva.asd.entity.CustomerUser;
import com.ageicheva.asd.entity.Position;
import com.ageicheva.asd.entity.Receipt;
import com.ageicheva.asd.repository.PositionRepository;
import com.ageicheva.asd.repository.ReceiptRepository;
import com.ageicheva.asd.repository.UserRepository;
import com.ageicheva.asd.service.RestTemplateService;
import com.ageicheva.common.dto.BuyDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

@AllArgsConstructor
@RestController
public class MainController {
    UserRepository userRepository;
    PositionRepository positionRepository;
    ReceiptRepository receiptRepository;
    RestTemplateService restTemplateService;

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestParam String name) {
        if (!userRepository.existsByUserName(name)) {
            userRepository.save(CustomerUser.builder().userName(name).build());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(400).body("Пользователь с таким именем уже есть");
        }
    }
    @GetMapping(value ="/getUser")
    public ResponseEntity<String> getUser(@RequestParam Integer id) {
        Gson gson = new GsonBuilder().create();
        String payloadStr = gson.toJson(userRepository.findById(id));
        return new ResponseEntity<String>(payloadStr, HttpStatus.OK);
    }
    @PostMapping(value ="/buy")
    public ResponseEntity<?> buy(@RequestBody BuyDTO dto) {
        int fullPrice = 0;
        if (!userRepository.existsByUserName(dto.getUserName())) {
            return ResponseEntity.status(401).build();
        }
        try {

            BuyDTO receipt = restTemplateService.postBuy(dto);
            for(val price:receipt.getPositionsHistory()){
                fullPrice +=price.getPrice();
            }
            val receiptId = receiptRepository.save(Receipt.builder().category(receipt.getCategory()).shopId(dto.getShopId()).fullPrice(fullPrice).userId(dto.getUserId()).userName(dto.getUserName()).build()).getReceiptId();
            for (val row : receipt.getPositionsHistory()) {
                positionRepository.save(new Position(receiptId, row.getPositionName(), row.getPrice(), row.getQuantity()));
            }
        } catch (HttpStatusCodeException exception) {
            return new ResponseEntity<>(exception.getStatusCode());
        }


        return ResponseEntity.ok().build();
    }

    /*@GetMapping("/find/allPurchasesById")
    public Optional<Position> findAllPurchasesById(@RequestParam Integer id) {
        return positionRepository.findById(id);
    }*/


   /* @GetMapping("/find/idAndCreatedBetween")
    public List<Purchases> findAllByIdAndCreatedBetween(@RequestParam Integer id, @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date from, @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date to) {
        // return purchasesRepository.findAllByIdAndCreatedBetween(id, from, to);
    }

    @GetMapping("/find/price")
    public List<Purchases> findAllByPrice(@RequestParam Integer price, @RequestParam Integer id) {
        //return purchasesRepository.findAllByIdAndPrice(id,price);
    }

    @GetMapping("/find/IdAndProductName")
    public List<Purchases> findAllByIdAndProductName(@RequestParam String productName, @RequestParam Integer id) {
        //return purchasesRepository.findAllByIdAndProductName(id ,productName);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Integer id, @RequestParam String productName) {
        // purchasesRepository.deleteByIdAndProductName(id,productName);

    }*/

}
