package com.example.scratch.service;

import com.example.scratch.model.PurchaseHistory;
import com.example.scratch.repository.projection.PurchaseItem;

import java.util.List;

public interface IPurchaseHistoryService {
  PurchaseHistory savePurchaseHistory(PurchaseHistory purchaseHistory);

  List<PurchaseItem> findPurchasedItemsOfUser(Long userId);
}
