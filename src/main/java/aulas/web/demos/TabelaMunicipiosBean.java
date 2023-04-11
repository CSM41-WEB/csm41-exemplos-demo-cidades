package aulas.web.demos;

import aulas.web.demos.suporte.MunicipioLazyDataModel;
import java.io.Serializable;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * Suporte à exibição da tabela de municípios.
 * @author Wilson Horstmeyer Bogado
 */
@Named
@ViewScoped
public class TabelaMunicipiosBean implements Serializable {
    
    private MunicipioLazyDataModel dataModel;
    
    @Inject
    private DemosAppBean appBean;

    @PostConstruct
    public void inicializa() {
        dataModel = new MunicipioLazyDataModel(appBean.getMunicipios());
    }

    public MunicipioLazyDataModel getDataModel() {
        return dataModel;
    }

    public void setDataModel(MunicipioLazyDataModel dataModel) {
        this.dataModel = dataModel;
    }
}
