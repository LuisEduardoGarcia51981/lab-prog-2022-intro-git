
public class Proceso implements Runnable{   
    private Centro_impresion un_centro;
    private int mi_tipo;//1->A; 2->B;3->AB
    private String mi_color;
    public Proceso (Centro_impresion un_centro, int mi_tipo,String mi_color)
    {    
        this.un_centro=un_centro;
        this.mi_tipo=mi_tipo; 
        this.mi_color= mi_color;         
    }
   
    public void run ()  
    {    
        int mi_impresora=this.un_centro.adquirirImpresora (this.mi_tipo,Thread.currentThread().getName(),this.mi_color);  
        this.un_centro.imprimir(this.mi_tipo, mi_impresora, Thread.currentThread().getName(),this.mi_color);
        this.un_centro.liberarImpresora (this.mi_tipo, mi_impresora);
    }
    
}
