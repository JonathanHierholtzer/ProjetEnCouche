
public class CtrlTransactions extends Controleur {

	public CtrlTransactions() {
		// TODO Auto-generated constructor stub
	}
	
	public void acheter(String nomP, int qte){
		getCatalogue().acheterStock(nomP, qte);
	}
	
	public void vendre(String nomP, int qte){
		getCatalogue().vendreStock(nomP, qte);
	}

}
