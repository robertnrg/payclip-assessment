package com.payclip.assessment.bo;

import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "transactionId" })
public final class Transaction implements java.io.Serializable, Comparable<Transaction> {

   private static final long serialVersionUID = 6327044045170919954L;

   @JsonProperty(value = "transaction_id", required = true)
   private String            transactionId;

   @NotNull(message = "amount cannot be null")
   @JsonProperty(value = "amount", required = true)
   private Double            amount;

   @NotBlank(message = "description cannot be null")
   @JsonProperty(value = "description", required = true)
   private String            description;

   @NotNull(message = "date cannot be null")
   @JsonProperty(value = "date", required = true)
   private Date              date;

   @NotNull(message = "user_id cannot be null")
   @JsonProperty(value = "user_id", required = true)
   private Long              userId;

   public Transaction(String transactionId, Double amount, String description, Date date, Long userId) {
      this.setTransactionId(transactionId);
      this.setAmount(amount);
      this.setDescription(description);
      this.setDate(date);
      this.setUserId(userId);
   }

   public Date getDate() {
      return this.date != null ? new Date(this.date.getTime()) : null;
   }

   public void setDate(Date date) {
      this.date = date != null ? new Date(date.getTime()) : null;
   }

   @Override
   public int compareTo(Transaction transaction) {
      return Objects.requireNonNull(transaction.getDate()).compareTo(Objects.requireNonNull(this.getDate()));
   }

}