package com.example.scratch.controller;


import com.example.scratch.model.PurchaseHistory;
import com.example.scratch.security.UserPrincipal;
import com.example.scratch.service.IPurchaseHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/purchase-history")
public class PurchaseHistoryController {

  @Autowired
  private IPurchaseHistoryService mPurchaseHistoryService;


  @PostMapping
  public ResponseEntity<?> savePurchaseHistory(@RequestBody PurchaseHistory purchaseHistory){

    return new ResponseEntity(mPurchaseHistoryService.savePurchaseHistory(purchaseHistory),
                              HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<?> getAllPurchasesOfUser(@AuthenticationPrincipal UserPrincipal userPrincipal){

    return new ResponseEntity<>(mPurchaseHistoryService.findPurchasedItemsOfUser(userPrincipal.getId()), HttpStatus.OK);
  }
}
