package model;

public class Teste {
    public static void main(String[] args){
        Eleitor eleitor = new Eleitor();
        //eleitor.cadastrarEleitor("09127666541");
        Urna urna = new Urna();
        //urna.validarEleitor("03143551542");
        System.out.println(urna.validarEleitor("03143551542"));
        //eleitor.cadastrarEleitor("982838746661");
    }
}
