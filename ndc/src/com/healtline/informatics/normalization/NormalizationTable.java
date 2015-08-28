package com.healtline.informatics.normalization;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Creates and rebuilds normalization tables.
 * 
 * @author Nick Moseyko
 * 
 */
public class NormalizationTable {

	private static Logger logger = Logger.getLogger(NormalizationTable.class);

	private Connection connection;

	private boolean isRebuildTaxonomy = true;

	/**
	 * Constructs class using JDBC connection. Normally, it is a DB connection
	 * to Healthline Taxonomy schema specified in the properties file.
	 * 
	 * @param connection
	 *            JDBC Connection
	 */
	public NormalizationTable(Connection connection) {
		this.connection = connection;
		if (connection == null) {
			throw new IllegalArgumentException("Argument 'connection' is null");
		}
	}

	/**
	 * Tests if normalization tables exist and rebuilds them if needed. Iterates
	 * through the tables specified in
	 * {@link com.healtline.informatics.normalization.NormalizationTable.NormalizationTableEnum NormalizationTableEnum}
	 * and invokes
	 * {@link #normalizeTable(com.healtline.informatics.normalization.NormalizationTable.NormalizationTableEnum)}.
	 */
	public void normalize() {

		for (NormalizationTableEnum tableEnum : NormalizationTableEnum.values()) {
			if (tableEnum == NormalizationTableEnum.CONCEPT_MRCONSO_NORM
					&& isNormalized(tableEnum)) {
				// table exists and normalized; don't do anything
				logger.debug("Skipping normalization");
			} else if (tableEnum == NormalizationTableEnum.TAXONOMY_SYN_NORM
					&& !isRebuildTaxonomy && isNormalized(tableEnum)) {
				// Table exists and normalized; don't do anything.
				// Unless it's a test, TAXONOMY_SYN_NORM table should be always
				// recreated.
				logger
						.warn("WARNING!!! Skipping rebuild of normalization table "
								+ tableEnum);
				logger.warn("Unless it's a test, " + tableEnum
						+ " should be always recreated.");
			} else {
				// Always recreate TAXONOMY_SYN_NORM table.
				if (tableEnum == NormalizationTableEnum.TAXONOMY_SYN_NORM) {
					// Drop it if exists.
					try {
                        logger.info("i am here"+tableEnum);
						dropIndexOnNormalizedColumn(tableEnum);
						dropIndex(tableEnum);
						dropTable(tableEnum);
					} catch (Exception e) {
						// Ignore exceptions here: index or table may not exist
						// yet. Just log them.
						logger.debug("Caught exception", e);
						logger
								.debug("Ignoring exceptions here: index or table may not exist yet.");
					}
				}
                logger.info("i amsdsd here");
				createTable(tableEnum);
                logger.info("i amsdssdfsdfdsd here");
				addNormalizedColumn(tableEnum);
				createIndex(tableEnum);
				normalizeTable(tableEnum);
				createIndexOnNormalizedColumn(tableEnum);
				logger.debug("Testing if normalization was successful");
				if (!isNormalized(tableEnum)) {
					throw new RuntimeException("Couldn't normalize the data.");
				}
			}
		}
	}

	/**
	 * Creates a normalization table.
	 * 
	 * @param tableEnum a normalization table
	 */
	public void createTable(NormalizationTableEnum tableEnum) {
		execute(tableEnum.getCreateTableSql());
	}

	/**
	 * Drops a normalization table.
	 * 
	 * @param tableEnum a normalization table
	 */
	public void dropTable(NormalizationTableEnum tableEnum) {
		execute(tableEnum.getDropTableSql());
	}

	/**
	 * Alters table and adds <code>STR_NORM</code> column for storing
	 * normalized data.
	 * 
	 * @param tableEnum a normalization table
	 */
	public void addNormalizedColumn(NormalizationTableEnum tableEnum) {
		execute(tableEnum.getAddNormalizedColumnSql());
	}

	/**
	 * Creates index on the <code>STR</code> column of the normalization
	 * table.
	 * 
	 * @param tableEnum a normalization table
	 */
	public void createIndex(NormalizationTableEnum tableEnum) {
		execute(tableEnum.getCreateIndexSql());
	}

	/**
	 * Drops index on the <code>STR</code> column of the normalization table.
	 * 
	 * @param tableEnum a normalization table
	 */
	public void dropIndex(NormalizationTableEnum tableEnum) {
		execute(tableEnum.getDropIndexSql());
	}

	/**
	 * Creates index on the <code>STR_NORM</code> column of the normalization
	 * table.
	 * 
	 * @param tableEnum a normalization table
	 */
	public void createIndexOnNormalizedColumn(NormalizationTableEnum tableEnum) {
		execute(tableEnum.getCreateIndexOnNormalizedColumnSql());
	}

	/**
	 * Drops index on the <code>STR_NORM</code> column of the normalization
	 * table.
	 * 
	 * @param tableEnum a normalization table
	 */
	public void dropIndexOnNormalizedColumn(NormalizationTableEnum tableEnum) {
		execute(tableEnum.getDropIndexOnNormalizedColumnSql());
	}

