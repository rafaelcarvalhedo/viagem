package br.pucgoias.viagem.controle;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ViagemBean {
    private Integer idViagem;

    private Timestamp diaPartida;

    private Timestamp diaChegada;

    private String origem;

    private String destino;

    public Integer getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(Integer idViagem) {
        this.idViagem = idViagem;
    }

    public Timestamp getDiaPartida() {
        return diaPartida;
    }

    public void setDiaPartida(Timestamp diaPartida) {
        this.diaPartida = diaPartida;
    }

    public Timestamp getDiaChegada() {
        return diaChegada;
    }

    public void setDiaChegada(Timestamp diaChegada) {
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
}
