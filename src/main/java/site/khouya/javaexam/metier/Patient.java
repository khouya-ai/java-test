package site.khouya.javaexam.metier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    private int id;
    private String nom;
    private String prenom;
    private String cin;
    private String telephone;
    private String email;
    private Date dateNaissance;

    public Patient( String nom, String prenom, String cin, String telephone, String email, Date dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.telephone = telephone;
        this.email = email;
        this.dateNaissance = dateNaissance;
    }

}
