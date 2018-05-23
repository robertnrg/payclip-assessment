package com.payclip.assessment.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
public class UUIDUtilTest {

   @Test
   public void testUUIDUtil_JunkCharacters() {
      String uuidInStr = "abc";
      log.debug("UUID: {}", uuidInStr);
      assertFalse(UUIDUtil.isValidUUID(uuidInStr));
   }

   @Test
   public void testUUIDUtil_ProperFormat() {
      String uuidInStr = "3dd4fa6e-2899-4429-b818-d34fe8df5dd0";
      log.debug("UUID: {}", uuidInStr);
      assertTrue(UUIDUtil.isValidUUID(uuidInStr));
   }

   @Test
   public void testUUIDUtil_MissingCharacters() {
      String uuidInStr = "3dd4fa-2899-4429-b818-d34fe8df5d";
      log.debug("UUID: {}", uuidInStr);
      assertFalse(UUIDUtil.isValidUUID(uuidInStr));
   }

   @Test
   public void testUUIDUtil_MoreCharacters() {
      String uuidInStr = "3dd5ee1234-2899-4429-b818-d34fe8df5ee123";
      log.debug("UUID: {}", uuidInStr);
      assertFalse(UUIDUtil.isValidUUID(uuidInStr));
   }

}