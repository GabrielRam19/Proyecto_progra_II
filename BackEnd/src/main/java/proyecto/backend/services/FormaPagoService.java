package proyecto.backend.services;

import proyecto.backend.dtos.FormaPagoRequestDto;
import proyecto.backend.dtos.FormaPagoResponseDto;
import proyecto.backend.models.FormaPago;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import proyecto.backend.repositories.FormaPagoRepository;

import java.util.List;

@Service
public class FormaPagoService {
    private final FormaPagoRepository formapagoRepository;
    private final ModelMapper modelMapper;
    public FormaPagoService(FormaPagoRepository formapagoRepository, ModelMapper modelMapper) {
        this.formapagoRepository = formapagoRepository;
        this.modelMapper = modelMapper;
    }

    public List<FormaPagoResponseDto> findAllFormaPago(){
        List<FormaPago> formaPagos = formapagoRepository.findAll();
        return formaPagos.stream().map(formapago->modelMapper.map(formapago, FormaPagoResponseDto.class)).toList();
    }

    public FormaPagoResponseDto findFormaPagoById(Integer id){
        FormaPago formapago = formapagoRepository.findById(id).orElse(null);
        return modelMapper.map(formapago, FormaPagoResponseDto.class);
    }

    public FormaPagoResponseDto saveFormaPago(FormaPagoRequestDto formaPagoDto){
        FormaPago formapago = modelMapper.map(formaPagoDto, FormaPago.class);
        FormaPago savedFormaPago = formapagoRepository.save(formapago);
        return modelMapper.map(savedFormaPago, FormaPagoResponseDto.class);
    }

    public FormaPagoResponseDto updateFormaPago(Integer id, FormaPagoRequestDto formaPagoDto){
        FormaPago formapago = formapagoRepository.findById(id).orElse(null);
        modelMapper.map(formaPagoDto, formapago);
        assert formapago != null;
        FormaPago savedFormaPago = formapagoRepository.save(formapago);
        return modelMapper.map(savedFormaPago, FormaPagoResponseDto.class);
    }

    public void deleteFormaPago(Integer id){
        formapagoRepository.deleteById(id);
    }
}
