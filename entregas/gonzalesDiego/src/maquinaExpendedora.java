import java.util.Scanner;

public class maquinaExpendedora {
    public static void main(String[] args) {
        Scanner entradaUsuario = new Scanner(System.in);
        
        
        String[] prods = {"Coca-Cola", "Chips", "Chocolate", "Agua"};
        double[] precios = {1.50, 1.00, 1.25, 0.75};
        int[] stock = {10, 8, 12, 15};
        
        double saldo = 0.0;
        double max_saldo_maq = 5.0; 
        
       
        double[] mon_val = {0.10, 0.20, 0.50, 1.0, 2.0};
        
        System.out.println("Maquina Expendedora v1.0");
        
        while (true) {
            System.out.println(); 
            System.out.println("Productos Disponibles");
            for (int i = 0; i < prods.length; i++) {
                System.out.println((i + 1) + ". " + prods[i] + " | Precio: " + precios[i] + "eur | Stock: " + stock[i]);
            }
            
            System.out.println(); 
            
            System.out.println("Saldo actual: " + ((int) saldo * 100) / 100 + " euros (Max. " + max_saldo_maq + "eur)");
            System.out.print("[1] Insertar moneda (Validas: ");
            for(int i = 0; i < mon_val.length; i++) {
                System.out.print(mon_val[i] + " ");
            }
            System.out.println(")");
            System.out.println("[2] Seleccionar producto");
            System.out.println("[3] Salir y recuperar cambio");
            System.out.print("Opcion: ");
            int opt = entradaUsuario.nextInt();
            
            if (opt == 1) {
                System.out.print("Introduce moneda: ");
                double m = entradaUsuario.nextDouble();
                boolean m_valida = false;
                for(int i = 0; i < mon_val.length; i++) {
                    if (m == mon_val[i]) {
                        m_valida = true;
                        break;
                    }
                }
                
                if (m_valida) {
                    if (saldo + m > max_saldo_maq) {
                        System.out.println("ERROR: La maquina no acepta mas de " + max_saldo_maq + " euros. Se devuelve " + m + " euros.");
                    } else {
                        saldo = saldo + m;
                        System.out.println("Moneda aceptada.");
                    }
                } else {
                    System.out.println("Moneda no valida. Se devuelve " + m + " euros.");
                }
            } else if (opt == 2) {
                System.out.print("Introduce el numero del producto: ");
                int sel = entradaUsuario.nextInt() - 1; 
                
                if (sel >= 0 && sel < prods.length) {
                    if (stock[sel] <= 0) {
                        System.out.println("Lo sentimos, producto agotado.");
                    } else if (saldo < precios[sel]) {
                        System.out.println("Saldo insuficiente. Necesitas " + precios[sel] + "eur.");
                    } else {
                        saldo = saldo - precios[sel];
                        stock[sel] = stock[sel] - 1;
                        System.out.println("¡Gracias! Aqui tienes tu " + prods[sel] + ".");
                    }
                } else {
                    System.out.println("Seleccion invalida.");
                }
            } else if (opt == 3) {
                if (saldo > 0) {
                    System.out.println("No olvides recoger tu cambio: " + ((int) saldo * 100) / 100 + " euros.");
                }
                System.out.println("Gracias por tu visita. ¡Hasta pronto!");
                break;
            } else {
                System.out.println("Opcion no valida.");
            }
        }
        entradaUsuario.close();
    }
}
