import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProduitDAOOracle implements I_ProduitDAO {

    private String cheminScriptBDD = "/Users/John/Documents/Cours/ACPI/ProjetBDUML/ProjetEnCouche/src/script.sql";
    private Connection cn = null;
    private Statement st = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private CallableStatement cst = null;

    public ProduitDAOOracle() {
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@gloin:1521:iut";
        String urlExterne = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
        String login = "meyrueixo";
        String mdp = "1104022264A";
        connexionBDD(driver, urlExterne, login, mdp);
    }


    private void connexionBDD(String driver, String urlExterne, String login, String mdp) {
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(urlExterne, login, mdp);
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //rs = st.executeQuery("SELECT * FROM Produit ORDER BY(nomProduit)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Pas de driver");
        }
    }

    public boolean ajoutProduit(I_Produit produit) {
        try {
            cst = cn.prepareCall("{call nouveauProduit(" + produit.getNom() + "," + produit.getPrixUnitaireHT() + "," + produit.getQuantite() + ")}");
            if (cst.execute()) {
                System.out.println("Produit ajouté");
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modifierProduit(I_Produit produit) {
        try {
            pst = cn.prepareStatement("SELECT * FROM Produit WHERE nomProduit =?");
            pst.setString(1,produit.getNom());
            rs = pst.executeQuery();
            if (rs.next()){
                rs.updateDouble(2,produit.getPrixUnitaireHT());
                rs.updateInt(3,produit.getQuantite());
                rs.updateRow();
                return true;
            }
            else {
                return false;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean supprimerProduit(I_Produit produit) {
        try {
            pst = cn.prepareStatement("SELECT * FROM Produit WHERE nomProduit =?");
            pst.setString(1,produit.getNom());
            rs = pst.executeQuery();
            if (rs.next()){
                rs.deleteRow();
                return true;
            }
            else {
                return false;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public I_Produit recupererProduit(String nom) {
        try {
            pst = cn.prepareStatement("SELECT * FROM Produit WHERE nomProduit =?");
            pst.setString(1,nom);
            rs = pst.executeQuery();
            if (rs.next()){
                I_Produit produit = new Produit(rs.getString(1),rs.getDouble(2),rs.getInt(3));
                return produit;
            }
            else {
                return null;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<I_Produit> recupererTousLesProduits() {
        try {
            rs = st.executeQuery("SELECT * FROM Produit ORDER BY(nomProduit)");
            List<I_Produit> maListeDeProduits = new ArrayList<I_Produit>();
            while (rs.next()){
                I_Produit produit = new Produit(rs.getString(1),rs.getDouble(2),rs.getInt(3));
                maListeDeProduits.add(produit);
            }
            return maListeDeProduits;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




    public void deconnexion() {
        try {
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
