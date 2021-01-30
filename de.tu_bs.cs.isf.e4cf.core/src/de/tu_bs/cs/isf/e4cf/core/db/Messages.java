package de.tu_bs.cs.isf.e4cf.core.db;

/**
 * 
 * Class that contains the constant outputs.
 *
 */
public final class Messages {

	// Messages concerning database
	public static final String _ER_LOAD_JDBC_D = "Error while loading JDBC-Driver: ";
	public static final String _DB_CR = "Database created: ";
	public static final String _ER_CR_DB = "Error while creating database: ";
	public static final String _DB_AL_EX = "Database already exists: ";
	public static final String _ER_GT_DB = "Error while getting database: ";
	public static final String _DB_NOT_EX = "Database does not exist: ";
	public static final String _DB_RM = "Database deleted: ";
	public static final String _ER_DB_MV = "Error while moving database: ";
	public static final String _DB_MV_SUCC = " was moved successfully from ";

	// Messages concerning data
	public static final String _ER_INS_DATA = "Error while insering data: ";
	public static final String _ER_UPD_DATA = "Error while updating data: ";
	public static final String _ER_DEL_DATA = "Error while deleting data: ";
	public static final String _ER_SEL_DATA = "Error while deleting data: ";
	public static final String _ER_CNT = "Error while executing count statement: ";
	public static final String _ER_SUM = "Error while executing sum statement: ";

	// Messages concerning tables
	public static final String _TB_CR = "Table created: ";
	public static final String _TB_AL_EX = "Table already exists: ";
	public static final String _TB_NO_CR = "Can not create table without column(s).";
	public static final String _ER_CR_TB = "Error while creating table: ";
	public static final String _TB_NO_EX = "Table does not exist: ";
	public static final String _ER_SEC = "Security Issue: ";
	public static final String _TB_RM = "Table deleted: ";
	public static final String _ER_RM_TB = "Error while deleting table: ";
	public static final String _ER_RN_TB = "Error while renaming table: ";
	public static final String _ER_RN_CLM = "Error while renaming column: ";
	public static final String _CLM_RM = "Column deleted: ";
	public static final String _CLM_AL_EX = "Column already exists: ";
	public static final String _CLM_NO_EX = "Column does not exist: ";
	public static final String _NO_CHANGE_ALLOWED = "Table has already data. Such change could bring an SQLFailure.";
	public static final String _FALSE_TYPE_CONSTRAINT = "False type constraint given.";
	public static final String _ER_GT_TB_METADT = "Error while getting table metadata.";
	public static final String _ER_AD_CLM = "Error while adding column: ";
	
	//General
	public static final String _TO = "to";
	
	
	private static Messages _INSTANCE = new Messages();

	public synchronized static Messages getInstance() {
		return _INSTANCE;
	}
}
