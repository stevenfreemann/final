/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import cEntity.divisaJPA;
import javax.ejb.Stateless;
import mapeo.Divisas;
import org.modelmapper.ModelMapper;
import pojos.pojodivisa;

/**
 *
 * @author steve
 */
@Stateless
public class divisas implements divisasLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void agregarDivisa(pojodivisa d) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            Divisas s = modelMapper.map(d, Divisas.class);
            divisaJPA jpa= new divisaJPA();
            jpa.create(s);
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    @Override
    public void editarDivisa(pojodivisa d) {
        ModelMapper modelMapper = new ModelMapper();
        Divisas di = modelMapper.map(d, Divisas.class);
        try{
            Divisas divisa;
            divisaJPA jpa=new divisaJPA();
            divisa=jpa.getEntityManager().getReference(Divisas.class, di.getIdDivisa());
            if(di.getDivisa()!=null){
                divisa.setDivisa(di.getDivisa());
            }
            if(di.getIdDivisa()!=null){
                divisa.setIdDivisa(di.getIdDivisa());
            }
            if(!d.getValor().isNaN()){
                divisa.setValor(di.getValor());
            }            
            jpa.edit(divisa);
        }catch(Exception ex){
            System.out.println("Ha ocurrido un error");
        }
    }

    @Override
    public void eliminarDivisa(int i) {
        try{
            divisaJPA jpa=new divisaJPA();
            jpa.destroy(i);
        }catch(Exception ex){
            System.out.println("Ha ocurrido un error");
        }
    }
}
