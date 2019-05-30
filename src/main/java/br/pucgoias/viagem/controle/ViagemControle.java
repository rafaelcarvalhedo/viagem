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


    //Inicializa controlador do formulario
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

    //Redireciona para pagina de listagem
    public String cancelar() {
        return "listar";
    }

    public String salvar() {
        if (viagemBean != null) {

            try {
                Viagem viagem = new Viagem();
                viagem.setIdViagem(viagemBean.getIdViagem());

                //Validações
                if (viagemBean.getOrigem() == null || viagemBean.getOrigem().isEmpty()) {
                    throw new ViagemException("Informe um valor para o campo Origem");
                }
                if (viagemBean.getDestino() == null || viagemBean.getDestino().isEmpty()) {
                    throw new ViagemException("Informe um valor para o campo Destino");
                }
                if (viagemBean.getDiaPartida() == null || viagemBean.getDiaPartida().isEmpty()
                        || Util.convertStringToTimestamp(viagemBean.getDiaPartida()) == null) {
                    throw new ViagemException("Informe um valor válido para o campo Dia Partida");
                }
                if (viagemBean.getDiaChegada() == null || viagemBean.getDiaChegada().isEmpty()
                        || Util.convertStringToTimestamp(viagemBean.getDiaChegada()) == null) {
                    throw new ViagemException("Informe um valor para o campo Dia Chegada");
                }
                if (viagemBean.getValor() == null || viagemBean.getValor().isEmpty()) {
                    throw new ViagemException("Informe um valor para o campo Valor");
                }

                // Preenche objeto persistencia
                viagem.setDiaChegada(Util.convertStringToTimestamp(viagemBean.getDiaChegada()));
                viagem.setDiaPartida(Util.convertStringToTimestamp(viagemBean.getDiaPartida()));
                viagem.setOrigem(viagemBean.getOrigem());
                viagem.setDestino(viagemBean.getDestino());
                viagem.setValor(new BigDecimal(viagemBean.getValor().replace(",", ".")));

                if (viagem.getValor().compareTo(BigDecimal.ZERO) <= 0) {
                    throw new ViagemException("Valor da viagem deve ser maior que zero");
                }
                // Caso tenha id alterar contrario disso é feito a inclusão
                if (viagem.getIdViagem() != null) {
                    viagemService.alterar(viagem);
                } else {
                    viagemService.incluir(viagem);
                }
                // Exibe mensagem de sucesso
                mostrarSucesso("Alterações realiadas com sucesso!");
                return "listar";
            } catch (ViagemException e) {
                mostrarErro(e.getMsg());
            } catch (Exception e) {
                mostrarErro(e.getMessage());
            }

        }
        return "";
    }

    public String excluir() {
        if (viagemBean != null) {

            try {
                if(viagemBean.getIdViagem() == null || viagemBean.getIdViagem() == 0)
                    throw new ViagemException("Não é possível excluir um novo registro");
                viagemService.excluir(viagemBean.getIdViagem());
                mostrarSucesso("Viagem excluida com sucesso!");
                return "listar";
            } catch (ViagemException e) {
                mostrarErro(e.getMsg());
            } catch (Exception e) {
                mostrarErro(e.getMessage());
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
