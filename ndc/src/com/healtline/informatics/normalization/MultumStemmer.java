package com.healtline.informatics.normalization;

import com.healtline.informatics.Controller;
import com.healtline.informatics.stemmer.HLPorterStemmer;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A slimmed-down version of com.helathline.stemmer.Stemmer. Only methods
 * relevant to Multum updates are left in the class and one convenience method
 * added ({@link #normalizeString(String)}).
 * 
 */
public class MultumStemmer {

	private static Logger logger = Logger.getLogger(MultumStemmer.class);

	private static HLPorterStemmer hlPS;

	/**
	 * Cleans a synonym string (e.g. white space, dash, slash, tags, etc.)
	 * 
	 * @param synonym
	 *            a synonym string
	 * @return a cleaned synonym string
	 */
	public static String cleanSynonym(String synonym) {

		synonym = " " + synonym.toLowerCase() + " ";
		synonym = synonym.replaceAll("\\-", " ").replaceAll("\\,", "")
				.replaceAll(", not otherwise specified ", " ").replaceAll(
						" <1> ", " ").replaceAll(" <2> ", " ").replaceAll(
						" <3> ", " ").replaceAll(" <4> ", " ").replaceAll(
						" <5> ", " ").replaceAll(" <6> ", " ").replaceAll(
						" <7> ", " ").replaceAll(" <17> ", " ").replaceAll(
						" nos ", " ").replaceAll(" nec ", " ").replaceAll(
						"<sub>", " ").replaceAll("</sub>", " ").replaceAll(
						"</sup>", " ").replaceAll("<sup>", " ").replaceAll(
						"\\>", " ").replaceAll("\\<", " ").replaceAll(
						" unspecified ", " ").replaceAll("\\(", " ")
				.replaceAll("\\)", " ");

		if (synonym.indexOf("[") != -1) {
			synonym = synonym.substring(0, synonym.indexOf("["))
					+ synonym.substring(synonym.indexOf("]") + 1).trim();
		}

		synonym = synonym.replaceAll(" of ", " ").replaceAll(" and ", " ")
				.replaceAll(" with ", " ").replaceAll(" for ", " ").replaceAll(
						" to ", " ").replaceAll(" in ", " ").replaceAll(" by ",
						" ").replace(" on ", " ").replaceAll(" the ", " ");

		return synonym;
	}

	/**
	 * Strips out common stop words and punctuation from the synonyms.
	 * 
	 * @param synonym
	 *            a synonym string
	 * 
	 * @return a synonym string stripped out from common stop words and
	 *         punctuation
	 */
	public static String cleanPunctuation(String synonym) {

		synonym = " " + synonym.trim() + " ";

		synonym = synonym.replaceAll("\\,", "");
		synonym = synonym.replaceAll("\\*", "").replaceAll("\\(", "")
				.replaceAll("\\)", "").replaceAll("\\*", "").replaceAll("\\&",
						"");
		synonym = synonym.replaceAll("\\~", "").replaceAll("\\@", "")
				.replaceAll("\\#", "").replaceAll("\\$", "").replaceAll("\\%",
						"");
		synonym = synonym.replaceAll("\\^", "").replaceAll("\\&", "")
				.replaceAll("\\*", "").replaceAll("\\_", " ").replaceAll("\\-",
						" ");
		synonym = synonym.replaceAll("\\+", "").replaceAll("\\=", "")
				.replaceAll("\\{", "").replaceAll("\\}", "").replaceAll("\\|",
						"");
		synonym = synonym.replaceAll("\\[", "").replaceAll("\\]", "")
				.replaceAll("\\:", "").replaceAll("\\;", " ");
		synonym = synonym.replaceAll("\\/", "").replaceAll("\\?", " ")
				.replaceAll("\\!", "").replaceAll("\\\\", "\\").replaceAll(
						"\\\"", " ").replaceAll("\\[", "")
				.replaceAll("\\]", "");

		return synonym;
	}

	/**
	 * Removes token duplicates from a given synonym and puts the synonym tokens
	 * in the alphabetical order.
	 * 
	 * @param synonym
	 *            a synonym string
	 * 
	 * @return a "deduped" synonym string
	 */
	public static String dedupeTokens(String synonym) {

		String[] strArray = synonym.trim().toLowerCase().split("\\s+");
		Arrays.sort(strArray);
		String normSynonyms = "";
		HashSet<String> hs = new HashSet<String>();
		int synonymsTokensCount = 0;
		for (int i = 0; i < strArray.length; i++) {
			if (hs.add(strArray[i].trim()) && strArray[i].trim() != null) {
				normSynonyms += strArray[i].trim() + " ";
				synonymsTokensCount++;
			}
		}
		return normSynonyms;
	}

	/**
	 * Removes the commoner morphological and inflexional endings from words.
	 * 
	 * @param phrase
	 *            a phrase, term or name
	 * @return a string with the commoner morphological and inflexional endings
	 *         removed from its words
	 * @throws DictionaryInitializationException
	 */
	public static String stemmPhrase(String phrase)
			throws DictionaryInitializationException {
		if (hlPS == null)
			hlPS = new HLPorterStemmer();

		phrase = phrase.toLowerCase().trim();
		String[] phraseWords = phrase.split(" ");
		String stemmedString = "";
		Set<String> dictionaryWords = Controller.getDictionaryWords();
		if (dictionaryWords == null)
			throw new DictionaryInitializationException();

		for (int i = 0; i < phraseWords.length; i++) {
			if (dictionaryWords.contains(phraseWords[i])) {
				stemmedString += phraseWords[i] + " ";
			} else if (phraseWords[i] != null
					&& !phraseWords[i].trim().equals("")) {
				stemmedString += hlPS.stem(phraseWords[i]) + " ";
			}
		}

		return stemmedString.trim();
	}

	/**
	 * Convenience method. Runs a given string through methods:
	 * {@link #cleanSynonym(String)}, {@link #cleanPunctuation(String)},
	 * {@link #dedupeTokens(String)}, and {@link #stemmPhrase(String)}.
	 * Catches and ignores all exceptions (only logs them) except for
	 * {@link com.healtline.informatics.normalization.DictionaryInitializationException DictionaryInitializationException}.
	 * 
	 * @param s
	 *            a phrase, term or name to normalize
	 * @return a normalized phrase, term or name
	 */
	public static String normalizeString(String s) {
		String sOriginal = new String(s);
		try {
			s = cleanSynonym(s);
			s = cleanPunctuation(s);
			s = dedupeTokens(s);
			s = stemmPhrase(s);
		} catch (DictionaryInitializationException e) {
			throw new RuntimeException(e);
		} catch (Exception ex) {
			logger.error("Caught exception; original string: " + sOriginal
					+ ", new string: " + s, ex);
			return null;
		}
		return s;

	}

}
