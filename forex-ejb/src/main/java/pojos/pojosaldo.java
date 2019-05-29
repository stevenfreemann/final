/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

/**
 *
 * @author steve
 */
public class pojosaldo {
    private int id_saldo;
    private Double saldo_actual;
    private Double saldo_temporal;

    public pojosaldo() {
    }

    public int getId_saldo() {
        return id_saldo;
    }

    public void setId_saldo(int id_saldo) {
        this.id_saldo = id_saldo;
    }

    public Double getSaldo_actual() {
        return saldo_actual;
    }

    public void setSaldo_actual(Double saldo_actual) {
        this.saldo_actual = saldo_actual;
    }

    public Double getSaldo_temporal() {
        return saldo_temporal;
    }

    public void setSaldo_temporal(Double saldo_temporal) {
        this.saldo_temporal = saldo_temporal;
    }
    
}
