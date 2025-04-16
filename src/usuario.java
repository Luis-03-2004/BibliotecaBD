import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class usuario extends pessoa {
    protected String endereco;
    protected String telefone;

    public usuario(String cpf , String nome, String endereco , String telefone){
        super (cpf, nome);
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public void insertData(String cpf,String nome , String endereco , String telefone) {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "INSERT INTO usuario (cpf,nome ,endereco , telefone) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, cpf);
                statement.setString(2,nome);
                statement.setString(3, endereco);
                statement.setString(4, telefone);
               
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new user was inserted successfully!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public  void readData() {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "SELECT * FROM usuario";
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {


                while (resultSet.next()) {
                    int cpf = resultSet.getInt("cpf");
                    String nome = resultSet.getString("nome");
                    String endereco = resultSet.getString("endereco");
                    String telefone = resultSet.getString("telefone");
                    System.out.println("CPF: " + cpf + ", Name: " + nome + ", Endereco: "+ endereco + ", Telefone: "+telefone );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alterData(String cpf,String nome,String endereco, String telefone) {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "UPDATE usuario SET nome = ? , endereco = ?, telefone = ? WHERE cpf = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, nome);
                statement.setString(2, endereco);
                statement.setString(3, telefone);
                statement.setString(4, cpf);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("The insertion was successfully!");
                }else{
                System.out.println("nothing was found in this cpf!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteData(String cpf) {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "DELETE FROM usuario WHERE cpf = ?";
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
