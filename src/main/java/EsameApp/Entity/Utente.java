/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EsameApp.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tss
 */
@Entity
@Table(name = "T_UTENTI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = Utente.FIND_ALL, query = "SELECT t FROM Utente t")
    , @NamedQuery(name = Utente.FIND_BY_EMAIL_AND_PWD, query = "SELECT t FROM Utente t WHERE t.email= :email AND t.password= :pwd")
})
public class Utente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_UTENTE")
    private long idUtente;

    @Column(name = "EMAIL", unique = true)
    @Basic(optional = false)
    private String email;

    @Column(name = "COGNOME")
    @Basic(optional = false)
    private String cognome;

    @Column(name = "NOME")
    @Basic(optional = false)
    private String nome;

    @Column(name = "INDIRIZZO")
    @Basic(optional = false)
    private String indirizzo;

    @Column(name = "PASSWORD")
    @Basic(optional = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Utente")
    private List<Prodotto> ownedProducts = new ArrayList<Prodotto>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Utente")
    private List<Offerta> offers = new ArrayList<Offerta>();

    public static final String FIND_ALL = "Utente.findAll";
    public static final String FIND_BY_EMAIL_AND_PWD = "Utente.findByEmailPwd";

    public Utente() {
    }

    public Utente(long idUtente, String email, String cognome, String nome, String password) {
        this.idUtente = idUtente;
        this.email = email;
        this.cognome = cognome;
        this.nome = nome;
        this.password = password;
    }

    public Utente(String nome, String cognome, String password, String email) {
        this.email = email;
        this.cognome = cognome;
        this.nome = nome;
        this.password = password;
    }

    public long getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(long idUtente) {
        this.idUtente = idUtente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public List<Prodotto> getOwnedProducts() {
        return ownedProducts;
    }

    public void setOwnedProducts(List<Prodotto> ownedProducts) {
        this.ownedProducts = ownedProducts;
    }

    @XmlTransient
    public List<Offerta> getOffers() {
        return offers;
    }

    public void setOffers(List<Offerta> offers) {
        this.offers = offers;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (this.idUtente ^ (this.idUtente >>> 32));
        hash = 97 * hash + Objects.hashCode(this.email);
        hash = 97 * hash + Objects.hashCode(this.cognome);
        hash = 97 * hash + Objects.hashCode(this.nome);
        hash = 97 * hash + Objects.hashCode(this.indirizzo);
        hash = 97 * hash + Objects.hashCode(this.password);
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
        final Utente other = (Utente) obj;
        if (this.idUtente != other.idUtente) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.cognome, other.cognome)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.indirizzo, other.indirizzo)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Utente{" + "idUtente=" + idUtente + ", email=" + email + ", cognome=" + cognome + ", nome=" + nome + ", indirizzo=" + indirizzo + ", password=" + password + '}';
    }

}
