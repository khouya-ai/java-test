package site.khouya.javaexam.metier;

import java.util.List;

public interface ICabinetMetier {

    // Gestion des patients
    void ajouterPatient(Patient p);
    void supprimerPatient(int idPatient);
    List<Patient> listerPatients();
    List<Patient> rechercherPatientsParMotCle(String motCle);
    List<Consultation> listerConsultationsParPatient(int idPatient);

    // Gestion des médecins
    void ajouterMedecin(Medecin m);
    void supprimerMedecin(int idMedecin);
    List<Medecin> listerMedecins();
    List<Consultation> listerConsultationsParMedecin(int idMedecin);

    // Gestion des consultations
    void ajouterConsultation(Consultation c);
    void supprimerConsultation(int idConsultation);
    List<Consultation> listerConsultations();
}
