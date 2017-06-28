/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EsameApp.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tss
 */
@Entity
@Table(name = "T_ASTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = Asta.FIND_ALL, query = "SELECT a FROM Asta a")
    ,
    @NamedQuery(name = Asta.FIND_BY_USER, query = "SELECT a FROM Asta a WHERE a.utente.idUtente=:idUtente GROUP BY a.terminata")
    ,
   @NamedQuery(name = Asta.FIND_BY_OTHER_USER, query = "SELECT a FROM Asta a WHERE a.utente.idUtente!=:idUtente AND a.terminata=FALSE")
})
public class Asta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ASTA")
    private long idAsta;

    @Basic(optional = false)
    @Column(name = "DATA_INIZIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInizio = new Date();

    @Basic(optional = false)
    @Column(name = "DATA_TERMINE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFine = new Date();

    @Basic(optional = false)
    @Column(name = "BASE_ASTA")
    private BigDecimal baseAsta;

    @Column(name = "DESCRIZIONE")
    private String descrizione;

    @JoinColumn(name = "ID_PROPRIETARIO", referencedColumnName = "ID_UTENTE")
    @ManyToOne(optional = false)
    private Utente utente;

    @JoinColumn(name = "ID_PRODOTTO", referencedColumnName = "ID_PRODOTTO")
    @ManyToOne(optional = false)
    private Prodotto prodotto;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Asta")
    private List<Offerta> elencoOfferte = new ArrayList<Offerta>();

    @Column(name = "TERMINATA")
    @NotNull
    private boolean terminata = false;

    public static final String FIND_ALL = "Asta.findAll";
    public static final String FIND_BY_USER = "Asta.findByUser";
    public static final String FIND_BY_OTHER_USER = "Asta.findByOtherUser";

    public Asta() {
    }

    public Asta(long idAsta, BigDecimal baseAsta, String descrizione, Utente utente, Prodotto prodotto) {
        this.idAsta = idAsta;
        this.baseAsta = baseAsta;
        this.descrizione = descrizione;
        this.utente = utente;
        this.prodotto = prodotto;
    }

    public long getIdAsta() {
        return idAsta;
    }

    public void setIdAsta(long idAsta) {
        this.idAsta = idAsta;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public void setDataFine(Date dataFine) {
        this.dataFine = dataFine;
    }

    public BigDecimal getBaseAsta() {
        return baseAsta;
    }

    public void setBaseAsta(BigDecimal baseAsta) {
        this.baseAsta = baseAsta;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    @XmlTransient
    public List<Offerta> getElencoOfferte() {
        return elencoOfferte;
    }

    public void setElencoOfferte(List<Offerta> elencoOfferte) {
        this.elencoOfferte = elencoOfferte;
    }

    public boolean isTerminata() {
        return terminata;
    }

    public void setTerminata(boolean terminata) {
        this.terminata = terminata;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (int) (this.idAsta ^ (this.idAsta >>> 32));
        hash = 53 * hash + Objects.hashCode(this.dataInizio);
        hash = 53 * hash + Objects.hashCode(this.dataFine);
        hash = 53 * hash + Objects.hashCode(this.baseAsta);
        hash = 53 * hash + Objects.hashCode(this.descrizione);
        hash = 53 * hash + Objects.hashCode(this.utente);
        hash = 53 * hash + Objects.hashCode(this.prodotto);
        hash = 53 * hash + Objects.hashCode(this.elencoOfferte);
        hash = 53 * hash + (this.terminata ? 1 : 0);
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
        final Asta other = (Asta) obj;
        if (this.idAsta != other.idAsta) {
            return false;
        }
        if (this.terminata != other.terminata) {
            return false;
        }
        if (!Objects.equals(this.descrizione, other.descrizione)) {
            return false;
        }
        if (!Objects.equals(this.dataInizio, other.dataInizio)) {
            return false;
        }
        if (!Objects.equals(this.dataFine, other.dataFine)) {
            return false;
        }
        if (!Objects.equals(this.baseAsta, other.baseAsta)) {
            return false;
        }
        if (!Objects.equals(this.utente, other.utente)) {
            return false;
        }
        if (!Objects.equals(this.prodotto, other.prodotto)) {
            return false;
        }
        if (!Objects.equals(this.elencoOfferte, other.elencoOfferte)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Asta{" + "idAsta=" + idAsta + ", dataInizio=" + dataInizio + ", dataFine=" + dataFine + ", baseAsta=" + baseAsta + ", descrizione=" + descrizione + ", utente=" + utente + ", prodotto=" + prodotto + ", elencoOfferte=" + elencoOfferte + ", terminata=" + terminata + '}';
    }

}
