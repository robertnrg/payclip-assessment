package com.payclip.assessment.util;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UUIDUtil implements java.io.Serializable {

   private static final long serialVersionUID = 29387865162776055L;

   public static boolean isValidUUID(String uuidStr) {
      try {
         log.debug("UUID receipt: {}", uuidStr);
         return UUID.fromString(uuidStr.trim()).toString().equals(uuidStr);
      } catch (IllegalArgumentException iae) {
         log.error("{}", iae.getMessage(), iae);

         return false;
      }
   }

}