package com.payclip.assessment.config;

import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JacksonConfig implements java.io.Serializable {

   private static final long serialVersionUID = -8615967492736090125L;

   @Bean
   public ObjectMapper objectMapper() {
      ObjectMapper mapper = new ObjectMapper();
      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
      mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));

      return mapper;
   }

}