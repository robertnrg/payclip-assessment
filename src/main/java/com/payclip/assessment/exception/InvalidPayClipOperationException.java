package com.payclip.assessment.exception;

public final class InvalidPayClipOperationException extends RuntimeException {

   private static final long serialVersionUID = 1861448236096136732L;

   public InvalidPayClipOperationException(String message) {
      super(message);
   }

}