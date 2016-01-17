import java.util.List;

public class CtrlProduits extends Controleur {

	public CtrlProduits() {
		// TODO Auto-generated constructor stub
	}
	
	public void ajouterProduit(String nom, double prix, int qte){
		getCatalogue().addProduit(nom, prix, qte);

	}

	public void ajouterProduits(List<I_Produit> l){
		getCatalogue().addProduits(l);
	}
	
	public void suprimmerProduit (String nom){
		getCatalogue().removeProduit(nom);
	}

}
