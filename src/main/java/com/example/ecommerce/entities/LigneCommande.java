package com.example.ecommerce.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LigneCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLigneCommande;
    private int quantiteProduit;
    private double prixCommande;

    @ManyToOne
    @JoinColumn(name = "idCommande")
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "id")
    private Produit produit;
}
