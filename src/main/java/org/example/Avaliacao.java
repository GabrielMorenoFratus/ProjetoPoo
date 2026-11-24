package org.example;

// classe que guarda os dados de uma avaliacao e faz os calculos
public class Avaliacao {

    // dados da avaliacao
    private Paciente paciente;
    private double peso;
    private double resistencia;
    private double reatancia;

    // construtor que recebe todos os dados
    public Avaliacao(Paciente paciente, double peso, double resistencia, double reatancia) {
        this.paciente = paciente;
        this.peso = peso;
        this.resistencia = resistencia;
        this.reatancia = reatancia;
    }

    // imc = peso / altura em metros ao quadrado
    public double calcularImc() {
        double alturaMetros = paciente.getAltura() / 100;
        return peso / (alturaMetros * alturaMetros);
    }

    // classifica o imc em faixas
    public String classificarImc() {
        double imc = calcularImc();
        if (imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc < 25) {
            return "Peso normal";
        } else if (imc < 30) {
            return "Sobrepeso";
        } else if (imc < 35) {
            return "Obesidade grau I";
        } else if (imc < 40) {
            return "Obesidade grau II";
        } else {
            return "Obesidade grau III";
        }
    }

    // massa magra e calculada pela classe do paciente (homem ou mulher)
    public double calcularMassaMagra() {

        return paciente.calcularMassaMagra(peso, resistencia, reatancia);
    }

    // massa gorda = peso - massa magra
    public double calcularMassaGorda() {

        return peso - calcularMassaMagra();
    }

    // percentual de gordura = massa gorda / peso * 100
    public double calcularPercentualGordura() {

        return (calcularMassaGorda() / peso) * 100;
    }

    // agua corporal calculada pela classe do paciente
    public double calcularAgua() {

        return paciente.calcularAgua(peso, resistencia);
    }

    // percentual de agua em relacao ao peso
    public double calcularPercentualAgua() {

        return (calcularAgua() / peso) * 100;
    }

    // angulo de fase = arco tangente de reatancia / resistencia, em graus
    public double calcularAnguloFase() {

        return Math.toDegrees(Math.atan(reatancia / resistencia));
    }

    // taxa metabolica basal calculada pela classe do paciente
    public double calcularTmb() {

        return paciente.calcularTmb(peso);
    }

    // arredonda para uma casa decimal
    public static double arredondar(double valor) {

        return Math.round(valor * 10) / 10.0;
    }

    // monta o texto do relatorio com todos os resultados
    public String gerarRelatorio() {
        String texto = "";
        texto = texto + "Paciente: " + paciente + "\n";
        texto = texto + "Peso: " + peso + " kg\n";
        texto = texto + "IMC: " + arredondar(calcularImc()) + " (" + classificarImc() + ")\n";
        texto = texto + "Massa magra: " + arredondar(calcularMassaMagra()) + " kg\n";
        texto = texto + "Massa gorda: " + arredondar(calcularMassaGorda()) + " kg\n";
        texto = texto + "Gordura corporal: " + arredondar(calcularPercentualGordura()) + " %\n";
        texto = texto + "Agua corporal: " + arredondar(calcularAgua()) + " L ("
                + arredondar(calcularPercentualAgua()) + " %)\n";
        texto = texto + "Angulo de fase: " + arredondar(calcularAnguloFase()) + " graus\n";
        texto = texto + "TMB: " + Math.round(calcularTmb()) + " kcal/dia";
        return texto;
    }

    // getters
    public Paciente getPaciente() {

        return paciente;
    }

    public double getPeso() {

        return peso;
    }

    public double getResistencia() {

        return resistencia;
    }

    public double getReatancia() {

        return reatancia;
    }
}
