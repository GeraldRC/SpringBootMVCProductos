package com.pruebas.awakelab.controller;

import com.pruebas.awakelab.model.Producto;
import com.pruebas.awakelab.service.IMarcaService;
import com.pruebas.awakelab.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private IProductoService service;

    @Autowired
    private IMarcaService marcaService;

    @GetMapping
    public String findAll(Model model, Pageable pageable){
        model.addAttribute("titulo", "Productos");
        model.addAttribute("productos", service.listarPageable(pageable));
        return "productos";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id, Model model){
        Producto producto = service.findById(id);
        model.addAttribute("producto",producto);
        model.addAttribute("titulo",producto.getNombre());
        return "detalle";
    }

    @GetMapping("/busqueda")
    public String findByPattern(@RequestParam String buscar, Model model){
        List<Producto> productos = service.findByPattern(buscar);
        model.addAttribute("productos",productos);
        model.addAttribute("titulo","Resultados Busqueda");

        return "productos";
    }

    @GetMapping("/registrar")
    public String productoModel(Model model){
        model.addAttribute("marcas",marcaService.findAll());
        model.addAttribute("titulo", "Registro de Productos");
        model.addAttribute("producto", new Producto());
        return "admin/productoIng";
    }

    @PostMapping("/registrar")
    public String registrarProducto(@Valid Producto producto, BindingResult result,
                                    @RequestParam("imagen") MultipartFile imagen, Model model){
        if (result.hasErrors()){
            Map<String, Object> errores = new HashMap<>();
            result.getFieldErrors().forEach(fieldError -> {
                errores.put(fieldError.getField(),fieldError.getDefaultMessage());
            });
            model.addAttribute("error", errores);
            model.addAttribute("marcas",marcaService.findAll());
            return "admin/productoIng";
        }
        if (!imagen.isEmpty()){
            Path directorioRecurso = Paths.get("src//main//resources//static/uploads");
            String rootPath = directorioRecurso.toFile().getAbsolutePath();
            try {
                byte[] bytes = imagen.getBytes();
                Path rutaCompleta = Paths.get(rootPath + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytes);
                producto.setRutaFoto(imagen.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Producto obj = service.register(producto);
        return "redirect:/productos";
    }
}
