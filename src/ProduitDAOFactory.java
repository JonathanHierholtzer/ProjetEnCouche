
public class ProduitDAOFactory {

    private I_ProduitDAO myDAOProduct;

    public ProduitDAOFactory() {

    }

    public I_ProduitDAO createProduitDAO(String serveur) {
        switch (serveur) {
            case "Oracle":
                this.myDAOProduct = new ProduitDAOOracle();
                break;
            case "XML":
                this.myDAOProduct = new AdapterDAO_XML();
                break;
            default:
                this.myDAOProduct = new ProduitDAOOracle();
        }
        return this.myDAOProduct;
    }
}
