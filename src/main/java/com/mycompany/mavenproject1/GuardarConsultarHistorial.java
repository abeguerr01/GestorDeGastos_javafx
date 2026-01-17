/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GuardarConsultarHistorial {

    private final String ARCHIVO_HISTORIAL = "historial.dat";

    public void añadirAlHistorial(int num1, int num2, String info1, String info2) {
        
        String fechaActual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        Object[] nuevoRegistro = {fechaActual, num1, num2, info1, info2};

        List<Object[]> listaTotal = consultarTodos();
        listaTotal.add(nuevoRegistro);
        
        guardarLista(listaTotal);
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> consultarTodos() {
        File file = new File(ARCHIVO_HISTORIAL);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_HISTORIAL))) {
            return (List<Object[]>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    public List<Object[]> consultarMasRecientes() {
        List<Object[]> lista = consultarTodos();
        // Invertimos la lista para que el último guardado sea el primero
        Collections.reverse(lista);
        return lista;
    }

    private void guardarLista(List<Object[]> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_HISTORIAL))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.err.println("Error al guardar el historial: " + e.getMessage());
        }
    }
}