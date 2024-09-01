package proyecto.backend.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import proyecto.backend.dtos.*;
import proyecto.backend.models.Factura;
import proyecto.backend.models.OrdenCompra;
import proyecto.backend.models.Venta;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Definir un mapeo personalizado entre FacturaRequestDto y Factura
        modelMapper.addMappings(new PropertyMap<FacturaRequestDto, Factura>() {
            @Override
            protected void configure() {
                // Ignorar el campo idFactura en el mapeo
                skip(destination.getIdFactura());
            }
        });

        modelMapper.addMappings(new PropertyMap<VentaRequestDto, Venta>() {
            @Override
            protected void configure() {
                // Ignorar el campo idVenta en el mapeo
                skip(destination.getIdVenta());
            }
        });

        // Definimos el mapeo específico para evitar conflictos
        modelMapper.addMappings(new PropertyMap<Factura, FacturaResponseDto>() {
            @Override
            protected void configure() {
                // Mapea idFormaPago a la propiedad correspondiente en FacturaResponseDto
                map(source.getIdFormaPago().getIdFormaPago(), destination.getIdFormaPago());
                map(source.getIdCliente().getIdCliente(), destination.getIdCliente());
                map(source.getIdUsuario().getIdUsuario(), destination.getIdUsuario());
            }
        });

        modelMapper.addMappings(new PropertyMap<Venta, VentaResponseDto>() {
            @Override
            protected void configure() {
                map(source.getIdProducto().getIdProducto(), destination.getIdProducto());
            }
        });

        modelMapper.addMappings(new PropertyMap<OrdenCompra, OrdenCompraResponseDto>() {
            @Override
            protected void configure() {
                map(source.getIdProducto().getIdProducto(), destination.getIdProducto());
            }
        });

        return modelMapper;
    }
}
