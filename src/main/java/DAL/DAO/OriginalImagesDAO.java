package DAL.DAO;

import DAL.Entities.OriginalImagesEntity;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;
import utils.HibernateUtil;
import java.util.ArrayList;

@Repository
public class OriginalImagesDAO extends AbstractHibernateDAO <OriginalImagesEntity> implements IOriginalImagesDAO{

   // Class aClass = OriginalImagesEntity.class;

    public OriginalImagesDAO() {
        super();

        setClazz(OriginalImagesEntity.class);
    }
    /*@Override
    public OriginalImagesEntity getById(final long id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        OriginalImagesEntity result = (OriginalImagesEntity) session.get(aClass, id);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return result;
    }

    @Override
    public ArrayList<OriginalImagesEntity> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = String.format("from %s",typeParameterClass.getCanonicalName());
        Query SQLQuery = session.createQuery(hql);
        ArrayList<Bean> result = (ArrayList<Bean>) SQLQuery.list();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return result;
    }

    public void create(final OriginalImagesEntity entity)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }*/
}
