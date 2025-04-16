import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class funcionario extends pessoa{
    protected String funcao;
    protected double salario;

    public funcionario(String cpf, String nome , String funcao , double salario){
        super (cpf , nome);
        this.funcao = funcao;
        this.salario = salario;
    }

    public void insertData(String cpf,String nome , String funcao , double salario) {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "INSERT INTO funcionario (cpf,nome ,funcao , salario) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, cpf);
                statement.setString(2,nome);
                statement.setString(3, funcao);
                statement.setDouble(4, salario);
               
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new employee was inserted successfully!");
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
           
            String sql = "SELECT * FROM funcionario";
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {


                while (resultSet.next()) {
                    String cpf = resultSet.getString("cpf");
                    String nome = resultSet.getString("nome");
                    String funcao = resultSet.getString("funcao");
                    String salario = resultSet.getString("salario");
                    System.out.println("CPF: " + cpf + ", Name: " + nome + ", Funcao: "+ funcao + ", Salario: "+salario );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alterData(String cpf,String nome,String funcao, double salario) {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "UPDATE funcionario SET nome = ? , funcao = ?, salario = ? WHERE cpf = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, nome);
                statement.setString(2, funcao);
                statement.setDouble(3, salario);
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
           
            String sql = "DELETE FROM funcionario WHERE cpf = ?";
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
