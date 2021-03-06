/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ProjectBDII.gui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Leticia
 */
public class Produtos extends Application {
    
    private static Stage stage;//Uma Janela
    
    
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/br/com/ProjectBDII/view/Produto.fxml"));//Carrega FXML
        
        Scene scene = new Scene(root);//Coloca o FXML em uma cena
        stage.setTitle("Produto");
        stage.setScene(scene);//Coloca a cena em uma janela
        stage.show();//Abre a Janela
        setStage(stage);
}
    
     public static void main(String[] args) {
        launch(args);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Produtos.stage = stage;
    }
    
}