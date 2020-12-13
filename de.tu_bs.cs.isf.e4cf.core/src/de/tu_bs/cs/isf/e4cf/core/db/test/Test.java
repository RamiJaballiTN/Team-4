package de.tu_bs.cs.isf.e4cf.core.db.test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;

import de.tu_bs.cs.isf.e4cf.core.db.Column;
import de.tu_bs.cs.isf.e4cf.core.db.DatabaseFactory;
import de.tu_bs.cs.isf.e4cf.core.db.TableServiceImp;

public class Test {

	public static void main(String[] args) throws SQLException, IOException {

		final String DATABASEPATH = "./testDatabases/";
		final String DATABASENAME = "TestDatenbank";
		final String TABLEENAME = "testTabelle";

		Column c1 = new Column("id", "integer", false, false, false,false);
		Column c2 = new Column("age", "integer");
		Column c3 = new Column("testID", "integer", false, false, false, false);

		TableServiceImp c = new TableServiceImp();

		DatabaseFactory.getInstance().createDatabase(DATABASEPATH, DATABASENAME);
		
		//DatabaseFactory.getInstance().deleteDatabase(DATABASEPATH, DATABASENAME);
		DatabaseFactory.getInstance().deleteDatabase(DATABASEPATH, "testDB");

		//c.renameColumn(DATABASEPATH, DATABASENAME, TABLEENAME, c3.getName()+"renamed", c3.getName());

		c.createTable(DATABASEPATH, DATABASENAME, TABLEENAME, c1, c2, c3);
		c.deleteColumn(DATABASEPATH, DATABASENAME, TABLEENAME, c1.getName(),c2.getName());
		//c.makeColumnAutoIncrement(DATABASEPATH, DATABASENAME, TABLEENAME, c1.getName());
		//System.out.println("Test: "+c.isColumnAutoIncrement(DATABASEPATH, DATABASENAME, TABLEENAME, c1.getName()));
		System.out.println("Test: "+c.columnExists(DATABASEPATH, DATABASENAME, TABLEENAME, c1.getName()));
		//System.out.println("Test: "+c.columnExists(DATABASEPATH, DATABASENAME, TABLEENAME, c2.getName()));
		//System.out.println("Test: "+c.columnExists(DATABASEPATH, DATABASENAME, TABLEENAME, c3.getName()));
		 //c.makeColumnPrimaryKey(DATABASEPATH, DATABASENAME, TABLEENAME, c3.getName());
		 
		 //c.getColumnsTable(DATABASEPATH, DATABASENAME, TABLEENAME);
		 
		 //c.deleteTable(DATABASEPATH, DATABASENAME, TABLEENAME);
		 
		 /*System.out.println("Test: "+c.isColumnPrimaryKey(DATABASEPATH, DATABASENAME,TABLEENAME, c1.getName()));

		 c.dropColumnPrimaryKey(DATABASEPATH, DATABASENAME, TABLEENAME, c1.getName());
		 
		 System.out.println("Test: "+c.isColumnPrimaryKey(DATABASEPATH, DATABASENAME,TABLEENAME, c1.getName()));
		 
		 System.out.println("Test: "+c.isColumnPrimaryKey(DATABASEPATH, DATABASENAME,TABLEENAME, c2.getName()));
		 
		 System.out.println("Test: "+c.isColumnPrimaryKey(DATABASEPATH, DATABASENAME,TABLEENAME, c3.getName()));
		*/
		/*
		 * System.out.println("Test: "+c.isColumnPrimaryKey(DATABASEPATH, DATABASENAME,
		 * TABLEENAME, c1.getName()));
		 * 
		 * System.out.println("Test: "+c.isColumnPrimaryKey(DATABASEPATH, DATABASENAME,
		 * TABLEENAME, c2.getName()));
		 * 
		 * System.out.println("Test: "+c.isColumnPrimaryKey(DATABASEPATH, DATABASENAME,
		 * TABLEENAME, c3.getName()));
		 */

		// c.makeColumnPrimaryKey(DATABASEPATH, DATABASENAME, TABLEENAME, c2.getName());

		// System.out.println("Test: "+c.isColumnPrimaryKey(DATABASEPATH, DATABASENAME,
		// TABLEENAME, c2.getName()));
		// hier kommen noch die anderen features

	}

}