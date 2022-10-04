package AvancesJorge;

import java.util.ArrayList;
import java.util.Date;

public class avanceJorge {
     public static void main(String[] args) {
        Date a = new Date(2002,06, 05);
        Date b = new Date(2022,10, 05);
        
        ArrayList<Articulo> articulo = new ArrayList<Articulo>(3);
        articulo.add(new Articulo(1.50f,"Papas Lays","Papas fritas corte Americano",1.500f));
        articulo.add(new Articulo(1.50f,"Papas Lays","Papas fritas corte Americano",1.500f));
        articulo.add(new Articulo(1.50f,"Papas Lays","Papas fritas corte Americano",1.500f));
           
        DetalleOrden d = new DetalleOrden(3);
        d.setArticulo(articulo.get(0));
        d.setArticulo(articulo.get(2));
        d.setArticulo(articulo.get(1));
        DetalleOrden c = new DetalleOrden(2);
        c.setArticulo(articulo.get(1));
        c.setArticulo(articulo.get(0));
        OrdenCompra lista = new OrdenCompra(a,"pago efectuado"); 
        OrdenCompra lista2=new OrdenCompra(b, "pago pendiente");
        lista.setOrden(d);
        lista2.setOrden(c);
        
        Cliente Jorge= new Cliente("Jorge", "21087983-8", new Direccion("Las Palmeras 602"));
        Cliente Dani= new Cliente("Dani", "21289833-3", new Direccion("Fragata María Isabel 165-b"));
        lista.setCliente(Dani);
        lista2.setCliente(Jorge);
        
        Pago dinero1 = new Efectivo(1.4f, a);
        Pago dinero15= new Efectivo(5.7f, b);
        Pago dinero2 = new Transferencia(5.7f, a, "Banco Falabella", "12345678");
        Pago dinero3 = new Tarjeta(5.7f, a, "debito", "12345678");
        lista.addPago(dinero1);
        lista.addPago(dinero15);
        lista2.addPago(dinero2);
        lista2.addPago(dinero3);
        dinero1.setOrdenCompra(lista);
        dinero15.setOrdenCompra(lista);
        dinero2.setOrdenCompra(lista2);
        dinero3.setOrdenCompra(lista2);
        
        System.out.println("ORDEN 1:"+"\n"+"Precio sin IVA: "+ lista.calcPrecioSinIVA());
        System.out.println("Precio con IVA: "+ lista.calcPrecio());
        System.out.println("IVA total: "+ lista.calcIVA());
        System.out.println("Peso total: "+ lista.calcPeso());
        System.out.println("Devolucion: "+((Efectivo)lista.Pagos.get(0)).calcDevolucion());
        
        
        System.out.println("ORDEN 2:"+"\n"+"Precio sin IVA: "+ lista2.calcPrecioSinIVA());
        System.out.println("Precio con IVA: "+ lista2.calcPrecio());
        System.out.println("IVA total: "+ lista2.calcIVA());
        System.out.println("Peso total: "+ lista2.calcPeso());
        
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
    public ArrayList<DetalleOrden> detOrden;
    public ArrayList<Pago> Pagos;
    
    public OrdenCompra(Date a, String b){
        fecha = a;
        estado = b;
        detOrden = new ArrayList<DetalleOrden>() ;
        Pagos= new ArrayList<Pago>();
    }
    public void setOrden(DetalleOrden a){
        detOrden.add(a);
    }
    
    public void setCliente(Cliente x){
        this.cliente = x;
    }
    public void addDetalle(DetalleOrden a){
        detOrden.add(a);
    
    }
    public void addPago(Pago a){
        Pagos.add(a);
    }
    
    public float calcPrecioSinIVA(){
        float pSinIVA = 0;
        for (int i = 0; i < detOrden.size();i++){
            pSinIVA += detOrden.get(i).calcPrecioSinIVA();  
        }
        return pSinIVA;
    }
    
    public float calcIVA(){
        float pIVA=0;
        for(int i=0; i<detOrden.size();i++){
            pIVA += detOrden.get(i).calcIVA();
        }
        return pIVA;
    }
    
    public float calcPrecio(){
        float p=0;
        for(int i=0; i<detOrden.size();i++){
            p += detOrden.get(i).calcPrecio();
        }
        return p;
    }   
    public float calcPeso(){
        float pW=0;
        for(int i=0; i<detOrden.size();i++){
             pW+=detOrden.get(i).calcPeso();
        }
        return pW;
    }
}

class DetalleOrden{
    private int cantidad;
    private Articulo list;
    
    public DetalleOrden(int a){
        cantidad = a;
    }
    
    public void setArticulo(Articulo a){
        this.list=a;
    }
    public Articulo getArticulo(){
        return list;
    }
    
    public float calcPrecio(){
        float precio = 0;
        precio += (list.getPrecio() * cantidad)*1.19;
        return precio;
    }
    
    public float calcPrecioSinIVA(){
        float precio = 0;
        precio += list.getPrecio()*cantidad;
        return precio;
    
    }public float calcIVA(){
        float precio = 0;
        
        precio += (list.getPrecio()*cantidad)*0.19;
        return precio;
    
    }
    
    public float calcPeso(){
        float peso=0;
        peso += list.getPeso()*cantidad;
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
    private OrdenCompra ordCompra;
    
    public Pago(float a, Date b){
        monto=a;
        fecha=b;
    
    }
    public void setOrdenCompra(OrdenCompra a){
        this.ordCompra=a;
        
    }
    public OrdenCompra getOrdCompra(){
        return ordCompra;
    }
    
    public float getMonto(){
        return monto;
    }
    

}

class Efectivo extends Pago{
    public Efectivo(float a, Date b){
        super(a, b);
    
    }
    
    public float calcDevolucion(){
       float devolucion=getMonto()- getOrdCompra().calcPrecio(); 
       if(devolucion < 0) devolucion = 0;
       return devolucion;
    }

}

class Transferencia extends Pago{
    private String banco;
    private String numCuenta;
    
    public Transferencia(float a, Date b, String e, String f){
        super(a, b);
        banco=e;
        numCuenta=f;
    
    }

}

class Tarjeta extends Pago{
    private String tipo;
    private String numTransaccion;
    
    public Tarjeta(float a, Date b, String d, String e){
        super(a, b);
        tipo=d;
        numTransaccion=e;
    }

}
