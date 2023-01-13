import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    //Point d'entré JAVA obligatoire au début de chaque programmes
    public static void main(String[] args) {
        String user = "root";
        String pwd = "root";
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:8082/binomotron";
        String requete = "";

        String ls = System.lineSeparator();
        Connection con = null;
        Statement st = null;
        ResultSet list_app = null;
        //ResultSet listrdm = null;
        //Declaration des variables

        try {
            con = DriverManager.getConnection(url, user, pwd);
            st = con.createStatement();
            //Requete de récuperation dans la table apprenants
            list_app = st.executeQuery("SELECT * FROM apprenants");
            List<String> rdm_liste = new ArrayList<>();

            while (list_app.next()) {
                rdm_liste.add(list_app.getString("nom") + " " + list_app.getString("prenom"));
            }

            //while(list_app.next()){
            //System.out.println(list_app.getString("nom") + " " + list_app.getString("prenom"));
            //Affiche la liste récuperé dans la BDD.
            // + " " + = ajout espace lors d'une concatenation de mots.
            //}

            Collections.shuffle(rdm_liste); //Mélangez les éléments de la liste.
            System.out.println(rdm_liste); //Affiche la liste mélanger.

            int num_groupes = 1;

            for (int i = 0; i < rdm_liste.size(); i++) {
                List<String> list_groupes = new ArrayList<>();
                list_groupes.add(rdm_liste.get(i));
                i++;
                // Création de la liste list_groupes
                // Ajout des groupes de deux dans la liste
                if (i < rdm_liste.size()) {
                    list_groupes.add(rdm_liste.get(i));
                }

                System.out.println("groupes :" + " " + (num_groupes) + " " + list_groupes);
                num_groupes++;
                //Affichage des numéros de groupes dans la console via num_groupes
            }

        } catch (Exception e) {
            System.err.println("Exception : " + e.getMessage());
            //Permet l'affichage du message d'erreur si il y en a un
        } finally {
            try {
                if (list_app != null) list_app.close();
                //if(rs2 != null) rs2.close();
                if (st != null) st.close();
                if (con != null) con.close();
                //Ferme la connection a la base de donnée
            } catch (Exception e) {
            }
        }
    }
}