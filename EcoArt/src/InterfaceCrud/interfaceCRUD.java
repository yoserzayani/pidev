/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceCrud;

import java.util.List;
//import product.product;

/**
 *
 * @author User
 */
public interface interfaceCRUD <T> {
     public int ajouter(T t);
     public T chercher(T t);
     public int supprimer(T t);
     public T modifier(T t ,T nouveau);
      public List<T>getAllProducts();
}
