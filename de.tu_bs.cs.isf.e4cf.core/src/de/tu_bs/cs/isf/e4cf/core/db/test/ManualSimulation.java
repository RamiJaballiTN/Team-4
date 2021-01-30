package de.tu_bs.cs.isf.e4cf.core.db.test;

import java.io.IOException;
import java.sql.SQLException;

import de.tu_bs.cs.isf.e4cf.core.db.DataServiceImp;
import de.tu_bs.cs.isf.e4cf.core.db.DatabaseFactory;
import de.tu_bs.cs.isf.e4cf.core.db.TableServiceImp;
import de.tu_bs.cs.isf.e4cf.core.db.model.AndCondition;
import de.tu_bs.cs.isf.e4cf.core.db.model.Column;
import de.tu_bs.cs.isf.e4cf.core.db.model.ColumnValue;
import de.tu_bs.cs.isf.e4cf.core.db.model.Condition;
import de.tu_bs.cs.isf.e4cf.core.db.model.Sorter;

/**
 * 
 * Test Class
 *
 */
public class ManualSimulation {

	public static void main(String[] args) throws SQLException, IOException {

		final String _DATABASEPATH = "./testDatabases/";
		final String _DATABASENAME = "TEstDB";
		final String _TABLEENAME = "testTabelle";

		/* Database */

		 DatabaseFactory.getInstance().createDatabase(_DATABASEPATH, _DATABASENAME);
		 DatabaseFactory.getInstance().renameDatabase(_DATABASEPATH, _DATABASENAME, "newName" + _DATABASENAME);
		 DatabaseFactory.getInstance().moveDatabase(_DATABASENAME, _DATABASEPATH, "");
		 DatabaseFactory.getInstance().deleteDatabase(_DATABASEPATH, _DATABASENAME);

		// _____________________________________________________________________________________

		/* Tables */

		TableServiceImp ts = new TableServiceImp();

		Column c1 = new Column("id", "integer", false, false, true, false);
		Column c2 = new Column("name", "varchar (60)", false, true, false, true);
		Column c3 = new Column("age", "integer");

		ts.createTable(_DATABASEPATH, _DATABASENAME, _TABLEENAME, c1, c2); 
		 
		System.out.println("Columns: "+ts.getColumnsTable(_DATABASEPATH, _DATABASENAME, _TABLEENAME));

		ts.deleteTable(_DATABASEPATH, _DATABASENAME, _TABLEENAME);
		 
		ts.renameTable(_DATABASEPATH, _DATABASENAME, _TABLEENAME, "newName" + _TABLEENAME);

		ts.renameColumn(_DATABASEPATH, _DATABASENAME, _TABLEENAME, "id", "id_table");

		ts.deleteColumn(_DATABASEPATH, _DATABASENAME, _TABLEENAME, "id");

		ts.addColumn(_DATABASEPATH, _DATABASENAME, _TABLEENAME, c3);
		 
		System.out.println("Columns: "+ts.getColumnsTable(_DATABASEPATH, _DATABASENAME, _TABLEENAME));
		 
		ts.switchColumnPrimaryKey(_DATABASEPATH, _DATABASENAME, _TABLEENAME, true, c1.getName());
		 
		ts.switchColumnUnique(_DATABASEPATH, _DATABASENAME, _TABLEENAME, false, c2.getName());
		 
		ts.switchColumnAutoIncrement(_DATABASEPATH, _DATABASENAME, _TABLEENAME, false, c1.getName());
		 
		ts.switchColumnNotNull(_DATABASEPATH, _DATABASENAME, _TABLEENAME, true, c3.getName());

		// _____________________________________________________________________________________

		/* Data */

		DataServiceImp ds = new DataServiceImp();

		ColumnValue cv1 = new ColumnValue(c2.getName(), new String("Isy P"));
		ColumnValue cv2 = new ColumnValue(c3.getName(), 44);

		ColumnValue cv3 = new ColumnValue(c2.getName(), "Rami J");
		ColumnValue cv4 = new ColumnValue(c3.getName(), 24);

		ds.insertData(_DATABASEPATH, _DATABASENAME, _TABLEENAME, cv1, cv2);
		ds.insertData(_DATABASEPATH, _DATABASENAME, _TABLEENAME, cv3, cv4);

		ds.printTable(_DATABASEPATH, _DATABASENAME, _TABLEENAME);

		Condition whereCondition = new AndCondition(cv4);

		ColumnValue newName = new ColumnValue(c2.getName(), "Rami Jaballi");
		ColumnValue age = new ColumnValue(c3.getName(), 25);

		ds.updateData(_DATABASEPATH, _DATABASENAME, _TABLEENAME, whereCondition, newName);

		ds.printTable(_DATABASEPATH, _DATABASENAME, _TABLEENAME);
		
		Condition orderCondition = new AndCondition(age);

		Condition noCondition = null;
		
		Sorter sorter = new Sorter(noCondition, orderCondition, "ASC");
		
		ds.selectData(_DATABASEPATH, _DATABASENAME, _TABLEENAME, noCondition, sorter, "name","age");

		ds.deleteData(_DATABASEPATH, _DATABASENAME, _TABLEENAME, whereCondition);

	}

}
