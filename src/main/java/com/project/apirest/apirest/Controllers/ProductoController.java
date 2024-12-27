package com.project.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.apirest.apirest.Repositories.ProductoRepository;
import com.project.apirest.apirest.Entities.Producto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> obtenerProductos(){
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id){
        return productoRepository.findById(id)
        .orElseThrow(() ->  new RuntimeException("No se encontro el producto con el ID: " + id));
    }

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto detalleProducto){
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() ->  new RuntimeException("No se encontro el producto con el ID: " + id));

        producto.setNombre(detalleProducto.getNombre());
        producto.setPrecio(detalleProducto.getPrecio());

        return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public String borrarProducto (@PathVariable Long id){
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() ->  new RuntimeException("No se encontro el producto con el ID: " + id));

        productoRepository.delete(producto);
        return "El producto con el ID: " + id + " fue eliminado exitosamente";
    }   

}
