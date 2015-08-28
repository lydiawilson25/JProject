package com.healtline.informatics.normalization;

/**
 * This class is used to insure that the dictionary was properly initialized.
 * 
 * @author Nick Moseyko
 * 
 */
public class DictionaryInitializationException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Returns an error message.
	 * 
	 * @return an error message
	 * 
	 */
	public String getMessage() {
		return "Dictionary is not properly initialized";
	}

}
