import java.util.Scanner;
import java.util.LinkedList;

public class Pagos{
    public static void main(String[] args) {
        int[] p = new int[3];
        int[] g = new int[3];
        double total = 0;
        Scanner sc = new Scanner(System.in);
        String letra = "";
        int temp = 0;
        do{
            System.out.print("Quién pagó? ");
            letra = sc.next();
            temp = quien(letra);
            if(temp != -1){
                System.out.print("Cuánto pagó? ");
                p[temp] += sc.nextDouble();
            }else{
                if(letra.toLowerCase().charAt(0) != 's')
                    System.out.println("Letra inválida");
            }
        }while(letra.toLowerCase().charAt(0) != 's');
        letra = "";
        
        //Cambio
        System.out.print("Cuánto fue el cambio: ");
        double cambio = sc.nextDouble();
        do{
            System.out.print("Quién recibió el cambio: ");
            String nombre = sc.next();
            temp = quien(nombre);
            p[temp] -= cambio;
        }while(temp == -1);

        //Total
        do{
            System.out.print("Costo: ");
            letra = sc.next();
            if(letra.matches("[+-]?\\d*(\\.\\d+)?")){
                total += Double.parseDouble(letra);
            }else{
                if(letra.toLowerCase().charAt(0) != 's')
                    System.out.println("Número inválido");
            }
        }while(letra.toLowerCase().charAt(0) != 's');
        letra = "";

        //Reparte
        LinkedList<Integer> lista = new LinkedList<Integer>();
        do{
            do{
                System.out.print("Quién gastó? ");
                letra = sc.next();
                temp = quien(letra);
                if(temp != -1){
                    lista.add(temp);
                }else{
                    if(letra.toLowerCase().charAt(0) != 's')
                        System.out.println("Letra inválida");
                }
            }while(letra.toLowerCase().charAt(0) != 's');
            double precio = 0;
            System.out.print("Cuánto gastó? ");
            precio = sc.nextDouble();
            for(int i : lista){
                g[i] += precio / lista.size();
            }
            System.out.print("Salir? ");
            letra = sc.next();
        }while(letra.toLowerCase().charAt(0) != 's');
        letra = "";

        //Cada uno
        double cadaUno = (total - g[0] - g[1] - g[2]) / 3;
        g[0] += cadaUno;
        g[1] += cadaUno;
        g[2] += cadaUno;
        p[0] -= g[0];
        p[1] -= g[1];
        p[2] -= g[2];

        //300
        //p ahora es lo que le deben pagar a cada uno
        int[] quienes = new int[3];
        do{
            System.out.print("A quién le deben? ");
            letra = sc.next();    
            temp = quien(letra);
                if(temp != -1){
                    quienes[temp] = 1;
                }else{
                    if(letra.toLowerCase().charAt(0) != 's')
                        System.out.println("Letra inválida");
                }
            for(int i=0; i < 3; i++){
                if(quienes[i] == 1){
                    p[i] += 300;
                    for(int j=0; j < 3; j++){
                        p[j] -= 100;
                    }
                }
            }
        }while(letra.toLowerCase().charAt(0) != 's');
        letra = "";

        //Comprobar
        System.out.println("Error de precios: " + (total - g[0] - g[1] - g[2]));
        System.out.println("Error de pagos: " + (p[0] + p[1] + p[2]));

        //Salida
        String[] nombres = {"José", "Mario", "Elisa"};
        for(int i=0; i < 3; i++){
            if(p[i] < 0){
                System.out.println(nombres[i] + " paga $" + (-p[i]));
            }else{
                System.out.println(nombres[i] + " recibe $" + p[i]);
            }
        }
        
    }   
    
    public static int quien(String nombre){
        if(nombre.toLowerCase().charAt(0) == 'j'){
            return 0;
        }else if(nombre.toLowerCase().charAt(0) == 'm'){
            return 1;
        }else if(nombre.toLowerCase().charAt(0) == 'e'){
            return 2;
        }else{
            return -1;
        }
    }
}