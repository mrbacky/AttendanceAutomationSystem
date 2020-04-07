package attendance.dal;

import attendance.dal.IDALFacade;

/**
 *
 * @author rado
 */
public class DALFacadeFactory {
    public enum FacadeType {
        DATABASE
    }
    
    private static DALFacadeFactory instance;

    

    private DALFacadeFactory() {
    }

    public static synchronized DALFacadeFactory getInstance() {
        if (instance == null) {
            instance = new DALFacadeFactory();
        }
        return instance;
    }

    public IDALFacade createFacade(FacadeType type) {
        switch (type) {
            case DATABASE:
                return new DALManager();
            default:
                return new DALManager();
        }
    }
}
