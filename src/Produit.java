import java.text.DecimalFormat;


public class Produit implements I_Produit {

	private int quantiteStock;
	
	private String nom;
	
	private double prixUnitaireHT;
	
	private static double tauxTVA = 0.2;
	
	public Produit() {
		
	}
	
	public Produit(String nom, double prixUnitaireHT, int qte){
		this.nom = nom;
		this.prixUnitaireHT = prixUnitaireHT;
		this.quantiteStock = qte;
	}

	@Override
	public boolean ajouter(int qteAchetee) {
		boolean verif = false;
		if (qteAchetee > 0){
			this.quantiteStock+=qteAchetee;
			verif = true;
		}
		return verif;
	}

	@Override
	public boolean enlever(int qteVendue) {
		boolean verif = false;
		if (qteVendue> 0 && (this.quantiteStock-qteVendue >=0)){
			this.quantiteStock-=qteVendue;
			verif = true;
		}
		return verif;
	}

	@Override
	public String getNom() {
		return this.nom;
	}

	@Override
	public int getQuantite() {
		return this.quantiteStock;
	}

	@Override
	public double getPrixUnitaireHT() {
		return this.prixUnitaireHT;
	}

	@Override
	public double getPrixUnitaireTTC() {
		return this.prixUnitaireHT*(1.0+this.tauxTVA);
	}

	@Override
	public double getPrixStockTTC() {
		return this.getPrixUnitaireTTC()*this.quantiteStock;
	}
	
	public String toString(){
		DecimalFormat df = new DecimalFormat ("0.00");
		return "" + this.nom + " - prix HT : " + df.format(this.prixUnitaireHT) + " € - prix TTC : " + df.format(this.getPrixUnitaireTTC()) + " € - quantité en stock : " + this.quantiteStock;
	}
	

}
