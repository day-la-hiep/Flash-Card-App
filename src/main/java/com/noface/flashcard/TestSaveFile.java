package com.noface.flashcard;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;

import com.noface.flashcard.model.Card;
import com.noface.flashcard.utils.FileGenerator;

public class TestSaveFile {
   public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
      TestSaveFile test = new TestSaveFile();
      test.testData();

      
   }

   public void testWriteCard() throws FileNotFoundException, IOException{
      Card card = new Card("Front content", "Back content", LocalDateTime.now().toString());
      System.out.println(card);
      ObjectOutputStream oos = new ObjectOutputStream(
               new FileOutputStream("out.dat"));
      oos.writeObject(card);
      oos.close();
   }

   public void testReadCard() throws FileNotFoundException, IOException, ClassNotFoundException {
      ObjectInputStream ois = new ObjectInputStream(new FileInputStream("out.dat"));
      Card card = (Card) ois.readObject();
      ois.close();
      System.out.println(card.hashCode());
      System.out.println(card);
   }

   public void testData() throws FileNotFoundException, IOException, ClassNotFoundException{
      String username = "KhongPhaiLaHiep";
      FileGenerator generator = new FileGenerator();
      generator.generateUser(username);
      System.out.println(generator.readSampleUser(username));

   }
}
