package com.levelupgamer.backend.controller;

import com.levelupgamer.backend.dto.ProductoRequestDto;
import com.levelupgamer.backend.dto.ProductoResponseDto;
import com.levelupgamer.backend.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "Operaciones relacionadas con los productos")
public class ProductoController {

    private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los productos", description = "Devuelve la lista completa de productos")
    public List<ProductoResponseDto> getAllProductos() {
        logger.info("GET /api/productos - Obtener todos los productos");
        return productoService.getAllProductos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por ID", description = "Devuelve un producto específico según el ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<ProductoResponseDto> getProductoById(@PathVariable Long id) {
        logger.info("GET /api/productos/{} - Consultando producto", id);
        return productoService.getProductoById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> {
                logger.warn("Producto no encontrado para ID: {}", id);
                return ResponseEntity.notFound().build();
            });
    }

    @PostMapping
    @Operation(summary = "Crear producto", description = "Agrega un nuevo producto")
    @ApiResponse(responseCode = "201", description = "Producto creado exitosamente")
    public ProductoResponseDto createProducto(@RequestBody ProductoRequestDto dto) {
        logger.info("POST /api/productos - Creando producto: {}", dto.getNombre());
        return productoService.saveProducto(dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto", description = "Elimina un producto por su identificador")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        logger.info("DELETE /api/productos/{} - Eliminando producto", id);
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/destacados")
    @Operation(summary = "Obtener productos destacados", description = "Devuelve sólo productos destacados")
    public List<ProductoResponseDto> getProductosDestacados() {
        logger.info("GET /api/productos/destacados - Productos destacados");
        return productoService.getProductosDestacados();
    }

}
