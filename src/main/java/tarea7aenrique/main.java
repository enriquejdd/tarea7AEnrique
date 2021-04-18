/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea7aenrique;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        String linea;

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
                tmp.setNombre(ape + "," + nombre);
//              Otra manera sería usar el método creado abajo, pero veo más rápido y claro hacerlo directamente al solo necesitar unir 2 String.
//              tmp.setNombre(crearNombre(tokens[0], tokens[1]));

                tmp.setDNI(tokens[2]);
                tmp.setPuesto(tokens[3]);
                // Usamos el método crearFecha el cual devuelve una fecha a partir del String dado con la fecha en él.
                tmp.setFechaIni(crearFecha(tokens[4]));

                // Creamos un if para cuando el empleado aun no se marchase saliera el valor máximo. En caso de que se fuera utiliza el m´ñetodo crearFecha
                if (tokens[5].length() == 2) {
                    tmp.setFechaFin(LocalDate.MAX);
                } else {
                    tmp.setFechaFin(crearFecha(tokens[5]));
                }
                tmp.setTlf(tokens[6]);
//                System.out.println(tokens[6]);
                tmp.setEvaluador(Boolean.valueOf(tokens[7]));
//                System.out.println(tokens[7]);
                tmp.setCoord(Boolean.valueOf(tokens[8]));
//                System.out.println(tokens[8]);

                empleados.add(tmp);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
//        System.out.println(empleados.size());

        String nomFichero = "RelPerCen2.csv";
        LocalDate hoy = LocalDate.now();
        LocalDate añosAtras = LocalDate.of(hoy.getYear() - 20, hoy.getMonth(), hoy.getDayOfMonth());
//        System.out.println(añosAtras);

        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(nomFichero))) {
            for (Empleado e : empleados) {
                if (añosAtras.compareTo(e.getFechaIni()) > 0) {
                    flujo.write(e.toString());
                    flujo.newLine();
                }
            }
            flujo.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método el cual a partir de un String de una fecha lo separa hasta obtener el dia, mes y año del string y devolverlo como un LocalDate.
    public static LocalDate crearFecha(String e) {
        String[] fec;
        String[] dias;
        String[] anios;

        String fecha; // Guardar la fecha en un String
        String d; // Guardar el dia en un String
        int dia;
        String a; // Guardar el año en un String
        int anio;

        // Creamos un nuevo split para separar la fecha en dias, mes y año.
        fecha = e;
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

        LocalDate f = LocalDate.of(anio, Integer.valueOf(fec[1]), dia);
        return f;
    }

    public static String crearNombre(String a, String b) {
        // Crear el string para el nombre completo.
        String nombreCompleto = a + "," + b;

        return nombreCompleto;
    }
}
