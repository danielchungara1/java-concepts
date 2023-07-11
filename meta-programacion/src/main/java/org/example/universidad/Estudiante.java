package org.example.universidad;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Estudiante {

    private Set<Materia> materias = new HashSet<>();
    private String nroLegajo;
    private String nombre;
    private String apellido;
    private String email;

    public void addMateria(Materia materia) {
        this.getMaterias().add(materia);
    }
}
