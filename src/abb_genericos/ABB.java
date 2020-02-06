package abb_genericos;

public class ABB<T extends Number & Comparable<T>>{

    private Nodo<T> nodoRaiz;
    private int numeroDeElementos;
    private int nivelProfundidadNodo;

    //public Nodo<T> getNodoRaiz() {
      //  return nodoRaiz;
    //}


    public ABB() {
        this.nodoRaiz = null;
        this.numeroDeElementos = 0;
        this.nivelProfundidadNodo = 0;
    }

    public boolean buscar(T dato) {
        if (dato == null) {
            throw new NullPointerException("El dato a buscar no debe ser nulo");
        }
        return buscar(nodoRaiz, dato);
    }

    private boolean buscar(Nodo<T> nodo, T dato) {
        if (nodo == null) {
            return false;
        }
        if (dato.equals(nodo.getDato())) {
            return true;
        } else if (dato.compareTo(nodo.getDato()) < 0) {
            return buscar(nodo.getHijoIzquierdo(), dato);
        } else {
            return buscar(nodo.getHijoDerecho(), dato);
        }
    }

    private Nodo<T> buscarNodo(Nodo<T> nodo, T n) {
        if (nodo == null) {
            return null;
        }
        if (n.equals(nodo.getDato())) {
            return nodo;
        } else if (n.compareTo(nodo.getDato()) < 0) {
            return buscarNodo(nodo.getHijoIzquierdo(), n);
        } else {
            return buscarNodo(nodo.getHijoDerecho(), n);
        }
    }

    public void insertarNodo(T valorDelElemento) {
        nodoRaiz = insertarNodo(nodoRaiz, valorDelElemento);
    }

    private Nodo<T> insertarNodo(Nodo<T> nodoRaiz, T valorDelElemento) {
        // Instanciamos un nuevo Nodo con el valorDelElemento
        // si el actual nodoRaiz no a sido previamente instanciado
        if (nodoRaiz == null) {
            return new Nodo<T>(valorDelElemento);
        }
        // Si el valor del valor que se está buscando Delelmento es menor que el valor del nodo nodoRaiz actual, entonces
        // atravesar al nodo izquierdo del nodoRaiz actual, configurando el nodo izquierdo actual a lo que sea devuelto
        //del método de inserción
        else if (valorDelElemento.compareTo(nodoRaiz.getDato()) < 0) {
            nodoRaiz.izquierdo = insertarNodo(nodoRaiz.getHijoIzquierdo(), valorDelElemento);
        }
        // si el valor del valorDelElemento que se busca es menor que el valor del nodo nodoRaiz actual, entonces
        // atravesar al nodo derecho del nodoRaiz actual, configurando el nodo derecho actual a lo que sea devuelto
        // del método de insertarNodo
        else if (valorDelElemento.compareTo(nodoRaiz.getDato()) > 0) {

            nodoRaiz.derecho = insertarNodo(nodoRaiz.getHijoDerecho(), valorDelElemento);
        }
        else {
            // Estilísticamente, tengo esto aquí para declarar explícitamente que somos
            //  no permitir la inserción de valores duplicados.
            ;
        }
        return nodoRaiz;
    }

    public int tamaño() { return tamaño(nodoRaiz); }

    private int tamaño(Nodo<T> nodoRaiz) {
        if (nodoRaiz == null) {
            return 0;
        } else {
            return 1 + tamaño(nodoRaiz.getHijoIzquierdo()) + tamaño(nodoRaiz.getHijoDerecho());
        }
    }

    public String generacionesDeNodos(int generacion) {
        if (generacion < 0) {
            throw new RuntimeException("La generacion no debe ser negativa.");
        }

        return generacionesDeNodos(this.nodoRaiz, generacion);
    }

