package aulas.web.demos.suporte;

import java.util.Objects;

/**
 * Representa um nome do Brasil.
 * @author Wilson Horstmeyer Bogado
 */
public class Estado {
    private String uf;
    private String nome;
    private Municipio capital;
    private String regiao;

    public Estado() {
    }
    
    public Estado(String uf) {
        this.uf = uf;
    }

    public Estado(String uf, String estado, String regiao) {
        this.uf = uf;
        this.nome = estado;
        this.regiao = regiao;
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

    public Municipio getCapital() {
        return capital;
    }

    public void setCapital(Municipio capital) {
        this.capital = capital;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.uf);
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
        final Estado other = (Estado) obj;
        return Objects.equals(this.uf, other.uf);
    }

    @Override
    public String toString() {
        return "Estado{" + "uf=" + uf + ", nome=" + nome + '}';
    }
}
