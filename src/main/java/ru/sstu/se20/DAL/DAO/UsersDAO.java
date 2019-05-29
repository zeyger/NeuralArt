package ru.sstu.se20.DAL.DAO;

import ru.sstu.se20.DAL.Entities.UsersEntity;
import org.springframework.stereotype.Repository;

@Repository
public class UsersDAO extends AbstractHibernateDAO <UsersEntity> implements IUsersDAO{

    public UsersDAO() {
        super();

        setClazz(UsersEntity.class);
    }
}
