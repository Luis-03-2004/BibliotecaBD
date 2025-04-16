import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class autores {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/func";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "ifsuldeminas";

    protected int codigo;
    protected String nome;
    protected String nacionalidade;
    
    public autores(int codigo, String nome, String nacionalidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
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

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public void insertData(int codigo, String nome, String nacionalidade) {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "INSERT INTO autores (codigo, nome, nacionalidade) VALUES (?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, codigo);
                statement.setString(2, nome);
                statement.setString(3, nacionalidade);

               
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new author has been registered!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  void readData() {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "SELECT * FROM autores";
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {


                while (resultSet.next()) {
                    int codigo = resultSet.getInt("codigo");
                    String name = resultSet.getString("nome");
                    String nacionalidade = resultSet.getString("nacionalidade");
                    System.out.println("codigo: " + codigo + ", Name: " + name + ", nacionalidade: " + nacionalidade);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void alterData(int codigo,String nome, String nacionalidade) {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "UPDATE autores SET nome = ?, nacionalidade = ? WHERE codigo = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, nome);
                statement.setString(2, nacionalidade);
                statement.setInt(3, codigo);

               
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new author was inserted successfully!");
                }else{
                System.out.println("no name was found in this code!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteData(int codigo) {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
            String sql = "DELETE FROM autores WHERE codigo = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, codigo);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("The deleting was successfully!");
                }else{
                System.out.println("nothing was found in this cpf!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
