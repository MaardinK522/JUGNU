import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mkproductions.jugnu.demos.Student;
import org.mkproductions.jugnu.entities.ClassifyAbleClass;
import org.mkproductions.jugnu.utilities.PackageReader;

import java.util.List;

public class JUGNUTester {
    @Test
    public void getClassesInPackage() {
        List<Class<?>> classSet = PackageReader.getInstance().findAllClasses("org.mkproductions");
        Assertions.assertEquals(10, classSet.size());
        System.out.println("Successfully found all the classes.");
    }

    @Test
    public void getClassifyAbleClassesInPackage() {
        List<Class<? extends ClassifyAbleClass>> classSet = PackageReader.getInstance().findAllClassesAnnotatedWith("org.mkproductions.jugnu");
        Assertions.assertEquals(2, classSet.size());
        System.out.println("Successfully found all the annotated classes.");
    }

    @Test
    public void getClassifyAbleVariablesInClasses() {
        PackageReader packageReader = PackageReader.getInstance();
        List<Class<? extends ClassifyAbleClass>> classSet = packageReader.findAllClassesAnnotatedWith("org.mkproductions.jugnu").stream().toList();
        for (var cls : classSet)
            if (cls.isInstance(Student.class))
                Assertions.assertEquals(1, packageReader.findAllAnnotatedFieldsFromClass(classSet.getFirst()).size());
    }

    @Test
    public void getClassifyAbleMethodsInClasses() {
        PackageReader packageReader = PackageReader.getInstance();
        List<Class<? extends ClassifyAbleClass>> classSet = packageReader.findAllClassesAnnotatedWith("org.mkproductions.jugnu").stream().toList();
        for (var cls : classSet)
            if (cls.isInstance(Student.class))
                Assertions.assertEquals(5, packageReader.findAllAnnotatedMethodsFromClass(classSet.getFirst()).size());
    }

    @Test
    public void getClassifyAbleConstructorsInClasses() {
        PackageReader packageReader = PackageReader.getInstance();
        List<Class<? extends ClassifyAbleClass>> classSet = packageReader.findAllClassesAnnotatedWith("org.mkproductions.jugnu").stream().toList();
        for (var cls : classSet)
            if (cls.isInstance(Student.class))
                Assertions.assertEquals(1, packageReader.findAllAnnotatedConstructorsFromClass(classSet.getFirst()).size());
    }
}
