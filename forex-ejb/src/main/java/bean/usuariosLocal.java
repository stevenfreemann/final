/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.ejb.Local;
import mapeo.Usuarios;
import pojos.pojousuario;

/**
 *
 * @author steve
 */
@Local
public interface usuariosLocal {
    public void agregarUsuario(pojousuario p);
    public void editarUsuario(pojousuario p);
    public void eliminarUsuario(int i);
    public Usuarios validarLogin(String user, String contrasena);
    
}