    private String generacionesDeNodos(Nodo<T> nodoRaiz, int generacion) {
        if (nodoRaiz == null) {
            return "";
        }
        if (generacion == 0) {
            return nodoRaiz.getDato() + " ";
        }

        if (nodoRaiz.getHijoDerecho() != null && nodoRaiz.getHijoIzquierdo() != null) {
            return generacionesDeNodos(nodoRaiz.getHijoDerecho(), generacion)
                    + generacionesDeNodos(nodoRaiz.getHijoIzquierdo(), generacion);
        } else if (nodoRaiz.getHijoDerecho() != null) {
            return generacionesDeNodos(nodoRaiz.getHijoDerecho(), generacion);
        } else {
            return generacionesDeNodos(nodoRaiz.getHijoIzquierdo(), generacion - 1);
        }
    }


    public Nodo<T> elementoMinimo(Nodo<T> raiz) {
        if (raiz.getHijoIzquierdo() == null) {
            return raiz;
        }
        return elementoMinimo(raiz.getHijoIzquierdo());
    }

    /*
     * Elimina un nodo del ABB
     * @param valorDelElemento de tipo T implementa la interfaz Comparable
     * @return void
     */
    public void eliminarNodo(T dato) {

        nodoRaiz = eliminarNodo(nodoRaiz, dato);
        System.out.println("Se a eliminado el dato: "+dato);
    }

    private Nodo<T> eliminarNodo(Nodo<T> nodoRaiz, T dato) {
        // si el nodo raíz es nulo, entonces no hay nada que eliminar o no se necesita más recorrido
        if (nodoRaiz == null) {
            return null;
        }
        // si el valor de los datos que se buscan es menor que el valor del nodo raíz actual, entonces
        // atravesar al nodo izquierdo de la raíz actual, configurando el nodo izquierdo actual
        // a lo que sea devuelto desde el método de eliminarNodo
        else if (dato.compareTo(nodoRaiz.dato) < 0) {
            nodoRaiz.izquierdo = eliminarNodo(nodoRaiz.izquierdo, dato);
        }
        // si el valor de los datos que se buscan es mayor que el valor del nodo raíz actual, entonces
        //atravesar al nodo derecho de la raíz actual, configurando el nodo derecho actual a lo que sea devuelto
        //desde el método de eliminarNodo
        else if (dato.compareTo(nodoRaiz.dato) > 0) {
            nodoRaiz.derecho = eliminarNodo(nodoRaiz.derecho, dato);
        }
        // esta declaración significa que los datos que se buscan son iguales a la raíz actual, lo que significa que
        // hemos encontrado el nodo que deseamos eliminar
        else {
            //si el nodo no tiene hijos, entonces devuelve un valor de nulo
            if (nodoRaiz.izquierdo == null && nodoRaiz.derecho == null) {
                return null;
            }
            //si el nodo tiene un hijo izquierdo, pero no tiene un hijo derecho, devuelva el hijo izquierdo
            else if (nodoRaiz.derecho == null) {
                return nodoRaiz.izquierdo;
            }
            // si el nodo tiene un hijo derecho, pero no tiene un hijo izquierdo, devuelva el hijo derecho
            else if (nodoRaiz.izquierdo == null) {
                return nodoRaiz.derecho;
            }
            // Si el nodo tiene dos hijos, establezca los datos del nodo como el elemento más grande
            // en el subárbol izquierdo del nodo, y luego configure los datos del niño izquierdo para que sean iguales a
            // los datos que se devuelven al eliminar los nuevos datos raíz del subárbol izquierdo
            // (es decir, los datos que se configuran actualmente en el elemento secundario izquierdo)
            else {

                nodoRaiz.dato = findMax(nodoRaiz.izquierdo);
                nodoRaiz.izquierdo = eliminarNodo(nodoRaiz.izquierdo, nodoRaiz.dato);
            }
        }
        return nodoRaiz;
    }
    private T findMax(Nodo<T> nodoRaiz) {
        //
        //simplemente continúe atravesando a la derecha hasta que no pueda ir de un lado a otro,
        // y luego encontrará el elemento mas grande
        while (nodoRaiz.derecho != null) {
            nodoRaiz = nodoRaiz.derecho;
        }
        return nodoRaiz.dato;
    }

