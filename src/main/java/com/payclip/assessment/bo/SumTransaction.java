package com.payclip.assessment.bo;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "userId" })
public final class SumTransaction implements java.io.Serializable {

   private static final long serialVersionUID = -1164844956030337880L;

   @NotNull(message = "amount cannot be null")
   @JsonProperty(value = "user_id", required = true)
   private Long              userId;

   @NotNull(message = "amount cannot be null")
   @JsonProperty(value = "sum", required = true)
   private Double            sum;

}