package Entidad;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id_usuario;
    private String nombre_completo;
    private String nombre_usuario;
    private String contrasena;
    private String correo_electronico;
    private Integer activo;
    private String descripcion;

    public Usuario(Long id_usuario, String nombre_completo, String nombre_usuario, String contrasena, String correo_electronico, Integer activo, String descripcion) {
        this.id_usuario = id_usuario;
        this.nombre_completo = nombre_completo;
        this.nombre_usuario = nombre_usuario;
        this.contrasena = contrasena;
        this.correo_electronico = correo_electronico;
        this.activo = activo;
        this.descripcion = descripcion;
    }

    public Usuario() {
    }

    @Override
    public String toString() {
        return "Usuario{" + "id_usuario=" + id_usuario + ", nombre_completo=" + nombre_completo + ", nombre_usuario=" + nombre_usuario + ", contrasena=" + contrasena + ", correo_electronico=" + correo_electronico + ", activo=" + activo + ", descripcion=" + descripcion + '}';
    }
    
}
