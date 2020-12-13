package com.pruebas.awakelab;

import com.pruebas.awakelab.model.Marca;
import com.pruebas.awakelab.model.Producto;
import com.pruebas.awakelab.repo.IMarcaRepo;
import com.pruebas.awakelab.repo.IProductoRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

@SpringBootTest
class AwakelabApplicationTests {

    @Autowired
    private IProductoRepo repo;

    @Autowired
    private IMarcaRepo marcaRepo;

    @Test
    void buscarProductos() {

        List<Producto> productos = repo.findByNombreStartingWith("spi");

        for (Producto p:
             productos) {
            System.out.println(p.getNombre());
        }
    }

    @Test
    void ingresarProducto(){
        Producto producto = new Producto();
        Marca marca = marcaRepo.findById(1).get();

        // producto.setId(8);
        producto.setMarca(marca);
        producto.setNombre("ant-man");
        producto.setRutaFoto("antman.jpg");
        producto.setStock(67);
        producto.setPrecio(12990f);

        repo.save(producto);
    }

}
