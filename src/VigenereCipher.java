/**
 * 
 * Created and designed by Christopher Castillo. 
 * Copyright © 2021 Chris36021. All rights reserved.
 * Created in 2020. Edited in 2021.
 *
 */
public class VigenereCipher {
	
	//These "dictionaries" facilitate mathematical operations of alphabetical characters
	//by assigning them each a number (its index in the String).
	public static final String ALPHABET_LOWER_DICTIONARY = "abcdefghijklmnopqrstuvwxyz";
	public static final String ALPHABET_UPPER_DICTIONARY = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	//This function is used to encrypt the text i.e., to mask its content.
	public static String encryptText(String text, String key) {
		if(key.length() <= 0) {
			throw new IllegalArgumentException("The key for the cipher cannot be empty.");
		}
		String result = "";
		/**
		 * Main encrypting loop. This loop goes through each character in the sequence and turns it
		 * into another character using the proper key. The way the cypher works is basically by
		 * "rolling over" or "shifting" a certain number of steps according to the index number
		 * of the currently selected character from the key. Afterwards, the old character
		 * is "replaced" by the newly selected character.
		 */
		for (int i = 0; i < text.length(); i++) {
			char letter = text.charAt(i);
			int keyShift;
			char newLetter;
			//If the current letter is uppercase, it will be replaced with another uppercase letter.
			if(Character.isUpperCase(letter)) {
				String tempKey = key.toUpperCase();
				keyShift = ALPHABET_UPPER_DICTIONARY.indexOf(tempKey.charAt(i % tempKey.length()));
				int cipherLPos = ((ALPHABET_UPPER_DICTIONARY.indexOf(letter) + keyShift) % 26);
				newLetter = ALPHABET_UPPER_DICTIONARY.charAt(cipherLPos);
				
			}
			//If the current letter is lowercase, it will be replaced with another lowercase letter.
			else if(Character.isLowerCase(letter)) {
				String tempKey = key.toLowerCase();
				keyShift = ALPHABET_LOWER_DICTIONARY.indexOf(tempKey.charAt(i % tempKey.length()));
				int cipherLPos = ((ALPHABET_LOWER_DICTIONARY.indexOf(letter) + keyShift) % 26);
				newLetter = ALPHABET_LOWER_DICTIONARY.charAt(cipherLPos);
			}
			/**
			 * The Vigenere Cypher doesn't account for numbers, but I decided to implement my own
			 * encryption method for them. In this case, I simply added the value that is usually 
			 * related to the currently selected key character and calculated its modulo 10 to
			 * obtain a digit in the 0-9 range. Using this method, numbers aren't just assigned 
			 * a different number that is constant for itself, so it's harder to notice a 
			 * pattern in the encrypted text.
			 */
			else if(Character.isDigit(letter)) {
				String tempKey = key.toLowerCase();
				int newNum = (Character.getNumericValue(letter) + ALPHABET_LOWER_DICTIONARY.indexOf(tempKey.charAt(i % tempKey.length()))) % 10;
				newLetter = Character.forDigit(newNum, 10);
			}
			//If the current "letter" is a symbol or any other special character it is kept the same.
			else {
				newLetter = letter;
			}
			result += newLetter;
		}
		//Return the encrypted text.
		return result;
	}
	//This function is used to decrypt the text i.e., to unmask its content. Basically
	//employs the same algorithm that is used to encrypt the text, just backwards.
	public static String decryptText(String text, String key) {
		if(key.length() <= 0) {
			throw new IllegalArgumentException("The key for the cipher cannot be empty.");
		}
		String result = "";
		for (int i = 0; i < text.length(); i++) {
			char letter = text.charAt(i);
			int keyShift;
			char newLetter;
			if(Character.isUpperCase(letter)) {
				String tempKey = key.toUpperCase();
				keyShift = ALPHABET_UPPER_DICTIONARY.indexOf(tempKey.charAt(i % tempKey.length()));
				int cipherLPos = ((ALPHABET_UPPER_DICTIONARY.indexOf(letter) - keyShift) % 26);
				if(cipherLPos < 0) {
					cipherLPos += 26;
				}
				newLetter = ALPHABET_UPPER_DICTIONARY.charAt(cipherLPos);
				
			}
			else if(Character.isLowerCase(letter)) {
				String tempKey = key.toLowerCase();
				keyShift = ALPHABET_LOWER_DICTIONARY.indexOf(tempKey.charAt(i % tempKey.length()));
				int cipherLPos = ((ALPHABET_LOWER_DICTIONARY.indexOf(letter) - keyShift) % 26);
				if(cipherLPos < 0) {
					cipherLPos += 26;
				}
				newLetter = ALPHABET_LOWER_DICTIONARY.charAt(cipherLPos);
			}
			else if(Character.isDigit(letter)) {
				String tempKey = key.toLowerCase();
				int newNum = (Character.getNumericValue(letter) - ALPHABET_LOWER_DICTIONARY.indexOf(tempKey.charAt(i % tempKey.length()))) % 10;
				if(newNum < 0) {
					newNum += 10;
				}
				newLetter = Character.forDigit(newNum, 10);
			}
			else {
				newLetter = letter;
			}
			result += newLetter;
		}
		return result;
	}
}