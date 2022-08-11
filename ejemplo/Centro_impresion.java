import java.util.concurrent.Semaphore;


public class Centro_impresion {
    private Semaphore [] arr_imp_A; 
    private Semaphore [] arr_imp_B;     
    private int numA;
    private int numB;
    public static final String ANSI_RESET = "\u001B[0m"; 
    
    public Centro_impresion (int num_a, int num_b)
    {    
        this.numA=num_a;
        this.numB=num_b;       
        this.arr_imp_A = new Semaphore[this.numA];
        this.arr_imp_B = new Semaphore[this.numB];
        
        for (int i=0; i<this.numA;i++)
        {
            this.arr_imp_A[i]= new Semaphore(1);
        }
        for (int j=0; j<this.numB;j++)
        {
            this.arr_imp_B[j]= new Semaphore(1);
        }        

    }
    public int adquirirImpresora (int tipo_impresora,String nombre_proceso, String color)//1->A; 2->B; 3->AB
    {    
        String cad_impresora="";
        if (tipo_impresora==1){cad_impresora="A";}
        else 
        if (tipo_impresora==2){cad_impresora="B";}        
        System.out.println(color+"Proceso "+nombre_proceso+" esta buscando una impresora tipo "+cad_impresora+ANSI_RESET);
        int retorno=0;
        if (tipo_impresora==1)
        {
            int indiceA=0;
            while (!this.arr_imp_A[indiceA].tryAcquire())
            {
                if (indiceA<this.numA-1)
                { indiceA++;}
                else
                { indiceA=0; }                
            }
            retorno=indiceA;
        }
        else if (tipo_impresora==2)
        {
            int indiceB=0;
            while (!this.arr_imp_B[indiceB].tryAcquire())
            {
                if (indiceB<this.numB-1)
                { indiceB++;}
                else
                { indiceB=0; }
            }
            retorno=indiceB;
        }
        System.out.println(color+"La impresora "+retorno+", tipo "+cad_impresora+", fue asignada al Proceso "+nombre_proceso+ANSI_RESET);
        return retorno;
    }
    public void liberarImpresora (int tipo_impresora, int indice_impresora)//1->A; 2->B; 3->AB
    {    
        if (tipo_impresora==1)
        {
            this.arr_imp_A[indice_impresora].release();
        }
        else if (tipo_impresora==2)
        {
            this.arr_imp_B[indice_impresora].release();
        }
    }
    
    public void imprimir(int tipo_impresora, int indice_semaforo, String nombre_proceso, String color)
    {
        int delay = 2; // 2 segundo
        String cad_impresora="";
        if (tipo_impresora==1){cad_impresora="A";}
        else 
        if (tipo_impresora==2){cad_impresora="B";}        
        
        try 
        {               
            System.out.println(color+"El proceso "+nombre_proceso+" comenzo la impresion en la impresora "+indice_semaforo+", tipo "+cad_impresora+ANSI_RESET);
            Thread.sleep(delay*1000); // 
            System.out.println(color+"El proceso "+nombre_proceso+" termino la impresion en la impresora "+indice_semaforo+", tipo "+cad_impresora+ANSI_RESET);
            
        }
        catch (InterruptedException e){}                   
    }

    
}
