package attendance.bll;

import attendance.dal.DALFacadeFactory;
import attendance.dal.IDALFacade;

/**
 *
 * @author rado
 */
public class BLLFacadeFactory {

    public enum FacadeType {
        PRODUCTION
    }

    private static BLLFacadeFactory instance;
    private static IDALFacade daLFacade;

    private BLLFacadeFactory() {
        daLFacade = DALFacadeFactory.getInstance().createFacade(DALFacadeFactory.FacadeType.DATABASE);
    }

    public static synchronized BLLFacadeFactory getInstance() {
        if (instance == null) {
            instance = new BLLFacadeFactory();
        }
        System.out.println("instance of BLLFacadeFactory: " + instance + " ...........................FROM SINGLETONS");
        return instance;
    }

    public IBLLFacade createFacade(FacadeType type) {
        switch (type) {
            case PRODUCTION:
                IBLLFacade bllManager = new BLLManager(daLFacade);
                System.out.println("BLLManager obj from BLLFacadeFactory: " + bllManager);
                return bllManager;

            default:
                return new BLLManager(daLFacade);

        }

    }
}
