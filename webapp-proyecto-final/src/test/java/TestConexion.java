import org.diplomado.domiapp.webapp.models.Producto;
import org.diplomado.domiapp.webapp.repositories.CrudRepository;
import org.diplomado.domiapp.webapp.repositories.ProductoRepositoryJdbcImpl;
import org.diplomado.domiapp.webapp.util.ConexionBaseDatos;
import org.diplomado.domiapp.webapp.util.ConexionBaseDatosDS;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class TestConexion {

    public static void main(String[] args) {
        System.out.println("**********");
        try (Connection connection = ConexionBaseDatosDS.getConnection();) {
            System.out.println("**********");
            connection.getClientInfo().values().forEach(System.out::println);
            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }
            try {
                CrudRepository<Producto> repositorio = new ProductoRepositoryJdbcImpl();
                System.out.println("***** Listar ********");
                repositorio.listar().forEach(System.out::println);
                System.out.println("******Obtener por id******");
                repositorio.obtenerPorId((long)1);
//            System.out.println("******Insertar nuevo producto******");
//            Producto producto = new Producto();
//            producto.setNombre("Chaqueta");
//            producto.setPrecio(15640145.0);
//            producto.setFechaRegistro(LocalDate.now());
//            Categoria categoria = new Categoria();
//            categoria.setId(1);
//            producto.setCategoria(categoria);
//            repositorio.guardar(producto);
//            System.out.println("Producto agregado con exito");
                repositorio.listar().forEach(System.out::println);
                connection.commit();
            } catch (SQLException sqlException) {
                connection.rollback();
                sqlException.printStackTrace();
            }
        } catch (SQLException | NamingException sqlException) {
            sqlException.printStackTrace();
        }

//        String url = "jdbc:mariadb://localhost:3306/diplomado_javaee";
//        String user = "root";
//        String password = "";
//        try (Connection connection = ConexionBaseDatos.getConnection();
//             Statement stmnt = connection.createStatement();
//             ResultSet resultSet = stmnt.executeQuery("SELECT * FROM productos");) {
//            stmnt.executeQuery("INSERT INTO curso_java.productos (nombre, precio, fecha_registro)\n" +
//                    "VALUES ('Zapatos', 1465160, '2022-06-02 21:29:21')");
//
//            stmnt.executeQuery("DELETE\n" +
//                    "FROM curso_java.productos\n" +
//                    "WHERE nombre LIKE '%Medias%'");
//            while (resultSet.next()) {
//                System.out.print(resultSet.getInt("id"));
//                System.out.print("|");
//                System.out.print(resultSet.getString("nombre"));
//                System.out.print("|");
//                System.out.print(resultSet.getDouble("precio"));
//                System.out.print("|");
//                System.out.println(resultSet.getDate("fecha_registro"));
//            }
//            ResultSet resultSet3 = stmnt.executeQuery("SELECT * FROM productos");
//            while (resultSet3.next()) {
//                System.out.print(resultSet3.getInt("id"));
//                System.out.print("|");
//                System.out.print(resultSet3.getString("nombre"));
//                System.out.print("|");
//                System.out.print(resultSet3.getDouble("precio"));
//                System.out.print("|");
//                System.out.println(resultSet3.getDate("fecha_registro"));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e.getMessage());
//        }
    }
}