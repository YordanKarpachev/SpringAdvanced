package bg.softuni.hateoas.WEB;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import bg.softuni.hateoas.model.dto.StudentDTO;
import bg.softuni.hateoas.services.StudentService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<StudentDTO>>> all() {

        List<EntityModel<StudentDTO>> entityModels = studentService.getAllStudents().
                stream().map(
                        s -> EntityModel.of(s, getStudentLinks(s))).toList();

        CollectionModel<EntityModel<StudentDTO>> collectionModel =
                CollectionModel.of(entityModels);

        return ResponseEntity.ok(collectionModel);

    }


    @GetMapping("/{id}")

    public ResponseEntity<EntityModel<StudentDTO>> getStudentById(@PathVariable("id") Long id) {
        Optional<StudentDTO> optStudent = this.studentService.getById(id);

        return optStudent.
                map(studentDTO -> ResponseEntity.ok(EntityModel.of(studentDTO, getStudentLinks(studentDTO)))).
                orElseGet(() -> ResponseEntity.notFound().build());

    }


    @GetMapping("/{id}/orders")

    public ResponseEntity<StudentDTO> getStudentOrders(@PathVariable("id") Long id) {

        throw new UnsupportedOperationException("not yet");

    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(
            @PathVariable("id") Long studentId,
            StudentDTO studentDTO) {
        throw new UnsupportedOperationException("Not yet");
    }

    private Link[] getStudentLinks(StudentDTO studentDTO) {

        List<Link> studentLinks = new ArrayList<>();

        Link selfLink = linkTo(
                methodOn(StudentController.class).getStudentById(studentDTO.getId())).
                withSelfRel();

        studentLinks.add(selfLink);

        if (studentDTO.isDeleted()) {

            Link orderLink = linkTo(methodOn(StudentController.class).getStudentOrders(studentDTO.getId())).withRel("orders");
            Link updateLink = linkTo(methodOn(StudentController.class).updateStudent(studentDTO.getId(), studentDTO)).withRel("update");

            studentLinks.add(orderLink);
            studentLinks.add(updateLink);
        }

        return studentLinks.toArray(new Link[0]);
    }


}
