package abb_genericos;

public class Main {

    public static void main(String[] args) throws Exception {
        int[] valores = new int[]{45, 23, 2, 7, 65, 38, 96, 52, 48};  //Definimos un arreglo de enteros
                                                                      //para insertarlos en el ABB
        /*ABB<Integer> abb2 = new ABB<>();
        abb2.inorden();
        abb2.insertarNodo(5);
        abb2.inorden();
        abb2.eliminarNodo(5);
        abb2.inorden();
        System.out.println(abb2.promedio());*/
        ABB<Integer> abb = new ABB<>();      //Creamos un objeto de la clase ABB de tipo entero
        for (int i = 0; i < valores.length; i++) {
            abb.insertarNodo(valores[i]);
        }
        /*
          45
       /     \
      23      65
     /  \    /  \
    2   38  52   96
     \      /
      7    48         */

        System.out.println("Existe el numero 45 en el abb: " + abb.buscar(45));
        System.out.println ("Tamaño del abb: " + abb.tamaño ());
        System.out.println ("**************************\nInOrder");
        abb.inorden();
        System.out.println("**************************");
        System.out.println ("**************************\nPreOrden");
        abb.preorden();
        System.out.println("**************************");
        System.out.println ("**************************\nPostOrden");
        abb.postorden();
        System.out.println("**************************");

        System.out.println ("Numero de hijos del nodo 52: " + abb.numeroDeHijos(52));
        System.out.println ("Padre del nodo 52: " + abb.getPadre (52));
        System.out.println("Balance de: " + abb.balance(45));
        abb.eliminarNodo(50);
        System.out.println ("Tamaño del abb: " + abb.tamaño ());


        System.out.println("La suma de los elementos: " + abb.promedio());

       // abb.eliminarNodo(65);
        abb.inorden();


    }
}
