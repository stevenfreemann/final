/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.ejb.Local;
import pojos.pojosaldo;

/**
 *
 * @author steve
 */
@Local
public interface saldoLocal {
    public void agregarSaldo(pojosaldo s);
    public void editarSaldo(pojosaldo s);
    public void eliminarSaldo(int i);
}
