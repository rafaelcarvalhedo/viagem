package br.pucgoias.viagem.controle;

import org.primefaces.PrimeFaces;
import org.springframework.stereotype.Component;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Map;

@Component
public abstract class BaseController implements Serializable {

    private FacesContext _facesContext;

    private static String NOTFY_DANGER = "danger";
    private static String NOTFY_SUCCESS = "success";
    private static String NOTFY_WARNING = "warning";

    protected String getParamRequestValue(String key) {
        Map<String, String> params = getFacesContext().getExternalContext().getRequestParameterMap();
        if (params.containsKey(key))
            return params.get(key);
        return null;
    }

    protected FacesContext getFacesContext() {
        if (_facesContext == null)
            _facesContext = FacesContext.getCurrentInstance();
        return _facesContext;
    }

    protected void mostrarSucesso(String message){
        adicionarNotifcacao("Atenção",message,NOTFY_SUCCESS);
    }
    protected void mostrarErro(String message){
        adicionarNotifcacao("Atenção",message,NOTFY_DANGER);
    }
    private void adicionarNotifcacao(String title,String message, String type) {
        // ...
        StringBuilder script = new StringBuilder();
        script.append("$.notify({");
        script.append("        title: '<strong>%s</strong>',");
        script.append("message: '%s'");
        script.append("},{");
        script.append("    type: '%s'");
        script.append("  });");
        renderScript(String.format(script.toString(),title,message,type));
    }

    //renderiza script para exibir mensagens
    //funciona somente nas requisicoes ajax
    public void renderScript(String script) {
        PrimeFaces.current().executeScript(script);
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ExternalContext extContext = ctx.getExternalContext();
//        if (ctx.getPartialViewContext().isAjaxRequest()) {
//            try {
//                extContext.setResponseContentType("text/xml");
//                extContext.addResponseHeader("Cache - Control ", "no - cache");
//                PartialResponseWriter writer = ctx.getPartialViewContext()
//                        .getPartialResponseWriter();
//                writer.startDocument();
//                writer.startEval();
//                writer.write(script);
//                writer.endEval();
//                writer.endDocument();
//                writer.flush();
//                ctx.responseComplete();
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//        }
    }
}
