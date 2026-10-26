package model;

import java.time.LocalDate;

public class Movimentacao {
	
	private String descricao;
    private double valor;
    private LocalDate data;
    private String tipo;
    private String categoria;

    public Movimentacao(String descricao, double valor,
                        LocalDate data, String tipo,
                        String categoria) {

        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException(
                "Informe a descrição."
            );
        }

        if (valor <= 0 || !Double.isFinite(valor)) {
            throw new IllegalArgumentException(
                "O valor deve ser maior que zero."
            );
        }

        if (data == null) {
            throw new IllegalArgumentException(
                "Informe uma data válida."
            );
        }

        if (!"Receita".equals(tipo)
                && !"Despesa".equals(tipo)) {
            throw new IllegalArgumentException(
                "Tipo inválido."
            );
        }

        if ("Despesa".equals(tipo)
                && (categoria == null || categoria.isBlank())) {
            throw new IllegalArgumentException(
                "Informe a categoria da despesa."
            );
        }

        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setDescricao(String descricao) {
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException(
                "Informe a descrição."
            );
        }
        this.descricao = descricao;
    }

    public void setValor(double valor) {
        if (valor <= 0 || !Double.isFinite(valor)) {
            throw new IllegalArgumentException(
                "O valor deve ser maior que zero."
            );
        }
        this.valor = valor;
    }

    public void setData(LocalDate data) {
        if (data == null) {
            throw new IllegalArgumentException(
                "Informe uma data válida."
            );
        }
        this.data = data;
    }

    public void setTipo(String tipo) {
        if (!"Receita".equals(tipo)
                && !"Despesa".equals(tipo)) {
            throw new IllegalArgumentException(
                "Tipo inválido."
            );
        }
        this.tipo = tipo;
    }

    public void setCategoria(String categoria) {
        if (categoria == null || categoria.isBlank()) {
            throw new IllegalArgumentException(
                "Informe uma categoria."
            );
        }
        this.categoria = categoria;
    }
}