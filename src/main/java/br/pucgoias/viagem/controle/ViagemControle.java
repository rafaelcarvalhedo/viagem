package br.pucgoias.viagem.controle;

import br.pucgoias.spring.context.scope.SpringScopeView;
import br.pucgoias.viagem.entidade.Viagem;
import br.pucgoias.viagem.negocio.ViagemService;
import br.pucgoias.viagem.util.Util;
import br.pucgoias.viagem.util.ViagemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 */
@SpringScopeView
@Controller
public class ViagemControle extends BaseController {


    private ViagemBean viagemBean;


    @Autowired
    private ViagemService viagemService;


    private List<ViagemBean> listaViagemBean;


    @PostConstruct
    public void postConstruct() {
        System.out.println("LOG  ViagemControle");
        try {
            if (getParamRequestValue("idViagem") != null) {
                Integer viagemId = Integer.valueOf(getParamRequestValue("idViagem"));
                viagemBean = new ViagemBean(viagemService.consultar(viagemId));
            } else {
                viagemBean = new ViagemBean();
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

    public String cancelar(){
        return "listar";
    }
    public String salvar() {
        if (viagemBean != null) {

            try {
                Viagem viagem = new Viagem();
                viagem.setIdViagem(viagemBean.getIdViagem());
                viagem.setDiaChegada(Util.convertStringToTimestamp(viagemBean.getDiaChegada()));
                viagem.setDiaPartida(Util.convertStringToTimestamp(viagemBean.getDiaPartida()));
                viagem.setOrigem(viagemBean.getOrigem());
                viagem.setDestino(viagemBean.getDestino());
                viagem.setValor(new BigDecimal(viagemBean.getValor().replace(",",".")));
                if (viagem.getIdViagem() != null) {
                    viagemService.alterar(viagem);
                } else {
                    viagemService.incluir(viagem);
                }
               return "listar";
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
        return "";
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
