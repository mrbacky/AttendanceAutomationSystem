package attendance.gui.model;

import attendance.bll.BLLFacadeFactory;
import attendance.gui.model.interfaces.IUserModel;
import attendance.bll.IBLLFacade;
import attendance.dal.DALFacadeFactory;
import attendance.gui.model.concrete.CourseModel;
import attendance.gui.model.concrete.LessonModel;
import attendance.gui.model.concrete.StudentModel;
import attendance.gui.model.concrete.UserModel;
import attendance.gui.model.interfaces.ICourseModel;
import attendance.gui.model.interfaces.ILessonModel;
import attendance.gui.model.interfaces.IStudentModel;

/**
 *
 * @author rado
 */
public class ModelCreator {

    private static ModelCreator instance;
    private IBLLFacade bllFacade;

    private ModelCreator() {
        bllFacade = BLLFacadeFactory.getInstance().createFacade(BLLFacadeFactory.FacadeType.PRODUCTION);
    }

    public static synchronized ModelCreator getInstance() {// singleton or just static methods?
        if (instance == null) {
            instance = new ModelCreator();
        }
        System.out.println("instance of ModelCreator: " + instance + " ...........................FROM SINGLETONS");
        return instance;
    }

    public IUserModel getUserModel() {
        System.out.println("created user Model");
        return new UserModel(bllFacade);
    }

    public IStudentModel getStudentModel() {
        System.out.println("created student Model");

        return new StudentModel(bllFacade);
    }

    public ILessonModel getLessonModel() {
        System.out.println("created lesson Model");

        return new LessonModel(bllFacade);
    }

    public ICourseModel getCourseModel() {
        System.out.println("created course Model");

        return new CourseModel(bllFacade);
    }
}
