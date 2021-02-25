package com.hardteach.school.objects_test;

import com.hardteach.school.common.Constantes;
import com.hardteach.school.entities.Asignatura;

public class AsignaturaObjetosTest {

    public AsignaturaObjetosTest(){}

    public static Asignatura asignaturaValida(){
        return new Asignatura("12345","Calculo",4,null,null);
    }

    public static Asignatura asignaturaSinNombre(){
        return new Asignatura("12345",null,4,null,null);
    }

    public static Asignatura asignaturaSinId(){
        return new Asignatura(null,"Calculo",4,null,null);
    }

    public static Asignatura asignaturaCreditoNegativo(){
        return new Asignatura("12345","Calculo",-1,null,null);
    }

    public static Asignatura asignaturaCreditoMayorTopeSup(){
        return new Asignatura("12345","Calculo", Constantes.NUM_MAX_CREDITOS_ASIGNATURA+1,null,null);
    }
}
