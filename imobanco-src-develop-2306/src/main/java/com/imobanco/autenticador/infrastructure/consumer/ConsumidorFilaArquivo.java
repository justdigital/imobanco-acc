package com.imobanco.autenticador.infrastructure.consumer;

import com.google.gson.Gson;
import com.imobanco.autenticador.api.model.input.FilaArquivoInput;
import com.imobanco.autenticador.infrastructure.Aws;
import com.imobanco.autenticador.service.AtividadeCadastroService;
import com.imobanco.autenticador.service.DocumentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;

import javax.transaction.Transactional;

@Service
@Slf4j
@PropertySource("classpath:application.properties")
public class ConsumidorFilaArquivo {

    private AmazonSQS amazonSQS;
    @Autowired
    private Aws aws;
    @Autowired
    public ConsumidorFilaArquivo(AmazonSQS amazonSQS) {
        this.amazonSQS = amazonSQS;
    }

    @Autowired
    AtividadeCadastroService atividadeCadastroService;

    @Autowired
    private DocumentoService documentoService;

    @Value("${aws.sqs.fila.identidade.pf}")
    private String filaUrlIdentidade;

    @Value("${aws.sqs.fila.documento.pf.dlq}")
    private String filaUrlDocumentoDlq;

    @Value("${aws.sqs.fila.endereco.pf.dlq}")
    private String filaUrlEnderecoDlq;

   @SqsListener( {"${aws.sqs.fila.endereco.pf}"})
   @Transactional
    public void receberMensagemEndereco(String mensagem) {
        try {
            log.info("status=mensagem recebida, fila={}, mensagem{}",  "${aws.sqs.fila.endereco.pf}",mensagem);
            FilaArquivoInput filaArquivoInput = uploadArquivo(mensagem);
            atividadeCadastroService.finalizarAtividadeAtual(filaArquivoInput.getIdCadastro());
            atividadeCadastroService.salvar(filaArquivoInput.getIdCadastro(), "CADASTRO_COMPROVANTE_ENDERECO");
        } catch (Exception e) {
            aws.filaEnviarMensagem( mensagem,filaUrlEnderecoDlq );
            log.info("status=mensagem não processada, fila={}, erro{}",  "${aws.sqs.fila.endereco.pf}",e.getCause());
        }
    }

    @SqsListener( {"${aws.sqs.fila.documento.pf}"})
    @Transactional
    public void receberMensagemDocumento(String mensagem) {
       try {
           log.info("status=mensagem recebida, fila={}, mensagem{}",  "${aws.sqs.fila.documento.pf}",mensagem);
           FilaArquivoInput filaArquivoInput = uploadArquivo(mensagem);
           if(documentoService.isDocumentosCadastrados(filaArquivoInput.getIdCadastro())) {
               atividadeCadastroService.finalizarAtividadeAtual(filaArquivoInput.getIdCadastro());
               atividadeCadastroService.salvar(filaArquivoInput.getIdCadastro(), "UPLOAD");
               aws.filaEnviarMensagem( filaArquivoInput.getIdCadastro().toString(),filaUrlIdentidade );
           } else {
               aws.filaEnviarMensagem( mensagem,filaUrlDocumentoDlq );
           }
       } catch (Exception e) {
           aws.filaEnviarMensagem( mensagem,filaUrlDocumentoDlq );
           log.info("status=mensagem não processada, fila={}, erro{}",  "${aws.sqs.fila.documento.pf}",e.getCause());
       }
    }

    private FilaArquivoInput uploadArquivo(String mensagem){
        FilaArquivoInput filaArquivoInput = new Gson().fromJson(mensagem, FilaArquivoInput.class);
        aws.uploadArquivos(filaArquivoInput);
        return filaArquivoInput;
    }


}
