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
    private static IBLLFacade bllManager = BLLFacadeFactory.getInstance().createFacade(BLLFacadeFactory.FacadeType.PRODUCTION);

    public ModelCreator(IBLLFacade bllManager) {
        this.bllManager = bllManager;
    }

    public static synchronized ModelCreator getInstance() {// singleton or just static methods?
        if (instance == null) {
            instance = new ModelCreator(bllManager);
        }
        return instance;
    }

    public IUserModel getUserModel() {
        return new UserModel(bllManager);
    }

    public IStudentModel getStudentModel() {
        return new StudentModel(bllManager);
    }

    public ILessonModel getLessonModel() {
        return new LessonModel(bllManager);
    }

    public ICourseModel getCourseModel() {
        return new CourseModel(bllManager);
    }
}
