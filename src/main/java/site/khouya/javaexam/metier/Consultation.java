package site.khouya.javaexam.metier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consultation {
    private int idConsultation;
    private Date dateConsultation;
    private Patient patient;
    private Medecin medecin;

    public Consultation(Date dateConsultation, Patient patient, Medecin medecin) {
        this.dateConsultation = dateConsultation;
        this.patient = patient;
        this.medecin = medecin;
    }
}
