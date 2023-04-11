package aulas.web.demos;

import aulas.web.demos.suporte.Estado;
import aulas.web.demos.suporte.Municipio;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

/**
 * Bean de aplicação.
 * @author Wilson Horstmeyer Bogado
 */
@Named
@ApplicationScoped
public class DemosAppBean {
    private List<Estado> estados;
    private List<Municipio> municipios;

    public DemosAppBean() {
    }

    public boolean isInicializado() {
        return estados != null && !estados.isEmpty()
               && municipios != null && !municipios.isEmpty();
    }
    
    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        if (estados != null) {
            estados.sort((e1, e2) -> e1.getUf().compareTo(e2.getUf()));
        }
        this.estados = estados;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }
}
