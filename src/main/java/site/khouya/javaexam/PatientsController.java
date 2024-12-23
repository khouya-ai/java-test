package site.khouya.javaexam;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import site.khouya.javaexam.metier.CabinetMetierImpl;
import site.khouya.javaexam.metier.ICabinetMetier;
import site.khouya.javaexam.metier.Patient;


import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class PatientsController implements Initializable {
    @FXML
    private Button btnActualiser;

    @FXML
    private TableColumn<Patient, String> tableColumnCIN;

    @FXML
    private TableColumn<Patient, Date> tableColumnDate;

    @FXML
    private TableColumn<Patient, String> tableColumnDepart;

    @FXML
    private TableColumn<Patient, String> tableColumnEmail;

    @FXML
    private TableColumn<Patient, Integer> tableColumnID;

    @FXML
    private TableColumn<Patient, String> tableColumnNom;

    @FXML
    private TableColumn<Patient, String> tableColumnPrenom;

    @FXML
    private TableView<Patient> table;

    ICabinetMetier metier = new CabinetMetierImpl();


    @FXML
    void onActualiserButtonClick(ActionEvent event) {
        loadTable();
    }

    @FXML
    void onAjouterButtonClick(ActionEvent event) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("patient-edit-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 850, 600);
            // Get the controller and pass the id
            PatientEditController controller = fxmlLoader.getController();
            controller.initializeForInsertion();
            controller.setParentController(this);
            Stage stage = new Stage();
            stage.setTitle("Ajouter un Patient");
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    void onModifierButtonClick(ActionEvent event) throws IOException {
        if(!table.getSelectionModel().getSelectedItems().isEmpty()) {
            Patient selectedProf = table.getSelectionModel().getSelectedItem();
            int profId = selectedProf.getId();
            System.out.println(profId);
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("patient-edit-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 850, 600);
            // Get the controller and pass the id
            PatientEditController controller = fxmlLoader.getController();
//            controller.initializeForUpdate(profId);
            controller.setParentController(this);
            Stage stage = new Stage();
            stage.setTitle("Modifier un Patient");
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void onSupprimerButtonClick(ActionEvent event) {
        if(!table.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention!");
            alert.setHeaderText("Suppression du Patient");
            alert.setContentText("Êtes-vous sûr de vouloir supprimer ce Patient ?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Patient p = table.getSelectionModel().getSelectedItem();
                    metier.supprimerPatient(p.getId());
                    loadTable();
                }
            });
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableColumnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tableColumnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        tableColumnCIN.setCellValueFactory(new PropertyValueFactory<>("cin"));
        loadTable();
    }

     void loadTable() {
        System.out.println("Table Reloading ...");
        ObservableList<Patient> data = FXCollections.observableArrayList(
                metier.listerPatients()
        );
        table.setItems(data);
    }
    @FXML
    private TextField SearchTextField;
    @FXML
    void onSearchButtonClicked(ActionEvent event) {
        String keyword = SearchTextField.getText();
        ObservableList<Patient> data = FXCollections.observableArrayList(
                metier.rechercherPatientsParMotCle(keyword)
        );
        table.setItems(data);
    }
}