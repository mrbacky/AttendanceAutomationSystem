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
    private static IDALFacade dalManager = DALFacadeFactory.getInstance().createFacade(DALFacadeFactory.FacadeType.DATABASE);

    private BLLFacadeFactory(IDALFacade dalManager) {
        this.dalManager = dalManager;
    }

    public static synchronized BLLFacadeFactory getInstance() {
        if (instance == null) {
            instance = new BLLFacadeFactory(dalManager);
        }
        return instance;
    }

    public IBLLFacade createFacade(FacadeType type) {
        switch (type) {
            case PRODUCTION:
                return new BLLManager(dalManager);
            default:
                return new BLLManager(dalManager);

        }

    }
}
