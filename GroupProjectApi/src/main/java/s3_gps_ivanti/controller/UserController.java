package s3_gps_ivanti.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import s3_gps_ivanti.business.user.*;
import s3_gps_ivanti.dto.user.*;
import s3_gps_ivanti.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import javax.swing.text.html.parser.Entity;
import java.net.URI;
import java.util.List;


@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class UserController {

    private final CreateCustomerUseCase createCustomer;
    private final DeleteCustomerUseCase deleteCustomer;
    private final GetCustomersUseCase getCustomers;
    private final GetCustomerUseCase getCustomer;
    private final UpdateCustomerUseCase updateCustomer;

    @GetMapping()
    public ResponseEntity<List<CustomerBasicInfoDTO>> getUsers() {
        return ResponseEntity.ok().body(getCustomers.getAllCustomers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDetailedInfoDTO> getUser(@PathVariable String username) {
        CustomerDetailedInfoDTO customerDetailedInfoDTO = getCustomer.getCustomer(username);

        if(customerDetailedInfoDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(getCustomer.getCustomer(username));
    }
    @PostMapping()
    public ResponseEntity<CreateCustomerResponseDTO> createUser(@RequestBody CreateCustomerRequestDTO user) {
        CreateCustomerResponseDTO customerResponseDTO = createCustomer.createCustomer(user);

        if(customerResponseDTO == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.ok().body(customerResponseDTO);
    }
    @PutMapping()
    public ResponseEntity<Object> updateCustomer(@RequestBody UpdateCustomerRequestDTO updatedUser) {
        updateCustomer.updateCustomer(updatedUser);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object>  deleteUser(@PathVariable String id) {
        deleteCustomer.DeleteCustomer(id);
        return ResponseEntity.ok().build();
    }


    //TODO fix this
   /* @GetMapping("{id}")
   public ResponseEntity<Application> getApplicationsBySearch(@PathVariable("id") long id) {

        Application Application = applicationService.getApplicationsByID(id);
        /       if(Application != null) {
            return ResponseEntity.ok().body(Application);} else {
            return ResponseEntity.notFound().build();
      }
    }*/


}

