/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import cEntity.saldoJPA;
import javax.ejb.Stateless;
import mapeo.Saldos;
import org.modelmapper.ModelMapper;
import pojos.pojosaldo;

/**
 *
 * @author steve
 */
@Stateless
public class saldo implements saldoLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void agregarSaldo(pojosaldo s) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            Saldos saldo = modelMapper.map(s, Saldos.class);
            saldoJPA jpa= new saldoJPA();
            jpa.create(saldo);
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    @Override
    public void editarSaldo(pojosaldo s) {
        ModelMapper modelMapper = new ModelMapper();
        Saldos u = modelMapper.map(s, Saldos.class);
        try{
            Saldos saldo;
            saldoJPA jpa=new saldoJPA();
            saldo=jpa.getEntityManager().getReference(Saldos.class, u.getIdSaldo());
            if(u.getIdSaldo()!=null){
                saldo.setIdSaldo(u.getIdSaldo());
            }
            if(u.getIdUsuario().getIdUsuario().equals(saldo.getIdUsuario().getIdUsuario())){
                saldo.setIdUsuario(u.getIdUsuario());
            }
            if(!s.getSaldo_actual().isNaN()){
                saldo.setSaldoActual(u.getSaldoActual());
            }
            if(!s.getSaldo_temporal().isNaN()){
                saldo.setSaldoTemporal(u.getSaldoTemporal());
            }
            jpa.edit(saldo);
        }catch(Exception ex){
            System.out.println("Ha ocurrido un error");
        }
    }

    @Override
    public void eliminarSaldo(int i) {
        try{
            saldoJPA jpa=new saldoJPA();
            jpa.destroy(i);
        }catch(Exception ex){
            System.out.println("Ha ocurrido un error");
        }
    }

}
