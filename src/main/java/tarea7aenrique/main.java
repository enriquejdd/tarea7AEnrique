/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea7aenrique;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author enrique
 */
public class main {

    public static void main(String[] args) {
        // Fichero a leer
        String idFichero = "RelPerCen.csv";

        ArrayList<Empleado> empleados = new ArrayList<>();

        // Variables para guardar los datos que se van leyendo
        String[] tokens;
        String linea;

        try (Scanner datosFichero = new Scanner(new FileReader(idFichero))) {

            while (datosFichero.hasNextLine()) {
                linea = datosFichero.nextLine();

                tokens = linea.split(",");

            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
