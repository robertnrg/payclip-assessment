package com.payclip.assessment.util;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.junit4.SpringRunner;

import com.payclip.assessment.bo.Transaction;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class FileUtilTests {

   @Test
   public void testFileUtil_createFile() throws IOException, ParseException {
      Transaction transactionOne = new Transaction("d373d206-6ee6-48dc-a73a-d0691c7bf404", 1.23 * ThreadLocalRandom.current().nextInt(1, 10 + 1), "",
            new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-20"), 435L);
      FileUtil.writeObjectInFile(transactionOne.getUserId() + "_" + transactionOne.getTransactionId(), false, transactionOne);
      log.debug("Saved {}", transactionOne);
      assertNotNull(transactionOne);
   }

   @Test
   public void testFileUtil_readFile() throws IOException, ClassNotFoundException {
      Transaction transaction = FileUtil.readObjectInFile("435_d373d206-6ee6-48dc-a73a-d0691c7bf404", Transaction.class);
      log.debug("{}", transaction);
      assertNotNull(transaction);
   }

   @Test
   public void testFileUtil_readFiles() throws IOException, ClassNotFoundException {
      List<Transaction> transactionList = FileUtil.readObjectsInFiles(".", "435_", Transaction.class);
      for (Transaction transaction : transactionList) {
         log.debug("Transaction: {}", transaction);
      }
      assertNotNull(transactionList);
   }

}