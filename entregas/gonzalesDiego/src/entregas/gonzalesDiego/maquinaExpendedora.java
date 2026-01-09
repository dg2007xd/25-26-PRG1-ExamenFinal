package entregas.gonzalesDiego;

import java.util.Scanner;

public class MaquinaExpendedora {

    public static void main(String[] args) {
        Scanner entradaUsuario = new Scanner(System.in);

        String[] productos = {"Coca-Cola", "Chips", "Chocolate", "Agua"};
        double[] precios = {1.50, 1.00, 1.25, 0.75};
        int[] stock = {10, 8, 12, 15};

        double saldo = 0.0;
        double saldoMaximo = 5.0;

        double[] monedasValidas = {0.10, 0.20, 0.50, 1.0, 2.0};

        System.out.println("Maquina Expendedora v2.0");

        while (true) {

            imprimirMenu(productos, precios, stock, saldo, saldoMaximo, monedasValidas);

            int opcion = entradaUsuario.nextInt();

            if (opcion == 1) {
                System.out.print("Introduce moneda: ");
                double monedaElejida = entradaUsuario.nextDouble();
                
                saldo = monedaElegida(saldo, saldoMaximo, monedaElejida, monedasValidas);

            } else if (opcion == 2) {
                System.out.print("Introduce el numero del producto: ");
                int seleccion = entradaUsuario.nextInt() - 1;

                saldo = procesarSeleccionProducto(seleccion, saldo, productos, precios, stock);

            } else if (opcion == 3) {

                procesarSalida(saldo);
                break;

            } else {
                System.out.println("Opcion no valida.");
            }
        }
        entradaUsuario.close();
    }

    static void imprimirMenu(String[] productos, double[] precios, int[] stock, double saldo, double saldoMaximo, double[] monedasValidas) {
        System.out.println();
        System.out.println("Productos Disponibles");
        for (int i = 0; i < productos.length; i++) {
            System.out.println((i + 1) + ". " + productos[i] + " | Precio: " + precios[i] + "euros | Stock: " + stock[i]);
        }

        System.out.println();
        System.out.println(
                "Saldo actual: " + ((int) (saldo * 100)) / 100.0 + " euros (Max. " + saldoMaximo + " euros)"
        );

        System.out.print("[1] Insertar moneda (Validas: ");
        for (int i = 0; i < monedasValidas.length; i++) {
            System.out.print(monedasValidas[i]);
            if (i < monedasValidas.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(")");
        System.out.println("[2] Seleccionar producto");
        System.out.println("[3] Salir y recuperar cambio");
        System.out.print("Opcion: ");
    }

    static double monedaElegida(double saldo, double saldoMaximo, double monedaElegida, double[] monedasValidas) {
        boolean monedaValida = false;
        for (double valorMonedaValida : monedasValidas) {
            if (monedaElegida == valorMonedaValida) {
                monedaValida = true;
                break;
            }
        }

        if (monedaValida) {
            if (saldo + monedaElegida > saldoMaximo) {
                System.out.println("ERROR: La maquina no acepta mas de " + saldoMaximo + " euros. Se devuelve " + monedaElegida + " euros.");
            } else {
                saldo = saldo + monedaElegida;
                System.out.println("Moneda aceptada.");
            }
        } else {
            System.out.println("Moneda no valida. Se devuelve " + monedaElegida + " euros.");
        }

        return saldo;
    }

    static double procesarSeleccionProducto(int seleccion, double saldo, String[] productos, double[] precios, int[] stock) {
        if (seleccion >= 0 && seleccion < productos.length) {
            if (stock[seleccion] <= 0) {
                System.out.println("Lo sentimos, producto agotado.");
            } else if (saldo < precios[seleccion]) {
                System.out.println("Saldo insuficiente. Necesitas " + precios[seleccion] + "eur.");
            } else {
                saldo = saldo - precios[seleccion];
                stock[seleccion] = stock[seleccion] - 1;
                System.out.println("¡Gracias! Aqui tienes tu " + productos[seleccion] + ".");
            }
        } else {
            System.out.println("Seleccion invalida.");
        }
        return saldo;
    }

    static void procesarSalida(double saldo) {
        if (saldo > 0) {
            System.out.println(
                    "No olvides recoger tu cambio: " + ((int) (saldo * 100)) / 100.0 + " euros."
            );
        }
        System.out.println("Gracias por tu visita. ¡Hasta pronto!");
    }
}
