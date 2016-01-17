public class Controleur {

    private static Catalogue cat;

    public Controleur() {
        this.cat = new Catalogue();
    }

    public Catalogue getCatalogue() {
        return this.cat;
    }

    public void deconnexionBD() {
        //this.connexionBD.deconnexion();
    }


}
