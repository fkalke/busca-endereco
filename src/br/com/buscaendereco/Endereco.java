package br.com.buscaendereco;

public class Endereco {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ddd;
    private String ibge;

    //Método para inserir as informações do endereço
    public void setEndereco(String cep, String logradouro, String complemento, String bairro,
                            String localidade, String uf, String ddd, String ibge){
        this.cep = cep;
        if (logradouro == "") this.logradouro = "Informação não disponível!";
        else this.logradouro = logradouro;

        if (complemento == "") this.complemento = "Informação não disponível!";
        else this.complemento = complemento;

        if (bairro == "") this.bairro = "Informação não disponível!";
        else this.bairro = bairro;

        this.localidade = localidade;
        this.uf = uf;
        this.ddd = ddd;
        this.ibge = ibge;
    }

    //Método para recuperar as informações do endereço passado por ele
    public String getEndereco(Endereco e){
        return "O endereço correspondente ao CEP é:\nCEP: " + e.cep + "\nLogradouro: " + e.logradouro + 
                "\nComplemento: " + e.complemento + "\nBairro: " + e.bairro + "\nLocalidade: " + e.localidade + 
                "\nUF: " + e.uf + "\nDDD: " + e.ddd + "\nIBGE: " + e.ibge;
    }
}
