package com.payclip.assessment.util;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileUtil implements java.io.Serializable {

   private static final long serialVersionUID = 4568245122477163027L;

   public static void writeObjectInFile(String fileName, boolean overwriting, Serializable object) throws IOException {
      if (!overwriting) {
         File fileToWrite = new File(fileName);
         if (fileToWrite.exists() && !fileToWrite.isDirectory()) {
            throw new FileAlreadyExistsException("File already exist!");
         }
      }
      try (FileOutputStream fos = new FileOutputStream(fileName, overwriting); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
         oos.writeObject(object);
         log.debug("Object {} saved", object);
      } catch (IOException ioe) {
         log.error("{}", ioe.getMessage(), ioe);
         throw ioe;
      }
   }

   public static <S> S readObjectInFile(String fileName, Class<S> serializableClass) throws IOException, ClassNotFoundException {
      try (FileInputStream fis = new FileInputStream(fileName); ObjectInputStream ois = new ObjectInputStream(fis)) {
         return serializableClass.cast(ois.readObject());
      } catch (Exception ex) {
         log.error("{}", ex.getMessage(), ex);
         throw ex;
      }
   }

   public static <S> List<S> readObjectsInFiles(String path, String contains, Class<S> serializableClass) throws IOException, ClassNotFoundException {
      List<S> serializableList = new ArrayList<>();
      for (File file : Arrays.stream(Objects.requireNonNull(new File(path).listFiles())).filter(f -> !f.isDirectory() && f.getName().contains(contains))
            .collect(Collectors.toList())) {
         serializableList.add(FileUtil.readObjectInFile(file.getCanonicalPath(), serializableClass));
      }

      return serializableList;
   }

}