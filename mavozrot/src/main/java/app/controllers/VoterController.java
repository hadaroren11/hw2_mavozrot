package app.controllers;
import app.models.Voter;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
@RestController
public class VoterController {
    private HashMap<Integer, Voter> voters = new HashMap<>();
    VoterController() {
    }
    @GetMapping("/employees")
    List<Employee> all() {
        return new ArrayList<>(employees.values());
    }
    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        employees.put(eid.getAndIncrement(), newEmployee);
        return newEmployee;
    }
    @GetMapping("/employees/{id}")
    Resource<Employee> one(@PathVariable int id) {
        Employee employee = employees.get(id);
        return new Resource<Employee>(employee,
                linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
    }
    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable int id) {
        return employees.get(id).setName(newEmployee.getName()).setRole(newEmployee.getRole());
    }
    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable int id) {
        employees.remove(id);
    }
}