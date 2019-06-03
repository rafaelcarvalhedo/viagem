package br.pucgoias.viagem.controle;

import br.pucgoias.spring.context.scope.SpringScopeView;
import br.pucgoias.viagem.entidade.Viagem;
import br.pucgoias.viagem.negocio.ViagemService;
import br.pucgoias.viagem.util.ViagemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador da pagina de listagem de viagens
 * Utiliza ciclo de vida de View para que a lista de objetos sejam descartadas ao sair da pagina
 */
@SpringScopeView
@Controller
public class ListaViagemControle implements Serializable {

    /**
     *  Array de ViagenBean utilizadas na visão
     */
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

    /**
     * Consulta e iniciliza lista de viagens a serem apresentadas
     */
    public void montarLista() {
        try {

            List<Viagem> listaViagem = viagemService.listar();
            if (listaViagem == null || listaViagem.isEmpty()) {
                return;
            }
            //preeche a lista de pessoas da tela
            listaViagemBean = new ArrayList<ViagemBean>();
            listaViagem.forEach(viagem -> listaViagemBean.add( new ViagemBean(viagem)));
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

    /**
     * @return redireciona para pagina de edição de viagem
     */
    public String editar(){
        return "editar";
    }

    private FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public List<ViagemBean> getListaViagemBean() {
        return listaViagemBean;
    }

    public void setListaViagemBean(List<ViagemBean> listaViagemBean) {
        this.listaViagemBean = listaViagemBean;
    }
}
