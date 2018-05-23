package com.payclip.assessment.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.payclip.assessment.bo.Transaction;
import com.payclip.assessment.dao.PayClipDAO;

@Repository(value = "databasePayClipDAOImpl")
public class DatabasePayClipDAOImpl implements PayClipDAO {

   private static final long serialVersionUID = -2021004905590111141L;

   @Override
   public Transaction save(Transaction transaction) throws Exception {
      throw new UnsupportedOperationException("Not implemented yet");
   }

   @Override
   public Transaction findByUserIdAndTransactionId(Long userId, String transactionId) throws Exception {
      throw new UnsupportedOperationException("Not implemented yet");
   }

   @Override
   public List<Transaction> getByUserId(Long userId) throws Exception {
      throw new UnsupportedOperationException("Not implemented yet");
   }

}