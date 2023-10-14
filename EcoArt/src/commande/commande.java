/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commande;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author User
 */
public class commande {
    long id_c;
    long numC;
    int id_client;
    String adresse;
    Date date;
    int numTel;
    String email; 

    public  commande(long id_c, long numC, int id_client, String adresse, Date date, int numTel, String email) {
        this.id_c = id_c;
        this.numC = numC;
        this.id_client = id_client;
        this.adresse = adresse;
        this.date = date;
        this.numTel = numTel;
        this.email = email;
    }

   

    public commande() {
    }

    public long getId_c() {
        return id_c;
    }

    public void setId_c(long id_c) {
        this.id_c = id_c;
    }
    

    public long getNumC() {
        return numC;
    }

    public void setNumC(long numC) {
        this.numC = numC;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "commande{" + "numC=" + numC + ", id_client=" + id_client + ", adresse=" + adresse + ", date=" + date + ", numTel=" + numTel + ", email=" + email + '}';
    }

   
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final commande other = (commande) obj;
        if (this.id_c != other.id_c) {
            return false;
        }
        if (this.numC != other.numC) {
            return false;
        }
        if (this.id_client != other.id_client) {
            return false;
        }
        if (this.numTel != other.numTel) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

   
     
    
    
    
}
