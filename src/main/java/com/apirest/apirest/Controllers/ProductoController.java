package com.apirest.apirest.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.apirest.apirest.Entities.Producto;
import com.apirest.apirest.Repositories.ProductoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getAllProductos(){
        return productoRepository.findAll();
    }

    @PostMapping
    public Producto createProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }

    @GetMapping("/{id}")
    public Producto getProductById(@PathVariable Long id){
        return productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se encontro el Producto con el id: "+ id));
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto producto){
        
        Producto productoUpdate = productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se encontro el Producto con el id: "+ id));
        
        productoUpdate.setNombre(producto.getNombre());
        productoUpdate.setPrecio(producto.getPrecio());

        return productoRepository.save(productoUpdate);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){

        Producto productDelete = productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se encontro el Producto con el id: "+ id));

        productoRepository.delete(productDelete);

        return "El producto con el id: " +id+ " fue eliminado correctamente";
    }

}
