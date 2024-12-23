package site.khouya.javaexam.metier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medecin {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String tel;

    public Medecin(String nom, String prenom, String email, String tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
    }
}
