package DAL.DAO;

import DAL.Entities.ImageEmotionsEntity;
import DAL.Entities.UsersEntity;
import org.springframework.stereotype.Repository;

@Repository
public class UsersDAO extends AbstractHibernateDAO <UsersEntity> implements IUsersDAO{

    public UsersDAO() {
        super();

        setClazz(UsersEntity.class);
    }
}
