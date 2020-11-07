package angularmail.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {

	public static void main(String[] args) {
		try { 
			String controlador="com.mysql.cj.jdbc.Driver";
			String url="jdbc:mysql://localhost:3306/angularmail?serverTimezone=Europe/Madrid";
			String usuario="angularmail";
			String pass="1234";
			String consulta="select * from usuario";
			// A través de la siguiente línea comprobamos si tenemos acceso al driver MySQL, si no fuera así
			// no podemos trabajar con esa BBDD.
			Class.forName(controlador); // Driver Mysql 8. Si fuera versión 5 quitaríamos el paquete ".cj."
		   
			// Necesitamos obtener un acceso a la BBDD, eso se materializa en un objeto de tipo Connection, al cual
			// le tenemos que pasar los parámetros de conexión.
			Connection conexion = DriverManager.getConnection(url,usuario,pass);
		   
			// Para poder ejecutar una consulta necesitamos utilizar un objeto de tipo Statement
			Statement st = conexion.createStatement(); 
			
			// La ejecución de la consulta se realiza a través del objeto Statement y se recibe en forma de objeto
			// de tipo ResultSet, que puede ser navegado para descubrir todos los registros obtenidos por la consulta
			ResultSet rs = st.executeQuery (consulta);
		   
			// Navegación del objeto ResultSet, cada campo se puede referenciar por su nombre o un número con la posición
			// que ocupa en la tupla, comenzando por el valor 1 (en este caso no comenzamos por el valor 0).
			while (rs.next()) { 
				System.out.println (rs.getInt("id") + " " + rs.getString (2)+ 
						" " + rs.getString(3) + " " + rs.getString(4) + 
						" " + rs.getString(5) + " " + rs.getString("fechaNacimiento")); 
			}
			// Cierre de los elementos
			rs.close();
			st.close();
			conexion.close();
		}
		catch (SQLException ex) {
			System.out.println("Error en la ejecución SQL: " + ex.getMessage());
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
