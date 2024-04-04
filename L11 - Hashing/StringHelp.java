// CS 0445 Spring 2022
// Recitation 9 Solution for generating a random String up to some
// maximum number of characters.

import java.util.*;

public class StringHelp
{
     static Random R = new Random();

     public static String randWord(int maxSize)
     {
          int length = R.nextInt(maxSize) + 1; // length between 1 and maxSize
          char [] letters = new char[length];  // make array of char
          for (int i = 0; i < letters.length; i++)
          {
               int val = R.nextInt(26) + 65;   // generate random int between
               				// 65 and 90.  These correspond to letters 'A'-'Z'
               char ch = (char) val;	// convert to a char
               letters[i] = ch;			// assign to the array		
          }
          return new String(letters);	// make a String out of chars
     }
}
