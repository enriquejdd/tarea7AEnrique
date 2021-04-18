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

        String fecha; // Guardar la fecha en un String
        String d; // Guardar el dia en un String
        int dia;
        String a; // Guardar el año en un String
        int anio;

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
                fecha = tokens[4];
//                System.out.println(fecha);
                fec = fecha.split("/");

                // Una vez separado lo volvemos a separar para quitar las comillas iniciales.
                d = fec[0];
                dias = d.split("");
                dia = Integer.valueOf(dias[1] + dias[2]);
//                System.out.println(dia);

                // Repetimos con las comillas finales del año
                a = fec[2];
                anios = a.split("");
                anio = Integer.valueOf(anios[0] + anios[1] + anios[2] + anios[3]);
//                System.out.println(anio);                
//                System.out.println(dia + "," + fec[1] + "," + anio);
                tmp.setFechaIni(LocalDate.of(anio, Integer.valueOf(fec[1]), dia));

                //Repetimos los mismos pasos que en el caso anterior y pero separando los que aun no se fueron y los que si.
                // Para ello si su fecha de cese solo tiene 2 espacios (las comillas) es que aun esta como empleado, si no ya se fue.
                if (tokens[5].length() == 2) {
                    tmp.setFechaFin(LocalDate.MAX);
                } else {
                    fecha = tokens[5];
                    fec = fecha.split("/");

                    d = fec[0];
                    dias = d.split("");
                    dia = Integer.valueOf(dias[1] + dias[2]);
//                    System.out.println(dia);

                    a = fec[2];
                    anios = a.split("");
                    anio = Integer.valueOf(anios[0] + anios[1] + anios[2] + anios[3]);
//                    System.out.println(dia + "," + fec[1] + "," + anio);

                    tmp.setFechaFin(LocalDate.of(anio, Integer.valueOf(fec[1]), dia));
                }

                tmp.setTlf(tokens[6]);
//                System.out.println(tokens[6]);
                tmp.setEvaluador(Boolean.valueOf(tokens[7]));
//                System.out.println(tokens[7]);
                tmp.setCoord(Boolean.valueOf(tokens[8]));
//                System.out.println(tokens[8]);

            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
