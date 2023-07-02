package bg.softuni.hateoas.services;


import bg.softuni.hateoas.WEB.StudentController;
import bg.softuni.hateoas.model.OrderEntity;
import bg.softuni.hateoas.model.StudentEntity;
import bg.softuni.hateoas.model.dto.OrderDTO;
import bg.softuni.hateoas.model.dto.StudentDTO;
import bg.softuni.hateoas.repository.StudentRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.
                findAll().
                stream().
                map(this::map).
                toList();
    }




    private StudentDTO map(StudentEntity studentEntity) {
        List<OrderDTO> orders = studentEntity.getOrders().stream().map(this::map).toList();
        return new StudentDTO().
                setAge(studentEntity.getAge()).
                setId(studentEntity.getId()).
                setName(studentEntity.getName()).
                setDeleted(studentEntity.isDeleted()).
                setOrders(orders);

    }

    private OrderDTO map(OrderEntity orderEntity){
        return new OrderDTO().
                setStudentId(orderEntity.getStudent().getId()).
                setCourseId(orderEntity.getCourse().getId());
    }


    public Optional<StudentDTO> getById(Long id) {

        return studentRepository.
                findById(id).
                map(this::map);
    }
}
