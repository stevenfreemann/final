/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import cEntity.historialJPA;
import javax.ejb.Stateless;
import mapeo.Historial;
import org.modelmapper.ModelMapper;
import pojos.pojohistorial;

/**
 *
 * @author steve
 */
@Stateless
public class historial implements historialLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void agregarHistorial(pojohistorial h) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            Historial s = modelMapper.map(h, Historial.class);
            historialJPA jpa= new historialJPA();
            jpa.create(s);
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    @Override
    public void editarHistorial(pojohistorial h) {
     ModelMapper modelMapper = new ModelMapper();
        Historial hi = modelMapper.map(h, Historial.class);
        try{
            Historial historial;
            historialJPA jpa=new historialJPA();
            historial=jpa.getEntityManager().getReference(Historial.class, hi.getIdHistorial());
            if(hi.getIdHistorial()!=null){
                historial.setIdHistorial(hi.getIdHistorial());
            }
            if(hi.getDivisa()!=null){
                historial.setDivisa(hi.getDivisa());
            }
            if(!hi.getValor().isNaN()){
                historial.setValor(hi.getValor());
            }
            if(hi.getTipoTransaccion()!=null){
                historial.setTipoTransaccion(hi.getTipoTransaccion());
            }
            if(!hi.getFechaAbrio().equals(null)){
                historial.setFechaAbrio(hi.getFechaAbrio());
            }
            if(!hi.getFechaCerro().equals(null)){
                historial.setFechaCerro(hi.getFechaCerro());
            }
            if(!hi.getPuntos().isNaN()){
                historial.setValor(hi.getPuntos());
            }
            if(hi.getIdUsuario().getIdUsuario()!=null){
                historial.setIdUsuario(hi.getIdUsuario());
            }
            jpa.edit(historial);
        }catch(Exception ex){
            System.out.println("Ha ocurrido un error");
        }
    }

    @Override
    public void eliminarHistorial(int i) {
        try{
            historialJPA jpa=new historialJPA();
            jpa.destroy(i);
        }catch(Exception ex){
            System.out.println("Ha ocurrido un error");
        }
    }
}
