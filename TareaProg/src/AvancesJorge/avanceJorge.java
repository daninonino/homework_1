package AvancesJorge;

import java.util.ArrayList;
import java.util.Date;

public class avanceJorge {
     public static void main(String[] args) {
        Date a = new Date(2002,06, 05);
        Date b = new Date(2022,10, 05);
        
        //DetalleOrden c= new DetalleOrden(5);
        ArrayList<Articulo> articulo = new ArrayList<Articulo>(3);
        articulo.add(new Articulo(1.50f,"Papas Lays","Papas fritas corte Americano",1.500f));
        articulo.add(new Articulo(1.50f,"Papas Lays","Papas fritas corte Americano",1.500f));
        articulo.add(new Articulo(1.50f,"Papas Lays","Papas fritas corte Americano",1.500f));
        
        ArrayList<Articulo> articulo2 = new ArrayList<Articulo>(2);
        articulo2.add(new Articulo(1.50f,"Papas Lays","Papas fritas corte Americano",1.500f));
        articulo2.add(new Articulo(1.50f,"Papas Lays","Papas fritas corte Americano",1.500f));
        
        //Preguntar por fecha, estado y como funciona DetalleOrden + articulo.
        DetalleOrden d= new DetalleOrden(3, articulo);
        DetalleOrden c= new DetalleOrden(2, articulo2);
        OrdenCompra lista = new OrdenCompra(a,"Hola",c); 
        OrdenCompra lista2=new OrdenCompra(b, "Chao", d);
        
        Cliente Jorge= new Cliente("Jorge", "21087983-8", new Direccion("Las Palmeras 602"));
        Cliente Dani= new Cliente("Dani", "21289833-3", new Direccion("Fragata María Isabel 165-b"));
        Efectivo dinero1 =new Efectivo(5.7f, a, lista);
        Efectivo dinero15=new Efectivo(5.7f, b, lista);
        Pago dinero2=new Transferencia(5.7f, a, lista, "Banco Falabella", "12345678");
        Pago dinero3 = new Tarjeta(5.7f, a, lista, "debito", "12345678");
         
        System.out.println("ORDEN 1:"+"\n"+"Precio sin IVA: "+ lista.calcPrecioSinIVA());
        System.out.println("Precio con IVA: "+ lista.calcPrecio());
        System.out.println("IVA total: "+ lista.calcIVA());
        System.out.println("Peso total: "+ lista.calcPeso());
        System.out.println("Devolucion: "+dinero1.calcDevolucion());
        
        
        System.out.println("ORDEN 2:"+"\n"+"Precio sin IVA: "+ lista2.calcPrecioSinIVA());
        System.out.println("Precio con IVA: "+ lista2.calcPrecio());
        System.out.println("IVA total: "+ lista2.calcIVA());
        System.out.println("Peso total: "+ lista2.calcPeso());
        System.out.println("Devolucion: "+dinero15.calcDevolucion());
        
    }
    
}
class Cliente{
    private String nombre;
    private String rut;
    public Direccion direccion;
    public OrdenCompra ordenCompra[];
    public Cliente(String a, String b, Direccion d){
        nombre=a;
        rut=b;
        direccion = d;
    
    }
    

}

class OrdenCompra{
    private Date fecha;
    private String estado;
    private Cliente cliente;
    public DetalleOrden detOrden;
    
    public OrdenCompra(Date a, String b, DetalleOrden d){
        fecha=a;
        estado=b;
        detOrden= d;
        
    }
    
    public float calcPrecioSinIVA(){
      return detOrden.calcPrecioSinIVA();  
    
    }
    
    public float calcIVA(){
        return detOrden.calcIVA();
    }
    
    public float calcPrecio(){
        return detOrden.calcPrecio();
    }   
    public float calcPeso(){
        return detOrden.calcPeso();
    }
}

class DetalleOrden{
    private int cantidad;
    private ArrayList<Articulo> list;
    
    public DetalleOrden(int a, ArrayList<Articulo> b){
        cantidad = a;
        list = b;
    }
    
    public float calcPrecio(){
        float precio = 0;
        for(int i = 0; i < cantidad; i++){
            precio += list.get(i).getPrecio()*1.19;
        }
        return precio;
    }
    
    public float calcPrecioSinIVA(){
        float precio = 0;
        for(int i = 0; i < cantidad; i++){
            precio += list.get(i).getPrecio();
        }
        return precio;
    
    }public float calcIVA(){
                float precio = 0;
        for(int i = 0; i < cantidad; i++){
            precio += list.get(i).getPrecio()*0.19;
        }
        return precio;
    
    }
    
    public float calcPeso(){
        float peso=0;
        for(int i = 0; i < cantidad; i++){
            peso += list.get(i).getPeso();
        }
        return peso;
   
    }
    
}

class Articulo{
    private float peso;
    private String nombre;
    private String descripcion;
    private float precio;
    
    public Articulo(float a, String b, String c, float d){
        peso=a;
        nombre=b;
        descripcion=c;
        precio=d;
    }
    
    public float getPrecio(){
        return precio;
    }
    
    public float getPeso(){
        return peso;
    }
    
    

}

class Direccion{
    private String direccion;
    private Cliente cliente[];
    private DocTributario docTributario[];
    public Direccion(String dir){
        direccion = dir;
    }
    
    public String getDireccion(){
        return direccion;
    }
}

class DocTributario{
   private String numero;
   private String rut;
   private Date fecha;
   private Direccion direccion;
   
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
    public String DocTributario(){
        return "boleta";
    }

}

class Factura extends DocTributario{
    public Factura(String a, String b, Date c){
        super(a, b, c);
    
    }
        public String DocTributario(){
        return "Factura";
    }

}

class Pago{
    private float monto;
    private Date fecha;
    public OrdenCompra ordCompra;
    
    public Pago(float a, Date b, OrdenCompra c){
        monto=a;
        fecha=b;
        ordCompra=c;
    
    }
    public float getMonto(){
//preguntar que pasa si monto es menor a precio
        return monto;
    }
    

}

class Efectivo extends Pago{
    public Efectivo(float a, Date b, OrdenCompra c){
        super(a, b, c);
    
    }
    
    public float calcDevolucion(){
       float devolucion=getMonto()-ordCompra.calcPrecio(); 
       return devolucion;
    }

}

class Transferencia extends Pago{
    private String banco;
    private String numCuenta;
    
    public Transferencia(float a, Date b, OrdenCompra c, String e, String f){
        super(a, b, c);
        banco=e;
        numCuenta=f;
    
    }

}

class Tarjeta extends Pago{
    private String tipo;
    private String numTransaccion;
    
    public Tarjeta(float a, Date b, OrdenCompra c, String d, String e){
        super(a, b, c);
        tipo=d;
        numTransaccion=e;
    }

}
