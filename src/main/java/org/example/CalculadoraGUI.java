package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraGUI extends JFrame {
    private JTextField campoTexto; // Campo donde se mostrará el resultado y los números integrados
    private double num1, num2; // Variables para almacenar los operandos
    private String operador; // Almacena el operador seleccionado
    private boolean nuevoNumero = false; // Boleano para comprobar si hay que borrar el campo de texto

    public CalculadoraGUI() {
        setTitle("Calculadora"); // Título de la ventana
        setSize(300, 400); // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar la ventana al presionar "X"

        // 1. Crear el campo de texto (donde se muestran los números y resultados)
        campoTexto = new JTextField();
        campoTexto.setHorizontalAlignment(JTextField.RIGHT); // Alinear texto a la derecha
        add(campoTexto, BorderLayout.NORTH); // Agregar el campo de texto en la parte superior

        // 2. Crear los botones de la calculadora en una cuadrícula
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(5,4)); // 5 filas x 4 columnas

        // 3. Definir los botones (ahora incluyendo "x**2" y "raíz cuadrada")
        String[] botones = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+",
                "x²", "√"
        };

        // 4. Crear los botones dinámicamente
        for (String texto : botones){
            JButton boton = new JButton(texto);
            panelBotones.add(boton);
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    manejarEvento(e.getActionCommand()); // Manejar la acción cuando se presiona un botón
                }
            });
        }

        // 5. Agregar el panel de botones a la ventana
        add(panelBotones, BorderLayout.CENTER);
    }

    // Manejar eventos de los botones
    private void manejarEvento(String comando) {
        if (comando.matches("[0-9]")) { // Si es un número
            if (nuevoNumero){
                campoTexto.setText(comando); // 🔹 Reemplaza el texto en vez de concatenarlo
                nuevoNumero = false;
            } else  {
                campoTexto.setText(campoTexto.getText() + comando);
            }

        } else if (comando.matches("[+\\-*/]")) { // Si es un operador
            num1 = Double.parseDouble(campoTexto.getText());
            operador = comando;
            //campoTexto.setText("");
            nuevoNumero = true; // 🔹 Indica que el siguiente número debe ser nuevo
        } else if (comando.equals("=")) { // Si es el botón "="
            num2 = Double.parseDouble(campoTexto.getText());
            double resultado = realizarOperacion(num1, num2, operador);
            campoTexto.setText(String.valueOf(resultado));
            nuevoNumero = true; // 🔹 Indica que el siguiente número debe ser nuevo
        } else if (comando.equals("C")) { // Si es el botón "C" (borrar)
            campoTexto.setText("");
            nuevoNumero = false;
        } else if (comando.equals("x²")) { // Si es el botón "x²"
            double num = Double.parseDouble(campoTexto.getText());
            campoTexto.setText(String.valueOf(Math.pow(num, 2)));
            nuevoNumero = true;
        } else if (comando.equals("√")) { // Si es el botón "√"
            double num = Double.parseDouble(campoTexto.getText());
            if (num < 0) {
                campoTexto.setText("Error");
            } else {
                campoTexto.setText(String.valueOf(Math.sqrt(num)));
            }
            nuevoNumero = true;
        }
    }

    // Método para realizar la operación matemática
    private double realizarOperacion(double a, double b, String operador) {
        switch (operador) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return (b != 0) ? a / b : 0;
            default: return 0;
        }
    }

    // Método main para ejecutar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculadoraGUI().setVisible(true);
        });
    }
}
