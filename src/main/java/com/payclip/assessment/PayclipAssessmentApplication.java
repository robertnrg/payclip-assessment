package com.payclip.assessment;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.payclip.assessment.main.PayClipAssessmentMain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = "com.payclip.assessment.*")
public class PayclipAssessmentApplication implements CommandLineRunner {

   @Autowired
   @Getter(value = AccessLevel.PRIVATE)
   private PayClipAssessmentMain payClipAssessmentMain;

   public static void main(String[] args) {
      SpringApplication.run(PayclipAssessmentApplication.class, args);
   }

   @Override
   public void run(String... args) throws Exception {
      log.debug("Args: {}", Arrays.toString(args));
      this.getPayClipAssessmentMain().run(args);
      Runtime.getRuntime().exit(0);
   }

}