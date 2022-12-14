package tareaprog;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;  //Ocupamos esta libreria para establecer un formato a la fecha
import java.util.Calendar;          //Ocupamos esta libreria para obtener la fecha actual del calendario

public class TareaProg {
     public static void main(String[] args) {
        
        //Agregamos Articulos
        ArrayList<Articulo> articulo = new ArrayList<Articulo>(3);
        articulo.add(new Articulo(3.50f,"Papas Lays","Papas fritas corte Americano",15.95f));
        articulo.add(new Articulo(3.00f,"Palmitos Esmeralda","Palmitos en conserva",30.00f));
        articulo.add(new Articulo(2.50f,"RedBull SugarFree","Bebida energetica sin azucar añadida",18.99f));
        
        //Agregamos la cantidad de Articulos por cada Orden   
        DetalleOrden d = new DetalleOrden(3);
        d.setArticulo(articulo.get(1));
        DetalleOrden c = new DetalleOrden(2);
        c.setArticulo(articulo.get(0));
        DetalleOrden e = new DetalleOrden(2);
        e.setArticulo(articulo.get(0));
        DetalleOrden f = new DetalleOrden(5);
        f.setArticulo(articulo.get(2));
        DetalleOrden g = new DetalleOrden(3);
        g.setArticulo(articulo.get(1));
        
        //Creamos las ordenes de listas
        OrdenCompra lista = new OrdenCompra("pago efectuado"); 
        OrdenCompra lista2 = new OrdenCompra( "pago pendiente");
        OrdenCompra lista3 = new OrdenCompra( "pago pendiente");
        
        //Agregamos los articulos a las ordenes de compra
        lista.setOrden(d);
        lista2.setOrden(c);
        lista3.setOrden(e);
        lista2.setOrden(g);
        lista3.setOrden(f);
        
        //Creamos las direcciones de casas
        Direccion CasaJ =  new Direccion("Las Palmeras 602");
        Direccion CasaD =  new Direccion("Fragata María Isabel 165-b");
        
        //Creamos los clientes
        Cliente Jorge= new Cliente("Jorge", "21087983-8", CasaJ);
        Cliente Dani= new Cliente("Dani", "21289833-3", CasaD);
        
        //Vinculamos a los clientes con las direcciones
        CasaJ.setCliente(Jorge);
        CasaD.setCliente(Dani);
        
        //Vinculamos a los clientes con ordenes de compras
        lista.setCliente(Jorge);
        lista2.setCliente(Jorge);
        lista3.setCliente(Dani);
        
        
        Pago dinero1 = new Efectivo(50.0f);
        Pago dinero15= new Efectivo(60.94f);
        Pago dinero2 = new Transferencia(146.6f, "Banco Falabella", "12345678");
        Pago dinero3 = new Tarjeta(151.7f, "debito", "12345678");
        lista.addPago(dinero1);
        lista.addPago(dinero15);
        lista2.addPago(dinero2);
        lista3.addPago(dinero3);
        dinero1.setOrdenCompra(lista);
        dinero15.setOrdenCompra(lista);
        dinero2.setOrdenCompra(lista2);
        dinero3.setOrdenCompra(lista3);
        
        //fechas
        Date a = new Date(Calendar.getInstance().getTimeInMillis());
        Date b = new Date(122,9,9,23,59,59);
        
        lista.setFecha(a);
        lista2.setFecha(b);
        lista3.setFecha(a);
        
        System.out.println("ORDEN 1:");
        System.out.println("Cliente: " + lista.toString());
        System.out.println(CasaJ.toString());
        System.out.println("Precio sin IVA: "+ lista.calcPrecioSinIVA());
        System.out.println("Precio con IVA: "+ lista.calcPrecio());
        System.out.println("IVA total: "+ lista.calcIVA());
        System.out.println("Peso total: "+ lista.calcPeso());
        System.out.println(dinero1.toString());
        System.out.println("Devolucion 1: "+((Efectivo)lista.Pagos.get(0)).calcDevolucion());
        System.out.println(dinero15.toString());
        System.out.println("Devolucion 2: "+((Efectivo)lista.Pagos.get(1)).calcDevolucion());
        
        System.out.println("\nORDEN 2:");
        System.out.println("Cliente: " + lista2.toString());
        System.out.println(CasaJ.toString());
        System.out.println(dinero2.toString());
        System.out.println("Precio sin IVA: "+ lista2.calcPrecioSinIVA());      
        System.out.println("Precio con IVA: "+ lista2.calcPrecio());
        System.out.println("IVA total: "+ lista2.calcIVA());
        System.out.println("Peso total: "+ lista2.calcPeso());
    
        System.out.println("\nORDEN 3:");      
        System.out.println("Cliente: " +lista3.toString());
        System.out.println(CasaD.toString());
        System.out.println(dinero3.toString());
        System.out.println("Precio sin IVA: "+ lista3.calcPrecioSinIVA());
        System.out.println("Precio con IVA: "+ lista3.calcPrecio());
        System.out.println("IVA total: "+ lista3.calcIVA());
        System.out.println("Peso total: "+ lista3.calcPeso());
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
    public String getNombre(){
        return nombre;
    }
    public String getRUT(){
        return rut;
    }
    public String toString(){
        return nombre + "\n" + "RUT: " + rut +"\n"+direccion ;
    }
}

class OrdenCompra{
    private Date fecha;
    private String estado;
    private Cliente cliente;
    public ArrayList<DetalleOrden> detOrden;
    public ArrayList<Pago> Pagos;
    
