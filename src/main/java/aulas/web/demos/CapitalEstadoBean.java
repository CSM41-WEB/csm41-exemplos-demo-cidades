package aulas.web.demos;

import aulas.web.demos.suporte.Estado;
import aulas.web.demos.suporte.Municipio;
import java.io.Serializable;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * Suporte à exibição da capital do estado.
 * @author Wilson Horstmeyer Bogado
 */
@Named
@ViewScoped
public class CapitalEstadoBean implements Serializable {
    private String uf;
    
    @Inject
    private DemosAppBean appBean;

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
    
    public Municipio getCapital() {
        Municipio capital = null;
        if (uf != null) {
            int idx = appBean.getEstados().indexOf(new Estado(uf));
            if (idx >= 0)
                capital = appBean.getEstados().get(idx).getCapital();
        }
        return capital;
    }
}
