package Recursos;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import Controles.Ctrl_Base_Datos;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("bitnet/usuario")
public class Recurso_Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@RolesAllowed("ADMIN")
	@Path("obtener-lista")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response obtener_lista() {
		Response resultado;
		Connection conn = null;

		try {
			Ctrl_Base_Datos ctrl_base_datos = new Ctrl_Base_Datos();
			conn = ctrl_base_datos.obtener_conexion_mysql();

			conn.setAutoCommit(false);

			Controles.Ctrl_Usuario ctrl_usuario = new Controles.Ctrl_Usuario();
			List<Entidades.Usuario> lista_usuario = ctrl_usuario.obtener_lista(conn);

			Gson gson = new GsonBuilder().serializeNulls().create();
			resultado = Response.ok(gson.toJson(lista_usuario), MediaType.APPLICATION_JSON).build();

			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			String mensaje = "PROYECTO: BITNET-JERSEY-REST-API ==> CLASE: " + this.getClass().getName()
					+ " ==> METODO: obtener_lista()" + " ERROR: " + ex.toString();

			resultado = Response.status(Status.NOT_FOUND).entity(mensaje).build();

			System.out.println("PROYECTO: BITNET-JERSEY-REST-API ==> CLASE: " + this.getClass().getName()
					+ " ==> METODO: obtener_lista()" + " ERROR: " + ex.toString());
		} finally {
			try {
				if (conn.isClosed()) {
					conn.close();
					conn = null;
				}
			} catch (Exception ex) {
				System.out.println("PROYECTO: BITNET-JERSEY-REST-API ==> CLASE: " + this.getClass().getName()
						+ " ==> METODO: obtener_lista()-Finally" + " ERROR: " + ex.toString());
			}
		}

		return resultado;
	}

	@RolesAllowed("ADMIN")
	@Path("obtener-id/{id_usuario}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response obtener_id(@PathParam("id_usuario") Long id_usuario) {
		Response resultado;
		Connection conn = null;

		try {
			Ctrl_Base_Datos ctrl_base_datos = new Ctrl_Base_Datos();
			conn = ctrl_base_datos.obtener_conexion_mysql();

			conn.setAutoCommit(false);

			Controles.Ctrl_Usuario ctrl_usuario = new Controles.Ctrl_Usuario();
			Entidades.Usuario usuario = ctrl_usuario.obtener_id(conn, id_usuario);

			Gson gson = new GsonBuilder().serializeNulls().create();
			resultado = Response.ok(gson.toJson(usuario), MediaType.APPLICATION_JSON).build();

			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			String mensaje = "PROYECTO: BITNET-JERSEY-REST-API ==> CLASE: " + this.getClass().getName()
					+ " ==> METODO: obtener_id()" + " ERROR: " + ex.toString();

			resultado = Response.status(Status.NOT_FOUND).entity(mensaje).build();

			System.out.println("PROYECTO: BITNET-JERSEY-REST-API ==> CLASE: " + this.getClass().getName()
					+ " ==> METODO: obtener_id()" + " ERROR: " + ex.toString());
		} finally {
			try {
				if (conn.isClosed()) {
					conn.close();
					conn = null;
				}
			} catch (Exception ex) {
				System.out.println("PROYECTO: BITNET-JERSEY-REST-API ==> CLASE: " + this.getClass().getName()
						+ " ==> METODO: obtener_id()-Finally" + " ERROR: " + ex.toString());
			}
		}

		return resultado;
	}

	@RolesAllowed("ADMIN")
	@Path("crear")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	public Response crear(String jsonString) {
		Response resultado;
		Connection conn = null;

		try {
			Ctrl_Base_Datos ctrl_base_datos = new Ctrl_Base_Datos();
			conn = ctrl_base_datos.obtener_conexion_mysql();

			conn.setAutoCommit(false);

			Controles.Ctrl_Usuario ctrl_usuario = new Controles.Ctrl_Usuario();
			Entidades.Usuario usuario = ctrl_usuario.crear(conn, jsonString);

			Gson gson = new GsonBuilder().serializeNulls().create();
			resultado = Response.ok(gson.toJson(usuario), MediaType.APPLICATION_JSON).build();

			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			String mensaje = "PROYECTO: BITNET-JERSEY-REST-API ==> CLASE: " + this.getClass().getName()
					+ " ==> METODO: crear()" + " ERROR: " + ex.toString();

			resultado = Response.status(Status.NOT_FOUND).entity(mensaje).build();

			System.out.println("PROYECTO: BITNET-JERSEY-REST-API ==> CLASE: " + this.getClass().getName()
					+ " ==> METODO: crear()" + " ERROR: " + ex.toString());
		} finally {
			try {
				if (conn.isClosed()) {
					conn.close();
					conn = null;
				}
			} catch (Exception ex) {
				System.out.println("PROYECTO: BITNET-JERSEY-REST-API ==> CLASE: " + this.getClass().getName()
						+ " ==> METODO: crear()-Finally" + " ERROR: " + ex.toString());
			}
		}

		return resultado;
	}

	@RolesAllowed("ADMIN")
	@Path("modificar/{id_usuario}")
	@PUT
	@Produces({ MediaType.APPLICATION_JSON })
	public Response modificar(@PathParam("id_usuario") Long id_usuario, String jsonString) {
		Response resultado;
		Connection conn = null;

		try {
			Ctrl_Base_Datos ctrl_base_datos = new Ctrl_Base_Datos();
			conn = ctrl_base_datos.obtener_conexion_mysql();

			conn.setAutoCommit(false);

			Controles.Ctrl_Usuario ctrl_usuario = new Controles.Ctrl_Usuario();
			Entidades.Usuario usuario = ctrl_usuario.modificar(conn, id_usuario, jsonString);

			Gson gson = new GsonBuilder().serializeNulls().create();
			resultado = Response.ok(gson.toJson(usuario), MediaType.APPLICATION_JSON).build();

			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			String mensaje = "PROYECTO: BITNET-JERSEY-REST-API ==> CLASE: " + this.getClass().getName()
					+ " ==> METODO: modificar()" + " ERROR: " + ex.toString();

			resultado = Response.status(Status.NOT_FOUND).entity(mensaje).build();

			System.out.println("PROYECTO: BITNET-JERSEY-REST-API ==> CLASE: " + this.getClass().getName()
					+ " ==> METODO: modificar()" + " ERROR: " + ex.toString());
		} finally {
			try {
				if (conn.isClosed()) {
					conn.close();
					conn = null;
				}
			} catch (Exception ex) {
				System.out.println("PROYECTO: BITNET-JERSEY-REST-API ==> CLASE: " + this.getClass().getName()
						+ " ==> METODO: modificar()-Finally" + " ERROR: " + ex.toString());
			}
		}

		return resultado;
	}

	@RolesAllowed("ADMIN")
	@Path("eliminar/{id_usuario}")
	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	public Response eliminar(@PathParam("id_usuario") Long id_usuario) {
		Response resultado;
		Connection conn = null;

		try {
			Ctrl_Base_Datos ctrl_base_datos = new Ctrl_Base_Datos();
			conn = ctrl_base_datos.obtener_conexion_mysql();

			conn.setAutoCommit(false);

			Controles.Ctrl_Usuario ctrl_usuario = new Controles.Ctrl_Usuario();
			Entidades.Usuario usuario = ctrl_usuario.eliminar(conn, id_usuario);

			Gson gson = new GsonBuilder().serializeNulls().create();
			resultado = Response.ok(gson.toJson(usuario), MediaType.APPLICATION_JSON).build();

			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			String mensaje = "PROYECTO: BITNET-JERSEY-REST-API ==> CLASE: " + this.getClass().getName()
					+ " ==> METODO: eliminar()" + " ERROR: " + ex.toString();

			resultado = Response.status(Status.NOT_FOUND).entity(mensaje).build();

			System.out.println("PROYECTO: BITNET-JERSEY-REST-API ==> CLASE: " + this.getClass().getName()
					+ " ==> METODO: eliminar()" + " ERROR: " + ex.toString());
		} finally {
			try {
				if (conn.isClosed()) {
					conn.close();
					conn = null;
				}
			} catch (Exception ex) {
				System.out.println("PROYECTO: BITNET-JERSEY-REST-API ==> CLASE: " + this.getClass().getName()
						+ " ==> METODO: eliminar()-Finally" + " ERROR: " + ex.toString());
			}
		}

		return resultado;
	}

	@RolesAllowed("ADMIN")
	@Path("autenticar/{nombre_usuario}/{contrasena}")
	@PUT
	@Produces({ MediaType.APPLICATION_JSON })
	public Response autenticar(@PathParam("nombre_usuario") String nombre_usuario, @PathParam("contrasena") String contrasena) {
		Response resultado;
		Connection conn = null;

		try {
			Ctrl_Base_Datos ctrl_base_datos = new Ctrl_Base_Datos();
			conn = ctrl_base_datos.obtener_conexion_mysql();

			conn.setAutoCommit(false);

			Controles.Ctrl_Usuario ctrl_usuario = new Controles.Ctrl_Usuario();
			Entidades.Usuario usuario = ctrl_usuario.autenticar(conn, nombre_usuario, contrasena);

			Gson gson = new GsonBuilder().serializeNulls().create();
			resultado = Response.ok(gson.toJson(usuario), MediaType.APPLICATION_JSON).build();

			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			String mensaje = "PROYECTO: BITNET-JERSEY-REST-API ==> CLASE: " + this.getClass().getName()
					+ " ==> METODO: autenticar()" + " ERROR: " + ex.toString();

			resultado = Response.status(Status.NOT_FOUND).entity(mensaje).build();

			System.out.println("PROYECTO: BITNET-JERSEY-REST-API ==> CLASE: " + this.getClass().getName()
					+ " ==> METODO: autenticar()" + " ERROR: " + ex.toString());
		} finally {
			try {
				if (conn.isClosed()) {
					conn.close();
					conn = null;
				}
			} catch (Exception ex) {
				System.out.println("PROYECTO: BITNET-JERSEY-REST-API ==> CLASE: " + this.getClass().getName()
						+ " ==> METODO: autenticar()-Finally" + " ERROR: " + ex.toString());
			}
		}

		return resultado;
	}

}
