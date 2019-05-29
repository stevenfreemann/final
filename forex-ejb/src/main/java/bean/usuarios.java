/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import cEntity.usuarioJPA;
import javax.ejb.Stateless;
import mapeo.Usuarios;
import org.modelmapper.ModelMapper;
import pojos.pojousuario;

/**
 *
 * @author steve
 */
@Stateless
public class usuarios implements usuariosLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void agregarUsuario(pojousuario p) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            Usuarios u = modelMapper.map(p, Usuarios.class);
            usuarioJPA jpa= new  usuarioJPA();
            jpa.create(u);
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    @Override
    public Usuarios validarLogin(String user, String contrasena) {
        usuarioJPA jpa= new usuarioJPA();
        Usuarios u=new Usuarios();
        u=jpa.validarLogin(user, contrasena);
        if(u!=null){
            return u;
        }else
            return null;
    }

    @Override
    public void editarUsuario(pojousuario p) {
        ModelMapper modelMapper = new ModelMapper();
        Usuarios u = modelMapper.map(p, Usuarios.class);
        try{
            Usuarios user;
            usuarioJPA jpa=new usuarioJPA();
            user=jpa.getEntityManager().getReference(Usuarios.class, u.getIdUsuario());
            if(u.getIdUsuario()!=null){
                user.setIdUsuario(u.getIdUsuario());
            }
            if(u.getNombre()!=null){
                user.setNombre(u.getNombre());
            }
            if(u.getNombre()!=null){
                user.setNombre(u.getNombre());
            }
            if(u.getClave()!=null){
                user.setClave(u.getClave());
            }
            if(u.getCorreo()!=null){
                user.setCorreo(u.getCorreo());
            }
            if(u.getEstado()!=user.getEstado()){
                user.setEstado(u.getEstado());
            }
            if(u.getHistorialList()!=null){
                user.setHistorialList(u.getHistorialList());
            }
            if(u.getSaldosList()!=null){
                user.setSaldosList(u.getSaldosList());
            }
            if(u.getToken()!=null){
                user.setToken(u.getToken());
            }
            if(u.getFoto()!=null){
                user.setFoto(u.getFoto());
            }
            if(user.getIdRol()!=u.getIdRol()){
                user.setIdRol(u.getIdRol());
            }            
            jpa.edit(user);
        }catch(Exception ex){
            System.out.println("Ha ocurrido un error");
        }
    }

    @Override
    public void eliminarUsuario(int i) {
        try{
            usuarioJPA jpa=new usuarioJPA();
            jpa.destroy(i);
        }catch(Exception ex){
            System.out.println("Ha ocurrido un error");
        }
    }
}
