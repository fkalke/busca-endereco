package br.com.buscaendereco;

import java.util.Scanner;
import org.json.simple.parser.ParseException;

public class BuscaEndereco {
    public static void main(String[] args) throws ParseException{
        //Instui o usuário para que digite o CEP que deseja verificar 
        System.out.println("Digite o CEP que você deseja consultar:");
        Scanner sc = new Scanner(System.in);
        //Faz a leitura do CEP digitado pelo usuário        
        String cep = sc.nextLine();
        /*Verifica se o usuário digitou um cep contendo ponto ou hífen e os deleta, 
        para que o cep fique no formato adequado para passar na url da API.*/ 
        cep = cep.replaceAll("\\.", "");
        cep = cep.replaceAll("-", "");

        while(cep.length() != 8){
            //faz a verificação do tamanho da string, para saber se o cep digitado pode ser válido ou não.
            //Caso o cep seja inválido, alerta ao usuário , e pede que ele tente novamente.
            System.out.println("Você não digitou um CEP válido. Por favor, tente novamente!");
            cep = sc.nextLine();
            cep = cep.replaceAll("\\.", "");
            cep = cep.replaceAll("-", "");
        }        
        sc.close();
        
        //Chama a classe ObtemEndereco
        ObtemEndereco oe = new ObtemEndereco();
        //Passa o cep digitado pelo usuário, e executa o método que retornará ao usuário as informações desejadas.
        oe.obtemEndereco(cep);
    } 
}
