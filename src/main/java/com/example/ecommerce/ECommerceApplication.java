package com.example.ecommerce;

import com.example.ecommerce.dao.ProduitRepository;
import com.example.ecommerce.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ECommerceApplication implements CommandLineRunner {
    @Autowired
    private ProduitRepository produitRepository;

    public static void main(String[] args) {
        SpringApplication.run(ECommerceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        produitRepository.save(new Produit(null, "Introduction à l'économie ", 30, 9, "https://zupimages.net/up/21/06/70eh.jpg", null));
        produitRepository.save(new Produit(null, "Introduction générale à l'économie", 45, 3, "https://zupimages.net/up/21/06/08p5.jpg", null));
        produitRepository.save(new Produit(null, "Internet et l'informatique", 67, 11, "https://zupimages.net/up/21/06/e9zj.jpg", null));
        produitRepository.save(new Produit(null, "Débuter en l'informatique pour les nuls", 150, 3, "https://zupimages.net/up/21/06/amhu.jpg", null));
        produitRepository.save(new Produit(null, "Développement informatique", 87, 4, "https://zupimages.net/up/21/06/q606.jpg", null));
        produitRepository.save(new Produit(null, "Economie de développement", 90, 10, "https://zupimages.net/up/21/06/b69k.jpg", null));
        produitRepository.save(new Produit(null, "Math'X", 65, 11, "https://zupimages.net/up/21/06/ob75.jpg", null));
        produitRepository.save(new Produit(null, "Déclic Mathématique", 30, 4, "https://zupimages.net/up/21/06/umsi.jpg", null));
        produitRepository.save(new Produit(null, "L'anglais de A jusqu'à Z", 30, 7, "https://zupimages.net/up/21/06/4y07.jpg", null));
        produitRepository.save(new Produit(null, "Mon cahier de français", 30, 15, "https://zupimages.net/up/21/06/5qkh.jpg", null));
        produitRepository.save(new Produit(null, "Physiqye, chimie", 10, 15, "https://zupimages.net/up/21/06/af67.jpg", null));
        produitRepository.save(new Produit(null, "Physique", 35, 15, "https://zupimages.net/up/21/06/am9w.jpg", null));


    }
}
