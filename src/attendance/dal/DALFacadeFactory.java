package attendance.dal;

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

    public IDALFacade createFacade(FacadeType type) throws Exception {
        switch (type) {
            case DATABASE:
                return new DALManager();
            default:
                throw new Exception("Unknown Facade type requested from DALFacadeFactory.");
        }
    }
}
