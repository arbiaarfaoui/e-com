package com.example.ecommerce.controllers;

import com.example.ecommerce.dao.ProduitRepository;
import com.example.ecommerce.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

//Puisque c'est un controleur, il daut utiliser l'annotaion controller
@Controller
public class ProduitController {

    List<Produit> pageProduitsPanier = new ArrayList<>();
    private double prixTotal = 0;

    //Afficher la liste des produits: nous avons besoins de la couche DAO
    //Il faut décalarer l'interface ProduitRepository
    @Autowired
    private ProduitRepository produitRepository;

//Méthode qui permet de retourner une vue index.html(products) Quand on écrit index tt cours, par défaut c index.html
//Pour accéder à cette méthode on utilise GetMapping càd si une requete http est envoyé avec get vers path="/index" c'est cette méthode qui va s'éxecuter
//Il faut créer le fichier index dans le fichier ressources
@GetMapping(path="/products")
//Récupérer les paramètres pour effecutuer la recherche d'un produit
public String products(Model model,
                       @RequestParam(name="page", defaultValue = "0")int page,
                       //içi même si nous avons x produits, on lui demande de nous envoyer que 5
                       @RequestParam(name="size", defaultValue="6") int size,
                       //Ajouter le paramètre motCle qui contient le mot recherché saisit par l'utilisateur
                       @RequestParam(name="motCle", defaultValue="")String motCle
) {

    //Récupérer tout les produits dans une liste ou page ensuite il faut stocker la liste dans le modèle: donc il faut le déclarer dans l'entête de la méthode  List<Produit> produits=produitRepository.findAll();

    //On utilise page et size que nous avons reçu comme paramètre
    Page<Produit> pageProduits=produitRepository
            .findByDesignationContains(motCle,PageRequest.of(page, size));
    //On stocke dans le modèle un attribut =clé quis'appelle listProduits dont la valeur est la liste des produits
    //Ensuite dans la vue il faut écrire le code thymeleaf pour faire une boucle sur les produits qui se trouve dans la liste des produits revenir vers la vue ==> products.html
    model.addAttribute("pageProduits", pageProduits);
    //Ajouter un attribut qui s'appelle pages et on créer un tableau de dimensions = nombre de pages ==> avoir les pages dans le modèle
    model.addAttribute("currentPage", page);
    model.addAttribute("size", size);
    //Ajouter le mot clé dans le modèle
    model.addAttribute("motCle", motCle);
    model.addAttribute("pages", new int[pageProduits.getTotalPages()]);
    return "products";
}

    //Méthode panier
    @GetMapping(path="/AjoutPanier")
    public String panier(Model model,Long idProduit){

        if (idProduit != null) {

            Produit pageProduitsPanierLoc= produitRepository.getOne(idProduit);
            pageProduitsPanier.add(pageProduitsPanierLoc);

            prixTotal = prixTotal + pageProduitsPanierLoc.getPrix();

            model.addAttribute("pageProduitsPanier", pageProduitsPanier);
            model.addAttribute("prixTotal", prixTotal);

            return "panier";
        }
        //Si idProduit est null càd la requete http = /AjoutPanier, on affiche le contenu du panier

        else {
            model.addAttribute("pageProduitsPanier", pageProduitsPanier);
            model.addAttribute("prixTotal", prixTotal);
            return "panier"; }

    }

    ///deleteProduitPanier
    @GetMapping(path="/deleteProduitPanier")
    public String deletePanier(Model model, Long id) {
        for(int i=0 ; i < pageProduitsPanier.size();i++) {
            if (id == pageProduitsPanier.get(i).getId()) {
                prixTotal = prixTotal - pageProduitsPanier.get(i).getPrix();
                pageProduitsPanier.remove(i);
            }
        }
        model.addAttribute("pageProduitsPanier", pageProduitsPanier);
        model.addAttribute("prixTotal", prixTotal);

        return "panier";
    }

    ////action par défaut
    @RequestMapping(value="/")
    public String home() {
    return "redirect:/products";
    }

    //Login
    @GetMapping(path="/login")
    public String login(Model model){
        return "login";
    }

    //Supprimer un produit
    @GetMapping(path="/deleteProduit")
    //On pense à récupérer toutes les paramètres
    public String delete(Long id, String motCle, String page, String size ) {
        produitRepository.deleteById(id);
        //si on supprime un produit on se redirige vers la page products qui correspond aux valeurs des ces paramètres
        return "redirect:/products?page="+page+"&motCle="+motCle+"&size="+size;
    }

    //Payment
    @GetMapping(path="/payment")
    public String payment (Model model){
        model.addAttribute("prixTotal", prixTotal);
        return "payment";
    }

}
