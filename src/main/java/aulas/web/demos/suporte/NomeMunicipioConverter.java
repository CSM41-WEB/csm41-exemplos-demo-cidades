package aulas.web.demos.suporte;

import aulas.web.demos.DemosAppBean;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Conversor usado quando temos apenas o nome do município.
 * @author Wilson Horstmeyer Bogado
 */
@Named
@FacesConverter(value = "nomeMunicipioConverter", managed = true)
public class NomeMunicipioConverter implements Converter<Municipio>{

    @Inject
    private DemosAppBean appBean;
    
    @Override
    public Municipio getAsObject(FacesContext fc, UIComponent uic, String chave) {
        try {
            if (chave == null || chave.isBlank())
                return null;
            else {
                Integer ibge = Integer.valueOf(chave);
                int idx = appBean.getMunicipios().indexOf(new Municipio(ibge));
                if (idx < 0)
                    throw new ConverterException(
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Município inexistente", null));
                else
                    return appBean.getMunicipios().get(idx);
            }
        } catch (NumberFormatException e) {
            throw new ConverterException(
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Município inválido", null));

        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Municipio mun) {
        return mun == null ? null : mun.getIbge().toString();
    }
    
}
