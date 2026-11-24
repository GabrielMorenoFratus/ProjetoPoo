package org.example;

// classe da paciente mulher, herda de paciente
public class Mulher extends Paciente {

    // construtor que repassa os dados para a classe pai
    public Mulher(String nome, int idade, double altura) {

        super(nome, idade, altura);
    }

    // retorna o sexo como texto
    @Override
    public String getSexo() {

        return "Feminino";
    }

    // massa magra com a equacao feminina
    @Override
    public double calcularMassaMagra(double peso, double resistencia, double reatancia) {
        // indice resistivo = altura ao quadrado / resistencia
        double indice = calcularIndiceResistivo(resistencia);
        return -9.53 + 0.69 * indice + 0.17 * peso + 0.02 * resistencia;
    }

    // agua corporal com a equacao feminina
    @Override
    public double calcularAgua(double peso, double resistencia) {
        double indice = calcularIndiceResistivo(resistencia);
        return 3.75 + 0.45 * indice + 0.11 * peso;
    }

    // tmb feminina (mifflin-st jeor)
    @Override
    public double calcularTmb(double peso) {

        return 10 * peso + 6.25 * getAltura() - 5 * getIdade() - 161;
    }
}