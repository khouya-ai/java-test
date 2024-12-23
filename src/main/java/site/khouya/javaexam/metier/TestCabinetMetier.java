package site.khouya.javaexam.metier;

import java.text.SimpleDateFormat;
import java.util.List;

public class TestCabinetMetier {
    public static void main(String[] args) {
        ICabinetMetier metier = new CabinetMetierImpl();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // Ajout Patients
            Patient patient1 = new Patient("Ali", "Alaoui", "AB12345", "123456789", "ali@example.com", sdf.parse("1985-07-15"));
            Patient patient2 = new Patient("Samira", "Rahimi", "CD67890", "987654321", "samira@example.com", sdf.parse("1990-12-25"));
            metier.ajouterPatient(patient1);
            metier.ajouterPatient(patient2);

            // List Patients
            System.out.println("List of Patients:");
            List<Patient> patients = metier.listerPatients();
            for (Patient p : patients) {
                System.out.println(p);
            }

            // Ajout Medecin
            Medecin doctor1 = new Medecin("Khalid", "Arabi", "khalid.arabi@example.com", "111222333");
            Medecin doctor2 = new Medecin("Rim", "Jabir", "rim.jabir@example.com", "444555666");
            metier.ajouterMedecin(doctor1);
            metier.ajouterMedecin(doctor2);

            // List Medecin
            System.out.println("\nList of Medecin:");
            List<Medecin> medecin = metier.listerMedecins();
            for (Medecin m : medecin) {
                System.out.println(m);
            }

            // Ajout Consultations
            Consultation consultation1 = new Consultation(sdf.parse("2024-01-01"), patients.get(0), medecin.get(0));
            Consultation consultation2 = new Consultation(sdf.parse("2024-02-01"), patients.get(1), medecin.get(1));
            metier.ajouterConsultation(consultation1);
            metier.ajouterConsultation(consultation2);

            // List Consultations
            System.out.println("\nList of Consultations:");
            List<Consultation> consultations = metier.listerConsultations();
            for (Consultation c : consultations) {
                System.out.println(c);
            }

            // Chercher Patient par Mot cle
            System.out.println("\nChercher Patient par Mot cle 'Alaoui':");
            List<Patient> searchResults = metier.rechercherPatientsParMotCle("Alaoui");
            for (Patient p : searchResults) {
                System.out.println(p);
            }

            // Supprimer Patient
            if (!patients.isEmpty()) {
                metier.supprimerPatient(patients.get(0).getId());
                System.out.println("\n Patient Supprimé: " + patients.get(0));
            }

            // List Patients
            System.out.println("\nNouvelle List of Patients:");
            patients = metier.listerPatients();
            for (Patient p : patients) {
                System.out.println(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
