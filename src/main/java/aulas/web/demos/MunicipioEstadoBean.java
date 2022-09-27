package aulas.web.demos;

import aulas.web.demos.suporte.Municipio;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Suporte à exibição de dados do município.
 * @author Wilson Horstmeyer Bogado
 */
@Named
@ViewScoped
public class MunicipioEstadoBean implements Serializable {
    private String uf;
    private Integer ibge;
    private Municipio municipioSelecionado;
    List<Municipio> municipios;
    
    @Inject
    private DemosAppBean appBean;

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Integer getIbge() {
        return ibge;
    }

    public void setIbge(Integer ibge) {
        this.ibge = ibge;
    }

    public List<Municipio> getMunicipios() {
        if (uf != null) {
            municipios = appBean.getMunicipios().stream()
                    .filter(m -> m.getUf().equals(uf))
                    .sorted((m1, m2) -> m1.getNome().compareTo(m2.getNome()))
                    .collect(Collectors.toList());
        }
        return municipios;
    }
    
    public Municipio getMunicipio() {
        Municipio m = null;
        if (ibge != null && municipios != null) {
            int idx = municipios.indexOf(new Municipio(ibge));
            if (idx >= 0)
                m = municipios.get(idx);
        }
        return m;
    }

    public Municipio getMunicipioSelecionado() {
        return municipioSelecionado;
    }

    public void setMunicipioSelecionado(Municipio municipioSelecionado) {
        this.municipioSelecionado = municipioSelecionado;
    }

    public List<Municipio> completeMunicipio(String parcial) {
        String lc = parcial.toLowerCase();
        List<Municipio> resultado = getMunicipios().stream()
                .filter(m -> m.getNome().toLowerCase().contains(lc))
                .limit(10)
                .collect(Collectors.toList());
        return resultado;
    }
}
