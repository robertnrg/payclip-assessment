package com.payclip.assessment.dao.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.payclip.assessment.bo.Transaction;
import com.payclip.assessment.dao.PayClipDAO;
import com.payclip.assessment.util.FileUtil;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository(value = "filePayClipDAOImpl")
public class FilePayClipDAOImpl implements PayClipDAO {

   private static final long   serialVersionUID = -221750289736722823L;

   @Getter(value = AccessLevel.PRIVATE)
   @Value(value = "${payclip.files-path}")
   private String              filePath;

   private static final String FILE_SEPARATOR   = System.getProperty("file.separator");

   @Override
   public Transaction save(Transaction transaction) throws Exception {
      transaction.setTransactionId(UUID.randomUUID().toString());
      String canonicalPath = this.getFilePath() + FILE_SEPARATOR + transaction.getUserId() + "_" + transaction.getTransactionId();
      log.debug("Path: {}", canonicalPath);
      FileUtil.writeObjectInFile(canonicalPath, false, transaction);
      log.info("Transaction {} was saved", transaction);

      return transaction;
   }

   @Override
   public Transaction findByUserIdAndTransactionId(Long userId, String transactionId) throws Exception {
      String canonicalPath = this.getFilePath() + FILE_SEPARATOR + userId + "_" + transactionId;
      log.debug("Path: {}", canonicalPath);
      return FileUtil.readObjectInFile(canonicalPath, Transaction.class);
   }

   @Override
   public List<Transaction> getByUserId(Long userId) throws Exception {
      log.debug("Path: {}", this.getFilePath());
      return FileUtil.readObjectsInFiles(this.getFilePath(), Long.toString(userId), Transaction.class);
   }

}