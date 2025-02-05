package org.example;

public class Calculadora {
    public double sumar(double a, double b){
        return a+b;
    }

    public double restar(double a, double b){
        return a-b;
    }

    public double multiplicar(double a, double b){
        return a*b;
    }

    public double dividir(double a, double b){
        if (b==0) {
            throw new ArithmeticException("No se puede dividir por cero.");
        }
        return a/b;
    }

    public double potenciarAlCuadrado(double a){
        return Math.pow(a,2);
    }

    public double raizCuadrada(double a){
        if (a < 0) {
            throw new IllegalArgumentException("No se puede calcular la raíz cuadrada de un número negativo.");
        }
        return Math.sqrt(a);
    }
}
