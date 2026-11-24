package org.example;

// classe abstrata com os dados comuns de homem e mulher
public abstract class Paciente implements Calculavel {

    // atributos do paciente
    private String nome;
    private int idade;
    private double altura;

    // construtor que recebe nome, idade e altura
    public Paciente(String nome, int idade, double altura) {
        this.nome = nome;
        this.idade = idade;
        this.altura = altura;
    }

    // metodo que as classes filhas precisam implementar
    public abstract String getSexo();

    // indice resistivo = altura ao quadrado / resistencia
    public double calcularIndiceResistivo(double resistencia) {

        return (altura * altura) / resistencia;
    }

    // getters e setters
    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public int getIdade() {

        return idade;
    }

    public void setIdade(int idade) {

        this.idade = idade;
    }

    public double getAltura() {

        return altura;
    }

    public void setAltura(double altura) {

        this.altura = altura;
    }

    // texto que representa o paciente: nome, sexo e idade
    @Override
    public String toString() {

        return nome + " (" + getSexo() + ", " + idade + " anos)";
    }
}