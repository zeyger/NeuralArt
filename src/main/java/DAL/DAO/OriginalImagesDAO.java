package DAL.DAO;

import DAL.Entities.OriginalImagesEntity;
import org.springframework.stereotype.Repository;

@Repository
public class OriginalImagesDAO extends AbstractHibernateDAO <OriginalImagesEntity> implements IOriginalImagesDAO{

    public OriginalImagesDAO() {
        super();

        setClazz(OriginalImagesEntity.class);
    }
}
