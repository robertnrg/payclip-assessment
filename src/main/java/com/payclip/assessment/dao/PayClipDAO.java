package com.payclip.assessment.dao;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.payclip.assessment.bo.Transaction;

@Validated
public interface PayClipDAO extends java.io.Serializable {

   Transaction save(@Valid Transaction transaction) throws Exception;

   Transaction findByUserIdAndTransactionId(Long userId, String transactionId) throws Exception;

   List<Transaction> getByUserId(Long userId) throws Exception;

}