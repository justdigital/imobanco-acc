package com.imobanco.autenticador.api.assembler;

import com.imobanco.autenticador.api.model.domain.model.CadastroPf;
import com.imobanco.autenticador.api.model.input.CadastroPfInput;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CadastroPfDisassembler {
    @Autowired
    private ModelMapper modelMapper;
 
    public CadastroPf toDomainObject(CadastroPfInput cadastroPfInput) {

        modelMapper.getConfiguration()// agora foi
                .setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper.map(cadastroPfInput, CadastroPf.class);
    }

    public void copyToDomainObject(CadastroPfInput cadastroPfInput, CadastroPf cadastroPf) {
        modelMapper.createTypeMap(CadastroPfInput.class, CadastroPf.class);



        modelMapper.map(cadastroPfInput, cadastroPf);
    }


}
