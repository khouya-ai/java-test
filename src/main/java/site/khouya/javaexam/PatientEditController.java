package site.khouya.javaexam;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import site.khouya.javaexam.metier.CabinetMetierImpl;
import site.khouya.javaexam.metier.ICabinetMetier;
import site.khouya.javaexam.metier.Patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PatientEditController {
    @FXML
    private Button btnCancel;

    @FXML
    private Button btnOK;

    @FXML
    private ChoiceBox<String> cboxDepart;

    @FXML
    private TextField txtAdresse;

    @FXML
    private TextField txtCIN;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtPrenom;

    @FXML
    private TextField txtRecretement;

    @FXML
    private TextField txtTel;
    ICabinetMetier metier = new CabinetMetierImpl();
    Patient patient;
//    List<Departement> departementList;
    boolean editMod = true;
    private PatientsController patientController;

    public void setParentController(PatientsController patientController) {
        this.patientController = patientController;
    }

    @FXML
    void Annuler(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void Valider(ActionEvent event) throws ParseException {
        if (!this.editMod) {
            patient = new Patient();
        }

        patient.setNom(txtNom.getText());
        patient.setPrenom(txtPrenom.getText());
        patient.setEmail(txtEmail.getText());
        patient.setCin(txtCIN.getText());
//        patient.setAdresse(txtAdresse.getText());
        // Parse Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateRecrutement = dateFormat.parse(txtRecretement.getText());
        patient.setDateNaissance(dateRecrutement);
        patient.setTelephone(txtTel.getText());
        // Set Department based on selection
        System.out.println(patient);
        metier.ajouterPatient(patient);

        this.patientController.loadTable();
    }

//    public void initializeForUpdate(int profId) {
//        editMod = true;
//        patient = metier.rechercherPatient(profId);
//        txtNom.setText(patient.getNom());
//        txtPrenom.setText(patient.getPrenom());
//        txtEmail.setText(patient.getEmail());
//        txtCIN.setText(patient.getCin());
//        txtAdresse.setText(patient.getAdresse());
//        txtRecretement.setText(patient.getDateRecrutement().toString());
//        txtTel.setText(patient.getTelephone());
//        departementList = metier.listerDepartements();
//        ObservableList<String> data = FXCollections.observableArrayList(
//                departementList.stream().map(Departement::getNom).toList()
//        );
//        cboxDepart.setItems(data);
//        cboxDepart.getSelectionModel().select(patient.getDepartNom());
//    }


    public void initializeForInsertion() {
        editMod = false;
//        departementList = metier.listerDepartements();
//        ObservableList<String> data = FXCollections.observableArrayList(
//                departementList.stream().map(Departement::getNom).toList()
//        );
//        cboxDepart.setItems(data);
//        cboxDepart.getSelectionModel().selectFirst();
    }
}
