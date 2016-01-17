import java.util.List;

public interface I_ProduitDAO {
	public abstract boolean ajoutProduit(I_Produit produit);
	public abstract boolean modifierProduit(I_Produit produit);
	public abstract boolean supprimerProduit(I_Produit produit);
	public abstract I_Produit recupererProduit(String nom);
	public abstract List<I_Produit> recupererTousLesProduits();
	public abstract void deconnexion();
	

}
