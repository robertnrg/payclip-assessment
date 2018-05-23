package com.payclip.assessment.service;

import java.util.List;

import com.payclip.assessment.bo.SumTransaction;
import com.payclip.assessment.bo.Transaction;

public interface PayClipService extends java.io.Serializable {

   Transaction save(Long userId, String jsonTransaction) throws Exception;

   Transaction findByUserIdAndTransactionId(Long userId, String transactionId) throws Exception;

   List<Transaction> getByUserId(Long userId) throws Exception;

   SumTransaction sum(Long userId) throws Exception;

}