package Heroes3_GUI.Controllers;

import Heroes3_GUI.Models.Creatures;
import Heroes3_GUI.Models.Fractions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController implements SmallWindowInterface {
    @FXML
    Button buttonStartFirst, purchaseButton, purchaseButton1;

    @FXML
    ComboBox<Fractions> comboBox;

    @FXML
    ComboBox<Fractions> comboBox1;

    @FXML
    TableView<Creatures> tableView;

    @FXML
    TableView<Creatures> tableView1;

    @FXML
    TableColumn<Creatures, String> creature;

    @FXML
    TableColumn<Creatures, Double> price;

    @FXML
    TableColumn<Creatures, Integer> amount;

    @FXML
    TableColumn<Creatures, String> creature1;

    @FXML
    TableColumn<Creatures, Double> price1;

    @FXML
    TableColumn<Creatures, Integer> amount1;

    private ObservableList<Fractions> fractionsObservableList;
    private ObservableList<Creatures> creaturesObservableList;


    @FXML
    public void initialize() {
        prepareView();
    }

    @FXML
    public void openSmallWindow(ActionEvent buttonStartFirst) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/SmallWindow.fxml"));
            Parent root1 = fxmlLoader.load();
            SmallWindowController smallWindowController = fxmlLoader.getController();
            smallWindowController.smallWindowInterface = this;
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void purchaseButton(ActionEvent event) {
        ObservableList<Creatures> dataRows = FXCollections.observableArrayList();

        for(Creatures creature : creaturesObservableList)
            if (creature.getAmount() > 0) {
                dataRows.add(creature);
                creature.getAmount();
                System.out.println(creature.getAmount());
                int aAmount = creature.getAmount() - 1;
                creature.setAmount(aAmount);
                tableView.refresh();
                System.out.println(creature.getAmount());
                break;
            }
        try {
           // tableView.getSelectionModel().selectedItemProperty().addListener((output, oldValue, newValue) -> {
            purchaseButton.addEventHandler((MouseEvent.MOUSE_CLICKED), (e) -> {


            });
                //Method is called after row selection

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void onKlikButton(ArrayList<Integer> heroesNumbers) {
        System.out.println("Wylosowana liczba gracza pierwszego: " + heroesNumbers.get(0).toString());
        System.out.println("Wylosowana liczba gracza drugiego: " + heroesNumbers.get(1).toString());
        if (heroesNumbers.get(0) > heroesNumbers.get(1)) {
            System.out.println(heroesNumbers.get(0));
        } else {
            System.out.println(heroesNumbers.get(1));
        }
    }

    private void prepareView() {
        prepareComboBoxes();

    }

    private void prepareComboBoxes() {
        comboBoxFraction();
        comboBox1Fraction();
    }


    private void comboBoxFraction() {
        List<Fractions> fractionsList = new ArrayList<>();
        fractionsList.add(new Fractions(Fractions.FractionNames.Inferno));
        fractionsList.add(new Fractions(Fractions.FractionNames.Haven));
        fractionsList.add(new Fractions(Fractions.FractionNames.Necropolis));
        // comboBox.getItems().addAll(fractionsList);

        fractionsObservableList = FXCollections.observableArrayList(fractionsList);
        comboBox.setItems(fractionsObservableList);

        comboBox.getSelectionModel().selectedItemProperty().addListener((output, oldValue, newValue) -> {
            setTableView(newValue.getName());
            //Method is called after row selection
        });
    }

    private void comboBox1Fraction() {
        List<Fractions> fractionsList = new ArrayList<>();
        fractionsList.add(new Fractions(Fractions.FractionNames.Inferno));
        fractionsList.add(new Fractions(Fractions.FractionNames.Haven));
        fractionsList.add(new Fractions(Fractions.FractionNames.Necropolis));
        // comboBox.getItems().addAll(fractionsList);

        fractionsObservableList = FXCollections.observableArrayList(fractionsList);
        comboBox1.setItems(fractionsObservableList);

        comboBox1.getSelectionModel().selectedItemProperty().addListener((output, oldValue, newValue) -> {
            setTableView1(newValue.getName());
            //Method is called after row selection
        });
    }

    private void setTableView(Fractions.FractionNames fractionName) {
        List<Creatures> creaturesList = new ArrayList<>();
        creature.setCellValueFactory(new PropertyValueFactory<>("Name"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        switch (fractionName) {
            case Inferno:
                creaturesList.add(new Creatures("Diaboł", 250.0, 10));
                break;
            case Haven:
                creaturesList.add(new Creatures("Anioł", 200.0, 10));
                break;
            case Necropolis:
                creaturesList.add(new Creatures("Szkieletor", 50.0, 10));
                creaturesList.add(new Creatures("Widmor", 1312.0, 4));
                break;
        }
        creaturesObservableList = FXCollections.observableArrayList(creaturesList);
        tableView.setItems(creaturesObservableList);

        tableView.setRowFactory(tv -> {
            TableRow<Creatures> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Creatures rowData = row.getItem();
                    System.out.println(rowData.getName());
                    openCreatureWindow(rowData);
                }
            });
            return row;
        });
    }

    private void setTableView1(Fractions.FractionNames fractionName) {
        List<Creatures> creaturesList = new ArrayList<>();
        creature1.setCellValueFactory(new PropertyValueFactory<>("Name"));
        price1.setCellValueFactory(new PropertyValueFactory<>("Price"));
        amount1.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        switch (fractionName) {
            case Inferno:
                creaturesList.add(new Creatures("Diaboł", 250.0, 10));
                break;
            case Haven:
                creaturesList.add(new Creatures("Anioł", 200.0, 10));
                break;
            case Necropolis:
                creaturesList.add(new Creatures("Szkieletor", 50.0, 10));
                break;
        }
        creaturesObservableList = FXCollections.observableArrayList(creaturesList);
        tableView1.setItems(creaturesObservableList);

        tableView1.setRowFactory(tv -> {
            TableRow<Creatures> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Creatures rowData = row.getItem();
                    //openCreatureWindow(rowData);
                }
            });
            return row;
        });
    }

    private void openCreatureWindow(Creatures creature) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/CreatureWindow.fxml"));
        Parent root1 = null;
        try {
            root1 = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //CreatureWindowController creatureWindowController = fxmlLoader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("Statistic");
        stage.show();
    }
}
