package org.example;

// classe do paciente homem, herda de paciente
public class Homem extends Paciente {

    // construtor que repassa os dados para a classe pai
    public Homem(String nome, int idade, double altura) {

        super(nome, idade, altura);
    }

    // retorna o sexo como texto
    @Override
    public String getSexo() {

        return "Masculino";
    }

    // massa magra com a equacao masculina
    @Override
    public double calcularMassaMagra(double peso, double resistencia, double reatancia) {
        // indice resistivo = altura ao quadrado / resistencia
        double indice = calcularIndiceResistivo(resistencia);
        return -10.68 + 0.65 * indice + 0.26 * peso + 0.02 * resistencia;
    }

    // agua corporal com a equacao masculina
    @Override
    public double calcularAgua(double peso, double resistencia) {
        double indice = calcularIndiceResistivo(resistencia);
        return 1.2 + 0.45 * indice + 0.18 * peso;
    }

    // tmb masculina (mifflin-st jeor)
    @Override
    public double calcularTmb(double peso) {

        return 10 * peso + 6.25 * getAltura() - 5 * getIdade() + 5;
    }
}