package lesson4;

import lesson4.controllers.GroupController;
import lesson4.controllers.StudentController;
import lesson4.controllers.TeacherController;
import lesson4.repositories.StudentRepository;
import lesson4.repositories.TeacherRepository;
import lesson4.services.GroupService;
import lesson4.services.StudentService;
import lesson4.services.TeacherService;
import lesson4.view.GroupView;
import lesson4.view.SortType;
import lesson4.view.StudentView;
import lesson4.view.TeacherView;

import java.util.Scanner;



public class UniversityApp {


    private static StudentView getStudentController() {
        studentRepository = new StudentRepository();
        studentService = new StudentService(studentRepository);
        studentController = new StudentController(studentService);
        return new StudentView(studentController);
    }

    private static TeacherView getTeacherController() {
        teacherRepository = new TeacherRepository();
        teacherService = new TeacherService(teacherRepository);
        teacherController = new TeacherController(teacherService);
        return new TeacherView(teacherController);
    }

    private static GroupView getGroupView() {
        GroupService groupService = new GroupService(studentService, teacherService);
        GroupController groupController = new GroupController(groupService);
        return new GroupView(groupController);
    }
    private static String Scan(){
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine().replace("_", " ");
        return command;
    }

    private static StudentRepository studentRepository;
    private static StudentService studentService;
    private static StudentController studentController;

    private static TeacherRepository teacherRepository;
    private static TeacherService teacherService;
    private static TeacherController teacherController;

    public void run(){

        StudentView studentView = getStudentController();
        GroupView groupView = getGroupView();
        TeacherView teacherView = getTeacherController();

        studentView.create("Ivan Morozov", 18, "02", "11Б");
        studentView.create("Ivan Morozov", 18, "02", "11Б");
        studentView.create("Petr Vorobev", 19, "03", "10А");
        studentView.create("Sidor Sidorov", 20, "112", "10А");
        studentView.create("Elena Ivanova", 19, "911", "10А");
        studentView.create("Anna Morozova", 17, "01", "11А");

        teacherView.create("Igor Mihalkov", 40, "5464", "11Б");
        teacherView.create("Egor Egorov", 35, "4564", "11Б");
        teacherView.create("Pasha Pavlov", 50, "4545", "10А");
        teacherView.create("Elena Anisimova", 59, "4648", "10А");
        teacherView.create("Ibragim Vam Znakom", 100, "1215", "10А");
        teacherView.create("Liliya Abatolevna", 29, "68445", "11А");


        while  (true) {
            System.out.println("Введите, get-student, если хотите увидеть всех студентов. "+
                    "Введите get-student или et-teacher , если хотите отобразить весь список cтудентов или преподавателей. \n" +
                    "Введите get-group, если хотите осуществить поиск по группе. \n" +
                    "Введите  create-student иди create-teacher и следуйте инструкциям, чтобы создать нового стужента или преподавателя. \n"+
                    "Введите delete-student или delete-teacher, чтобы удалить студента или преподавателя из списков.");
            switch (Scan()) {
                case "get-student":
                    studentView.sendOnConsole(SortType.ID);
                    break;
                case "get-teacher":
                    teacherView.sendOnConsole(SortType.ID);
                case "get-group":
                    System.out.println("Введите группу, которую нужно вывести на экран");
                    groupView.printAllFromGroup(Scan());
                    break;
                case "create-student":
                    System.out.println("Введите полное имя студента: ");
                    String nameStudy = Scan();
                    System.out.println("Введите возраст студента: ");
                    int ageStudy = Integer.parseInt(Scan());
                    System.out.println("Введите номер телефона студента");
                    String phoneNumbStudy = Scan();
                    System.out.println("Введите группу студента");
                    String groupNumStudy = Scan();
                    studentView.create(nameStudy, ageStudy, phoneNumbStudy, groupNumStudy);
                    break;
                case "delete-student":
                    System.out.println("Введите полное имя студента, которого надо удалить из списка");
                    String fullNameStudy = Scan();
                    studentView.removeUser(fullNameStudy);
                    break;
                case "create-teacher":
                    System.out.println("Введите полное имя преподавателя: ");
                    String nameTeach = Scan();
                    System.out.println("Введите возраст преподавателя: ");
                    int ageTeach = Integer.parseInt(Scan());
                    System.out.println("Введите номер телефона преподавателя");
                    String phoneNumbTeach = Scan();
                    System.out.println("Введите группу преподавателя");
                    String groupNumTeach = Scan();
                    teacherView.create(nameTeach, ageTeach, phoneNumbTeach, groupNumTeach);
                    break;
                case "delete-teacher":
                    System.out.println("Введите полное имя студента, которого надо удалить из списка");
                    String fullNameTeach = Scan();
                    teacherView.removeUser(fullNameTeach);
                    break;
                case "exit":
                    System.exit(0);
            }
        }
    }


}