	/**
	 * Tests if normalization tables exist and rebuilds them if needed.
	 * 
	 * @param tableEnum a normalization table
	 */
	public void normalizeTable(NormalizationTableEnum tableEnum) {
		logger.debug("Normalizing table " + tableEnum);
		PreparedStatement ps = null;
		PreparedStatement psUpd = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement("select str from " + tableEnum
					+ " where str_norm is null");
			psUpd = connection.prepareStatement("update " + tableEnum
					+ " set str_norm = ? where str = ?");
			rs = ps.executeQuery();
			while (rs.next()) {
				String synOriginal = rs.getString(1);
				String syn = MultumStemmer.normalizeString(synOriginal);
				if (syn == null) {
					logger.debug("\nSkipping normalization for '" + synOriginal
							+ "'");
					logger
							.debug("Will continue to normalize the data. Please wait...");
					continue;
				}
				psUpd.clearParameters();
				psUpd.setString(1, syn);
				psUpd.setString(2, synOriginal);
				psUpd.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (psUpd != null)
					psUpd.close();
			} catch (Exception e) {
				logger.error("Caught exception", e);
			}
		}
		logger.debug("Finished normalization");
	}

	/**
	 * Tests if the normalization table exists and if it has any data in column
	 * <code>STR_NORM</code>.
	 * 
	 * @param tableEnum a normalization table
	 * @return true if table exists and row count in <code>STR_NORM</code>
	 *         with non-null values > 0
	 */
	public boolean isNormalized(NormalizationTableEnum tableEnum) {
		String sql = "select count(*) from " + tableEnum
				+ " where str_norm is not null";
		logger.info("Testing if normalized table " + tableEnum + " exists");
		logger.debug("Running query: " + sql);
		logger.info("Please wait...");
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				String result = rs.getString(1);
				logger.info("Table exists, row count: " + result);
				if (Integer.parseInt(result) > 0) {
					// table exists and looks OK
					logger.debug("Data is normalized");
					return true;
				} else {
					String err = "No data in column str_norm. Not normalized?";
					logger.error(err);
					throw new IllegalStateException(err);
				}
			}
		} catch (SQLException e) {

			String expectedError = "ORA-00942: table or view does not exist";
			if (e.getMessage().trim().equals(expectedError)) {
				logger.debug("Got expected error " + expectedError);
				return false;
			} else {
				throw new RuntimeException(e);
			}

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
		return false;
	}

	/**
	 * Convenience method for executing an arbitrary SQL command. Used in
	 * create/alter/drop table methods in this class.
	 * 
	 * @param sql a SQL command to execute (create, alter or drop table)
	 */
	public void execute(String sql) {
		logger.debug("Running sql code: " + sql);
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.execute(sql);
		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
				logger.error("Caught exception", e);

			}
		}
	}

	/**
	 * Used for defining normalization tables and table-specific SQL commands.
	 */
	public enum NormalizationTableEnum {
		CONCEPT_MRCONSO_NORM {
			String getCreateTableSql() {
				return "create table "
						+ this
						+ " as select distinct cui, str, p_name, tui_sty from concept_mrconso";
			}
		},
		TAXONOMY_SYN_NORM {
			String getCreateTableSql() {
				return "create table "
						+ this
						+ " as select csyn.concept_id, csyn.str from concept_synonyms csyn, CONCEPT_STY csty " +
                        "where csyn.concept_id=csty.concept_id\n" +
                        "and csty.sty_id in (\n" +
                        "7850118,\n" +
                        "7850119,\n" +
                        "7850143,\n" +
                        "7850265,\n" +
                        "5159441,\n" +
                        "7850261,\n" +
                        "5159513\n" +
                        ") ";
						//+ " as select concept_id, str from concept_synonyms union "
						//+ "select a.concept_id, b.str from concept_external a, concept_mrconso b "
						//+ "where a.external_concept_id = b.cui and a.taxonomy_id = 1";
			}
		};

		/**
		 * Returns "create table ..." SQL code
		 */
		abstract String getCreateTableSql();

		/**
		 * Returns "alter table ... add ..." SQL code
		 */
		String getAddNormalizedColumnSql() {
			return "alter table " + this + " add (str_norm varchar2(3000))";
		}

		/**
		 * Returns "drop table ..." SQL code
		 */
		String getDropTableSql() {
			return "drop table " + this;
		}

		/**
		 * Returns "create index ..." SQL code
		 */
		String getCreateIndexSql() {
			return "create index " + this + "_idx on " + this + " (str)";
		}

		/**
		 * Returns "create index ..." SQL code on normalized column
		 */
		String getCreateIndexOnNormalizedColumnSql() {
			return "create index " + this + "_idxn on " + this + " (str_norm)";
		}

		/**
		 * Returns "create index ..." SQL code
		 */
		String getDropIndexSql() {
			return "drop index " + this + "_idx";
		}

		/**
		 * Returns "create index ..." SQL code on normalized column
		 */
		String getDropIndexOnNormalizedColumnSql() {
			return "drop index " + this + "_idxn";
		}
	}

	/**
	 * Convenience method used in testing. If set to <code>false</code>, table
	 * <code>TAXONOMY_SYN_NORM</code> will not be rebuilt if it exists and
	 * normalized. Normally, this table should be always rebuilt on each run of
	 * the Multum updates.
	 * 
	 * @param isRebuildTaxonomy
	 *            if <code>true</code>, table <code>TAXONOMY_SYN_NORM</code>
	 *            will not be rebuilt if it exists and normalized
	 */
	public void setRebuildTaxonomy(boolean isRebuildTaxonomy) {
		this.isRebuildTaxonomy = isRebuildTaxonomy;
	}

}
