package DAL;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "result_images", schema = "neuralart_db")
public class ResultImagesEntity {
    private int id;
    private byte private_status;
    private java.util.Date creationDate;
    private OriginalImagesEntity originalImagesByOriginalImage;
    private ContextImagesEntity contextImagesByContextImage;
    private Collection<ImageEmotionsEntity> imageEmotionsById;
    private UsersEntity usersByUser;
    private byte[] image;

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
    @Column(name = "image")
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Basic
    @Column(name = "private", nullable = false)
    public byte getPrivate_status() {
        return private_status;
    }

    public void setPrivate_status(byte private_status) {
        this.private_status = private_status;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date", nullable = true)
    public java.util.Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(java.util.Date creationDate) {
        this.creationDate = creationDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultImagesEntity that = (ResultImagesEntity) o;
        return id == that.id &&
                private_status == that.private_status &&
                Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, private_status, creationDate);
    }

    @ManyToOne
    @JoinColumn(name = "original_image", referencedColumnName = "id")
    public OriginalImagesEntity getOriginalImagesByOriginalImage() {
        return originalImagesByOriginalImage;
    }

    public void setOriginalImagesByOriginalImage(OriginalImagesEntity originalImagesByOriginalImage) {
        this.originalImagesByOriginalImage = originalImagesByOriginalImage;
    }

    @ManyToOne
    @JoinColumn(name = "context_image", referencedColumnName = "id")
    public ContextImagesEntity getContextImagesByContextImage() {
        return contextImagesByContextImage;
    }

    public void setContextImagesByContextImage(ContextImagesEntity contextImagesByContextImage) {
        this.contextImagesByContextImage = contextImagesByContextImage;
    }

    @OneToMany(mappedBy = "resultImagesByImage")
    public Collection<ImageEmotionsEntity> getImageEmotionsById() {
        return imageEmotionsById;
    }

    public void setImageEmotionsById(Collection<ImageEmotionsEntity> imageEmotionsById) {
        this.imageEmotionsById = imageEmotionsById;
    }

 @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    public UsersEntity getUsersByUser() {
        return usersByUser;
    }

    public void setUsersByUser(UsersEntity usersByUser) {
        this.usersByUser = usersByUser;
    }
}
