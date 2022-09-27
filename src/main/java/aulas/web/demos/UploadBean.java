package aulas.web.demos;

import aulas.web.demos.suporte.Estado;
import aulas.web.demos.suporte.Municipio;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

/**
 * Suporte ao upload de arquivos.
 * @author Wilson Horstmeyer Bogado
 */
@Named
@RequestScoped
public class UploadBean implements Serializable {
    private UploadedFile file;
    private static final String FILE_CHARSET = "ISO-8859-1";
    private static final CSVFormat csvFormat = CSVFormat.EXCEL.builder()
            .setHeader()
            .setDelimiter(';')
            .setIgnoreSurroundingSpaces(true)
            .build();

    @Inject
    private DemosAppBean appBean;
    
    public UploadBean() {
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    private Estado toEstado(CSVRecord r) {
        return new Estado(r.get("UF"), r.get("Estado"), r.get("Região"));
    }
    
    private void carregaEstados(InputStream is) throws IOException {
        List<Estado> estados = new ArrayList<>();
        appBean.setMunicipios(null);
        try (CSVParser parser = CSVParser.parse(is, Charset.forName(FILE_CHARSET), csvFormat)) {
            for (CSVRecord r : parser) {
                Estado e = toEstado(r);
                estados.add(e);
            }
        }
        appBean.setEstados(estados);
    }
    
    private void updateCapital(Municipio m) {
        int idx = appBean.getEstados().indexOf(new Estado(m.getUf()));
        if (idx >= 0)
            appBean.getEstados().get(idx).setCapital(m);
    }
    
    private Municipio toMunicipio(CSVRecord r) {
        Integer ibge = Integer.valueOf(r.get("IBGE"));
        Municipio m = new Municipio(ibge, r.get("UF"), r.get("Município"));
        m.setPorte(r.get("Porte"));
        String pop2010 = r.get("População 2010");
        if (pop2010 != null && !pop2010.isBlank())
            m.setPopulacao(Integer.valueOf(pop2010));
        return m;
    }
    
    private void carregaMunicipios(InputStream is) throws IOException {
        List<Municipio> municipios = new ArrayList<>();
        try (CSVParser parser = CSVParser.parse(is, Charset.forName(FILE_CHARSET), csvFormat)) {
            for (CSVRecord r: parser) {
                Municipio m = toMunicipio(r);
                municipios.add(m);
                String capital = r.get("Capital");
                if (capital != null && !capital.isBlank()) {
                    updateCapital(m);
                }
            }
        }
        appBean.setMunicipios(municipios);
    }
    
    /**
     * Executado quando um arquivo é carregado pelo usuário.
     * @param event O evento de upload de arquivo
     */
    public void uploadListener(FileUploadEvent event) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        this.file = event.getFile();
        try {
            if (file != null) {
                FacesMessage fm = new FacesMessage(
                        FacesMessage.SEVERITY_INFO, 
                        MessageFormat.format("{0} carregado", file.getFileName()),
                        null);
                switch (event.getComponent().getId()) {
                    case "carrega-estados" -> carregaEstados(file.getInputStream());
                    case "carrega-municipios" -> carregaMunicipios(file.getInputStream());
                }
                fctx.addMessage(null, fm);
            }
        } catch (Throwable t) {
            String fn = file == null ? ""  : file.getFileName();
            String msg = MessageFormat.format("Erro processando arquivo {0}", fn);
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            fctx.addMessage(null, fm);
            fctx.getExternalContext().log(msg, t);
        }
    }    
}
