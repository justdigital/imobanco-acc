package com.imobanco.autenticador.api.assembler;

import com.imobanco.autenticador.api.model.CadastroPfModel;
import com.imobanco.autenticador.api.model.domain.model.CadastroPf;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CadastroPfAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CadastroPfModel toModel(CadastroPf cadastropf) {
        return modelMapper.map(cadastropf, CadastroPfModel.class);
    }

    public List<CadastroPfModel> toCollectionModel(List<CadastroPf> cadastropfs) {
        return cadastropfs.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
    

