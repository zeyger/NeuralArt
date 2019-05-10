package DAL;

import javax.persistence.*;
import java.util.Objects;
import java.util.Collection;

@Entity
@Table(name = "users", schema = "neuralart_db")
public class UsersEntity {
    private int id;
    private String email;
    private String password;
    private byte defaultPrivate;
   private Collection<ResultImagesEntity> resultImagesById;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "default_private", nullable = false)
    public byte getDefaultPrivate() {
        return defaultPrivate;
    }

    public void setDefaultPrivate(byte defaultPrivate) {
        this.defaultPrivate = defaultPrivate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return id == that.id &&
                defaultPrivate == that.defaultPrivate &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, defaultPrivate);
    }

    @OneToMany(mappedBy = "usersByUser")
    public Collection<ResultImagesEntity> getResultImagesById() {
        return resultImagesById;
    }

    public void setResultImagesById(Collection<ResultImagesEntity> resultImagesById) {
        this.resultImagesById = resultImagesById;
    }
}
