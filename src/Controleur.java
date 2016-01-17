public class Controleur {
	private static Catalogue cat;
	private I_ProduitDAO connexionBD;
	private ProduitDAOFactory myFactoryProduct= new ProduitDAOFactory();
	
	public Controleur() {
		this.cat=new Catalogue();
		this.connexionBD = myFactoryProduct.createProduitDAO("Oracle");
	}
	
	public Catalogue getCatalogue(){
		return this.cat;
	}
	
	public void deconnexionBD(){
		this.connexionBD.deconnexion();
	}
	
/*	public void initCatalogue(String[] tab){
		List<I_Produit> list = new ArrayList(Arrays.asList(tab));
		this.cat.addProduits(list);
	}
*/	
	

}
