package com.example.datamanagement.exposition;
import java.time.LocalDateTime;
import java.util.List;
import com.example.datamanagement.dto.EmployeDto;
import com.example.datamanagement.entities.BadgingEmploye;
import com.example.datamanagement.repositories.BadgingEmpRepository;
import com.example.datamanagement.repositories.EmployeeRepository;
import com.example.datamanagement.services.EmpServiceImpl;
import com.example.datamanagement.entities.Employee;
import com.example.datamanagement.utility.DateTimeUtility;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employeeApi")
@Api("API pour les opérations CRUD sur les employes")
@CrossOrigin(origins = "*")
public class EmpController {
	
	@Autowired
	EmpServiceImpl empServiceImpl;
	String patternDate = "dd-MM-yyyy HH:mm";

	//ResponseStatus(HttpStatus.ACCEPTED)
	@PostMapping("/authenticate")
	public Employee authenticate(@RequestBody Employee newEmploye){
		String email = newEmploye.getEmail();
		String pass  = newEmploye.getPass();
		return empServiceImpl.authenticateByEmailAndPass(email,pass);
	}

	@ApiOperation(value = "Authentification d'un utilisateur par email et mot de pass")
	@GetMapping("/authenticatewithlogin/{email}/{pass}")
	public Employee authenticate(@PathVariable("email") String email,@PathVariable("pass") String pass){
		return empServiceImpl.authenticateByEmailAndPass(email,pass);
	}

	@GetMapping("/auhthentifier/{name}/{pass}")
	public boolean authenticateEmp(@PathVariable("name") String name,@PathVariable("pass") String pass) {
		return empServiceImpl.authenticate(name,pass);
	}


	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/addNewEmp")
	public Employee addNewEmp(@RequestBody EmployeDto newEmployeDto){
		return empServiceImpl.addNewEmploye(newEmployeDto);
	}

	@PostMapping("/badgeOnMorning/{email}/{pass}")
	public ResponseEntity<BadgingEmploye> savedEmpMorningTime(@PathVariable("email") String email,
											  @PathVariable("pass") String pass,
											  @RequestBody BadgingEmploye badgingRequest){
		BadgingEmploye badgingEmploye = new BadgingEmploye();
		badgingEmploye = empServiceImpl.badgingOnMorning(badgingRequest,email,pass);
		return new ResponseEntity<>(badgingRequest, HttpStatus.CREATED);
	}

	@PostMapping("/badgeAfternoon/{email}/{pass}")
	public ResponseEntity<BadgingEmploye> badgingAfternoon(@PathVariable("email") String email,
												 @PathVariable("pass") String pass,
	@RequestBody BadgingEmploye badgingRequest){
		BadgingEmploye badgingEmploye = new BadgingEmploye();
		badgingEmploye = empServiceImpl.badgingAfternoon(badgingRequest,email,pass);
		return new ResponseEntity<>(badgingRequest, HttpStatus.CREATED);
	}


	@GetMapping("/findallEmp")
	public List<EmployeDto> getAllEmployee() {
		return empServiceImpl.findAllEmployee();
	}

	//TODO
	@GetMapping("/findRegistrationsByEmpName/{name}")
	public List<BadgingEmploye> getAllBadgingOfEmploye(@PathVariable("name") String name){
		return empServiceImpl.displayBadgingByName(name);
	}

	@ApiOperation(value = "Affichage de tous les emargements")
	@GetMapping("/findallRegistration")
	public List<BadgingEmploye> getAllRegistration() {
		return empServiceImpl.displayAllRegistration();
	}

	@ApiOperation(value = "Affichage recherche d'un employer par son nom")
	@GetMapping("/findEmployeByname/{name}")
	public Employee getEmpByName(@PathVariable("name") String name) {
		return empServiceImpl.getByName(name);
	}

	@ApiOperation(value = "Affichage recherche d'un employer par son nom et mot de pass")
	@GetMapping("/findEmployeBynameAndPass/{name}/{pass}")
	public Employee getEmployeByNameAndPass(@PathVariable("name") String name,@PathVariable("pass") String pass) {
		return empServiceImpl.getEmpByNameAndPass(name,pass);
	}

	@ApiOperation(value = "Suppression d'un employé par le nom")
	@DeleteMapping("/deleteEmp/{name}")
	public void deleteEmpByName(@PathVariable("name") String pname) {
		empServiceImpl.deleteEmployeeByName(pname);
	}

	@ApiOperation(value = "Suppression d'un emargement par ID")
	@DeleteMapping("/deleteRegistryById/{id}")
	public void deleteRegistryById(@PathVariable("id") long id)
	{
		empServiceImpl.deleteRegistryById(id);
	}
}
