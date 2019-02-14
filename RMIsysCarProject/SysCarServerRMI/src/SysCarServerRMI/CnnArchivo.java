
package SysCarServerRMI;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CnnArchivo {
   Connection cnn = null;
        
    public boolean getConnection() {
        boolean r = false;
        try {
            File fl = new File(System.getProperty("user.dir") + "\\db.txt");
            FileReader fr = new FileReader(fl);
            BufferedReader bu = new BufferedReader(fr);
            String linea = null;
            while ((linea = bu.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(linea, ",");
                String temp = (st.nextToken());
                String db = (st.nextToken());
                String user = (st.nextToken());
                String pass = (st.nextToken());

                String url = temp + db;
                Class.forName("org.postgresql.Driver");
                cnn = (org.postgresql.jdbc.PgConnection) DriverManager.getConnection(url, user, pass);
                
                r= true;
            }
            fr.close();
        } catch (Exception e) {
              r=false;
        }
        return r;
    }
     void crearArchivo(String server, String puerto, String db, String usuario,String clave){
         try {
            String ruta = (System.getProperty("user.dir") + "\\db.txt");
            String contenido = "jdbc:postgresql://,"+server+":"+puerto+"/"+db+","+usuario+","+clave;
            File file = new File(ruta);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }   
     
    public boolean conexionAdministrador(String url,String usarname,String password){
        boolean r=false;
        try{
        
           Class.forName("org.postgresql.Driver");
           
           cnn = (Connection) DriverManager.getConnection(url,usarname,password);
          
            r= true;
        }catch(Exception e){
            
        }
        return r;
    }
    public void conexionNull(){
       try {
           cnn.close();
       } catch (SQLException ex) {
           Logger.getLogger(CnnArchivo.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
        
}

