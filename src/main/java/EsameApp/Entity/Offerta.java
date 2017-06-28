/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EsameApp.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tss
 */
@Entity
@Table(name = "T_OFFERTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = Offerta.FIND_ALL, query = "SELECT o FROM Offerta o"),
    @NamedQuery(name=Offerta.FIND_BY_USER, query="SELECT o FROM Offerta o WHERE o.utente.idUtente=:idUtente ORDER BY o.dataOfferta DESC"),
    @NamedQuery(name=Offerta.FIND_BY_AUCTION, query="SELECT o FROM Offerta o WHERE o.asta.idAsta=:idAsta")//???
})
public class Offerta implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_OFFERTA")
    private long idOfferta;
    
    @JoinColumn(name="ID_ASTA", referencedColumnName="ID_ASTA")
    @ManyToOne(optional = false)
    private Asta asta;
    
    @JoinColumn(name="ID_OFFERENTE", referencedColumnName = "ID_UTENTE")
    @ManyToOne(optional = false)
    private Utente utente;
    
    @Column(name="IMPORTO")
    @Basic(optional=false)
    private BigDecimal importo;
    
    @Column(name="DATA_OFFERTA")
    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataOfferta=new Date();
    
    public static final String FIND_ALL = "Offerta.findAll";
    public static final String FIND_BY_USER="Offerta.findByUser";
    public static final String FIND_BY_AUCTION="Offerta.findByAuction";

    public Offerta() {
    }

    public Offerta(long idOfferta, Asta asta, Utente utente, BigDecimal importo) {
        this.idOfferta = idOfferta;
        this.asta = asta;
        this.utente = utente;
        this.importo = importo;
    }

    public long getIdOfferta() {
        return idOfferta;
    }

    public Asta getAsta() {
        return asta;
    }

    public Utente getUtente() {
        return utente;
    }

    public BigDecimal getImporto() {
        return importo;
    }

    public Date getDataOfferta() {
        return dataOfferta;
    }

    public void setIdOfferta(long idOfferta) {
        this.idOfferta = idOfferta;
    }

    public void setAsta(Asta asta) {
        this.asta = asta;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public void setImporto(BigDecimal importo) {
        this.importo = importo;
    }

    public void setDataOfferta(Date dataOfferta) {
        this.dataOfferta = dataOfferta;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + (int) (this.idOfferta ^ (this.idOfferta >>> 32));
        hash = 41 * hash + Objects.hashCode(this.asta);
        hash = 41 * hash + Objects.hashCode(this.utente);
        hash = 41 * hash + Objects.hashCode(this.importo);
        hash = 41 * hash + Objects.hashCode(this.dataOfferta);
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
        final Offerta other = (Offerta) obj;
        if (this.idOfferta != other.idOfferta) {
            return false;
        }
        if (!Objects.equals(this.asta, other.asta)) {
            return false;
        }
        if (!Objects.equals(this.utente, other.utente)) {
            return false;
        }
        if (!Objects.equals(this.importo, other.importo)) {
            return false;
        }
        if (!Objects.equals(this.dataOfferta, other.dataOfferta)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Offerta{" + "idOfferta=" + idOfferta + ", asta=" + asta + ", utente=" + utente + ", importo=" + importo + ", dataOfferta=" + dataOfferta + '}';
    }
}
