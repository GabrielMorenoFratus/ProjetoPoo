package org.example;

// interface que obriga as classes a ter os metodos de calculo
public interface Calculavel {

    // calcula a massa magra em kg
    double calcularMassaMagra(double peso, double resistencia, double reatancia);

    // calcula a agua corporal em litros
    double calcularAgua(double peso, double resistencia);

    // calcula a taxa metabolica basal
    double calcularTmb(double peso);
}
