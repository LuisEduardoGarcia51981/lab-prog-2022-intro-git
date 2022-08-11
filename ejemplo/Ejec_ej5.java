

public class Ejec_ej5 {
    public static void main (String[] args)     
    {       
        
        int num_a=2;//cantidad de impresoras tipo A
        int num_b=2;//cantidad de impresoras tipo B
        Centro_impresion un_centro= new Centro_impresion(num_a,num_b);               
        Colores2 un_color=new Colores2();                

        int cant_procesos_a = 4;
        int cant_procesos_b = 4;
        Proceso[] procesos_a = new Proceso[cant_procesos_a];  
        Proceso[] procesos_b = new Proceso[cant_procesos_b];  
        
        for (int i = 0; i < cant_procesos_a; i++) {                   
            procesos_a[i] = new Proceso(un_centro, 1, un_color.get_color(i));             
        }
        for (int i = 0; i < cant_procesos_b; i++) {                   
            procesos_b[i] = new Proceso(un_centro, 2,un_color.get_color(i));             
        }


        Thread[] hilos_procesos_a = new Thread[cant_procesos_a];
        for (int i = 0; i < cant_procesos_a; i++) {
            hilos_procesos_a[i] = new Thread(procesos_a[i], "PA"+i );
            hilos_procesos_a[i].start();
        }
       
        Thread[] hilos_procesos_b = new Thread[cant_procesos_b];
        for (int i = 0; i < cant_procesos_b; i++) {
            hilos_procesos_b[i] = new Thread(procesos_b[i], "PB"+i );
            hilos_procesos_b[i].start();
        }

    }
}
