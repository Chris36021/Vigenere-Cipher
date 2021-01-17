/**
 * 
 * Created and designed by Christopher Castillo. 
 * Copyright © 2021 Chris36021. All rights reserved.
 * Created in 2020. Edited in 2021.
 *
 */
import java.util.Scanner;

public class VigenereCipherTester {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		System.out.print("Enter the text that is to be encrypted: ");
		Scanner inWord = new Scanner(System.in);
		String word = inWord.nextLine();
		
		System.out.print("Now enter the key to begin encryption: ");
		Scanner inKey = new Scanner(System.in);
		String key = inKey.nextLine();
		
		String encryptedWord = VigenereCipher.encryptText(word, key);
		String decryptedWord = VigenereCipher.decryptText(encryptedWord, key);
		
		System.out.println("");
		System.out.print("The encoded text is: ");
		System.out.println(encryptedWord);
		System.out.println("");
		System.out.print("The original text is: ");
		System.out.println(decryptedWord);
	}
}