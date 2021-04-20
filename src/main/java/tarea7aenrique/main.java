/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea7aenrique;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        try (Scanner datosFichero = new Scanner(new File(idFichero), "ISO-8859-1")) {
            datosFichero.nextLine();
            while (datosFichero.hasNextLine()) {

                linea = datosFichero.nextLine();

                tokens = linea.split(",");
                Empleado tmp = new Empleado();

                // Crear el string para el nombre completo.
                // Creamos dos String que cogan el token 0 (Apellidos) y el tokens 1 (nombre) para juntarlo en el set
                tmp.setApellidos(quitarComillas(tokens[0]));
//                System.out.println(ape);
                tmp.setNombre(quitarComillas(tokens[1]));
//                System.out.println(nombre);

                tmp.setDNI(quitarComillas(tokens[2]));
                tmp.setPuesto(quitarComillas(tokens[3]));
                // Usamos el método crearFecha el cual devuelve una fecha a partir del String dado con la fecha en él.
                tmp.setFechaIni(crearFecha(tokens[4]));

                // Creamos un if para cuando el empleado aun no se marchase saliera el valor máximo. En caso de que se fuera utiliza el m´ñetodo crearFecha
                if (tokens[5].length() == 2) {
                    tmp.setFechaFin(LocalDate.MAX);
                } else {
                    tmp.setFechaFin(crearFecha(tokens[5]));
                }
                tmp.setTlf(quitarComillas(tokens[6]));
//                System.out.println(tokens[6]);
                tmp.setEvaluador(verdaderoFalso(quitarComillas(tokens[7])));
//                System.out.println(tokens[7]);
                tmp.setCoord(verdaderoFalso(quitarComillas(tokens[8])));
//                System.out.println(tokens[8]);

                empleados.add(tmp);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
//        System.out.println(empleados.size());
//        empleados.forEach(System.out::println);

        String nomFichero = "RelPerCen2.csv";
        LocalDate hoy = LocalDate.now();
        LocalDate añosAtras = LocalDate.of(hoy.getYear() - 20, hoy.getMonth(), hoy.getDayOfMonth());
//        System.out.println(añosAtras);

        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(nomFichero))) {
            for (Empleado e : empleados) {
                if (añosAtras.compareTo(e.getFechaIni()) > 0 && e.getFechaFin().compareTo(LocalDate.MAX) == 0) {
                    flujo.write(e.toString());
                    flujo.newLine();
                }
            }
            flujo.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        /*
        AMPLIACIÓN DEL EJERCICIO.
        a) Sin usar API Stream
 - Contar el número de profesores de Informática.
 - Saber si algún profesor/a de Biología es también coordinador
 - Obtener una lista ordenada alfabéticamente con todos los apellidos de los empleados cuyo NIF contenga la letra N.
 - Verificar que ningún profesor se llama "Jonh".

        b) Repetir el apartado a) usando API Stream        
         */
        System.out.println("Sin API Stream");
        System.out.println("");
        // Contar el número de profesores de Informática.
        String infor = "Informática P.E.S.";
        int contador = 0;
        for (Empleado e : empleados) {

            if (e.getPuesto().equals(infor)) {
                contador++;
            }
        }
        System.out.println("El número de profesores de informática es " + contador);
        System.out.println("");

        // Saber si algún profesor/a de Biología es también coordinador
        String bio = "Bio";
        boolean cierto = false;
        for (Empleado e : empleados) {
            // Existe 1 caso en el que si.
            if (e.getPuesto().contains(bio) && e.getCoord().equals(true)) {
                cierto = true;
            }
        }

        if (cierto) {
            System.out.println("Si existe un coordinador que de la asignatura de Biología");
        } else {
            System.out.println("No existe un coordinador que de la asignatura de Biología");
        }
        System.out.println("");

        // Obtener una lista ordenada alfabéticamente con todos los apellidos de los empleados cuyo NIF contenga la letra N.
        ArrayList<Empleado> empl = new ArrayList<>();
        Comparator<Empleado> criterioPrecio = (c1, c2) -> c1.getApellidos().compareTo(c2.getApellidos());
        Collections.sort(empleados, criterioPrecio);

        for (Empleado e : empleados) {
            if (e.getDNI().contains("N")) {
                empl.add(e);
            }
        }
        empl.forEach(System.out::println);
        System.out.println("");

        // Verificar que ningún profesor se llama "Jonh".
        int contadorJonh = 0;
        for (Empleado e : empleados) {
            if (e.getNombre().equals("Jonh")) {
                contadorJonh++;
            }
        }
        System.out.println("Existen en total " + contadorJonh + " trabajadores que se llamen Jonh");
        System.out.println("");
        System.out.println("");

        // b) Repetir el apartado a) usando API Stream  
        System.out.println("Con API Stream");
        System.out.println("");

        System.out.println("Número de empleados Informáticos " + empleados.stream().filter(p->p.getPuesto().equals(infor)).count());
        System.out.println("");

        // Saber si algún profesor/a de Biología es también coordinador
        if(empleados.stream().filter(p -> p.getPuesto().contains("Biología") && p.getCoord().equals(true)).count() < 1){
            System.out.println("No existe un coordinador que de la asignatura de Biología");
        }else{
            System.out.println("Si existe un coordinador que de la asignatura de Biología");
            
        }
        System.out.println("");

        // Obtener una lista ordenada alfabéticamente con todos los apellidos de los empleados cuyo NIF contenga la letra N.
        System.out.println("Empleados con la letra N en el DNI");
        List<String> listnom = empleados.stream().filter(p -> p.getDNI().contains("N")).map(p -> p.getApellidos()).sorted().collect(Collectors.toList());
        listnom.forEach(System.out::println);
        System.out.println("");

        // Verificar que ningún profesor se llama "Jonh".
        System.out.println("Existen en total " + empleados.stream().filter(p -> p.getNombre().contains("Jonh")).count() + " trabajadores que se llamen Jonh");
        System.out.println("");
        
        // Lista de las fechas de Cese que hay en los empleados.
        List<LocalDate> fechasCese = empleados.stream().map(p -> p.getFechaFin()).filter(p-> p != LocalDate.MAX).collect(Collectors.toList());
        System.out.println("Fecha de cese de los empleados");
        fechasCese.forEach(System.out::println);
        System.out.println("");
        
        // Apellidos de los empleados que cesan mañana su trabajo.
        int masUnDia = 1;
        LocalDate fecManana= LocalDate.now().plusDays(masUnDia);
        List<String> CeseMañana = empleados.stream().filter(p-> p.getFechaFin().equals(fecManana)).map(p -> p.getApellidos()).collect(Collectors.toList());
        System.out.println("Apellidos de los empleados que cesan mañana su trabajo");
        CeseMañana.forEach(System.out::println);
        System.out.println("");
        
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

    public static Boolean verdaderoFalso(String a) {
        return !a.equals("No");
    }

    public static String quitarComillas(String a) {
        String sinComillas = "";

        String[] palabra;
        palabra = a.split("");

        for (int i = 1; i < palabra.length - 1; i++) {
            sinComillas += palabra[i];
        }

        return sinComillas;
    }
}
