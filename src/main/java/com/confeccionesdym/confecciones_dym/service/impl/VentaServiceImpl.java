package com.confeccionesdym.confecciones_dym.service.impl;

import com.confeccionesdym.confecciones_dym.dto.sale.SaleRequestDto;
import com.confeccionesdym.confecciones_dym.dto.sale.SaleResponseDto;
import com.confeccionesdym.confecciones_dym.exception.*;
import com.confeccionesdym.confecciones_dym.mapper.VentaMapper;
import com.confeccionesdym.confecciones_dym.model.entity.Cliente;
import com.confeccionesdym.confecciones_dym.model.entity.Prenda;
import com.confeccionesdym.confecciones_dym.model.entity.Usuario;
import com.confeccionesdym.confecciones_dym.model.entity.Venta;
import com.confeccionesdym.confecciones_dym.repository.ClienteRepository;
import com.confeccionesdym.confecciones_dym.repository.PrendaRepository;
import com.confeccionesdym.confecciones_dym.repository.UsuarioRepository;
import com.confeccionesdym.confecciones_dym.repository.VentaRepository;
import com.confeccionesdym.confecciones_dym.service.VentaService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.aop.config.AdviceEntry;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;
    private final VentaMapper ventaMapper;
    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;
    private final PrendaRepository prendaRepository;

    @Override
    public List<SaleResponseDto> listAll() {
        return this.ventaMapper.toSalesResponseDto(this.ventaRepository.findAll());
    }

    @Override
    public SaleResponseDto findById(Integer id) {
        if(id==null || id<=0) throw new BadRequestException("El id es incorrecto");
        return this.ventaMapper.toSaleResponseDto(this.ventaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró la venta con el id: "+id)
        ));
    }

    @Override
    @Transactional
    public SaleResponseDto save(SaleRequestDto saleRequestDto) {

        Venta venta = this.ventaMapper.toVenta(saleRequestDto);

        asignarRelaciones(saleRequestDto, venta);

        try {
            return this.ventaMapper.toSaleResponseDto(this.ventaRepository.save(venta));
        }
        catch (DataIntegrityViolationException ex) {
            throw new DuplicateResourceException("Venta ya registrada anteriormente");
        }
        catch (Exception exception) {
            throw new InternalServerErrorException("Error inesperado al guardar la venta");
        }
    }

    @Override
    @Transactional
    public SaleResponseDto update(Integer id, SaleRequestDto saleRequestDto) {
        if(id==null || id<=0) throw new BadRequestException("El id es incorrecto");
        return this.ventaMapper.toSaleResponseDto(this.ventaRepository.findById(id)
                .map(venta -> {
                    this.ventaMapper.updateVentaFromDto(saleRequestDto, venta);
                    asignarRelaciones(saleRequestDto, venta);
                    try {
                        return this.ventaRepository.save(venta);
                    }
                    catch (DataIntegrityViolationException ex) {
                        throw new DuplicateResourceException("Venta ya se encuentra registrada");
                    }
                    catch (Exception exception) {
                        throw new InternalServerErrorException("Error inesperado al actualizar la venta");
                    }
                })
                .orElseThrow(
                        () -> new ResourceNotFoundException("No se encontró la venta con el id: "+id)
                )
        );
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if(id==null || id<=0) throw new BadRequestException("El id es incorrecto");
        Venta venta = this.ventaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró la venta con el id: " + id)
        );
        try {
            this.ventaRepository.delete(venta);
            this.ventaRepository.flush();
        }
        catch (DataIntegrityViolationException ex) {
            throw new ConflictException("No se puede eliminar el cliente porque hubo conflicto");
        }
        catch (Exception exception) {
            throw new InternalServerErrorException("Error inesperado al eliminar la venta");
        }
    }

    @Override
    public List<SaleResponseDto> listAllByTipoPago(String tipoPago) {
        return this.ventaMapper.toSalesResponseDto(this.ventaRepository.findAllByTipoPagoOrderByTotalPagoDesc(tipoPago));
    }

    @Override
    public List<SaleResponseDto> listByDateBetween(String fechaInicial, String fechaFinal) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        LocalDateTime initialDate;
        LocalDateTime endDate;

        try {
            initialDate = LocalDateTime.parse(fechaInicial, formatter);
            endDate = LocalDateTime.parse(fechaFinal, formatter);
        } catch (DateTimeParseException e) {
            throw new BadRequestException("Formato incorrecto: Formato requerido (dd/MM/yyyy HH:mm:ss)");
        }

        return this.ventaMapper.toSalesResponseDto(this.ventaRepository.findAllByFechaEmisionBetween(initialDate, endDate));
    }

    @Override
    public Page<SaleResponseDto> listPagSales(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);

        return this.ventaRepository.findAll(pageRequest).map(this.ventaMapper::toSaleResponseDto);
    }


    private void asignarRelaciones(@NotNull SaleRequestDto saleRequestDto,@NotNull Venta venta) {

        Usuario usuario = this.usuarioRepository.findById(saleRequestDto.idUser()).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró el usuario con el id: "+saleRequestDto.idUser())
        );
        venta.setUsuario(usuario);

        Cliente cliente = this.clienteRepository.findById(saleRequestDto.idClient()).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró el cliente con el id: "+saleRequestDto.idClient())
        );
        venta.setCliente(cliente);

        Prenda prenda = this.prendaRepository.findById(saleRequestDto.idGarment()).orElseThrow(
                () -> new ResourceNotFoundException("No se encontró la prenda con el id: "+saleRequestDto.idGarment())
        );
        venta.setPrenda(prenda);
    }

}
