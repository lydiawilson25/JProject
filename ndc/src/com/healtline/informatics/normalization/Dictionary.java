package com.healtline.informatics.normalization;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is used for loading and retrieving a set of the dictionary words
 * from table <code>STEDMAN_DICTIONARY</code>.
 * 
 * @author Nick Moseyko
 */
public class Dictionary {

	private static Logger logger = Logger.getLogger(Dictionary.class);

	private Set<String> dictionaryWords = new HashSet<String>();

	private Connection connection;

	public Dictionary(Connection connection) {
		this.connection = connection;
		if (connection == null) {
			throw new IllegalArgumentException("Argument 'connection' is null");
		}

		init();
	}

	/**
	 * 
	 * Initializes dictionary: loads a set of the dictionary words.
	 */
	private void init() {
		String sql = "select distinct trim(lower(CLEAR_WORD)) from STEDMAN_DICTIONARY where CLEAR_WORD  not like '% %' and rownum < 2";
		logger.debug("Running query: " + sql);
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				dictionaryWords.add(rs.getString(1));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (statement != null)
					statement.close();
			} catch (Exception e) {
				logger.error("Caught exception", e);
			}
		}
	}

	/**
	 * 
	 * Returns a set of the dictionary words.
	 * 
	 * @return a set of the dictionary words
	 */
	public Set<String> getDictionaryWords() {
		return dictionaryWords;
	}

}
