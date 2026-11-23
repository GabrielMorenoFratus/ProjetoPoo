
package org.example;

import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

public class Estilo {

    private static final String FUNDO = "#102A43";
    private static final String BOTAO = "#0B1F33";
    private static final String TEXTO = "#FFFFFF";
    private static final String CAMPO = "#243B53";

    public static void aplicarTema(Scene scene) {

        Parent root = scene.getRoot();

        root.setStyle(
                "-fx-background-color: " + FUNDO + ";" +
                        "-fx-font-family: Arial;"
        );

        scene.setFill(Color.web(FUNDO));

        estilizarComponentes(root);
    }

    private static void estilizarComponentes(Parent parent) {

        for (var node : parent.getChildrenUnmodifiable()) {

            if (node instanceof Button botao) {
                estilizarBotao(botao);
            }

            else if (node instanceof Label label) {
                label.setStyle("-fx-text-fill: " + TEXTO + ";");
            }

            else if (node instanceof TextField campo) {
                campo.setStyle(
                        "-fx-background-color: " + CAMPO + ";" +
                                "-fx-text-fill: white;" +
                                "-fx-prompt-text-fill: #BCCCDC;"
                );
            }

            else if (node instanceof TextArea campo) {
                campo.setStyle(
                        "-fx-control-inner-background: " + CAMPO + ";" +
                                "-fx-text-fill: white;" +
                                "-fx-prompt-text-fill: #BCCCDC;"
                );
            }

            else if (node instanceof ComboBox<?> combo) {
                combo.setStyle(
                        "-fx-background-color: " + CAMPO + ";"
                );
            }

            if (node instanceof Parent filho) {
                estilizarComponentes(filho);
            }
        }
    }

    public static void estilizarBotao(Button botao) {
        botao.setStyle(
                "-fx-background-color: " + BOTAO + ";" +
                        "-fx-text-fill: " + TEXTO + ";" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 8;" +
                        "-fx-padding: 9 16;"
        );
    }

    public static void estilizarSexo(ComboBox<String> combo) {

        combo.setButtonCell(new ListCell<String>() {
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText("");
                } else {
                    setText(item);
                }

                setTextFill(Color.WHITE);
            }
        });
    }


}
