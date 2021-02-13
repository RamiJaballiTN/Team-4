package de.tu_bs.cs.isf.e4cf.core.db.view;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import de.tu_bs.cs.isf.e4cf.core.db.*;
import de.tu_bs.cs.isf.e4cf.core.db.model.Column;

/**
 * 
 * Class responsible for viewing the databases as GUI.
 *
 */
public final class DatabaseView extends Application {

	public static final String _DATABSEPATH = "./testDatabases/";

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(final Stage stage) {

		// draw the window
		stage.setTitle("DB View");
		stage.setWidth(450);
		stage.setHeight(200);

		final Button openButton = new Button("Open Database");
		final Label labelDb = new Label("Choose a database: ");
		ChoiceBox<String> dbListChoiceBox = new ChoiceBox<String>();

		// get the list of databases in path testDatabases
		final File file = new File(_DATABSEPATH);
		final ObservableList<String> dataFiles = FXCollections.observableArrayList(getFiles(file));

		// fill the choicebox with the given db list
		dbListChoiceBox.setItems(dataFiles);

		openButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				String selectedDb = dbListChoiceBox.getSelectionModel().getSelectedItem().toString();
				File dbFile = new File(selectedDb);
				selectTable(dbFile, stage);
			}
		});

		final GridPane inputGridPane = new GridPane();

		GridPane.setConstraints(labelDb, 0, 0);
		GridPane.setConstraints(openButton, 1, 1);
		GridPane.setConstraints(dbListChoiceBox, 1, 0);
		inputGridPane.setHgap(10);
		inputGridPane.setVgap(20);
		inputGridPane.getChildren().addAll(labelDb, dbListChoiceBox, openButton);

		final Pane rootGroup = new VBox(12);
		rootGroup.getChildren().addAll(inputGridPane);
		rootGroup.setPadding(new Insets(12, 12, 12, 12));

		stage.setScene(new Scene(rootGroup));
		stage.show();
	}

	protected List<String> getFiles(final File folder) {
		List<String> files = new ArrayList<String>();

		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				getFiles(fileEntry);
			} else {
				files.add(fileEntry.getName());
			}
		}
		return files;
	}

	protected void selectTable(File file, Stage stage) {

		List<String> tableList = new ArrayList<String>();
		final Button openTable = new Button("Open Table");
		final Label labelTable = new Label("Choose a table: ");
		ChoiceBox<String> tableListChoiceBox = new ChoiceBox<String>();
		final Button backButton = new Button("Go Back");

		try {
			TableServiceImp tI = new TableServiceImp();

			// get the tables in the chosen database
			tableList = tI.getTables(_DATABSEPATH, file.getName());

			// fill choicebox with tables of the chosen databse
			final ObservableList<String> tableNames = FXCollections.observableArrayList(tableList);
			tableListChoiceBox.setItems(tableNames);

			openTable.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(final ActionEvent e) {
					String selectedTable = tableListChoiceBox.getSelectionModel().getSelectedItem().toString();
					showTable(file, selectedTable);
				}
			});

			backButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(final ActionEvent e) {
					start(stage);
				}
			});

			final GridPane inputGridPane = new GridPane();
			GridPane.setConstraints(labelTable, 0, 0);
			GridPane.setConstraints(openTable, 1, 1);
			GridPane.setConstraints(tableListChoiceBox, 1, 0);
			GridPane.setConstraints(backButton, 2, 1);

			inputGridPane.setHgap(10);
			inputGridPane.setVgap(20);
			inputGridPane.getChildren().addAll(labelTable, tableListChoiceBox, openTable, backButton);

			final Pane rootGroup = new VBox(12);
			rootGroup.getChildren().addAll(inputGridPane);
			rootGroup.setPadding(new Insets(12, 12, 12, 12));

			stage.setScene(new Scene(rootGroup));
			stage.show();

		} catch (Exception e) {
			System.err.println(e);
		}
	}

	protected void showTable(File file, String tableName) {
		try {
			TableServiceImp tI = new TableServiceImp();

			Stage tableStage = new Stage();
			tableStage.setWidth(400);
			tableStage.setHeight(500);
			tableStage.setTitle(tableName);
			tableStage.setResizable(true);

			final GridPane tableGridPane = new GridPane();

			// get columns of the chosen table
			List<Column> cols = tI.getColumnsTable(_DATABSEPATH, file.getName(), tableName);
			final TableView<List<StringProperty>> table = new TableView<>();

			final Label label = new Label(tableName);
			label.setFont(new Font("Arial", 20));

			table.setEditable(false);
			table.isResizable();
			table.prefHeightProperty().bind(tableStage.heightProperty());
			table.prefWidthProperty().bind(tableStage.widthProperty());
			table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

			for (int i = 0; i < cols.size(); i++) {
				TableColumn<List<StringProperty>, String> column = new TableColumn<>(cols.get(i).getName());
				final int j = i;
				column.setCellValueFactory(celldata -> celldata.getValue().get(j));
				table.getColumns().add(column);
			}

			final ObservableList<List<StringProperty>> data = FXCollections.observableArrayList();
			List<StringProperty> myData = new ArrayList<>();
			ResultSet result = getDataTable(_DATABSEPATH, file.getName(), tableName);

			while (result.next()) {
				for (int i = 1; i <= cols.size(); i++) {
					myData.add(new SimpleStringProperty(result.getString(i)));
				}
			}

			for (int j = 0; j < (myData.size() / cols.size()); j++) {
				data.add(j, myData.subList(j * cols.size(), j * cols.size() + cols.size()));
			}

			table.setItems(data);

			tableGridPane.setHgap(6);
			tableGridPane.setVgap(6);
			tableGridPane.add(table, 0, 0);

			final Pane vbox = new VBox(12);
			vbox.getChildren().addAll(tableGridPane);
			vbox.setPadding(new Insets(12, 12, 12, 12));

			tableStage.setScene(new Scene(vbox));
			tableStage.show();

		} catch (Exception e) {
			System.err.println(e);
		}
	}

	protected ResultSet getDataTable(final String pPath, final String pDbName, final String pTableName)
			throws SQLException {
		final Connection con = DatabaseFactory.getInstance().getDatabase(pPath, pDbName);
		final Statement stm = con.createStatement();
		ResultSet result = stm.executeQuery(Messages.SELECT + Messages.STAR + Messages.FROM + pTableName);
		return result;
	}
}