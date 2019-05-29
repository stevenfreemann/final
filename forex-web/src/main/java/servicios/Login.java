/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import bean.divisas;
import bean.divisasLocal;
import bean.saldo;
import bean.saldoLocal;
import bean.usuarios;
import bean.usuariosLocal;
import javax.ejb.EJB;
import pojos.pojousuario;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pojos.pojodivisa;
import utilitarios.token;

/**
 *
 * @author steve
 */
@javax.enterprise.context.RequestScoped
@Path("login")
public class Login {
    @EJB
    usuariosLocal user;
    
    @EJB
    saldoLocal saldo;
    
    @EJB
    divisasLocal divisa;
     @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{user},{pass}")    
    public Response validarLogin(@PathParam("user") String user, @PathParam("pass") String pass) {
        pojousuario u=new pojousuario();
        //u=pojousuario.validarLogin(user, pass);
        if(u!=null) {
                JsonObject json = Json.createObjectBuilder()
                                      .add("token-auto", token.generarToken(u.getNombre(),u.getCorreo()))
                                      .build();                
                //return Response.status(Response.Status.OK).entity(json).build();
                System.out.println(json);
                return Response.ok(json).build();
        } else {
                JsonObject json = Json.createObjectBuilder()
                                      .add("mensaje", "credenciales incorrectas")
                                      .build();
                return Response.status(Response.Status.UNAUTHORIZED).entity(json).build();
                
        }
    }
    
    @GET
    //@Produces(MediaType.APPLICATION_JSON)
    @Path("/{token}")    
    public Response desencriptar(@PathParam("token") String tok) {
        
        //Token.imprimirEstructura(token);
        token.imprimirBody(tok);
        return Response.ok().build();                

    }
    
    @GET
    @Path("registro")    
    public Response registrar() {
        pojousuario p=new pojousuario();
        p.setNombre("wilson");
        p.setApellido("rodriguez");
        p.setCedula(199299);
        p.setCorreo("weqwe@ddf.com");
        p.setId_rol(1);
        p.setFoto("asdasdaasd");
        //p.setIdUsuario(1);
        
        
        user.agregarUsuario(p);
        
      /*  PojoSaldo s=new PojoSaldo();
        s.setIdSaldo(1);
        s.setIdUsuario(1);
        s.setSaldoActual(20.2D);
        s.setSaldoTemporal(50.5D);        
        saldo.editarSaldo(s);
        
      /*  pojodivisa d=new pojodivisa();        
        d.setIdDivisa(2);
        d.setValor(1.7D);
        d.setDivisa("EUR/USD");
        divisa.editarDivisa(d);*/
        
        return Response.ok().build();            
    }
}
