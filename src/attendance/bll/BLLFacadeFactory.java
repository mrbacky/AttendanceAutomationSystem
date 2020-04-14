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

    private BLLFacadeFactory() throws Exception {
        dalFacade = DALFacadeFactory.getInstance().createFacade(DALFacadeFactory.FacadeType.DATABASE);
    }

    public static synchronized BLLFacadeFactory getInstance() throws Exception {
        if (instance == null) {
            instance = new BLLFacadeFactory();
        }
        return instance;
    }

    public IBLLFacade createFacade(FacadeType type) throws Exception {
        switch (type) {
            case PRODUCTION:
                return new BLLManager(dalFacade);

            default:
                throw new Exception("Unknown Facade type requested from BLLFacadeFactory.");
        }

    }
}
