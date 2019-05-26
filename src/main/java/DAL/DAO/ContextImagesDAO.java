package DAL.DAO;

import DAL.Entities.ContextImagesEntity;
import org.springframework.stereotype.Repository;

@Repository
public class ContextImagesDAO extends AbstractHibernateDAO <ContextImagesEntity> implements IContextImagesDAO{

    public ContextImagesDAO() {
        super();

        setClazz(ContextImagesEntity.class);
    }
}
