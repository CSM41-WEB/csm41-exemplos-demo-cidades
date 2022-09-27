package aulas.web.demos.suporte;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 * Modelo de dados para tabela carregada por demanda.
 * @author Wilsonh Horstmeyer Bogado
 */
public class MunicipioLazyDataModel extends LazyDataModel<Municipio> {
    
    private final List<Municipio> municipios;

    public MunicipioLazyDataModel(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    @Override
    public int count(Map<String, FilterMeta> map) {
        Long c = municipios.stream()
                    .filter(m -> filtrar(m, map))
                    .count();
        return c.intValue();
    }

    @Override
    public List<Municipio> load(int offset, int pageSize, Map<String, SortMeta> ordem, Map<String, FilterMeta> filtros) {
        List<Municipio> muns = municipios.stream()
                    .skip(offset)
                    .filter(m -> filtrar(m, filtros))
                    .limit(pageSize)
                    .collect(Collectors.toList());
        ordenar(muns, ordem);
        return muns;
    }
    
    private boolean startsWith(Object o, Object inicio) {
        return o.toString().toLowerCase().startsWith(inicio.toString().toLowerCase());
    }
    
    private boolean filtrar(Municipio mun, Map<String, FilterMeta> filtros) {
        boolean incluir = true;
        
        for (String key: filtros.keySet()) {
            Object fv = filtros.get(key).getFilterValue();
            switch (key) {
                case "nome" -> incluir = incluir && startsWith(mun.getNome(), fv);
                case "uf" ->  incluir = incluir && startsWith(mun.getUf(), fv);
            }
        }
        
        return incluir;
    }
    
    private void ordenar(List<Municipio> muns, Map<String, SortMeta> ordem) {
        for (String key: ordem.keySet()) {
            boolean asc = ordem.get(key).getOrder().isAscending();
            switch (key) {
                case "nome" -> {
                    if (asc)
                        muns.sort((m1, m2) -> m1.getNome().compareTo(m2.getNome()));
                    else
                        muns.sort((m2, m1) -> m1.getNome().compareTo(m2.getNome()));
                }
                case "uf" -> {
                    if (asc)
                        muns.sort((m1, m2) -> m1.getUf().compareTo(m2.getUf()));
                    else
                        muns.sort((m2, m1) -> m1.getUf().compareTo(m2.getUf()));
                }
            }
        }
    }
    
    
}
