package abb_genericos;

public class Nodo <T> {
     Nodo<T> izquierdo;
     Nodo<T> derecho;
     T dato;

    public Nodo(T d) {
        izquierdo = null;
        derecho = null;
        dato = d;
    }

    public Nodo<T> getHijoIzquierdo() { return izquierdo; }

    public Nodo<T> getHijoDerecho() { return derecho; }

    public T getDato() { return dato; }

}
