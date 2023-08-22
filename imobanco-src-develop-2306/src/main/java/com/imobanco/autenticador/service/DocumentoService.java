package com.imobanco.autenticador.service;

import com.google.gson.Gson;
import com.imobanco.autenticador.api.assembler.CadastroPfAssembler;
import com.imobanco.autenticador.api.model.ArquivoModel;
import com.imobanco.autenticador.api.model.domain.enums.TipoDocumentoEnum;
import com.imobanco.autenticador.api.model.domain.exception.DefaultException;
import com.imobanco.autenticador.api.model.input.ArquivoInput;
import com.imobanco.autenticador.api.model.input.DocumentoInput;
import com.imobanco.autenticador.api.model.input.FilaArquivoInput;
import com.imobanco.autenticador.infrastructure.Aws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@AllArgsConstructor
@PropertySource("classpath:application.properties")
public class DocumentoService {

    @Autowired
    private Aws aws;
    @Autowired
    private CadastroPfAssembler cadastroPfAssembler;
    @Value("${aws.bucket.documento.pf}")
    private String bucketDocumentoIdentidade;

    @Value("${aws.bucket.comprovante.endereco}")
    private String bucketComprovanteEndereco;

    @Value("${aws.sqs.fila.documento.pf}")
    private String filaUrlDocumento;

    @Value("${aws.sqs.fila.endereco.pf}")
    private String filaUrlComprovanteEndereco;

    @Transactional
    public void salvar(DocumentoInput documentoInput) {

        documentoInput.getImagemDocumento().stream().forEach(
                arquivoInput -> {

                    aws.filaEnviarMensagem(new Gson().toJson(contruirMensagemFila(documentoInput.getIdCadastro(),arquivoInput)),
                             arquivoInput.getTipoDocumento().equalsIgnoreCase(TipoDocumentoEnum.COMPROVANTE_ENDERECO.name())?
                                    filaUrlComprovanteEndereco: filaUrlDocumento);

        });

       // String mensagemFila = new Gson().toJson(contruirMensagemFila(documentoInput));


      //  aws.filaEnviarMensagem(mensagemFila,filaUrl );

    }

    public List<ArquivoModel> buscarPorId(Long cadastroPfId, TipoDocumentoEnum tipoDocumentoEnum) {

        return aws.downloadArquivos(bucketDocumentoIdentidade,tipoDocumentoEnum.getChave().concat("-").concat(cadastroPfId.toString()));

    }

    @Transactional
    public void atualizar (Long cadastroPfId, DocumentoInput documentoInput) throws Exception{


    }

    public void verificarTipoDocumentoEnumValido(DocumentoInput documentoInput){

        List<String> nomesTipoDocumentoEnumArrays = Arrays.stream(TipoDocumentoEnum.values())
                .map( tipoDocumentoEnum -> tipoDocumentoEnum.name()).collect(Collectors.toList());

        documentoInput.getImagemDocumento().stream().forEach(
                arquivoInput -> {
                    if(!nomesTipoDocumentoEnumArrays.stream().anyMatch( s -> arquivoInput.getTipoDocumento().equals(s)))
                        throw new DefaultException(HttpStatus.UNPROCESSABLE_ENTITY, "Valor enviado para tipoDocumento [".concat(arquivoInput.getTipoDocumento())
                                .concat("] Inválido. Valores aceitos para TipoDocumento são:[")
                                .concat(nomesTipoDocumentoEnumArrays.stream()
                                        .reduce(  (s, s2) -> s + "," + s2).get()).concat("]") );

                });


    }

   /* private FilaArquivoInput contruirMensagemFila(DocumentoInput documentoInput){

        StringBuilder chaveObjeto = new StringBuilder();
        chaveObjeto.append(documentoInput.getImagemDocumento().getTipoDocumento().getChave())
                .append("-")
                .append(documentoInput.getIdCadastro().toString());

        return FilaArquivoInput.builder().arquivo(documentoInput.getImagemDocumento().getArquivo() )
                .nomeBucket(documentoInput.getImagemDocumento().getTipoDocumento().equals(TipoDocumentoEnum.COMPROVANTE_ENDERECO)?
                        bucketComprovanteEndereco:bucketDocumentoIdentidade )
                .chaveObjetoBucket(chaveObjeto.toString())
                .extensao(documentoInput.getImagemDocumento().getExtensao())
                .build();

    }*/

    private FilaArquivoInput contruirMensagemFila(Long idCadastroPF, ArquivoInput arquivoInput){

        StringBuilder chaveObjeto = new StringBuilder();
        chaveObjeto.append(TipoDocumentoEnum.valueOf(arquivoInput.getTipoDocumento()).getChave())
                .append("-")
                .append(idCadastroPF);

        return FilaArquivoInput.builder().arquivo(arquivoInput.getArquivo() )
                .nomeBucket(arquivoInput.getTipoDocumento().equals(TipoDocumentoEnum.COMPROVANTE_ENDERECO)?
                        bucketComprovanteEndereco:bucketDocumentoIdentidade )
                .chaveObjetoBucket(chaveObjeto.toString())
                .extensao(arquivoInput.getExtensao())
                .idCadastro(idCadastroPF)
                .build();

    }

    public boolean isDocumentosCadastrados(Long idCadastro){
        return isCNHCadastrada(idCadastro) || isRGCadastrado(idCadastro);

    }

    private boolean isCNHCadastrada(Long idCadastro){

       return !aws.listarArquivosPorPrefixo(bucketDocumentoIdentidade,
                TipoDocumentoEnum.CNH.getChave().concat("-").concat(idCadastro.toString()).concat("."))
                .getObjectSummaries().isEmpty();

    }

    private boolean isRGCadastrado(Long idCadastro){

        return !aws.listarArquivosPorPrefixo(bucketDocumentoIdentidade,
                        TipoDocumentoEnum.RG_FRENTE.getChave().concat("-").concat(idCadastro.toString()).concat("."))
                .getObjectSummaries().isEmpty() &&
                !aws.listarArquivosPorPrefixo(bucketDocumentoIdentidade,
                                TipoDocumentoEnum.RG_VERSO.getChave().concat("-").concat(idCadastro.toString()).concat("."))
                        .getObjectSummaries().isEmpty();

    }

     public void listar() {


    }

    public void excluir (Long clienteId, TipoDocumentoEnum tipoDocumentoEnum ){

        aws.removerArquivos(tipoDocumentoEnum.equals(TipoDocumentoEnum.COMPROVANTE_ENDERECO)?
                bucketComprovanteEndereco:bucketDocumentoIdentidade,tipoDocumentoEnum.getChave().concat("-").concat(clienteId.toString()));


    }


}
