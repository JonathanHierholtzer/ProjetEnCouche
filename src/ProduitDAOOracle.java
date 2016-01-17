import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProduitDAOOracle implements I_ProduitDAO {

    private Connection cn = null;
    private Statement st = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private CallableStatement cst = null;

    private String driver = "oracle.jdbc.driver.OracleDriver";
    //private String url= "jdbc:oracle:thin:@gloin:1521:iut";
    private String url= "jdbc:oracle:thin:@162.38.222.149:1521:iut";
    private String login= "meyrueixo";
    private String mdp= "1104022264A";

    public ProduitDAOOracle() {
        connexionBDD();
        deconnexion();
    }


    private void connexionBDD() {
        try {
            Class.forName(this.driver);
            cn = DriverManager.getConnection(this.url, this.login, this.mdp);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Pas de driver");
        }
    }

    public boolean ajoutProduit(I_Produit produit) {
        connexionBDD();
        try {
            cst = cn.prepareCall("{call nouveauProduit(" + produit.getNom() + "," + produit.getPrixUnitaireHT() + "," + produit.getQuantite() + ")}");
            boolean estExecute = cst.execute();
            deconnexion();
            if (estExecute) {
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
        connexionBDD();
        try {
            pst = cn.prepareStatement("SELECT * FROM Produit WHERE nomProduit =?");
            pst.setString(1,produit.getNom());
            rs = pst.executeQuery();
            deconnexion();
            if (rs.next()){
                rs.updateDouble(3,produit.getPrixUnitaireHT());
                rs.updateInt(4,produit.getQuantite());
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
        connexionBDD();
        try {
            pst = cn.prepareStatement("SELECT * FROM Produit WHERE nomProduit =?");
            pst.setString(1,produit.getNom());
            rs = pst.executeQuery();
            deconnexion();
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
        connexionBDD();
        try {
            pst = cn.prepareStatement("SELECT * FROM Produit WHERE nomProduit =?");
            pst.setString(1,nom);
            rs = pst.executeQuery();
            deconnexion();
            if (rs.next()){
                I_Produit produit = new Produit(rs.getString(2),rs.getDouble(3),rs.getInt(4));
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
        connexionBDD();
        try {
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery("SELECT * FROM Produit ORDER BY(nomProduit)");
            deconnexion();
            List<I_Produit> maListeDeProduits = new ArrayList<I_Produit>();
            while (rs.next()){
                I_Produit produit = new Produit(rs.getString(2),rs.getDouble(3),rs.getInt(4));
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
            rs.close();
            rs = null;
            st.close();
            st = null;
            pst.close();
            pst =null;
            cst.close();
            cst =null;
            cn.close();
            cn=null;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
