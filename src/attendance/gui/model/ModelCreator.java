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
        return instance;
    }

    public IUserModel getUserModel() {
        return new UserModel(bllFacade);
    }

    public IStudentModel getStudentModel() {
        return new StudentModel(bllFacade);
    }

    public ILessonModel getLessonModel() {
        return new LessonModel(bllFacade);
    }

    public ICourseModel getCourseModel() {
        return new CourseModel(bllFacade);
    }
}
