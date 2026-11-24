package org.example;

// imports do javafx: aplicacao, layout, controles e imagens
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// classe da tela principal do sistema
public class TelaBioimpedancia extends Application {

    // guarda todas as avaliacoes feitas
    private Historico historico = new Historico();

    // campos de entrada do formulario
    private TextField txtNome = new TextField();
    private TextField txtIdade = new TextField();
    private ComboBox<String> cbSexo = new ComboBox<>();
    private TextField txtAltura = new TextField();
    private TextField txtPeso = new TextField();

    // valores fixos de resistencia e reatancia (simulacao)
    private double txtResistencia = 480;
    private double txtReatancia = 55;

    // areas de texto para resultado e historico
    private TextArea areaResultado = new TextArea();
    private TextArea areaHistorico = new TextArea();

    // componente que mostra a imagem e as tres imagens possiveis
    private ImageView logo = new ImageView();
    private Image imagemAbaixo;
    private Image imagemNormal;
    private Image imagemObesidade;
    private Image logoApp;



    // metodo que monta a tela
    @Override
    public void start(Stage stage) {

        // adiciona as opcoes de sexo e estiliza o combobox
        cbSexo.getItems().add("Masculino");
        cbSexo.getItems().add("Feminino");
        Estilo.estilizarSexo(cbSexo);

        // configura as areas de texto (leitura e quantidade de linhas)
        areaResultado.setEditable(false);
        areaResultado.setPrefRowCount(9);
        areaHistorico.setEditable(false);
        areaHistorico.setPrefRowCount(6);
        areaHistorico.setText(historico.gerarResumo());

        // monta o formulario em grade
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(8);
        form.addRow(0, new Label("Nome:"), txtNome);
        form.addRow(1, new Label("Idade:"), txtIdade);
        form.addRow(2, new Label("Sexo:"), cbSexo);
        form.addRow(3, new Label("Altura (cm):"), txtAltura);
        form.addRow(4, new Label("Peso (kg):"), txtPeso);
        form.addRow(5, new Label("Resistencia (ohm):  480"));
        form.addRow(6, new Label("Reatancia (ohm):  55"));

        // botao calcular
        Button btnCalcular = new Button("Calcular");
        btnCalcular.setOnAction(e -> calcular());

        // botao limpar campos
        Button btnLimpar = new Button("Limpar campos");
        btnLimpar.setOnAction(e -> limparCampos());

        // botao limpar historico
        Button btnLimparHistorico = new Button("Limpar historico");
        btnLimparHistorico.setOnAction(e -> limparHistorico());

        // agrupa os botoes na horizontal
        HBox botoes = new HBox(10, btnCalcular, btnLimpar, btnLimparHistorico);

        // carrega as imagens da pasta downloads
        imagemNormal = new Image("file:C:/Users/gmore/Downloads/gabrielprojeto.jpg");
        imagemAbaixo = new Image("file:C:/Users/gmore/Downloads/magro.jpg");
        imagemObesidade = new Image("file:C:/Users/gmore/Downloads/gordo.jpg");
        logoApp = new Image("file:C:/Users/gmore/Downloads/logo.jpg");

        // define o tamanho da imagem mantendo a proporcao
        logo.setFitWidth(300);
        logo.setPreserveRatio(true);

        // centraliza a imagem em um container
        HBox topo = new HBox(logo);
        topo.setAlignment(Pos.CENTER);


        // container principal com tudo na vertical
        VBox raiz = new VBox(10, form, botoes,
                new Label("Resultado:"), areaResultado,
                new Label("Historico:"), areaHistorico, topo);

        // margem interna da tela
        raiz.setPadding(new Insets(16));

        // titulo e icone da janela
        stage.setTitle("Sistema de Bioimpedância");
        stage.getIcons().add(logoApp);

        // cria a cena e aplica o tema escuro
        Scene scene = new Scene(raiz, 480, 840);

        Estilo.aplicarTema(scene);

        // mostra a janela
        stage.setScene(scene);
        stage.show();

    }

    // le os campos, cria a avaliacao e mostra o resultado
    private void calcular() {

        // valida o nome
        String nome = txtNome.getText().trim();
        if (nome.isEmpty()) {
            mostrarAviso("Digite o nome do paciente!");
            return;
        }

        // valida o sexo
        if (cbSexo.getValue() == null) {
            mostrarAviso("Escolha o sexo!");
            return;
        }

        // try para tratar erro de conversao de numero
        try {

            // le e converte os valores digitados
            int idade = Integer.parseInt(txtIdade.getText().trim());
            double altura = converter(txtAltura.getText());
            double peso = converter(txtPeso.getText());
            double resistencia = 480;
            double reatancia = 55;

            // valida se os valores sao positivos
            if (idade <= 0 || altura <= 0 || peso <= 0 || resistencia <= 0 || reatancia < 0) {
                mostrarAviso("Os valores precisam ser maiores que zero!");
                return;
            }

            // cria homem ou mulher conforme o sexo escolhido
            Paciente paciente;
            if (cbSexo.getValue().equals("Masculino")) {
                paciente = new Homem(nome, idade, altura);
            } else {
                paciente = new Mulher(nome, idade, altura);
            }

            // cria a avaliacao e guarda no historico
            Avaliacao avaliacao = new Avaliacao(paciente, peso, resistencia, reatancia);
            historico.adicionar(avaliacao);

            // atualiza as areas de texto
            areaResultado.setText(avaliacao.gerarRelatorio());
            areaHistorico.setText(historico.gerarResumo());

            // calcula o imc e mostra no console para teste
            double imc = avaliacao.calcularImc();
            System.out.println("IMC: " + imc);
            System.out.println("Erro na imagem abaixo? " + imagemAbaixo.isError());

            // escolhe a imagem de acordo com o imc
            if (imc >= 40) {
                logo.setImage(imagemObesidade);
            } else if (imc < 19) {
                logo.setImage(imagemAbaixo);
            } else {
                logo.setImage(imagemNormal);
            }

        } catch (NumberFormatException erro) {
            // avisa se digitou letras nos campos de numero
            mostrarAviso("Digite apenas numeros nos campos de numero!");
        }
    }

    // troca virgula por ponto e converte para double
    private double converter(String texto) {
        texto = texto.trim().replace(",", ".");
        return Double.parseDouble(texto);
    }

    // limpa os campos, a imagem e o resultado
    private void limparCampos() {
        txtNome.clear();
        txtIdade.clear();
        cbSexo.setValue(null);
        txtAltura.clear();
        txtPeso.clear();
        logo.setImage(null);

        areaResultado.clear();
    }

    // apaga o historico e atualiza a area de texto
    private void limparHistorico() {
        historico.limpar();
        areaHistorico.setText(historico.gerarResumo());
    }

    // mostra uma janela de aviso com a mensagem recebida
    private void mostrarAviso(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Atencao");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    // metodo main que inicia o javafx
    public static void main(String[] args) {

        launch(args);
    }


}