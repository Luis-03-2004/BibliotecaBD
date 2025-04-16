import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class editora {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/func";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "ifsuldeminas";

    protected int codigo;
    protected String nome;
    protected String contato;

    public editora(int codigo, String nome, String contato) {
        this.codigo = codigo;
        this.nome = nome;
        this.contato = contato;
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getContato() {
        return contato;
    }
    public void setContato(String contato) {
        this.contato = contato;
    }

    public void insertData(int codigo, String nome, String contato) {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "INSERT INTO editora (codigo, nome, contato) VALUES (?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, codigo);
                statement.setString(2, nome);
                statement.setString(3, contato);

               
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new publishing company was inserted successfully!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  void readData() {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "SELECT * FROM editora";
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {


                while (resultSet.next()) {
                    int codigo = resultSet.getInt("codigo");
                    String name = resultSet.getString("nome");
                    String contato = resultSet.getString("contato");
                    System.out.println("Code: " + codigo + ", Name: " + name + ", Contact: "+contato);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void alterData(int codigo,String nome, String contato) {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "UPDATE editora SET nome = ?, contato = ? WHERE codigo = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, nome);
                statement.setString(2, contato);
                statement.setInt(3, codigo);

               
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new publisher was registered!");
                }else{
                System.out.println("no publisher was found in this code!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteData(int codigo) {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "DELETE FROM editora WHERE codigo = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, codigo);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("The deleting was successfully!");
                }else{
                System.out.println("nothing was found in this code!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
