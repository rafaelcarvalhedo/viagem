package br.pucgoias.viagem.controle;

import br.pucgoias.viagem.entidade.Viagem;
import br.pucgoias.viagem.negocio.ViagemService;
import br.pucgoias.viagem.util.ViagemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@ViewScoped
@Controller
public class ListaViagemControle implements Serializable {


    private ViagemBean viagemBean;

    private List<ViagemBean> listaViagemBean;

    @Autowired
    private ViagemService viagemService;

    /**
     * ViagemControle as pessoas cadastradas
     *
     * @return
     */
    @PostConstruct
    public void posConstruct(){
        montarLista();
    }

    public void montarLista() {
        try {

            List<Viagem> listaViagem = viagemService.listar();

            if (listaViagem == null || listaViagem.isEmpty()) {
                return;
            }

            //preeche a lista de pessoas da tela
            listaViagemBean = new ArrayList<ViagemBean>();
            for (Viagem viagem : listaViagem) {
                ViagemBean viagemBean = new ViagemBean();
                viagemBean.setIdViagem(viagem.getIdViagem());
                viagemBean.setDestino(viagem.getDestino());
                viagemBean.setOrigem(viagem.getOrigem());
                viagemBean.setDiaChegada(viagem.getDiaChegada());
                viagemBean.setDiaPartida(viagem.getDiaPartida());
                listaViagemBean.add(viagemBean);
            }
        } catch (ViagemException e) {
            e.printStackTrace();
            String msg = "Listagem nao realizada. Movito: " + e.getMsg();
            FacesMessage message = new FacesMessage(msg);
            this.getFacesContext().addMessage("formulario", message);
        } catch (Exception e) {
            e.printStackTrace();
            String msg = "Listagem nao realizada. Movito: " + e.getMessage();
            FacesMessage message = new FacesMessage(msg);
            this.getFacesContext().addMessage("formulario", message);
        }
    }

    public String editar(){
        return "editar";
    }

    private FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public ViagemBean getViagemBean() {
        return viagemBean;
    }

    public void setViagemBean(ViagemBean viagemBean) {
        this.viagemBean = viagemBean;
    }

    public List<ViagemBean> getListaViagemBean() {
        return listaViagemBean;
    }

    public void setListaViagemBean(List<ViagemBean> listaViagemBean) {
        this.listaViagemBean = listaViagemBean;
    }
}
