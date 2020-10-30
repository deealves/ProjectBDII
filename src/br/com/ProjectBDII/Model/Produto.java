/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ProjectBDII.Model;

/**
 *
 * @author Matrix
 */
public class Produto {
    private int Id;
    private String Nome;
    private String Descricao;
    private float Valor;
    private int Quant;
    private int Unidade;

    public Produto(int Id, String Nome, String Descricao, float Valor, int Quant, int Unidade) {    
        this.Id = Id;
        this.Nome = Nome;
        this.Descricao = Descricao;
        this.Valor = Valor;
        this.Quant = Quant;
        this.Unidade = Unidade;
    }

    public Produto(String Nome, String Descricao, float Valor, int Quant, int Unidade) {
        this.Nome = Nome;
        this.Descricao = Descricao;
        this.Valor = Valor;
        this.Quant = Quant;
        this.Unidade = Unidade;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    

    public Produto() {
        
    }


    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public float getValor() {
        return Valor;
    }

    public void setValor(float Valor) {
        this.Valor = Valor;
    }

    public int getQuant() {
        return Quant;
    }

    public void setQuant(int Quant) {
        this.Quant = Quant;
    }

    public int getUnidade() {
        return Unidade;
    }

    public void setUnidade(int Unidade) {
        this.Unidade = Unidade;
    }
    
    
}
