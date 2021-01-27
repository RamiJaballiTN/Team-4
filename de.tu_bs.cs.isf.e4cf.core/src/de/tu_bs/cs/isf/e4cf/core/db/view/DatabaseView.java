package de.tu_bs.cs.isf.e4cf.core.db.view;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
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

public final class DatabaseView extends Application {

	@Override
	public void start(final Stage stage) {
		stage.setTitle("DB View");
		stage.setWidth(500);
		stage.setHeight(300);

		final Button openButton = new Button("Open Database");
		final Button openTable = new Button("Open Table");
		final Label labelDb = new Label("Datenbank auswählen: ");
		final Label labelTable = new Label("Tabelle auswählen: ");
		ChoiceBox<String> cbDb = new ChoiceBox<String>();
		ChoiceBox<String> cbTable = new ChoiceBox<String>();

		final File file = new File("./testDatabases/");
		final ObservableList<String> dataFiles = FXCollections.observableArrayList(getFiles(file));

		cbDb.setItems(dataFiles);

		openButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				String selectedDb = cbDb.getSelectionModel().getSelectedItem().toString();
				File dbFile = new File(selectedDb);
				List<String> tNames = selectTable(dbFile);

				final ObservableList<String> tableNames = FXCollections.observableArrayList(tNames);
				cbTable.setItems(tableNames);
				openTable.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent e) {
						String selectedTable = cbTable.getSelectionModel().getSelectedItem().toString();
						showTable(dbFile, selectedTable);
					}
				});
			}
		});

		final GridPane inputGridPane = new GridPane();

		GridPane.setConstraints(labelDb, 0, 0);
		GridPane.setConstraints(openButton, 1, 1);
		GridPane.setConstraints(cbDb, 1, 0);
		GridPane.setConstraints(labelTable, 0, 2);
		GridPane.setConstraints(openTable, 1, 4);
		GridPane.setConstraints(cbTable, 1, 2);
		inputGridPane.setHgap(6);
		inputGridPane.setVgap(6);
		inputGridPane.getChildren().addAll(labelDb, cbDb, openButton, labelTable, cbTable, openTable);

		final Pane rootGroup = new VBox(12);
		rootGroup.getChildren().addAll(inputGridPane);
		rootGroup.setPadding(new Insets(12, 12, 12, 12));

		stage.setScene(new Scene(rootGroup));
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	private List<String> getFiles(final File folder) {
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

	private List<String> selectTable(File file) {
		List<String> tables = new ArrayList<String>();
		try {
			TableServiceImp tI = new TableServiceImp();
			tables = tI.getTables("./testDatabases/", file.getName());

		} catch (Exception e) {
			System.err.println(e);
		}
		return tables;
	}

	private void showTable(File file, String tableName) {
		try {
			TableServiceImp tI = new TableServiceImp();

			Stage tableStage = new Stage();
			tableStage.setWidth(400);
			tableStage.setHeight(500);
			tableStage.setTitle(tableName);
			tableStage.setResizable(true);

			final GridPane tableGridPane = new GridPane();

			List<Column> cols = tI.getColumnsTable("./testDatabases/", file.getName(), tableName);
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

			final Connection con = DatabaseFactory.getInstance().getDatabase("./testDatabases/", file.getName());
			final Statement stm = con.createStatement();
			ResultSet result = stm.executeQuery("SELECT * FROM " + tableName);

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
}