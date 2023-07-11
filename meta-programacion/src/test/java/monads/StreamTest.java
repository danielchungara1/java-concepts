package monads;

import org.example.universidad.Estudiante;
import org.example.universidad.Materia;
import org.example.universidad.Profesor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

    @Test
    public void dadoUnEstudianteInscriptoAVariasMateriasObtenerLosProfesoresQueLeToco() {

        Profesor edgardo = new Profesor();
        edgardo.setNombre("Edgardo L.");

        Profesor adriano = new Profesor();
        adriano.setNombre("Adriano F.");

        Materia ingenieriaSoftware = new Materia();
        ingenieriaSoftware.setNombre("Ingenieria de Software");
        ingenieriaSoftware.setProfesor(edgardo);

        Materia inteligenciaArtificial = new Materia();
        inteligenciaArtificial.setNombre("Inteligencia Artificial");
        inteligenciaArtificial.setProfesor(edgardo);

        Materia administracionRecursos = new Materia();
        administracionRecursos.setNombre("Administracion de Recursos");
        administracionRecursos.setProfesor(adriano);

        Estudiante daniel = new Estudiante();
        daniel.setNombre("Daniel");
        daniel.setApellido("Chungara");
        daniel.setEmail("danielchungara1@gmail.com");
        daniel.setNroLegajo("1366440");
        daniel.addMateria(ingenieriaSoftware);
        daniel.addMateria(inteligenciaArtificial);
        daniel.addMateria(administracionRecursos);

        String profesores = daniel.getMaterias().stream().map(Materia::getProfesor).map(Profesor::getNombre).reduce("", (acum, nextItem) -> {
            String result;
            if(!acum.contains(nextItem)) {
                result = acum + " " + nextItem;
            } else {
                result = acum + "";
            }
            return result.trim();
        });

        Assertions.assertTrue(profesores.contains("Adriano F."));
        Assertions.assertTrue(profesores.contains("Edgardo L."));

    }

    @Test
    public void flatMap() {
        List<List<String>> tareasDiarias = new ArrayList<>();
        List<String> lunes = Arrays.asList("compra titulos");
        List<String> martes = Arrays.asList("venta titulos", "compra MEP");
        List<String> miercoles = Arrays.asList("gym", "english");
        List<String> jueves = Arrays.asList("compra biberes", "english");
        List<String> viernes = Arrays.asList("gym", "english");
        List<String> sabado = Arrays.asList("compra comida rapida");
        List<String> domingo = Arrays.asList("ver peliculas");
        tareasDiarias.add(lunes);
        tareasDiarias.add(martes);
        tareasDiarias.add(miercoles);
        tareasDiarias.add(jueves);
        tareasDiarias.add(viernes);
        tareasDiarias.add(sabado);
        tareasDiarias.add(domingo);

        List<String> tareas = tareasDiarias.stream()
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(tareas);
        Assertions.assertEquals(8, tareas.size());
    }

    @Test
    public void top3Ordered() {
        List<String> nombres = Arrays.asList("Juan", "María", "Pedro", "Ana", "Carlos", "Sofía", "Luis");
//        String result = nombres.stream()
//                .sorted()
//                .distinct()
//                .reduce("", (a, b) -> !a.equals("") ? String.join(",", a, b) : b);
//        List<String> list = Arrays.asList(result.split(","));
//
//        for (int i = 0; i < 3; i++) {
//            System.out.println(list.get(i));
//        }

        List<String> resultado = nombres.stream()
                .sorted()
                .limit(3)
                .collect(Collectors.toList());

        System.out.println(resultado);
    }

}
