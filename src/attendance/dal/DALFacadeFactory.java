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
        System.out.println("instance of DALFacadeFactory: " + instance.toString() + " ...........................FROM SINGLETONS");
        return instance;

    }

    public IDALFacade createFacade(FacadeType type) {
        switch (type) {
            case DATABASE:
                IDALFacade dalFacade = new DALManager();
                System.out.println("BLLManager obj from BLLFacadeFactory: " + dalFacade);
                return dalFacade;

            default:
                return new DALManager();
        }
    }
}
