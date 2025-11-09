package com.levelupgamer.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.levelupgamer.backend.dto.ProductoRequestDto;
import com.levelupgamer.backend.dto.ProductoResponseDto;
import com.levelupgamer.backend.model.Producto;
import com.levelupgamer.backend.repository.ProductoRepository;

@Service
public class ProductoService {

    private static final Logger logger = LoggerFactory.getLogger(ProductoService.class);

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Transformar entidad a DTO para respuesta
    public ProductoResponseDto toResponseDto(Producto producto) {
        ProductoResponseDto dto = new ProductoResponseDto();
        dto.setId(producto.getId());
        dto.setImagen(producto.getImagen());
        dto.setCodigo(producto.getCodigo());
        dto.setCategoria(producto.getCategoria());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setDestacado(producto.getDestacado());
        return dto;
    }

    // Transformar DTO de solicitud a entidad para guardar
    public Producto toEntity(ProductoRequestDto dto) {
        Producto producto = new Producto();
        producto.setImagen(dto.getImagen());
        producto.setCodigo(dto.getCodigo());
        producto.setCategoria(dto.getCategoria());
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setDestacado(dto.getDestacado());
        return producto;
    }

    // Obtener todos los productos como lista de DTOs
    public List<ProductoResponseDto> getAllProductos() {
        logger.info("Obteniendo todos los productos");
        return productoRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    // Buscar producto por id, devuelve DTO
    public Optional<ProductoResponseDto> getProductoById(Long id) {
        logger.info("Buscando producto con ID: {}", id);
        return productoRepository.findById(id).map(this::toResponseDto);
    }

    // Guardar producto a partir del DTO de petición, devuelve DTO
    public ProductoResponseDto saveProducto(ProductoRequestDto dto) {
        logger.info("Guardando producto: {}", dto.getNombre());
        Producto producto = toEntity(dto);
        Producto savedProducto = productoRepository.save(producto);
        return toResponseDto(savedProducto);
    }

    // Eliminar producto por id
    public void deleteProducto(Long id) {
        logger.info("Eliminando producto con ID: {}", id);
        productoRepository.deleteById(id);
    }

    // Obtener productos destacados como DTO
    public List<ProductoResponseDto> getProductosDestacados() {
        logger.info("Buscando productos destacados");
        return productoRepository.findAll()
                .stream()
                .filter(Producto::getDestacado)
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
}
