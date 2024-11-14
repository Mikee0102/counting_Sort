import java.util.Scanner;

public class CountingSort {
    public static void ordenarPorConteo(int[] arregloOriginal) {
        // Si el arreglo está vacío, no hay nada que ordenar
        if (arregloOriginal.length == 0) {
            return;
        }

        // PASO 1: Encontrar el número más grande del arreglo
        int numeroMayor = arregloOriginal[0];
        for (int i = 1; i < arregloOriginal.length; i++) {
            if (arregloOriginal[i] > numeroMayor) {
                numeroMayor = arregloOriginal[i];
            }
        }

        // PASO 2: Crear el arreglo para contar las repeticiones de cada número
        // El tamaño es numeroMayor + 1 porque necesitamos incluir el 0
        int[] conteoNumeros = new int[numeroMayor + 1];

        // PASO 3: Contar cuántas veces aparece cada número
        for (int i = 0; i < arregloOriginal.length; i++) {
            int numeroActual = arregloOriginal[i];
            conteoNumeros[numeroActual]++;
        }

        // PASO 4: Reconstruir el arreglo ordenado
        int posicionActual = 0;
        // Para cada número posible (0 hasta el número mayor)
        for (int numero = 0; numero <= numeroMayor; numero++) {
            // Mientras ese número tenga apariciones pendientes
            while (conteoNumeros[numero] > 0) {
                arregloOriginal[posicionActual] = numero;
                posicionActual++;
                conteoNumeros[numero]--;
            }
        }
    }

    // Met para ordenar el arreglo
    public static void imprimirArreglo(int[] arreglo) {
        System.out.print("[ ");
        for (int i = 0; i < arreglo.length; i++) {
            System.out.print(arreglo[i]);
            if (i < arreglo.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" ]");
    }

    public static void main(String[] args) {
        Scanner entradaUsuario = new Scanner(System.in);

        try {
            System.out.print("¿Cuántos números desea ordenar?: ");
            int cantidadNumeros = entradaUsuario.nextInt();

            if (cantidadNumeros <= 0) {
                System.out.println("Error: La cantidad debe ser un número positivo.");
                return;
            }

            // Crear el arreglo del tamaño especificado
            int[] numeros = new int[cantidadNumeros];

            // Ingresar los numeros
            System.out.println("\nIngrese los números (solo números enteros positivos):");
            for (int i = 0; i < cantidadNumeros; i++) {
                System.out.print("Número " + (i + 1) + ": ");
                int numeroIngresado = entradaUsuario.nextInt();

                // Validar que el número sea positivo
                if (numeroIngresado < 0) {
                    System.out.println("Error: Solo se permiten números positivos.");
                    return;
                }

                numeros[i] = numeroIngresado;
            }

            // Mostrar el arreglo antes de ordenar
            System.out.println("\nNúmeros ingresados (sin ordenar):");
            imprimirArreglo(numeros);

            // Ordenar el arreglo
            ordenarPorConteo(numeros);

            // Mostrar el arreglo después de ordenar
            System.out.println("\nNúmeros ordenados de menor a mayor:");
            imprimirArreglo(numeros);

        } catch (Exception error) {
            System.out.println("Error: Por favor ingrese solo números enteros válidos.");
        } finally {
            entradaUsuario.close();
        }
    }
}