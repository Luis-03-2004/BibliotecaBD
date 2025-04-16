import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class livro {
    protected static final String DATABASE_URL = "jdbc:mysql://localhost:3306/func";
    protected static final String DATABASE_USER = "root";
    protected static final String DATABASE_PASSWORD = "ifsuldeminas";
    protected int codigo;
    protected String titulo;
    protected boolean status;
    protected int edicao;
    protected String genero;
    protected int anoPublicacao;
    protected int codigoEditora;



public livro(int codigo, String titulo, boolean status, int edicao, String genero, int anoPublicacao, int codigoEditora) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.status = status;
        this.edicao = edicao;
        this.genero = genero;
        this.anoPublicacao = anoPublicacao;
        this.codigoEditora = codigoEditora;
        }



    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public int getCodigoEditora() {
        return codigoEditora;
    }

    public void setCodigoEditora(int codigoEditora) {
        this.codigoEditora = codigoEditora;
    }

        /*codigo int,
titulo varchar(50),
estado boolean,
edicao int,
genero varchar(15),
anoPublicacao int,
codigoEditora int */

   public void insertData(int codigo, String titulo, int edicao, String genero,int anoPublicacao, int codigoEditora  ) {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "INSERT INTO livros (codigo,titulo,estado,edicao,genero,anoPublicacao) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, codigo);
                statement.setString(2, titulo);
                statement.setBoolean(3, false);//se esta true quer dizer que o livro nao esta emprestado
                statement.setInt(4, edicao);
                statement.setString(5, genero);
                statement.setInt(6, anoPublicacao);
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new book was inserted successfully!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            String sql1 = "INSERT INTO editoralivro (codigolivro,codigoeditor) VALUES (?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql1)) {
                statement.setInt(1, codigo);
                statement.setInt(2, codigoEditora);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new book was inserted successfully!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  void readData() {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "SELECT * FROM livros";
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {


                while (resultSet.next()) {
                    int codigo = resultSet.getInt("codigo");
                    String titulo= resultSet.getString("titulo");
                    boolean estado = resultSet.getBoolean("estado");
                    int edicao= resultSet.getInt("edicao");
                    String genero= resultSet.getString("genero");
                    int anoPublicacao = resultSet.getInt("anoPublicacao");
                    System.out.println("Codigo: " + codigo + ", titulo: " + titulo + ", Emprestado: " +estado+ ", Edicao: "+edicao+ ", genero: "+genero+ ", ano de Publicaco: "+anoPublicacao);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void alterData(int codigo,String titulo,int edicao,String genero, int anoPublicacao) {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "UPDATE livros SET titulo = ? , edicao = ? , genero = ? , anoPublicacao = ? WHERE codigo = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, titulo);
                statement.setInt(2, edicao);
                statement.setString(3, genero);
                statement.setInt(4, anoPublicacao);
                statement.setInt(5, codigo);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new book was inserted successfully!");
                }else{
                System.out.println("no name was found in this cpf!");
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteData(int codigo) {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
            String sql = "DELETE FROM livros WHERE codigo = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, codigo);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("The deleting was successfully!");
                }else{
                System.out.println("dont have book with this code!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pegarEmprestadoFunc(int codigo, String cpffunc) {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
             String sql = "UPDATE livros SET estado = ? WHERE codigo = ?";
             try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setBoolean(1, true);
                statement.setInt(2, codigo);


                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("The book was taken!");
                }else{
                System.out.println("dont have book with this code!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            String sql1 = "INSERT INTO livrofuncionario (codigolivro, cpfemprestado) VALUES (?, ?) ";
             try (PreparedStatement statement = conn.prepareStatement(sql1)) {
                statement.setInt(1, codigo);
                statement.setString(2, cpffunc);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("The book" + codigo + " was taken to :" + cpffunc);
                }else{
                System.out.println("dont have book with this code or this cpf isn't registered!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pegarEmprestadoUser(int codigo, String cpfuser) {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
             String sql = "UPDATE livros SET estado = ? WHERE codigo = ?";
             try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setBoolean(1, true);
                statement.setInt(2, codigo);
                statement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
            String sql1 = "INSERT INTO livroUsuario (codigolivro, cpfemprestado) VALUES (?, ?) ";
             try (PreparedStatement statement = conn.prepareStatement(sql1)) {
                statement.setInt(1, codigo);
                statement.setString(2, cpfuser);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("The book " +codigo+ " was taken to : " +cpfuser);
                }else{
                System.out.println("dont have book with this code or this cpf isn't registered!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void devolverUser(int codigo) {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
                    String sql = "UPDATE livros SET estado = false WHERE codigo = ?";
                    try (PreparedStatement statement = conn.prepareStatement(sql)) {
                       statement.setInt(1, codigo);
                       statement.executeUpdate();

                   } catch (Exception e) {
                       e.printStackTrace();
                   }
            String sql1 = "DELETE FROM livroUsuario WHERE codigolivro = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql1)) {
               statement.setInt(1, codigo);

               int rowsInserted = statement.executeUpdate();
               if (rowsInserted > 0) {
                   System.out.println("The book was returned!");
               }else{
               System.out.println("dont have book with this code!");
               }
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public void devolverFunc(int codigo) {
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
           
                    String sql = "UPDATE livros SET estado = false WHERE codigo = ?";
                    try (PreparedStatement statement = conn.prepareStatement(sql)) {
                       statement.setInt(1, codigo);
                       statement.executeUpdate();
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
            String sql1 = "DELETE FROM livrofuncionario WHERE codigolivro = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql1)) {
               statement.setInt(1, codigo);

               int rowsInserted = statement.executeUpdate();
               if (rowsInserted > 0) {
                   System.out.println("The book was returned!");
               }else{
               System.out.println("dont have book with this code!");
               }
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
