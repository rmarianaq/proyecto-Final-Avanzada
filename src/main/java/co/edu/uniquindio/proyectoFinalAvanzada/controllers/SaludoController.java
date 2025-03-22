package co.edu.uniquindio.proyectoFinalAvanzada.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController //Indica que esta clase es un controlador REST, lo que significa que manejará solicitudes HTTP y devolverá respuestas en formato de texto o JSON.
@RequestMapping("/saludo") // Prefijo común para todas las rutas del controlador
public class SaludoController {
    @GetMapping
    public String saludar(){
        return "Hola, bienvenido a la aplicación";
    }


    @GetMapping("/{nombre}")
    public String saludarNombre(@PathVariable String nombre){
        return "Hola %s, bienvenido a la aplicación".formatted(nombre);
    }


}

