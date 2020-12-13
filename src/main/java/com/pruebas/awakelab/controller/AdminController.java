package com.pruebas.awakelab.controller;

import com.pruebas.awakelab.model.Producto;
import com.pruebas.awakelab.service.IMarcaService;
import com.pruebas.awakelab.service.IProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IMarcaService marcaService;

    @GetMapping("/producto/registrar")
    public String productoModel(Model model){
        model.addAttribute("marcas",marcaService.findAll());
        model.addAttribute("titulo", "Registro de Productos");
        model.addAttribute("producto", new Producto());
        return "admin/productoIng";
    }

    @PostMapping("/producto/registrar")
    public String registrarProducto(@Valid Producto producto, BindingResult result,
                                    @RequestParam("imagen") MultipartFile imagen, Model model, RedirectAttributes flash){
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

            String uniqueFileName = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();
            Path rootPath = Paths.get("uploads").resolve(uniqueFileName);
            Path rootAbsolutePath = rootPath.toAbsolutePath();

            log.info("rootPath :",rootPath);
            log.info("rootAbsolutePath :",rootAbsolutePath);

            try {
                Files.copy(imagen.getInputStream(),rootAbsolutePath);
                flash.addFlashAttribute("info","Imagen Subida Correctamente" + uniqueFileName);
                producto.setRutaFoto(uniqueFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        productoService.register(producto);
        return "redirect:/productos/registrar";
    }

    @GetMapping("/producto/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id , RedirectAttributes flash){
        Producto producto = productoService.findById(id);
        Map<String, Object> errores = new HashMap<>();

        if (producto.getId() == null){
            errores.put("error","El id no ha sido encontrado");
            flash.addFlashAttribute("error",errores);
            return "redirect:/";
        }
        productoService.delete(id);
        return "redirect:/";
    }
}
