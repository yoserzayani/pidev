/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LineOrder;

import java.util.Objects;

/**
 *
 * @author User
 */
public class LineOrder {
    
    
private int orderId;
private int id_pdts;
private String productName;
private int quantite;
private float subTotal;
private float remise;

    public LineOrder(int orderId, int id_pdts, String productName, int quantite, float subTotal, float remise) {
        this.orderId = orderId;
        this.id_pdts = id_pdts;
        this.productName = productName;
        this.quantite = quantite;
        this.subTotal = subTotal;
        this.remise = remise;
    }

    public LineOrder() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getId_pdts() {
        return id_pdts;
    }

    public void setId_pdts(int id_pdts) {
        this.id_pdts = id_pdts;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public float getRemise() {
        return remise;
    }

    public void setRemise(float remise) {
        this.remise = remise;
    }

    
}