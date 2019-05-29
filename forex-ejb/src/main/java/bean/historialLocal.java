/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.ejb.Local;
import pojos.pojohistorial;

/**
 *
 * @author steve
 */
@Local
public interface historialLocal {
    public void agregarHistorial(pojohistorial h);
    public void editarHistorial(pojohistorial h);
    public void eliminarHistorial(int i);
}
