package org.example;

// imports do javafx: cena, container, controles e cores
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

// classe que aplica o tema escuro nos componentes da tela
public class Estilo {

    // cores do tema
    private static final String FUNDO = "#102A43";
    private static final String BOTAO = "#0B1F33";
    private static final String TEXTO = "#FFFFFF";
    private static final String CAMPO = "#243B53";

    // aplica o tema na cena inteira
    public static void aplicarTema(Scene scene) {

        // pega o container principal da cena
        Parent root = scene.getRoot();

        // define cor de fundo e fonte do container principal
        root.setStyle(
                "-fx-background-color: " + FUNDO + ";" +
                        "-fx-font-family: Arial;"
        );

        // define a cor de fundo da cena
        scene.setFill(Color.web(FUNDO));

        // estiliza todos os componentes dentro do container
        estilizarComponentes(root);
    }

    // percorre os filhos do container e estiliza cada tipo de componente
    private static void estilizarComponentes(Parent parent) {

        // passa por cada filho do container
        for (var node : parent.getChildrenUnmodifiable()) {

            // se for botao, usa o estilo de botao
            if (node instanceof Button botao) {
                estilizarBotao(botao);
            }

            // se for label, deixa o texto branco
            else if (node instanceof Label label) {
                label.setStyle("-fx-text-fill: " + TEXTO + ";");
            }

            // se for campo de texto, muda fundo e cor do texto
            else if (node instanceof TextField campo) {
                campo.setStyle(
                        "-fx-background-color: " + CAMPO + ";" +
                                "-fx-text-fill: white;" +
                                "-fx-prompt-text-fill: #BCCCDC;"
                );
            }

            // se for area de texto, muda fundo interno e cor do texto
            else if (node instanceof TextArea campo) {
                campo.setStyle(
                        "-fx-control-inner-background: " + CAMPO + ";" +
                                "-fx-text-fill: white;" +
                                "-fx-prompt-text-fill: #BCCCDC;"
                );
            }

            // se for combobox, muda so a cor de fundo
            else if (node instanceof ComboBox<?> combo) {
                combo.setStyle(
                        "-fx-background-color: " + CAMPO + ";"
                );
            }

            // se o no tambem for um container, chama o metodo de novo
            if (node instanceof Parent filho) {
                estilizarComponentes(filho);
            }
        }
    }

    // aplica o estilo padrao em um botao
    public static void estilizarBotao(Button botao) {
        botao.setStyle(
                "-fx-background-color: " + BOTAO + ";" +
                        "-fx-text-fill: " + TEXTO + ";" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 8;" +
                        "-fx-padding: 9 16;"
        );
    }

    // deixa o texto do item selecionado do combobox branco
    public static void estilizarSexo(ComboBox<String> combo) {

        // cria uma celula personalizada para o botao do combobox
        combo.setButtonCell(new ListCell<String>() {
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                // se estiver vazio mostra texto vazio, senao mostra o item
                if (empty || item == null) {
                    setText("");
                } else {
                    setText(item);
                }

                // cor do texto branca
                setTextFill(Color.WHITE);
            }
        });
    }


}