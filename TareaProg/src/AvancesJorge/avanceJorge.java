/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AvancesJorge;

import java.util.Date;

/**
 *
 * @author jorge
 */
public class avanceJorge {
     public static void main(String[] args) {
//solo rellene los constructores y puse voids a los metodos para que no me lanzara error, despues los cambiamos(tambien hay que hacer getters y setters)
//el IVA en Chile es de 19%      
    }
    
}
class Cliente{
    private String nombre;
    private String rut;
    
    public Cliente(String a, String b){
        nombre=a;
        rut=b;
    
    }

}

class OrdenCompra{
    private Date fecha;
    private String estado;
    
    public OrdenCompra(Date a, String b){
        fecha=a;
        estado=b;
        
    }
    
    public void calcPrecioSinIVA(){}
    
    public void calcIVA(){}
    
    public void calcPrecio(){}
    
    public void calcPeso(){}
}

class DetalleOrden{
    private int cantidad;
    
    public DetalleOrden(int a){
        cantidad=a;
    
    }
    
    public void calcPrecio(){};
    
    public void calcPrecioSinIVA(){}
    
    public void calcPeso(){}
    
}

class Articulo{
    private float peso;
    private String nombre;
    private String descripcion;
    
    public Articulo(float a, String b, String c){
        peso=a;
        nombre=b;
        descripcion=c;
    
    }

}

class Direccion{
    private String direccion;

}

class DocTributario{
   private String numero;
   private String rut;
   private Date fecha;
   
   public DocTributario(String a, String b, Date c){
       numero=a;
       rut=b;
       fecha=c;
   
   }
   
}

class Boleta extends DocTributario{
    public Boleta(String a, String b, Date c){
        super(a, b, c);
    
    }

}

class Factura extends DocTributario{
    public Factura(String a, String b, Date c){
        super(a, b, c);
    
    }

}

class Pago{
    private float monto;
    private Date fecha;
    
    public Pago(float a, Date b){
        monto=a;
        fecha=b;
    
    }

}

class Efectivo extends Pago{
    public Efectivo(float a, Date b){
        super(a, b);
    
    }
    
    public void calcDevolucion(){};

}

class Transferencia extends Pago{
    private String banco;
    private String numCuenta;
    
    public Transferencia(float a, Date b){
        super(a, b);
    
    }

}

class Tarjeta extends Pago{
    private String tipo;
    private String numTransaccion;
    
    public Tarjeta(float a, Date b){
        super(a, b);
    
    }

}
