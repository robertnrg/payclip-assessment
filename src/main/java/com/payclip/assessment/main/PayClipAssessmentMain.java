package com.payclip.assessment.main;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payclip.assessment.bo.SumTransaction;
import com.payclip.assessment.bo.Transaction;
import com.payclip.assessment.exception.InvalidPayClipOperationException;
import com.payclip.assessment.service.PayClipService;
import com.payclip.assessment.util.UUIDUtil;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component(value = "payClipAssessmentMain")
public final class PayClipAssessmentMain implements java.io.Serializable {

   private static final long serialVersionUID = 4640433254227934639L;

   @Autowired
   @Getter(value = AccessLevel.PRIVATE)
   private PayClipService    payClipService;

   @Autowired
   @Getter(value = AccessLevel.PRIVATE)
   private ObjectMapper      objectMapper;

   public void run(String... args) {
      try {
         if (args.length == 2) {
            switch (args[1].trim().toLowerCase()) {
               case "list":
                  List<Transaction> transactions = this.getPayClipService().getByUserId(Long.parseLong(args[0].trim()));
                  Collections.sort(transactions);
                  log.info("{}", this.getObjectMapper().writeValueAsString(transactions));
                  break;
               case "sum":
                  SumTransaction sumTransaction = this.getPayClipService().sum(Long.parseLong(args[0].trim()));
                  log.info("{}", this.getObjectMapper().writeValueAsString(sumTransaction));
                  break;
               default:
                  if (StringUtils.isNumeric(args[0]) && UUIDUtil.isValidUUID(args[1])) {
                     Transaction transactionByUser = this.getPayClipService().findByUserIdAndTransactionId(Long.parseLong(args[0].trim()), args[1].trim());
                     log.info("{}", this.getObjectMapper().writeValueAsString(transactionByUser));
                  } else {
                     throw new InvalidPayClipOperationException(Arrays.toString(args));
                  }
                  break;
            }
         } else if (args.length == 3) {
            if (StringUtils.isNumeric(args[0]) && args[1].trim().equalsIgnoreCase("add")) {
               Transaction transaction = this.getPayClipService().save(Long.parseLong(args[0].trim()), args[2].trim());
               log.info("{}", this.getObjectMapper().writeValueAsString(transaction));
            } else {
               throw new InvalidPayClipOperationException(Arrays.toString(args));
            }
         } else {
            log.error("{}", "Invalid number of arguments!");
         }
      } catch (FileNotFoundException fne) {
         log.debug("{}", fne.getMessage(), fne);
         log.error("{}", "Transaction not found!");
      } catch (ConstraintViolationException cve) {
         log.debug("{}", cve.getMessage(), cve);
         log.error("{}", "All parameters are required!");
      } catch (InvalidPayClipOperationException pce) {
         log.debug("{}", pce.getMessage(), pce);
         log.error("{}", "Invalid operation!");
      } catch (Exception ex) {
         log.debug("{}", ex.getMessage(), ex);
         log.error("{}", "Internal Error!");
      }
   }

}