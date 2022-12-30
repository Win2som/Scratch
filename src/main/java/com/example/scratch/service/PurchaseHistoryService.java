package com.example.scratch.service;

import com.example.scratch.model.PurchaseHistory;
import com.example.scratch.repository.PurchaseHistoryRepository;
import com.example.scratch.repository.projection.PurchaseItem;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseHistoryService implements IPurchaseHistoryService{

  private final PurchaseHistoryRepository purchaseHistoryRepository;

  public PurchaseHistoryService(PurchaseHistoryRepository purchaseHistoryRepository) {
    this.purchaseHistoryRepository = purchaseHistoryRepository;
  }

  @Override
  public PurchaseHistory savePurchaseHistory(PurchaseHistory purchaseHistory){

    purchaseHistory.setPurchaseTime(LocalDateTime.now());

    return purchaseHistoryRepository.save(purchaseHistory);
  }

  @Override
  public List<PurchaseItem> findPurchasedItemsOfUser(Long userId) {
    return purchaseHistoryRepository.findAllPurchasesOfUser(userId);
  }

}
