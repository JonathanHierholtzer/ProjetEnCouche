import java.util.List;

public class AdapterDAO_XML implements I_ProduitDAO{

    private ProduitDAO_XML monProduitDAO_XML;

    public AdapterDAO_XML() {

        this.monProduitDAO_XML = new ProduitDAO_XML();
    }

    @Override
    public boolean ajoutProduit(I_Produit produit) {
        return this.monProduitDAO_XML.creer(produit);

    }

    @Override
    public boolean modifierProduit(I_Produit produit) {
        return this.monProduitDAO_XML.maj(produit);
    }

    @Override
    public boolean supprimerProduit(I_Produit produit) {
        return this.monProduitDAO_XML.supprimer(produit);
    }

    @Override
    public I_Produit recupererProduit(String nom) {
        return this.monProduitDAO_XML.lire(nom);
    }

    @Override
    public List<I_Produit> recupererTousLesProduits() {
        return this.monProduitDAO_XML.lireTous();
    }

    @Override
    public void deconnexion() {

    }
}
