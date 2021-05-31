package br.com.buscaendereco;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ObtemEndereco {
    private String url(String url){
        try{
            URL viaCep = new URL(url);
            BufferedReader br = new BufferedReader(new InputStreamReader(viaCep.openStream()));
            String retornoJson;
            //Adiciona as informações obtidas da API, a um StringBuilder
            StringBuilder builder = new StringBuilder();
            while((retornoJson = br.readLine()) != null)
                builder.append(retornoJson);
            br.close();        
            return builder.toString();
        }catch(Exception ex){
            return "{\"erro\": true}";
        }
        
    }

    private JSONObject chamaUrl(String cep) throws ParseException{
        //Concatenando as partes estaticas da url com a String do CEP, para assim formar a url completa  
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        String retornoJson = url(url);
        JSONParser parser = new JSONParser();
        //Passa os dados da string retornoJson, obtidos no retorno do metodo url, para um JSONObject
        JSONObject objetoJson = (JSONObject) parser.parse(retornoJson);
        return objetoJson;
    }

    public void obtemEndereco(String cep) throws ParseException{              
        
        JSONObject obj = chamaUrl(cep);
    
        boolean erro;
        /*Quando passamos um CEP inexistente, a api nos devolve um campo "erro" que retorna um boolean true
        esse campo "erro", não é apresentado quando passado um CEP existente. Com isso a aplicação lançaria
        um Exception, pois estaria tentando atribuir o valor null a um boolean. Para resolver isso, tratamos
        o Exception da seguinte forma. */        
        try{
            erro = (Boolean) obj.get("erro");
        }catch(Exception e){
            erro = false;
        }

        if (erro == true){
            //Se o CEP passado for inválido ou não hover conexão, será exibia ao usuário a mensagem a seguir
            System.out.println("Sem conexão com o servidor, ou CEP inexistente!");
        }else{
            //Obtendo as informações da API, e adicionando-as em Strings
            String cepObtido = (String) obj.get("cep");
            String logradouro = (String) obj.get("logradouro");
            String complemento = (String) obj.get("complemento");
            String bairro = (String) obj.get("bairro");
            String localidade = (String) obj.get("localidade");
            String uf = (String) obj.get("uf");
            String ddd = (String) obj.get("ddd");
            String ibge = (String) obj.get("ibge");

            //Cria um endereço
            Endereco endereco = new Endereco();
            //Chama o método para adicionar as informações ao endereço criado
            endereco.setEndereco(cepObtido, logradouro, complemento, bairro, localidade, uf, ddd, ibge);
            //Imprime para o usuário o endereço adicionado
            System.out.println(endereco.getEndereco(endereco));
        }
        
    }
    
}
