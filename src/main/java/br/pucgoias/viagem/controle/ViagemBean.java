package br.pucgoias.viagem.controle;

import br.pucgoias.viagem.entidade.Viagem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Component
public class ViagemBean {
    private Integer idViagem;

    private String diaPartida;

    private String diaChegada;

    private String origem;

    private String destino;
    private String valor = "0,00";

    public Integer getIdViagem() {
        return idViagem;
    }

    public ViagemBean(){

    }
    public ViagemBean(Viagem viagem) {
        setIdViagem(viagem.getIdViagem());
        setDestino(viagem.getDestino());
        setOrigem(viagem.getOrigem());
        setValor(viagem.getValor().toString().replace(".",","));
        DateTimeFormatter dtf = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.SHORT)
                .withLocale(new Locale("pt", "br"));
        if(viagem.getDiaChegada() != null) {
            this.setDiaChegada(dtf.format(viagem.getDiaChegada().toLocalDateTime()));
        }
        if(viagem.getDiaPartida() != null) {
            this.setDiaPartida(dtf.format(viagem.getDiaPartida().toLocalDateTime()));
        }
    }

    public void setIdViagem(Integer idViagem) {
        this.idViagem = idViagem;
    }

    public String getDiaPartida() {
        return diaPartida;
    }

    public void setDiaPartida(String diaPartida) {
        this.diaPartida = diaPartida;
    }

    public String getDiaChegada() {
        return diaChegada;
    }

    public void setDiaChegada(String diaChegada) {
        this.diaChegada = diaChegada;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
