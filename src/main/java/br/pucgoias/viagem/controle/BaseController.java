package br.pucgoias.viagem.controle;

import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Map;

public abstract class BaseController  implements Serializable {

    private FacesContext _facesContext;

    protected String getParamRequestValue(String key) {
        Map<String, String> params = getFacesContext().getExternalContext().getRequestParameterMap();
        if(params.containsKey(key))
            return params.get(key);
        return null;
    }

    protected FacesContext getFacesContext() {
        if (_facesContext == null)
            _facesContext = FacesContext.getCurrentInstance();
        return _facesContext;
    }
}
