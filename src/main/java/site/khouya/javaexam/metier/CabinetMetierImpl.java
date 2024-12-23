package site.khouya.javaexam.metier;

import java.sql.Connection;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CabinetMetierImpl implements ICabinetMetier{

    private final Connection dbConnection = SingletonConnexionDB.getConnexion();

    @Override
    public void ajouterPatient(Patient p) {
        try {
            String sql = "INSERT INTO Patients (nom, prenom, cin, telephone, email, date_naissance) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
            ps.setString(3, p.getCin());
            ps.setString(4, p.getTelephone());
            ps.setString(5, p.getEmail());
            ps.setDate(6, new java.sql.Date(p.getDateNaissance().getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimerPatient(int idPatient) {
        try {
            String sql = "DELETE FROM Patients WHERE id_patient = ?";
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ps.setInt(1, idPatient);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Patient> listerPatients() {
        List<Patient> patients = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Patients";
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Patient p = new Patient();
                p.setId(rs.getInt("id_patient"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setCin(rs.getString("cin"));
                p.setTelephone(rs.getString("telephone"));
                p.setEmail(rs.getString("email"));
                p.setDateNaissance(rs.getDate("date_naissance"));
                patients.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public List<Patient> rechercherPatientsParMotCle(String motCle) {
        List<Patient> patients = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Patients WHERE nom LIKE ? OR prenom LIKE ? OR cin LIKE ?";
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ps.setString(1, "%" + motCle + "%");
            ps.setString(2, "%" + motCle + "%");
            ps.setString(3, "%" + motCle + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Patient p = new Patient();
                p.setId(rs.getInt("id_patient"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setCin(rs.getString("cin"));
                p.setTelephone(rs.getString("telephone"));
                p.setEmail(rs.getString("email"));
                p.setDateNaissance(rs.getDate("date_naissance"));
                patients.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public List<Consultation> listerConsultationsParPatient(int idPatient) {
        List<Consultation> consultations = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Consultations WHERE id_patient = ?";
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ps.setInt(1, idPatient);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Consultation c = new Consultation();
                c.setIdConsultation(rs.getInt("id_consultation"));
                c.setDateConsultation(rs.getDate("date_consultation"));
                consultations.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultations;
    }



    @Override
    public void ajouterMedecin(Medecin m) {
        try {
            String sql = "INSERT INTO Medecins (nom, prenom, email, tel) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ps.setString(1, m.getNom());
            ps.setString(2, m.getPrenom());
            ps.setString(3, m.getEmail());
            ps.setString(4, m.getTel());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimerMedecin(int idMedecin) {
        try {
            String sql = "DELETE FROM Medecins WHERE id_medecin = ?";
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ps.setInt(1, idMedecin);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Medecin> listerMedecins() {
        List<Medecin> medecins = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Medecins";
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Medecin m = new Medecin();
                m.setId(rs.getInt("id_medecin"));
                m.setNom(rs.getString("nom"));
                m.setPrenom(rs.getString("prenom"));
                m.setEmail(rs.getString("email"));
                m.setTel(rs.getString("tel"));
                medecins.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medecins;
    }

    @Override
    public List<Consultation> listerConsultationsParMedecin(int idMedecin) {
        List<Consultation> consultations = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Consultations WHERE id_medecin = ?";
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ps.setInt(1, idMedecin);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Consultation c = new Consultation();
                c.setIdConsultation(rs.getInt("id_consultation"));
                c.setDateConsultation(rs.getDate("date_consultation"));
                consultations.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultations;
    }

    @Override
    public void ajouterConsultation(Consultation c) {
        try {
            String sql = "INSERT INTO Consultations (date_consultation, id_patient, id_medecin) VALUES (?, ?, ?)";
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(c.getDateConsultation().getTime()));
            ps.setInt(2, c.getPatient().getId());
            ps.setInt(3, c.getMedecin().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimerConsultation(int idConsultation) {
        try {
            String sql = "DELETE FROM Consultations WHERE id_consultation = ?";
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ps.setInt(1, idConsultation);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Consultation> listerConsultations() {
        List<Consultation> consultations = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Consultations";
            PreparedStatement ps = dbConnection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Consultation c = new Consultation();
                c.setIdConsultation(rs.getInt("id_consultation"));
                c.setDateConsultation(rs.getDate("date_consultation"));
                consultations.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultations;
    }
}
