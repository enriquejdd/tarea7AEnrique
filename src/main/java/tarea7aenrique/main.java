/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea7aenrique;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.Month;
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
        String[] fec;
        String[] dias;
        String[] anios;
        String linea;
        int contador = 0;

        try (Scanner datosFichero = new Scanner(new FileReader(idFichero))) {
            datosFichero.nextLine();
            while (datosFichero.hasNextLine()) {

                linea = datosFichero.nextLine();

                tokens = linea.split(",");
                Empleado tmp = new Empleado();

                // Crear el string para el nombre completo.
                // Creamos dos String que cogan el token 0 (Apellidos) y el tokens 1 (nombre) para juntarlo en el set
                String ape = tokens[0];
//                System.out.println(ape);
                String nombre = tokens[1];
//                System.out.println(nombre);
                tmp.setNombre(ape + ", " + nombre);

                tmp.setDNI(tokens[2]);
                tmp.setPuesto(tokens[3]);

                // Creamos un nuevo split para separar la fecha en dias, mes y año.
                String fecha = tokens[4];
//                System.out.println(fecha);
                fec = fecha.split("/");

                // Una vez separado lo volvemos a separar para quitar las comillas iniciales.
                String d = fec[0];
                dias = d.split("");
                int dia = Integer.valueOf(dias[1] + dias[2]);
//                System.out.println(dia);

                // Repetimos con las comillas finales del año
                String a = fec[2];
                anios = a.split("");
                int anio = Integer.valueOf(anios[0] + anios[1]+ anios[2] + anios[3]);
//                System.out.println(anio);                
//                System.out.println(dia + ", " + fec[1] + ", " + anio);
                tmp.setFechaIni(LocalDate.of(dia, Integer.valueOf(fec[1]), anio));
                
                
                tmp.setNombre(tokens[0]);
                tmp.setNombre(tokens[0]);
                tmp.setNombre(tokens[0]);
                tmp.setNombre(tokens[0]);
                tmp.setNombre(tokens[0]);

            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
