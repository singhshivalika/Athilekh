import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnect {
    private static DBConnect dBConnect = new DBConnect();
    private Connection connection=null;
    private DBConnect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://"+"127.0.0.1"+":"+"3306"+"/"+ "ATHILEKH","root", "arihant");
            System.out.println("DB Working");
        }catch(Exception e){System.out.println(e);}
    }

    public static DBConnect getInstance(){
        return dBConnect;
    }

    public ResultSet runFetchQuery(String q){
        try {
            Statement s = connection.createStatement();
            return s.executeQuery(q);
        }catch (Exception e){return null;}
    }

    public Connection getConnection(){
        return connection;
    }

    public boolean runManipulationQuery(String q){
        try {
            Statement s = connection.createStatement();
            int a = s.executeUpdate(q);
            if(a>=0)return true;
            else return false;
        }catch (Exception e){return false;}
    }

    public boolean runInsertQuery(String q){
        try {
            Statement s = connection.createStatement();
            return s.execute(q);
        }catch (Exception e){return false;}
    }

    public ArrayList<String> runInsertQueryAndgetKey(String q){
        try {
            ArrayList<String> keys = new ArrayList<>();
            Statement s = connection.createStatement();
            s.execute(q,Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = s.getGeneratedKeys();
            while(rs.next()){
                keys.add(rs.getString(1));
            }
            return keys;
        }catch (Exception e){ e.printStackTrace(); return null;}
    }


}