    public OrdenCompra(String b){
        estado = b;
        detOrden = new ArrayList<DetalleOrden>() ;
        Pagos= new ArrayList<Pago>();
    }
    public void setOrden(DetalleOrden a){
        detOrden.add(a);
    }
    public void setFecha (Date d){
        this.fecha = d;
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
    
    public Date getFecha(){
        return fecha;
    
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
    
    public String toString(){ 
        SimpleDateFormat timeFormat= new SimpleDateFormat("dd/MM/yy"); 
        SimpleDateFormat timeFormat2= new SimpleDateFormat("hh:mm:ss"); 
        return cliente.getNombre() +"\n" +"RUT: " + cliente.getRUT()+"\nFecha: "+timeFormat.format(fecha)+" "+timeFormat2.format(fecha);
    }
}

class DetalleOrden{
    private int cantidad;
    private Articulo list;
    
    public DetalleOrden(int a){
        cantidad = a;
    }
    
    public void setArticulo(Articulo a){
        this.list = a;
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
    private ArrayList<Cliente> cliente;
    public Direccion(String dir){
        direccion = dir;
        cliente = new ArrayList<Cliente>();
    }
    
    public String getDireccion(){
        return direccion;
    }
    public void setCliente(Cliente c){
        cliente.add(c);
    }
    
    public String toString(){
        return "Direccion: " + direccion;
    
    }
    
}

class DocTributario{
   private String numero;
   private String rut;
   private Date fecha;
   private Direccion direccion;
   private ArrayList<DocTributario> docTributario;
   
   public DocTributario(String a, String b, Date c){
       numero=a;
       rut=b;
       fecha=c;
   
   }
   
   public String getNumero(){
       return numero;
   
   }
   
   public String getRUT(){
       return rut;
   
   }
   public Date getFecha(){
       return fecha;
   
   }
   
}

class Boleta extends DocTributario{
    public Boleta(String a, String b, Date c){
        super(a, b, c);
    
    }
    public String DocTributario(){
        return "Boleta: "+getNumero()+"\n"+"RUT: "+getRUT();
    }

}

class Factura extends DocTributario{
    public Factura(String a, String b, Date c){
        super(a, b, c);
    
    }
        public String DocTributario(){
        return "Factura: "+getNumero()+"\n"+"RUT: "+getRUT();
    }

}

class Pago{
    private float monto;
    private Date fecha;
    private OrdenCompra ordCompra;
    
    public Pago(float a){
        monto=a;
    }
    
    public void setFecha (Date d){
        this.fecha = d;
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
    public Efectivo(float a){
        super(a);
    
    }
    
    public float calcDevolucion(){
       float devolucion = - getOrdCompra().calcPrecio(); 
       int i = 0;
       while(true){
           devolucion += getOrdCompra().Pagos.get(i).getMonto();
           if(Math.abs(getOrdCompra().Pagos.get(i).getMonto()-getMonto())<=0.000001) break;
           else i++;
       }
       if(devolucion < 0) devolucion = 0;
       return devolucion;
    }
    
    public String toString(){
        return "Efectivo: "+'\n'+"Saldo: " + getMonto();
    
    }

}

class Transferencia extends Pago{
    private String banco;
    private String numCuenta;
    
    public Transferencia(float a, String e, String f){
        super(a);
        banco=e;
        numCuenta=f;
    
    }
    
    public String toString(){
        return "Banco: "+banco+"\n"+"Numero de Cuenta: "+numCuenta+"\n"+"Saldo: "+ getMonto();
    
    }

}

class Tarjeta extends Pago{
    private String tipo;
    private String numTransaccion;
    
    public Tarjeta(float a, String d, String e){
        super(a);
        tipo=d;
        numTransaccion=e;
    }
    
    public String toString(){
        return "Tipo de Tarjeta: " + tipo +"\n"+ "Numero de Transaccion: " + numTransaccion+"\n"+"Saldo: "+ getMonto();
    
    }

}