    public void inorden() {
        imprimirInOrder(nodoRaiz);
        System.out.println();
    }

    // (izquierda, Raiz, derecha)
    private void imprimirInOrder(Nodo<T> raiz) {
        if (raiz != null) {
            imprimirInOrder(raiz.getHijoIzquierdo());
            System.out.print(raiz.getDato() + " ");
            imprimirInOrder(raiz.getHijoDerecho());
        }
    }

    public void postorden() {
        imprimirPostOrder(nodoRaiz);
        System.out.println();
    }

    // (Izquierda, Derecha, Raiz)
    private void imprimirPostOrder(Nodo<T> raiz) {
        if (raiz != null) {
            imprimirPostOrder(raiz.getHijoIzquierdo());
            imprimirPostOrder(raiz.getHijoDerecho());
            System.out.print(raiz.getDato() + " ");
        }
    }

    public void preorden() {
        imprimirPreOrder(nodoRaiz);
        System.out.println();
    }

    // (Raiz, izquierda, derecha)
    private void imprimirPreOrder(Nodo<T> r) {
        if (r != null) {
            System.out.print(r.getDato() + " ");
            imprimirPreOrder(r.getHijoIzquierdo());
            imprimirPreOrder(r.getHijoDerecho());
        }
    }

    public T getPadre(T n) {
        if (n == null) {
            throw new NullPointerException("El dato a buscar no debe ser nulo");
        }
        return getPadre(nodoRaiz, n);
    }


    private T getPadre(Nodo<T> nodo, T n) {
        if (nodo == null) {
            return null;
        }

        if ((nodo.getHijoDerecho() != null && nodo.getHijoDerecho().getDato().equals(n))
                || (nodo.getHijoIzquierdo() != null && nodo.getHijoIzquierdo().getDato().equals(n))) {
            return nodo.getDato();
        } else if (nodo.getDato().compareTo(n) < 0) {
            return getPadre(nodo.getHijoDerecho(), n);
        } else {
            return getPadre(nodo.getHijoIzquierdo(), n);
        }
    }

    public int numeroDeHijos(T numero) {

        if (numero == null) {
            throw new NullPointerException("El dato a buscar no debe ser nulo.");
        }

        if (nodoRaiz == null) {
            return -2;
        }
        return nHijos(nodoRaiz, numero);
    }

    private int nHijos(Nodo<T> raiz, T n) {
        Nodo nodo = buscarNodo(raiz, n);
        int valor = 0;

        if (nodo == null) {
            return -1;
        } else {
            if (nodo.getHijoIzquierdo() != null) {
                valor++;
            }
            if (nodo.getHijoDerecho() != null) {
                valor++;
            }
        }
        return valor;
    }

    public int balance() throws Exception {
        if(this.nodoRaiz == null) {
            throw new Exception("El arbol en nulo");
        }

        return balance(this.nodoRaiz);
    }

    public int balance(T dato) throws Exception {
        if (dato == null)  throw new Exception("el dato a buscar no debe ser nulo");
        Nodo<T> nodo = buscarNodo(this.nodoRaiz, dato);
        if(nodo == null)  throw new Exception("El dato a buscar no existe en el arbol.");

        return balance(nodo);
    }

    private int balance(Nodo<T> nodo) { return tamaño(nodo.getHijoIzquierdo()) - tamaño(nodo.getHijoDerecho()); }

    private Double sumar(Nodo<T> nodoRaiz) {
        if (nodoRaiz == null)
            return 0.0;
        else
            return (nodoRaiz.dato.doubleValue() + sumar(nodoRaiz.getHijoIzquierdo()) + sumar(nodoRaiz.getHijoDerecho()));
    }

    public Double promedio() {
        if (nodoRaiz == null)
            throw new NullPointerException("El arbol no debe ser null.");
        else
            return sumar(nodoRaiz) / tamaño();
    }
}