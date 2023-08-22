package com.imobanco.autenticador.infrastructure.consumer;

import com.amazonaws.services.sqs.AmazonSQS;
import com.imobanco.autenticador.api.client.response.BasicData;
import com.imobanco.autenticador.api.client.response.PeopleBasicDataResponse;
import com.imobanco.autenticador.api.model.domain.model.CadastroPf;
import com.imobanco.autenticador.api.model.input.FilaArquivoInput;
import com.imobanco.autenticador.infrastructure.Aws;
import com.imobanco.autenticador.service.BigDataFeignClientService;
import com.imobanco.autenticador.service.CadastroPfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@PropertySource("classpath:application.properties")
public class ConsumidorFilaIdentidade {

    private AmazonSQS amazonSQS;
    @Autowired
    private Aws aws;

    @Autowired
    public ConsumidorFilaIdentidade(AmazonSQS amazonSQS) {
        this.amazonSQS = amazonSQS;
    }

    @Autowired
    private CadastroPfService cadastroPfService;

    @Value("${aws.sqs.fila.identidade.dlq.pf}")
    private String filaUrlIdentidadeDlq;

    @Autowired
    BigDataFeignClientService bigDataFeignClientService;


    @SqsListener({"${aws.sqs.fila.identidade.pf}"})
    @Transactional
    public void receberMensagemIdentidade(String mensagem) {

        try {
            log.info("status=mensagem recebida, fila={}, mensagem{}", "${aws.sqs.fila.identidade.pf}", mensagem);
            Long idCadastro = Long.valueOf(mensagem);
            CadastroPf cadastroPf = cadastroPfService.buscarPorIdOuFalhar(idCadastro);
            PeopleBasicDataResponse peopleBasicDataResponse = bigDataFeignClientService.requestDadosPessoa(cadastroPf.getNumeroCpfUsu());

            if (!peopleBasicDataResponse.getResults().isEmpty()) {

                BasicData basicData = peopleBasicDataResponse.getResults().get(0).getBasicData();
                if (cadastroPf.seNomeIguail(basicData.getName())  // primeira validação - Nome
                        && cadastroPf.seDataNascimentoIgual(basicData.getBirthDate()) //segunda validação - Data de nascimento
                        && cadastroPf.seNomeMaeIgual(basicData.getMotherName())  //terceira validação - Nome da Mãe
                        && basicData.getTaxIdStatus().equalsIgnoreCase("REGULAR")  // quarta validação - CPF está regular
                        && cadastroPf.getGeneroUsuPf().getSiglaGenero().equalsIgnoreCase(basicData.getGender())) //quinta validação - verifica se o sexo é o mesmo informado
                {
                    // o que fazer ?

                } else {
                    aws.filaEnviarMensagem(mensagem, filaUrlIdentidadeDlq);
                }
            }

        } catch (Exception e) {
            aws.filaEnviarMensagem(mensagem, filaUrlIdentidadeDlq);
            log.info("status=mensagem não processada, fila={}, erro{}", "${aws.sqs.fila.identidade.pf}", e.getCause());
        }

    }
}
