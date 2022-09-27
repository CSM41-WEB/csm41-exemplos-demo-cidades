package aulas.web.demos.suporte;

import java.util.Objects;

/**
 * Representa um município do Brasil
 * @author Wilson Horstmeyer Bogado
 */
public class Municipio {
    private Integer ibge;
    private String uf;
    private String nome;
    private Integer populacao;
    private String porte;

    public Municipio() {
    }

    public Municipio(Integer ibge) {
        this.ibge = ibge;
    }

    public Municipio(Integer ibge, String uf, String nome) {
        this.ibge = ibge;
        this.uf = uf;
        this.nome = nome;
    }

    public Integer getIbge() {
        return ibge;
    }

    public void setIbge(Integer ibge) {
        this.ibge = ibge;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPopulacao() {
        return populacao;
    }

    public void setPopulacao(Integer populacao) {
        this.populacao = populacao;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.ibge);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Municipio other = (Municipio) obj;
        return Objects.equals(this.ibge, other.ibge);
    }

    @Override
    public String toString() {
        return "Municipio{" + "ibge=" + ibge + ", uf=" + uf + ", nome=" + nome + '}';
    }
}
