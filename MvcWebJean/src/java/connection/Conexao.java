package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author PEDRO_TI
 */
public class Conexao {
    private final String bd;
    private final String usuario;
    private final String senha;
    private Connection con; 

    public Conexao() {
        bd = "jdbc:mariadb://localhost:3306/mvcjean";
        usuario = "root";
        senha = "root";
        con = null; 
    }

    public Connection estabeleceConexao() throws SQLException {        
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(bd, usuario, senha); 
            System.out.println("Conexão bem sucedida");
            return con; 
        } catch (ClassNotFoundException erro) {
            System.out.println("Erro na conexão: " + erro);
            return null;
        }
    }    
    
    public void fecharConexao(){
        try{
            this.con.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
}
