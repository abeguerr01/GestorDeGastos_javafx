/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.io.*;

/**
 *
 * @author USER
 */
public class GuardarConsultarArchivos {
    private final String RUTA_ARCHIVO = "datos.dat";

    public void guardarArray(int[] datos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_ARCHIVO))) {
            oos.writeObject(datos);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public int[] leerArray() {
        File file = new File(RUTA_ARCHIVO);
        if (!file.exists()) {
            return new int[12]; 
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_ARCHIVO))) {
            return (int[]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new int[12]; // En caso de error, devolvemos un array limpio
        }
    }

    public void modificar(int posicion, int valor) {
        if (posicion < 1 || posicion > 12) {
            System.out.println("Posición fuera de rango (1-12)");
            return;
        }

        int[] datosActuales = leerArray();

        datosActuales[posicion - 1] += valor;

        guardarArray(datosActuales);
        
        System.out.println("Posición " + posicion + " actualizada. Nuevo valor: " + datosActuales[posicion - 1]);
    }
    
    public void sumaMod(int pos, int suma){
        int[] val = leerArray();
        modificar(pos, val[pos]+suma);
        System.out.println("Suma correcta");
    }
    
    public void restaMod(int pos, int resta){
        int[] val = leerArray();
        modificar(pos, val[pos]-resta);
        System.out.println("Suma correcta");
    }
}
