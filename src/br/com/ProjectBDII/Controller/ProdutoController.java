/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ProjectBDII.Controller;

import br.com.ProjectBDII.Model.Produto;
import br.com.ProjectBDII.dao.ProdutoDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Matrix
 */
public class ProdutoController implements Initializable {
    @FXML private TableColumn<Produto,String > clmNome;
    @FXML private TableColumn<Produto, Integer> clmUnidade;
    @FXML private TextField tfDescricao;
    @FXML private TextField tfUnidade;
    @FXML private TableColumn<Produto, String> clmDescricao;
    @FXML private TextField tfValor;
    @FXML private TableView<Produto> Tabela;
    @FXML private TableColumn<Produto, Integer> clmID;
    @FXML private Button btEditar;
    @FXML private Button btCadastrar;
    @FXML private Button btRemover;
    @FXML private TextField tfQuant;
    @FXML private TableColumn<Produto, Integer> clmQuant;
    @FXML private TextField tfNome;
    @FXML private TableColumn<Produto, Float> clmValor;
    @FXML private Label lbId;
    @FXML private TextField tfBuscar;
    private Produto selecionada;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initTable();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Tabela.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
          @Override
          public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selecionada = (Produto) newValue;            
            }
        });
        btCadastrar.setOnMouseClicked((MouseEvent e) ->{
            if(btCadastrar.getText().equals("Salvar")){
                try {
                    salvarProduto();
                } catch (SQLException ex) {
                    Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                try {
                    cadastrarProduto();
                } catch (SQLException ex) {
                    Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        btEditar.setOnMouseClicked((MouseEvent e) ->{
            editarProduto();
            
        });
        
        btRemover.setOnMouseClicked((MouseEvent e) ->{
            try {
                removerProduto();
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        tfBuscar.setOnKeyReleased((KeyEvent e)-> {
            buscarProduto();
        });
        
    }
    

    private void initTable() throws SQLException {
        clmID.setCellValueFactory(new PropertyValueFactory("id"));
        clmNome.setCellValueFactory(new PropertyValueFactory("nome"));
        clmDescricao.setCellValueFactory(new PropertyValueFactory("descricao"));
        clmValor.setCellValueFactory(new PropertyValueFactory("valor"));
        clmQuant.setCellValueFactory(new PropertyValueFactory("quant"));
        clmUnidade.setCellValueFactory(new PropertyValueFactory("unidade"));
        Tabela.setItems(atualizaTabela());
    }

    private ObservableList<Produto> atualizaTabela() throws SQLException {
        ProdutoDAO dao = new ProdutoDAO();
        return FXCollections.observableArrayList(dao.listar());
    }

    private void salvarProduto() throws SQLException {
            String nome = tfNome.getText(); 
            String descricao = tfDescricao.getText();
            float valor = Float.parseFloat(tfValor.getText());
            int quant = Integer.parseInt(tfQuant.getText());
            int unidade = Integer.parseInt(tfUnidade.getText());
            int id = Integer.parseInt(lbId.getText());

            ProdutoDAO dao = new ProdutoDAO();
            Produto p = new Produto();
            p.setNome(nome);
            p.setDescricao(descricao);
            p.setValor(valor);
            p.setQuant(quant);
            p.setUnidade(unidade);
            p.setId(id);
            if(dao.update(p)){
                Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                al.setHeaderText ("Produto Atualizado");
                al.show();
                limpaCampos();
                btCadastrar.setText("Cadastrar");
                //lbId.setText(" ");
                Tabela.setItems(atualizaTabela());
                
          
            }else{
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setHeaderText ("Produto Não Atualizado");
                al.show();
            }
    }

    private void cadastrarProduto() throws SQLException {
       if(tfNome.getText().equals("") && tfDescricao.getText().equals("")
                && tfValor.getText().equals("") && tfQuant.getText().equals("") && tfUnidade.getText().equals("")){
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText ("Preencha todos os Campos");
            al.show();
        }else{
            String nome = tfNome.getText(); 
            String descricao = tfDescricao.getText();
            float valor = Float.parseFloat(tfValor.getText());
            int quant = Integer.parseInt(tfQuant.getText());
            int unidade = Integer.parseInt(tfUnidade.getText());

            Produto p = new Produto();
            p.setNome(nome);
            p.setDescricao(descricao);
            p.setValor(valor);
            p.setQuant(quant);
            p.setUnidade(unidade);
            ProdutoDAO dao = new ProdutoDAO();

            if(dao.insert(p)){
                Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                al.setHeaderText ("Produto Cadastrado");
                al.show();
                limpaCampos();
                


            }else{
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setHeaderText ("Produto Não Cadastrado");
                al.show();
            }
        }
    }

    private void limpaCampos() throws SQLException {
        tfNome.setText("");
        tfDescricao.setText("");
        tfValor.setText("");
        tfQuant.setText("");
        tfUnidade.setText("");
        Tabela.setItems(atualizaTabela());
    }

    private void removerProduto() throws SQLException {
        if(selecionada != null){
            ProdutoDAO dao = new ProdutoDAO();
            dao.delete(selecionada);
            Alert al = new Alert(Alert.AlertType.CONFIRMATION);
            al.setHeaderText("Removido com Sucesso");
            al.show();
            Tabela.setItems(atualizaTabela());
        }else {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setHeaderText("Nenhum Produto Selecionado");
            al.show();
        }
    }

    private void editarProduto() {
        if(selecionada != null){
            lbId.setText(String.valueOf(selecionada.getId()));
            tfNome.setText(selecionada.getNome());
            tfDescricao.setText(selecionada.getDescricao());
            tfValor.setText(String.valueOf(selecionada.getValor()));
            tfQuant.setText(String.valueOf(selecionada.getQuant()));
            tfUnidade.setText(String.valueOf(selecionada.getUnidade()));
            btCadastrar.setText("Salvar");

        }else{
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setHeaderText("Nenhum Produto Selecionado");
            al.show();
        }
    }

    private void buscarProduto() {
        String busca = tfBuscar.getText();
        ProdutoDAO dao = new ProdutoDAO();
        try {
            List<Produto> resultado = dao.buscar(busca);
            initTable2(busca);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private void initTable2(String busca) throws SQLException {
        clmID.setCellValueFactory(new PropertyValueFactory("id"));
        clmNome.setCellValueFactory(new PropertyValueFactory("nome"));
        clmDescricao.setCellValueFactory(new PropertyValueFactory("descricao"));
        clmValor.setCellValueFactory(new PropertyValueFactory("valor"));
        clmQuant.setCellValueFactory(new PropertyValueFactory("quant"));
        clmUnidade.setCellValueFactory(new PropertyValueFactory("unidade"));
        Tabela.setItems(buscaTabela(busca));
    }
    
    private ObservableList<Produto> buscaTabela(String busca) throws SQLException {
        ProdutoDAO dao = new ProdutoDAO();
        return FXCollections.observableArrayList(dao.buscar(busca));
    }
}
