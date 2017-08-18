package db;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Erin on 8/14/2017.
 */
public class DBUtil {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("OwlPU");

    public static EntityManagerFactory getEmFactory(){
        return emf;
    }
}
