package com.imobanco.autenticador.infrastructure;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.util.IOUtils;
import com.imobanco.autenticador.api.model.ArquivoModel;
import com.imobanco.autenticador.api.model.domain.exception.DefaultException;
import com.imobanco.autenticador.api.model.input.ArquivoInput;
import com.imobanco.autenticador.api.model.input.FilaArquivoInput;
import com.imobanco.autenticador.infrastructure.config.AwsCredenciaisSingleton;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class Aws {

    private final AwsCredenciaisSingleton awsCredenciaisSingleton;

    private AmazonSQS getClienteSqs(){

        return AmazonSQSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredenciaisSingleton.getInstance()))
                .build();
    }

    private AmazonS3 getClienteS3(){
        return  AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredenciaisSingleton.getInstance()))
                .build();
    }

    public void filaEnviarMensagem(String mensagem, String nomeFila) {

        SendMessageRequest sendMsgRequest = new SendMessageRequest()
                .withQueueUrl(nomeFila)
                .withMessageBody(mensagem);

        getClienteSqs().sendMessage(sendMsgRequest);
    }

    public void uploadArquivos(String nomeBucket, String chaveObjeto, ArquivoInput arquivo) {


        try {
            InputStream inputStream = new ByteArrayInputStream(arquivo.getArquivo());
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(arquivo.getArquivo().length);
            PutObjectRequest request = new PutObjectRequest(nomeBucket, chaveObjeto.concat(".").concat(arquivo.getExtensao()), inputStream, metadata);
            getClienteS3().putObject(request);
            System.out.println("Arquivo carregado com sucesso!");
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        }

    }

    public void uploadArquivos(FilaArquivoInput filaArquivoInputarquivo) {
        uploadArquivos(filaArquivoInputarquivo.getNomeBucket(),
                filaArquivoInputarquivo.getChaveObjetoBucket(),
                ArquivoInput.builder()
                        .arquivo(filaArquivoInputarquivo.getArquivo())
                        .extensao(filaArquivoInputarquivo.getExtensao()).build());

    }

    public void removerArquivos(String nomeBucket, String prefixoChaveObjeto) {

        ObjectListing objectListing = listarArquivosPorPrefixo(nomeBucket,prefixoChaveObjeto);
        if(objectListing.getObjectSummaries().isEmpty())
            throw new DefaultException(HttpStatus.NOT_FOUND, "Documento Não encontrado" );
        else
            getClienteS3().deleteObject(nomeBucket,objectListing.getObjectSummaries().get(0).getKey());

    }


    public ObjectListing listarArquivosPorPrefixo(String nomeBucket, String prefixoChaveObjeto){

        return getClienteS3().listObjects(new ListObjectsRequest()
                .withBucketName(nomeBucket)
                .withPrefix(prefixoChaveObjeto));
    }





    public List<ArquivoModel> downloadArquivos(String nomeBucket, String prefixoChaveObjeto) {

        byte[] bytes = null;
        String objectKey = null;
        List<ArquivoModel> arquivoModels;

        arquivoModels = listarArquivosPorPrefixo( nomeBucket, prefixoChaveObjeto).getObjectSummaries().stream().map(s3ObjectSummary ->
                {
                    S3ObjectInputStream objectInputStream = getClienteS3().getObject(
                            new GetObjectRequest(nomeBucket,
                                    s3ObjectSummary.getKey())).getObjectContent();
                    try {
                        byte[] bytess = IOUtils.toByteArray(objectInputStream);
                        return new ArquivoModel(bytess, s3ObjectSummary.getKey(), s3ObjectSummary.getKey().substring(s3ObjectSummary.getKey().indexOf(".") + 1));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList());

        return arquivoModels;

    }
}





