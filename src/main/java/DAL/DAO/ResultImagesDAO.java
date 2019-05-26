package DAL.DAO;

import DAL.Entities.OriginalImagesEntity;
import DAL.Entities.ResultImagesEntity;
import org.springframework.stereotype.Repository;

@Repository
public class ResultImagesDAO extends AbstractHibernateDAO <ResultImagesEntity> implements IResultImagesDAO{

    public ResultImagesDAO() {
        super();

        setClazz(ResultImagesEntity.class);
    }
}
