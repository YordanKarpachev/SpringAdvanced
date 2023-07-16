package repository;

import com.example.Caching.model.StudentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    private Logger logger = LoggerFactory.getLogger(StudentRepository.class);

    public List<StudentDTO> findAllStudent(){
        logger.info("Downloading students...");
        dummyWait();
        logger.info("Students downloaded...");
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


}
