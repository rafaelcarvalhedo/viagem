package br.pucgoias.viagem.controle;

import br.pucgoias.viagem.negocio.ViagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 */
@ViewScoped
@Controller
public class ViagemControle implements Serializable{


    private ViagemBean viagemBean;


    @Autowired
    private ViagemService viagemService;


    private List<ViagemBean> listaViagemBean;


    @PostConstruct
    public void postConstruct(){
        System.out.println("LOG  ViagemControle");
//        FacesContext fc = FacesContext.getCurrentInstance();
//        Map<String,String> params =
//                fc.getExternalContext().getRequestParameterMap();
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
