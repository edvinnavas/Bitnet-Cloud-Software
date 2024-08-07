package Controles;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Ctrl_Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    public Ctrl_Usuario() {

    }

    public List<Entidades.Usuario> obtener_lista(Connection conn) {
        List<Entidades.Usuario> resultado = new ArrayList<>();

        try {
            String sql = "SELECT "
                    + "A.ID_USUARIO, "
                    + "A.NOMBRE_COMPLETO, "
                    + "A.NOMBRE_USUARIO, "
                    + "A.CONTRASENA, "
                    + "A.CORREO_ELECTRONICO, "
                    + "A.ACTIVO, "
                    + "A.AUTENTICACION, "
                    + "A.DESCRIPCION, "
                    + "A.ID_ROL, "
                    + "A.USUARIO_M, "
                    + "A.FECHA_HORA "
                    + "FROM "
                    + "USUARIO A";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Entidades.Usuario usuario = new Entidades.Usuario();
                usuario.setId_usuario(rs.getLong(1));
                usuario.setNombre_completo(rs.getString(2));
                usuario.setNombre_usuario(rs.getString(3));
                usuario.setConstrasena("**********");
                usuario.setCorreo_electronico(rs.getString(5));
                usuario.setActivo(rs.getInt(6));
                usuario.setAutenticacion(rs.getString(7));
                usuario.setDescripcion(rs.getString(8));
                usuario.setRol(new Ctrl_Rol().obtener_id(conn, rs.getLong(9)));
                usuario.setUsuario_m(rs.getString(10));
                usuario.setFecha_hora(rs.getDate(11));

                resultado.add(usuario);
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            System.out.println("PROYECTO: BITNET-JERSEY-REST-API ==> CLASE: " + this.getClass().getName()
                    + " ==> METODO: obtener_lista()" + " ERROR: " + ex.toString());
        }

        return resultado;
    }

    public Entidades.Usuario obtener_id(Connection conn, Long id_usuario) {
        Entidades.Usuario resultado = new Entidades.Usuario();

        try {
            String sql = "SELECT "
                    + "A.ID_USUARIO, "
                    + "A.NOMBRE_COMPLETO, "
                    + "A.NOMBRE_USUARIO, "
                    + "A.CONTRASENA, "
                    + "A.CORREO_ELECTRONICO, "
                    + "A.ACTIVO, "
                    + "A.AUTENTICACION, "
                    + "A.DESCRIPCION, "
                    + "A.ID_ROL, "
                    + "A.USUARIO_M, "
                    + "A.FECHA_HORA "
                    + "FROM "
                    + "USUARIO A "
                    + "WHERE "
                    + "A.ID_USUARIO=" + id_usuario;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                resultado.setId_usuario(rs.getLong(1));
                resultado.setNombre_completo(rs.getString(2));
                resultado.setNombre_usuario(rs.getString(3));
                resultado.setConstrasena("**********");
                resultado.setCorreo_electronico(rs.getString(5));
                resultado.setActivo(rs.getInt(6));
                resultado.setAutenticacion(rs.getString(7));
                resultado.setDescripcion(rs.getString(8));
                resultado.setRol(new Ctrl_Rol().obtener_id(conn, rs.getLong(9)));
                resultado.setUsuario_m(rs.getString(10));
                resultado.setFecha_hora(rs.getDate(11));
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            System.out.println("PROYECTO: BITNET-JERSEY-REST-API ==> CLASE: " + this.getClass().getName()
                    + " ==> METODO: obtener_lista()" + " ERROR: " + ex.toString());
        }

        return resultado;
    }

    public Entidades.Usuario crear(Connection conn, String jsonString) {
        Entidades.Usuario resultado = new Entidades.Usuario();

        try {
            Type ObjectType = new TypeToken<Entidades.Usuario>() {
            }.getType();
            resultado = new Gson().fromJson(jsonString, ObjectType);

            Ctrl_Base_Datos ctrl_base_datos = new Ctrl_Base_Datos();

            Long id_usuario = ctrl_base_datos
                    .ObtenerLong("SELECT IFNULL(MAX(A.ID_USUARIO),0) + 1 MAXIMO FROM USUARIO A", conn);
            resultado.setId_usuario(id_usuario);

            resultado.setFecha_hora(new Date());

            SimpleDateFormat db_formato_fecha_hora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String sql = "INSERT INTO USUARIO ("
                    + "ID_USUARIO,"
                    + "NOMBRE_COMPLETO,"
                    + "NOMBRE_USUARIO,"
                    + "CONTRASENA,"
                    + "CORREO_ELECTRONICO,"
                    + "ACTIVO,"
                    + "AUTENTICACION,"
                    + "DESCRIPCION,"
                    + "ID_ROL,"
                    + "USUARIO_M,"
                    + "FECHA_HORA) VALUES ("
                    + resultado.getId_usuario() + ",'"
                    + resultado.getNombre_completo() + "','"
                    + resultado.getNombre_usuario() + "','"
                    + DigestUtils.sha256Hex(resultado.getConstrasena()) + "','"
                    + resultado.getCorreo_electronico() + "',"
                    + resultado.getActivo() + ",'"
                    + resultado.getAutenticacion() + "','"
                    + resultado.getDescripcion() + "',"
                    + resultado.getRol().getId_rol() + ",'"
                    + resultado.getUsuario_m() + "','"
                    + db_formato_fecha_hora.format(resultado.getFecha_hora()) + "')";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception ex) {
            System.out.println("PROYECTO: BITNET-JERSEY-REST-API ==> CLASE: " + this.getClass().getName()
                    + " ==> METODO: crear()" + " ERROR: " + ex.toString());
        }

        return resultado;
    }

    public Entidades.Usuario modificar(Connection conn, Long id_usuario, String jsonString) {
        Entidades.Usuario resultado = new Entidades.Usuario();

        try {
            Type ObjectType = new TypeToken<Entidades.Usuario>() {
            }.getType();
            resultado = new Gson().fromJson(jsonString, ObjectType);

            resultado.setId_usuario(id_usuario);

            resultado.setFecha_hora(new Date());

            SimpleDateFormat db_formato_fecha_hora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String sql = "UPDATE USUARIO SET "
                    + "NOMBRE_COMPLETO='" + resultado.getNombre_completo() + "', "
                    + "NOMBRE_USUARIO='" + resultado.getNombre_usuario() + "', "
                    + "CORREO_ELECTRONICO='" + resultado.getCorreo_electronico() + "', "
                    + "ACTIVO=" + resultado.getActivo() + ", "
                    + "AUTENTICACION='" + resultado.getAutenticacion() + "', "
                    + "DESCRIPCION='" + resultado.getDescripcion() + "', "
                    + "ID_ROL=" + resultado.getRol().getId_rol() + ", "
                    + "USUARIO_M='" + resultado.getUsuario_m() + "', "
                    + "FECHA_HORA='" + db_formato_fecha_hora.format(resultado.getFecha_hora()) + "' "
                    + "WHERE "
                    + "ID_USUARIO=" + id_usuario;
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception ex) {
            System.out.println("PROYECTO: BITNET-JERSEY-REST-API ==> CLASE: " + this.getClass().getName()
                    + " ==> METODO: modificar()" + " ERROR: " + ex.toString());
        }

        return resultado;
    }

    public Entidades.Usuario eliminar(Connection conn, Long id_usuario) {
        Entidades.Usuario resultado = new Entidades.Usuario();

        try {
            String sql = "SELECT "
                    + "A.ID_USUARIO, "
                    + "A.NOMBRE_COMPLETO, "
                    + "A.NOMBRE_USUARIO, "
                    + "A.CONTRASENA, "
                    + "A.CORREO_ELECTRONICO, "
                    + "A.ACTIVO, "
                    + "A.AUTENTICACION, "
                    + "A.DESCRIPCION, "
                    + "A.ID_ROL, "
                    + "A.USUARIO_M, "
                    + "A.FECHA_HORA "
                    + "FROM "
                    + "USUARIO A "
                    + "WHERE "
                    + "A.ID_USUARIO=" + id_usuario;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                resultado.setId_usuario(rs.getLong(1));
                resultado.setNombre_completo(rs.getString(2));
                resultado.setNombre_usuario(rs.getString(3));
                resultado.setConstrasena("**********");
                resultado.setCorreo_electronico(rs.getString(5));
                resultado.setActivo(rs.getInt(6));
                resultado.setAutenticacion(rs.getString(7));
                resultado.setDescripcion(rs.getString(8));
                resultado.setRol(new Ctrl_Rol().obtener_id(conn, rs.getLong(9)));
                resultado.setUsuario_m(rs.getString(10));
                resultado.setFecha_hora(rs.getDate(11));
            }
            rs.close();
            stmt.close();

            sql = "DELETE FROM USUARIO WHERE ID_USUARIO=" + id_usuario;
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception ex) {
            System.out.println("PROYECTO: BITNET-JERSEY-REST-API ==> CLASE: " + this.getClass().getName()
                    + " ==> METODO: eliminar()" + " ERROR: " + ex.toString());
        }

        return resultado;
    }

    public String cambiar_contrasena(Connection conn, Long id_usuario, String contrasena) {
        String resultado = "";

        try {
            String sql = "UPDATE USUARIO SET "
                    + "CONTRASENA='" + DigestUtils.sha256Hex(contrasena) + "' "
                    + "WHERE "
                    + "ID_USUARIO=" + id_usuario;
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();

            resultado = "Contraseña actualizada correctamente.";
        } catch (Exception ex) {
            resultado = "PROYECTO: BITNET-JERSEY-REST-API ==> CLASE: " + this.getClass().getName()
                    + " ==> METODO: cambiar_contrasena()" + " ERROR: " + ex.toString();
            System.out.println("PROYECTO: BITNET-JERSEY-REST-API ==> CLASE: " + this.getClass().getName()
                    + " ==> METODO: cambiar_contrasena()" + " ERROR: " + ex.toString());
        }

        return resultado;
    }

    public Entidades.Usuario autenticar(Connection conn, String nombre_usuario, String contrasena) {
        Entidades.Usuario resultado = new Entidades.Usuario();

        try {
            Gson gson = new GsonBuilder().serializeNulls().create();

            String sql = "SELECT "
                    + "A.ID_USUARIO, "
                    + "A.NOMBRE_COMPLETO, "
                    + "A.NOMBRE_USUARIO, "
                    + "A.CONTRASENA, "
                    + "A.CORREO_ELECTRONICO, "
                    + "A.ACTIVO, "
                    + "A.AUTENTICACION, "
                    + "A.DESCRIPCION, "
                    + "A.ID_ROL, "
                    + "A.USUARIO_M, "
                    + "A.FECHA_HORA "
                    + "FROM "
                    + "USUARIO A "
                    + "WHERE "
                    + "A.NOMBRE_USUARIO='" + nombre_usuario + "' AND "
                    + "A.CONTRASENA='" + DigestUtils.sha256Hex(contrasena) + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                resultado.setId_usuario(rs.getLong(1));
                resultado.setNombre_completo(rs.getString(2));
                resultado.setNombre_usuario(rs.getString(3));
                resultado.setConstrasena("**********");
                resultado.setCorreo_electronico(rs.getString(5));
                resultado.setActivo(rs.getInt(6));
                resultado.setAutenticacion(rs.getString(7));
                resultado.setDescripcion(rs.getString(8));
                resultado.setRol(new Ctrl_Rol().obtener_id(conn, rs.getLong(9)));
                resultado.setUsuario_m(rs.getString(10));
                resultado.setFecha_hora(rs.getDate(11));

                // REGISTRAR EVENTO.
                Entidades.Log_Evento log_evento = new Entidades.Log_Evento();
                log_evento.setId_evento(null);
                log_evento.setTipo_evento(new Controles.Ctrl_Tipo_Evento().obtener_id(conn, Long.valueOf("1")));
                log_evento.setUsuario(resultado);
                log_evento.setDescripcion("Usuario " + resultado.getNombre_usuario() + " se autentico en la sistema.");
                log_evento.setFecha_hora(null);
                log_evento.getTipo_evento().setFecha_hora(null);
                log_evento.getUsuario().getRol().setFecha_hora(null);
                log_evento.getUsuario().setFecha_hora(null);
                log_evento = new Controles.Ctrl_Log_Evento().crear(conn, gson.toJson(log_evento));
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            System.out.println("PROYECTO: BITNET-JERSEY-REST-API ==> CLASE: " + this.getClass().getName()
                    + " ==> METODO: autenticar()" + " ERROR: " + ex.toString());
        }

        return resultado;
    }

}
