package com.payclip.assessment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payclip.assessment.bo.SumTransaction;
import com.payclip.assessment.bo.Transaction;
import com.payclip.assessment.dao.PayClipDAO;
import com.payclip.assessment.service.PayClipService;

import lombok.AccessLevel;
import lombok.Getter;

@Service(value = "payClipService")
public final class PayClipServiceImpl implements PayClipService {

   private static final long serialVersionUID = 2499375205127101072L;

   @Autowired
   @Getter(value = AccessLevel.PRIVATE)
   @Qualifier(value = "filePayClipDAOImpl")
   private PayClipDAO        payClipDAO;

   @Autowired
   @Getter(value = AccessLevel.PRIVATE)
   private ObjectMapper      objectMapper;

   @Override
   public Transaction save(Long userId, String jsonTransaction) throws Exception {
      Transaction transaction = this.getObjectMapper().readValue(jsonTransaction, Transaction.class);
      transaction.setUserId(userId);

      return this.getPayClipDAO().save(transaction);
   }

   @Override
   public Transaction findByUserIdAndTransactionId(Long userId, String transactionId) throws Exception {
      return this.getPayClipDAO().findByUserIdAndTransactionId(userId, transactionId);
   }

   @Override
   public List<Transaction> getByUserId(Long userId) throws Exception {
      return this.getPayClipDAO().getByUserId(userId);
   }

   @Override
   public SumTransaction sum(Long userId) throws Exception {
      return new SumTransaction(userId, this.getByUserId(userId).stream().map(Transaction::getAmount).reduce(0D, Double::sum));
   }

}