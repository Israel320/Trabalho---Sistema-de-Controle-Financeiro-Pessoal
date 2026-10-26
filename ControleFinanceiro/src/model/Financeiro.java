package model;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class Financeiro {
	
	private final ArrayList<Movimentacao> movimentacoes =
            new ArrayList<>();

    public void adicionar(Movimentacao movimentacao) {
        if (movimentacao == null) {
            throw new IllegalArgumentException(
                "Movimentação inválida."
            );
        }

        movimentacoes.add(movimentacao);
    }

    public List<Movimentacao> getMovimentacoes() {
        return new ArrayList<>(movimentacoes);
    }

    public double getTotalReceitas() {
        double total = 0;

        for (Movimentacao m : movimentacoes) {
            if ("Receita".equals(m.getTipo())) {
                total += m.getValor();
            }
        }

        return total;
    }

    public double getTotalDespesas() {
        double total = 0;

        for (Movimentacao m : movimentacoes) {
            if ("Despesa".equals(m.getTipo())) {
                total += m.getValor();
            }
        }

        return total;
    }

    public double getSaldo() {
        return getTotalReceitas() - getTotalDespesas();
    }

    public Map<String, Double> getDespesasPorCategoria() {
        Map<String, Double> totais = new TreeMap<>();

        for (Movimentacao m : movimentacoes) {
            if ("Despesa".equals(m.getTipo())) {
                String categoria = m.getCategoria();

                if (categoria == null || categoria.isBlank()) {
                    categoria = "Outros";
                }

                double atual = totais.getOrDefault(
                    categoria, 0.0
                );

                totais.put(
                    categoria, atual + m.getValor()
                );
            }
        }

        return totais;
    }

    public Map<String, double[]> getResumoMensal(
            int quantidadeMeses) {

        if (quantidadeMeses < 1) {
            throw new IllegalArgumentException(
                "A quantidade de meses deve ser positiva."
            );
        }

        YearMonth mesAtual = YearMonth.now();

        Map<String, double[]> resumo = new LinkedHashMap<>();

        // Cria os meses em ordem cronológica,
        // incluindo meses sem movimentações.
        for (int i = quantidadeMeses - 1; i >= 0; i--) {
            YearMonth mes = mesAtual.minusMonths(i);

            resumo.put(
                mes.toString(),
                new double[] {0.0, 0.0}
            );
        }

        for (Movimentacao m : movimentacoes) {
            YearMonth mes = YearMonth.from(m.getData());
            double[] valores = resumo.get(mes.toString());

            if (valores == null) {
                continue;
            }

            if ("Receita".equals(m.getTipo())) {
                valores[0] += m.getValor();
            } else if ("Despesa".equals(m.getTipo())) {
                valores[1] += m.getValor();
            }
        }

        return resumo;
    }

    public String getCategoriaComMaiorDespesa() {
        String categoriaMaior = "Nenhuma despesa";
        double maiorValor = 0;

        for (Map.Entry<String, Double> item
                : getDespesasPorCategoria().entrySet()) {

            if (item.getValue() > maiorValor) {
                maiorValor = item.getValue();
                categoriaMaior = item.getKey();
            }
        }

        return categoriaMaior;
    }

    public boolean editar(int indice,
                          Movimentacao novaMovimentacao) {

        if (indice < 0 || indice >= movimentacoes.size()
                || novaMovimentacao == null) {
            return false;
        }

        movimentacoes.set(indice, novaMovimentacao);
        return true;
    }

    public boolean excluir(int indice) {
        if (indice < 0 || indice >= movimentacoes.size()) {
            return false;
        }

        movimentacoes.remove(indice);
        return true;
    }
}