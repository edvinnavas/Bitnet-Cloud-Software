package ClientesRest;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.Serializable;
import org.glassfish.jersey.client.ClientConfig;

public class ClienteRestApi implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String BASE_URI = "http://BITNET-REST-API:8080/bitnet/";
    private ClientConfig clientConfig;
    private Client client;

    public ClienteRestApi() {
        try {
            this.clientConfig = new ClientConfig();
            this.clientConfig.register(String.class);
            this.client = ClientBuilder.newClient(this.clientConfig);
        } catch (Exception ex) {
            System.out.println("PROYECTO: bitnet-web-app, CLASE: " + this.getClass().getName() + ", METODO: ClienteRestApi(), ERRROR: " + ex.toString());
        }
    }

    public String autenticar(String usuario, String contrasena) {
        String resultado = "";

        try {
            WebTarget webTarget = this.client.target(BASE_URI).path("autenticar/" + usuario + "/" + contrasena);
            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.get();
            // System.out.println("CONEXION JDE-REST-API: " + response.getStatus());
            if (response.getStatus() == 200) {
                resultado = response.readEntity(String.class);
            } else {
                resultado = response.getStatus() + ": " + response.getStatusInfo();
            }
        } catch (Exception ex) {
            System.out.println("PROYECTO: bitnet-web-app, CLASE: " + this.getClass().getName() + ", METODO: autenticar(), ERRROR: " + ex.toString());
        }

        return resultado;
    }

}
