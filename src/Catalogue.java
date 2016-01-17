import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Catalogue implements I_Catalogue {

	private ArrayList<I_Produit> lesProduits;
	
	public Catalogue() {
		this.lesProduits = new ArrayList<I_Produit>();
	}
	
	@Override
	public boolean addProduit(I_Produit produit) {
		boolean verif=false;
		int cpt=0;
		
		if (produit!=null && produit.getPrixUnitaireHT()>0 && produit.getQuantite()>=0){
				while (cpt < this.lesProduits.size() && this.lesProduits.get(cpt).getNom()!=produit.getNom()){
					cpt++;
				}
				if (cpt>=this.lesProduits.size()){
					this.lesProduits.add(produit);
					verif = true;
				}
		}
		return verif;
	}

	@Override
	public boolean addProduit(String nom, double prix, int qte) {
		boolean verif=false;
		int cpt=0;
		
		if (prix>0 && qte>=0){
				while (cpt < this.lesProduits.size() && this.lesProduits.get(cpt).getNom()!=nom){
					cpt++;
				}
				if (cpt>=this.lesProduits.size()){
					Produit p1 = new Produit(nom,prix,qte);
					this.lesProduits.add(p1);
					//connexionBD.ajoutProduit(nom, prix, qte);
					verif = true;
				}
		}
		return verif;
	}

	@Override
	public int addProduits(List<I_Produit> l) {
		int res=0;
		if (!(l==null)){
			for (int i=0;i<l.size();i++){
				if(this.addProduit(l.get(i))){
					res++;
				}
			}
		}
		return res;
	}

	@Override
	public boolean removeProduit(String nom) {
		int cpt=0;
		boolean verif = false;
		while (cpt < this.lesProduits.size() && this.lesProduits.get(cpt).getNom()!=nom){
			cpt++;
		}
		if (cpt < this.lesProduits.size()){
			this.lesProduits.remove(cpt);
			verif = true;
		}
		return verif;
	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		int cpt=0;
		boolean verif = false;
		while (cpt < this.lesProduits.size() && this.lesProduits.get(cpt).getNom()!=nomProduit){
			cpt++;
		}
		if (cpt < this.lesProduits.size()){
			verif = this.lesProduits.get(cpt).ajouter(qteAchetee);
		}
		return verif;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		int cpt=0;
		boolean verif = false;
		while (cpt < this.lesProduits.size() && this.lesProduits.get(cpt).getNom()!=nomProduit){
			cpt++;
		}
		if (cpt < this.lesProduits.size()){
			verif = this.lesProduits.get(cpt).enlever(qteVendue);
		}
		return verif;
	}

	@Override
	public String[] getNomProduits() {
		String tabNoms[] = new String[this.lesProduits.size()];
		for (int i=0;i<this.lesProduits.size();i++){
			tabNoms[i] = this.lesProduits.get(i).getNom();
		}
		Arrays.sort(tabNoms);
		return tabNoms;
	}

	@Override
	public double getMontantTotalTTC() {
		double res=0;
		for (int i=0;i< this.lesProduits.size();i++){
			res = res + this.lesProduits.get(i).getPrixStockTTC();
		}
		return (double)Math.round(res * 100) / 100;
	}

	@Override
	public void clear() {
		this.lesProduits.clear();

	}
	
	public String toString(){
		String res="";
		DecimalFormat df = new DecimalFormat ("0.00");
		for (int i=0; i<this.lesProduits.size(); i++ ){
			res=res+this.lesProduits.get(i).toString()+ "\n";
		}
		res=res+"\n"+ "Montant total TTC du stock : " + df.format(this.getMontantTotalTTC())+ " €";
		res=res.replaceAll("\\.", ",");
		return res;
	}

}
