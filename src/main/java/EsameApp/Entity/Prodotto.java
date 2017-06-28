/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EsameApp.Entity;

import java.io.Serializable;
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

/**
 *
 * @author tss
 */
@Table(name = "T_PRODOTTI")
@Entity
@NamedQueries({
    @NamedQuery(name = Prodotto.FIND_ALL, query = "SELECT p FROM Prodotto p"),
    @NamedQuery(name=Prodotto.FIND_BY_USER, query="SELECT p FROM Prodotto p WHERE p.utente.idUtente=:idUtente")
})
public class Prodotto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODOTTO")
    private long idProduct;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_PROPRIETARIO", referencedColumnName = "ID_UTENTE")
    private Utente utente;
    
    @Column(name="DESCRIZIONE")
    @Basic(optional=false)
    private String descrizione;
    
    @Column(name="NOME_PRODOTTO")
    @Basic(optional=false)
    private String nome;
    
    @Column(name="IMMAGINE")
    @Basic(optional=false)
    private String imgUrl;
    
    public static final String FIND_ALL = "Prodotto.findAll";
    public static final String FIND_BY_USER="Prodotto.findByUser";

    public Prodotto() {
    }

    public Prodotto(long idProduct, Utente utente, String descrizione, String nome, String imgUrl) {
        this.idProduct = idProduct;
        this.utente = utente;
        this.descrizione = descrizione;
        this.nome = nome;
        this.imgUrl = imgUrl;
    }

    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (this.idProduct ^ (this.idProduct >>> 32));
        hash = 71 * hash + Objects.hashCode(this.utente);
        hash = 71 * hash + Objects.hashCode(this.descrizione);
        hash = 71 * hash + Objects.hashCode(this.nome);
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
        final Prodotto other = (Prodotto) obj;
        if (this.idProduct != other.idProduct) {
            return false;
        }
        if (!Objects.equals(this.descrizione, other.descrizione)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.utente, other.utente)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Prodotto{" + "idProduct=" + idProduct + ", utente=" + utente + ", descrizione=" + descrizione + ", nome=" + nome + '}';
    }

    

}
