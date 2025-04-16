import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
    


public class pessoa {
    protected static final String DATABASE_URL = "jdbc:mysql://localhost:3306/func";
    protected static final String DATABASE_USER = "root";
    protected static final String DATABASE_PASSWORD = "ifsuldeminas";

    protected String nome;
    protected String cpf;

    public pessoa (String cpf, String nome){
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void insertData(int cpf, String nome) {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "INSERT INTO pessoa (cpf, nome) VALUES (?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, cpf);
                statement.setString(2, nome);
               
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new user was inserted successfully!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  void readData() {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "SELECT * FROM pessoa";
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {


                while (resultSet.next()) {
                    int id = resultSet.getInt("cpf");
                    String name = resultSet.getString("nome");;
                    System.out.println("ID: " + id + ", Name: " + name + ", Age: ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void alterData(String cpf,String nome) {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "UPDATE pessoa SET nome = ? WHERE cpf = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, nome);
                statement.setString(2, cpf);
               
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new user was inserted successfully!");
                }else{
                System.out.println("no name was found in this cpf!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteData(String cpf) {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "DELETE FROM pessoa WHERE cpf = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, cpf);

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
