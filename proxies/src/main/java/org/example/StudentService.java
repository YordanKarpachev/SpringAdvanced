package org.example;

import java.lang.reflect.Proxy;
import java.util.List;

public class StudentService implements StudentServiceIfc{
    @Override
    @Cacheable("students")
    public List<StudentDTO> findAllStudents() {
        System.out.println("Downloading students...");
        dummyWait();
        System.out.println("Students downloaded...");
        return List.of(
                new StudentDTO().setName("Peter").setAge(22),
                new StudentDTO().setName("Lisa").setAge(21)
        );
    }




    private void dummyWait()  {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    private static  StudentServiceIfc createStudentService(){
      return (StudentServiceIfc)  Proxy.newProxyInstance(
                Main.class.getClassLoader(),
              new Class[]{StudentServiceIfc.class},
              new CacheableInvocationHandler(new StudentService())
        );
    }
}
