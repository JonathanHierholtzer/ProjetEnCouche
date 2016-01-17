import java.sql.*;


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
            //resetBDD();
            rs = st.executeQuery("SELECT * FROM Produit ORDER BY(nomProduit)");
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
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /*public void resetBDD() {
        dropTable("Produit");
        creerTableProduit();
        dropSequenceProduit();
        creerSequenceProduit();
        creerProcedureNouveauProduit();

    }

    private void creerProcedureNouveauProduit() {
        String requete = "CREATE OR REPLACE PROCEDURE nouveauProduit ("
                + "p_nomProduit IN Produit.nomProduit%TYPE,"
                + "p_prixProduit IN Produit.prixProduit%TYPE,"
                + "p_qteProduit IN Produit.qteProduit%TYPE) IS "
                + "BEGIN "
                + "INSERT INTO Produit (codeProduit, nomProduit, prixProduit, qteProduit) "
                + "VALUES (seqProd.NEXTVAL, p_nomProduit, p_prixProduit, p_qteProduit);"
                + "END;";
        executerRequeteDeMaJ(requete);
    }

    private void creerSequenceProduit() {
        String requete = "CREATE SEQUENCE seqProd START WITH 1 INCREMENT BY 1";
        executerRequeteDeMaJ(requete);

    }

    private void dropSequenceProduit() {
        String requete = "DROP SEQUENCE seqPROD";
        executerRequeteDeMaJ(requete);
    }

    private void creerTableProduit() {
        String requete = "CREATE TABLE Produit ("
                + "codeProduit NUMBER(10), "
                + "nomProduit VARCHAR(20), "
                + "prixProduit FLOAT(10), "
                + "qteProduit NUMBER(10), "
                + "CONSTRAINT pk_produit PRIMARY KEY (codeProduit), "
                + "CONSTRAINT nn_qteProduit CHECK (qteProduit IS NOT NULL)"
                + ")";

        executerRequeteDeMaJ(requete);
    }

    private void dropTable(String table) {
        String requete = "DROP TABLE " + table + " CASCADE CONSTRAINTS";
        executerRequeteDeMaJ(requete);
    }*/

    private void executerRequeteDeMaJ(String requete) {
        try {
            st.executeUpdate(requete);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();

        }
    }



    /*private boolean laTableExisteDansLaBDD(SQLException e) {
        boolean tableExiste = e.getErrorCode() != 942;
        return tableExiste;
    }

    private boolean laSequenceExisteDansLaBDD(SQLException e) {
        boolean sequenceExiste = e.getErrorCode() != 2289;
        return sequenceExiste;
    }*/

    public void deconnexion() {
        try {
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
