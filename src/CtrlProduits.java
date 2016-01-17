import javax.swing.JTextField;


public class CtrlProduits extends Controleur {

	public CtrlProduits() {
		// TODO Auto-generated constructor stub
	}
	
	public void ajouterProduit(String nom, double prix, int qte){
		getCatalogue().addProduit(nom, prix, qte);
	}
	
	public void suprimmerProduit (String nom){
		getCatalogue().removeProduit(nom);
	}

}
