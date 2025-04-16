// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.Statement;
import java.util.Scanner;


public class App {
    
    // private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/func";
    // private static final String DATABASE_USER = "root";
    // private static final String DATABASE_PASSWORD = "ifsuldeminas";


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        /*insertData(1, "Teste");
        readData();*/
        editora editora = new editora(0, "0", "0");
        funcionario f = new funcionario("0", "0", "0", 0);
        livro l= new livro(0, "0", false, 0, "0", 0, 0);
        autores aut= new autores(0, "0", "0");
        usuario user= new usuario("0", "0", "0", "0");
        String cpf,nome,funcao,contato,titulo,genero, nacionalidade,endereco,telefone;
        int op,op1,codigo,anopub,codigoeditora,edicao;
        double salario;

        

        do{
            System.out.println(" +----------------------------------+");
            System.out.println("|             biblioteca             |");
            System.out.println(" +----------------------------------+ ");
            System.out.println("|     Choose the option you want:    |");
            System.out.println("|                                    |");
            System.out.println("|0 - Exit                            |");
            System.out.println("|1 - register                        |");
            System.out.println("|2 - List                            |");
            System.out.println("|3 - remove                          |");
            System.out.println("|4 - borrow                          |");
            System.out.println("|5 - give back a book                |");
            System.out.println("|6 - edit info                       |");
            System.out.println("|                                    |");
            System.out.println(" +----------------------------------+");
            System.out.println(" ");
            op = sc.nextInt();

            switch (op){
                case 0:
                break;
                case 1:
                System.out.println(" +----------------------------------+ ");
                System.out.println("|     Choose the option you want:    |");
                System.out.println(" +----------------------------------+ ");
                System.out.println("|                                    |");
                System.out.println("|0 - Register employee               |");
                System.out.println("|1 - Register book                   |");
                System.out.println("|2 - Register publishing company     |");
                System.out.println("|3 - Register author                 |");
                System.out.println("|4 - Register user                   |");
                System.out.println("|                                    |");
                System.out.println(" +----------------------------------+");
                System.out.println(" ");
                op1 = sc.nextInt();

                switch(op1){
                    case 0:
                    System.out.println("Digite o cpf do funcionario: ");
                    sc.nextLine();
                     cpf = sc.nextLine();
                    System.out.println("Digite o nome do funcionario");
                     nome = sc.nextLine();
                    System.out.println("Digite a funcao do funcionario");

                     funcao = sc.nextLine();
                    System.out.println("Digite o salario do funcionario");
                     salario = sc.nextDouble();
                     f = new funcionario(cpf, nome, funcao, salario);
                    f.insertData(cpf,nome,funcao,salario);

                    break;

                    case 1:
                    System.out.println("Digite o codigo do livro: ");
                     codigo = sc.nextInt();
                    System.out.println("Digite o titulo do livro");
                    sc.nextLine();
                     titulo = sc.nextLine();
                    System.out.println("Digite a edicao do livro");
                     edicao = sc.nextInt();
                    System.out.println("Digite o genero do livro");
                    sc.nextLine();
                     genero = sc.nextLine();
                    System.out.println("Digite o ano de publicacao do livro ");
                     anopub = sc.nextInt();
                    System.out.println("Digite o codigo da editora do livro");
                     codigoeditora = sc.nextInt();
                    //int codigo, String titulo, boolean status, int edicao, String genero, int anoPublicacao, int codigoEditora)

                     l = new livro(codigo, titulo, false, edicao, genero, anopub, codigoeditora);
                    l.insertData(codigo, titulo, edicao, genero, anopub, codigoeditora);

                    break;
                    case 2:
                    System.out.println("Digite o codigo da editora: ");
                    codigoeditora = sc.nextInt();
                   System.out.println("Digite o nome da editora");
                    sc.nextLine();
                    nome = sc.nextLine();
                   System.out.println("Digite o contato da editora");

                    contato = sc.nextLine();
                     editora = new editora(codigoeditora, nome, contato);
                    editora.insertData(codigoeditora, nome, contato);

                    break;
                    case 3:
                    System.out.println("Digite o codigo do autor: ");
                    codigo = sc.nextInt();
                   System.out.println("Digite o nome do autor");
                    sc.nextLine();
                    nome = sc.nextLine();
                   System.out.println("Digite a nacionalidade do autor");
                    nacionalidade = sc.nextLine();
                    aut = new autores(codigo, nome, nacionalidade);
                    aut.insertData(codigo, nome, nacionalidade);
                    break;

                    case 4:
                    System.out.println("digite o cpf do usuario");
                    sc.nextLine();
                    cpf = sc.nextLine();
                    System.out.println("digite o nome do usuario");
                    nome = sc.nextLine();
                    System.out.println("Digite o endereço do usuario");
                    endereco = sc.nextLine();
                    System.out.println("Digite o telefone do usuario");
                    telefone = sc.nextLine();
                    user = new usuario(cpf, nome, endereco, telefone);
                    user.insertData(cpf, nome, endereco, telefone);

                    break;
                }
                break;
                case 2:
                System.out.println(" +----------------------------------+ ");
                System.out.println("|     Choose the option you want:    |");
                System.out.println(" +----------------------------------+ ");
                System.out.println("|                                    |");
                System.out.println("|0 - list employees                  |");
                System.out.println("|1 - list books                      |");
                System.out.println("|2 - list publishing companys        |");
                System.out.println("|3 - list authors                    |");
                System.out.println("|4 - list users                      |");
                System.out.println("|                                    |");
                System.out.println(" +----------------------------------+");
                System.out.println(" ");
                
                op1 = sc.nextInt();

                switch (op1){
                    case 0:
                    f.readData();
                    break;
                    case 1:
                    l.readData();
                    break;
                    case 2:
                    editora.readData();
                    break;
                    case 3:
                    aut.readData();
                    break;
                    case 4:
                    user.readData();
                    break;
                }

                break;
                case 3:
                System.out.println(" +----------------------------------+ ");
                System.out.println("|     Choose the option you want:    |");
                System.out.println(" +----------------------------------+ ");
                System.out.println("|                                    |");
                System.out.println("|0 - remove employees                |");
                System.out.println("|1 - remove books                    |");
                System.out.println("|2 - remove publishing companys      |");
                System.out.println("|3 - remove authors                  |");
                System.out.println("|4 - remove users                    |");
                System.out.println("|                                    |");
                System.out.println(" +----------------------------------+");
                System.out.println(" ");
                
                op1 = sc.nextInt();

                switch (op1){
                    case 0:
                    System.out.println("digite o cpf do funcionario a ser deletado");
                    sc.nextLine();
                    cpf = sc.nextLine();
                    f.deleteData(cpf);
                    break;
                    case 1:
                    System.out.println("digite o codigo do livro a ser deletado");
                    codigo = sc.nextInt();
                    l.deleteData(codigo);
                    break;
                    case 2:
                    System.out.println("digite o codigo da editora a ser deletada");
                    codigo = sc.nextInt();
                    editora.deleteData(codigo);
                    break;
                    case 3:
                    System.out.println("digite o codigo do autor a ser deletado: ");
                    codigo = sc.nextInt();
                    aut.deleteData(codigo);
                    break;
                    case 4:
                    System.out.println("digite o cpf do usuario a ser deletado");
                   sc.nextLine();
                    cpf = sc.nextLine();
                    user.deleteData(cpf);
                    break;
                }

                break;
                case 4:
                System.out.println("Digite o codigo do livro a ser pego: ");
                sc.nextLine();
                codigo = sc.nextInt();
                System.out.println("Digite o cpf a ser emprestado:");
                sc.nextLine();
                cpf = sc.nextLine();
                System.out.println("cpf de funcionario(0) ou de usuario(1): ");
                op1 = sc.nextInt();
                while (op1 != 0 && op1 !=1 ){
                    System.out.println("digite uma opcao valida: ");
                    sc.nextLine();
                    op1 = sc.nextInt();
                }
                switch (op1){
                    case 0:
                    l.pegarEmprestadoFunc(codigo, cpf);
                    break;
                    case 1:
                    l.pegarEmprestadoUser(codigo, cpf);
                    break;
                }
                break;
                case 5:
                System.out.println("Digite o codigo do livro a ser devolvido: ");
                sc.nextLine();
                codigo = sc.nextInt();
                System.out.println("devolucao de funcionario(0) ou de usuario(1): ");
                op1 = sc.nextInt();
                while (op1 != 0 && op1 !=1 ){
                    System.out.println("digite uma opcao valida: ");
                    sc.nextLine();
                    op1 = sc.nextInt();
                }
                switch (op1){
                    case 0:
                    l.devolverFunc(codigo);
                    break;
                    case 1:
                    l.devolverUser(codigo);
                    break;
                }
                break;

                case 6:
                System.out.println(" +----------------------------------+ ");
                System.out.println("|     Choose the option you want:    |");
                System.out.println(" +----------------------------------+ ");
                System.out.println("|                                    |");
                System.out.println("|0 - edit employees                  |");
                System.out.println("|1 - edit books                      |");
                System.out.println("|2 - edit publishing companys        |");
                System.out.println("|3 - edit authors                    |");
                System.out.println("|4 - edit users                      |");
                System.out.println("|                                    |");
                System.out.println(" +----------------------------------+");
                System.out.println(" ");
                
                op1 = sc.nextInt();

                switch (op1){
                    case 0:
                    System.out.println("digite o cpf do funcionario a ser editado: ");
                    sc.nextLine();
                    cpf = sc.nextLine();
                    System.out.println("digite o novo nome: ");
                    sc.nextLine();
                    nome = sc.nextLine();
                    System.out.println("Digite a nova funcao: ");
                    sc.nextLine();
                    funcao = sc.nextLine();
                    System.out.println("digite o novo salario: ");
                    sc.nextLine();
                    salario = sc.nextDouble();
                    f.alterData(cpf, nome, funcao, salario);
                    
                    break;
                    case 1:
                    System.out.println("Digite o codigo do livro a ser modificado: ");
                    sc.nextLine();
                    codigo = sc.nextInt();
                    System.out.println("digite o novo titulo do livro: ");
                    sc.nextLine();
                    titulo = sc.nextLine();
                    System.out.println("Digite a nova edicao do livro: ");
                    sc.nextLine();
                    edicao = sc.nextInt();
                    System.out.println("Digite o novo genero do livro");
                    sc.nextLine();
                    genero = sc.nextLine();
                    System.out.println(" digite o novo ano de publicacao do livro: ");
                    sc.nextLine();
                    anopub = sc.nextInt();

                    l.alterData(codigo, titulo, edicao, genero, anopub);

                    break;
                    case 2:
                    System.out.println("Digite o codigo da editora a ser alterada: ");
                    sc.nextLine();
                    codigo = sc.nextInt();
                    System.out.println("Digite o novo nome da editora: ");
                    sc.nextLine();
                    nome = sc.nextLine();
                    System.out.println("Digite o novo contato da editora: ");
                    sc.nextLine();
                    contato = sc.nextLine();
                    editora.alterData(codigo, nome, contato);
                    break;

                    case 3:
                    System.out.println("Digite o codigo do autor a ser editado");
                    sc.nextLine();
                    codigo = sc.nextInt();
                    System.out.println("Digite o novo nome do autor");
                    sc.nextLine();
                    nome = sc.nextLine();
                    System.out.println("Digite a nacionalidade do autor: ");
                    sc.nextLine();
                    nacionalidade = sc.nextLine();

                    aut.alterData(codigo, nome, nacionalidade);
                    break;
                    case 4:
                    System.out.println("digite o cpf do usuario a ser editado");
                    sc.nextLine();
                    cpf = sc.nextLine();
                    System.out.println("digite o novo nome do usuario: ");
                    sc.nextLine();
                    nome = sc.nextLine();
                    System.out.println("digite o novo endereco do usuario: ");
                    sc.nextLine();
                    endereco = sc.nextLine();
                    System.out.println("Digite o telefone do usuario: ");
                    sc.nextLine();
                    telefone = sc.nextLine();
                    user.alterData(cpf, nome, endereco, telefone);
                    break;
                }

                break;

                default:
                break;
            }


        }while(op!=0);

    }



}
