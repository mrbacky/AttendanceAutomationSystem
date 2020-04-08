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
    private static IDALFacade dalFacade;

    private BLLFacadeFactory() {
        dalFacade = DALFacadeFactory.getInstance().createFacade(DALFacadeFactory.FacadeType.DATABASE);
    }

    public static synchronized BLLFacadeFactory getInstance() {
        if (instance == null) {
            instance = new BLLFacadeFactory();
        }
        return instance;
    }

    public IBLLFacade createFacade(FacadeType type) {
        switch (type) {
            case PRODUCTION:
                return new BLLManager(dalFacade);

            default:
                return new BLLManager(dalFacade);

        }

    }
}
