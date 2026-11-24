package org.example;

import java.util.ArrayList;

// classe que guarda a lista de avaliacoes
public class Historico {

    // lista com todas as avaliacoes
    private ArrayList<Avaliacao> avaliacoes = new ArrayList<>();

    // adiciona uma avaliacao na lista
    public void adicionar(Avaliacao avaliacao) {
        avaliacoes.add(avaliacao);
    }

    // remove pela posicao, so se a posicao existir
    public void remover(int posicao) {
        if (posicao >= 0 && posicao < avaliacoes.size()) {
            avaliacoes.remove(posicao);
        }
    }

    // apaga todas as avaliacoes
    public void limpar() {
        avaliacoes.clear();
    }

    // retorna quantas avaliacoes existem
    public int quantidade() {
        return avaliacoes.size();
    }

    // retorna a lista completa
    public ArrayList<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    // busca avaliacoes pelo nome do paciente, ignorando maiuscula e minuscula
    public ArrayList<Avaliacao> buscarPorNome(String nome) {
        // lista que guarda as avaliacoes encontradas
        ArrayList<Avaliacao> encontradas = new ArrayList<>();
        // percorre a lista comparando os nomes
        for (int i = 0; i < avaliacoes.size(); i++) {
            Avaliacao a = avaliacoes.get(i);
            if (a.getPaciente().getNome().equalsIgnoreCase(nome)) {
                encontradas.add(a);
            }
        }
        return encontradas;
    }

    // calcula a media do percentual de gordura de todas as avaliacoes
    public double calcularMediaGordura() {
        // evita divisao por zero se a lista estiver vazia
        if (avaliacoes.size() == 0) {
            return 0;
        }
        // soma os percentuais de gordura
        double soma = 0;
        for (int i = 0; i < avaliacoes.size(); i++) {
            soma = soma + avaliacoes.get(i).calcularPercentualGordura();
        }
        // divide pela quantidade
        return soma / avaliacoes.size();
    }

    // monta o texto do resumo do historico
    public String gerarResumo() {
        // mensagem para lista vazia
        if (avaliacoes.size() == 0) {
            return "Nenhuma avaliacao feita ainda.";
        }
        // uma linha por avaliacao com nome, imc e gordura
        String texto = "";
        for (int i = 0; i < avaliacoes.size(); i++) {
            Avaliacao a = avaliacoes.get(i);
            texto = texto + (i + 1) + " - " + a.getPaciente().getNome()
                    + " | IMC " + Avaliacao.arredondar(a.calcularImc())
                    + " | Gordura " + Avaliacao.arredondar(a.calcularPercentualGordura()) + " %\n";
        }
        // linha final com a media
        texto = texto + "Media de gordura: " + Avaliacao.arredondar(calcularMediaGordura()) + " %";
        return texto;
    }
}