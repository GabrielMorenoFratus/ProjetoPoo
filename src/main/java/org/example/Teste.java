package org.example;

import java.util.ArrayList;

// classe para testar as outras classes pelo console
public class Teste {

    public static void main(String[] args) {
        // cria uma lista de pacientes de exemplo
        ArrayList<Paciente> pacientes = new ArrayList<>();
        pacientes.add(new Homem("Joao Silva", 31, 178));
        pacientes.add(new Mulher("Maria Souza", 28, 165));
        pacientes.add(new Homem("Carlos Lima", 45, 172));

        // cria o historico e adiciona uma avaliacao para cada paciente
        Historico historico = new Historico();
        historico.adicionar(new Avaliacao(pacientes.get(0), 80.0, 480, 55));
        historico.adicionar(new Avaliacao(pacientes.get(1), 62.0, 560, 58));
        historico.adicionar(new Avaliacao(pacientes.get(2), 95.0, 450, 50));

        // imprime o relatorio de cada avaliacao
        ArrayList<Avaliacao> lista = historico.getAvaliacoes();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).gerarRelatorio());
            System.out.println("-----------------------------");
        }

        // imprime o resumo do historico
        System.out.println(historico.gerarResumo());

        // testa a busca por nome
        ArrayList<Avaliacao> achadas = historico.buscarPorNome("maria souza");
        System.out.println("Avaliacoes da Maria: " + achadas.size());
    }
}