package com.pruebas.awakelab.controller;

import com.pruebas.awakelab.model.Producto;
import com.pruebas.awakelab.service.IMarcaService;
import com.pruebas.awakelab.service.IProductoService;
import com.pruebas.awakelab.util.paginator.PageRender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/productos")
public class ProductoController {

     private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private IProductoService service;

    @Autowired
    private IMarcaService marcaService;

    @GetMapping
    public String findAll(Model model,@RequestParam(name = "page", defaultValue = "0") Integer page){

        Pageable pageRequest = PageRequest.of(page,5);

        Page<Producto> productos = service.listarPageable(pageRequest);

        PageRender<Producto> pageRender = new PageRender<>("/productos", productos);

        model.addAttribute("titulo", "Productos");
        model.addAttribute("productos",productos);
        model.addAttribute("page", pageRender);
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

    @GetMapping("/busqueda/marca")
    public String findByMarca(@RequestParam String marca, Model model){
        List<Producto> productos = service.findByMarca(marca);
        model.addAttribute("productos",productos);
        model.addAttribute("titulo","Resultados Busqueda");
        return "productos";
    }


}
