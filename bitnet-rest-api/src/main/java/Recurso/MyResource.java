package Recurso;

import Control.Usuario;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.io.Serializable;

@Path("bitnet")
public class MyResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @GET
    @Path("autenticar/{usuario}/{contrasena}")
    @Produces(MediaType.APPLICATION_JSON)
    public String autenticar(
            @PathParam("usuario") String usuario, 
            @PathParam("contrasena") String contrasena) {
        
        String resultado;

        try {
            Usuario ctrl_usuario = new Usuario();
            resultado = ctrl_usuario.autenticar(usuario, contrasena);
        } catch (Exception ex) {
            resultado = "PROYECTO: bitnet-rest-api, CLASE: " + this.getClass().getName() + ", METODO: autenticar(), ERRROR: " + ex.toString();
        }

        return resultado;
    }

}
