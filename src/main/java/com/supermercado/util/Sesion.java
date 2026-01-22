package com.supermercado.util;

import com.supermercado.modelos.Empleado;

public class Sesion {
    private static Empleado empleadoLogueado;

    public static void setEmpleado(Empleado empleado) {
        empleadoLogueado = empleado;
    }

    public static Empleado getEmpleado() {
        return empleadoLogueado;
    }
}