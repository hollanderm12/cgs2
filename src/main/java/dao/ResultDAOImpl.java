package dao;

import java.util.List;
import model.Result;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ResultDAOImpl implements ResultDAO {
    @Autowired
    private SessionFactory sessionFactory;
    	
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
    @Override
    @Transactional
    public void addResult(Result r) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(r);
    }

    @Override
    public void updateResult(Result r) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(r);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Result> listResults() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Result> resultsList = session.createQuery("from Result order by resultID").list();
        return resultsList;
    }

    @Override
    public Result getResultById(int id) {
        Session session = this.sessionFactory.getCurrentSession();		
        Result s = (Result) session.get(Result.class, id);
        return s;
    }

    @Override
    public void removeResult(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Result s = (Result) session.load(Result.class, id);
        if(null != s){
            session.delete(s);
        }
    }
}
