package com.example.ecommerce.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Commande implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommande;
    private Date dateCommande;
    private double prixCommande;

    /*@ManyToOne
    @JoinColumn(name="idUtilisateur")
    //Un utilisateur peut commander plusieurs commandes ==> ManyToOne ==> Many commande to one utilisateur
    private Utilisateur utilisateur;*/

    @OneToMany(mappedBy = "commande")
    private List<LigneCommande> ligneCommande;

}
