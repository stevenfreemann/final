/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.ejb.Local;
import pojos.pojodivisa;

/**
 *
 * @author steve
 */
@Local
public interface divisasLocal {
    public void agregarDivisa(pojodivisa d);
    public void editarDivisa(pojodivisa d);
    public void eliminarDivisa(int i);
}
