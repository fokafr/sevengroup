package com.example.datamanagement.services;

import com.example.datamanagement.dto.EmployeDto;
import com.example.datamanagement.entities.BadgingEmploye;
import com.example.datamanagement.entities.Employee;
import com.example.datamanagement.exception.ResourceException;
import com.example.datamanagement.mappers.EmpMapperImpl;
import com.example.datamanagement.repositories.BadgingEmpRepository;
import com.example.datamanagement.repositories.EmployeeRepository;
import com.example.datamanagement.utility.DateTimeUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class EmpServiceImpl implements EmpService {

    Logger logger = LoggerFactory.getLogger(EmpServiceImpl.class);

    /*@Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }*/

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private BadgingEmpRepository badgingEmpRepository;

    //private PasswordEncoder passwordEncoder;

    String patternDate = "dd-MM-yyyy HH:mm";
   // String lastDayOf = "7";

    public EmpServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public boolean authenticate(String name, String pass) {
       /* String enCriptedPass = passwordEncoder.encode(pass);
        logger.info("Authentiqued pass inputed :"+enCriptedPass);*/
        return !employeeRepository.findByNameAndPass(name,pass).get().equals(null)?true:false;
    }

    @Override
    public Employee authenticateByEmailAndPass(String email, String pass) {
        return employeeRepository.findByEmailAndPass(email,pass).orElse(null);
    }

    @Override
    public Employee getByName(String name) {
        Employee employee = new Employee();
        List<Optional<Employee>> listeEmp = new ArrayList<>();
        listeEmp =  employeeRepository.findByName(name);
        if(!Objects.equals(listeEmp,null)){
            int count = 0;
            for(Optional<Employee> optEmp:listeEmp){
                employee = optEmp.orElse(null);
                count++;
                if(count == 1) break;
            }
        }
        return employee;
    }

    @Override
    public Employee getEmpByNameAndPass(String name, String pass) {
        Employee employee = employeeRepository.findByNameAndPass(name,pass).orElse(null);
        return employee;
    }

    //TODO
   /* @Override
    public BadgingEmploye saveReport(Employee emp, String contain) {
        if(!Objects.equals(authenticateByEmailAndPass(emp.getEmail(),emp.getPass()),null)){
            emp.setActivity(contain);
        }
        return null;
    }*/

    @Override
    public BadgingEmploye badgingOnMorning(BadgingEmploye badge, String email, String pass) {
        Employee employee  = authenticateByEmailAndPass(email,pass);
        badge.setTimeOnMorning(DateTimeUtility.dateTimeFormat(LocalDateTime.now(),patternDate));
        employee.addBadgingOnEmp(badge);
        employee = employeeRepository.save(employee);
        return employee.getBadgingEmploye().get(0);
    }

    @Override
    public BadgingEmploye badgingAfternoon(BadgingEmploye badg, String email, String pass) {
        Employee employee  = authenticateByEmailAndPass(email,pass);
        badg.setTimeOnAfternoon(DateTimeUtility.dateTimeFormat(LocalDateTime.now(),patternDate));
        employee.addBadgingOnEmp(badg);
        employee = employeeRepository.save(employee);
        return employee.getBadgingEmploye().get(0);
    }

    @Override
    public List<BadgingEmploye> displayBadgingByName(String name) {
        return badgingEmpRepository.findByEmpName(name);
    }

    @Override
    public boolean isLoginAlreadyInUse(String name) {
       // EmpServiceImpl empService = new EmpServiceImpl();
        String findedName = getByName(name).getName();
        if(findedName != null && findedName.equals(name))  return true;
        else return false;
    }

    @Override
    public void deleteEmployeeByName(String name) {
        if(isLoginAlreadyInUse(name)){
            Employee emTodelete = new Employee();
            emTodelete = getByName(name);
            if(emTodelete !=null)   employeeRepository.delete(emTodelete);
        }
    }

    @Override
    public void deleteRegistryById(long id) {
        BadgingEmploye badgingEmploye = badgingEmpRepository.findById(id).get();
        if(badgingEmploye != null) badgingEmpRepository.delete(badgingEmploye);
    }

    @Override
    public List<EmployeDto> findAllEmployee() {
        List<Employee> employeDao = new ArrayList<>();
        List<EmployeDto> employeDto = new ArrayList<>();
        employeDao = employeeRepository.findAll();
        for(Employee em:employeDao){
            EmpMapperImpl empMapper = new EmpMapperImpl();
            employeDto.add(empMapper.fromEmpToDto(em));
        }
        return employeDto;
    }

  /*  @Override
    public List<EmployeDto> finAllEmployeeInPeriod(String lastNumberOfDay) {
        List<Employee> employeDaoList = new ArrayList<>();
        List<EmployeDto> employeDtoList = new ArrayList<>();
        List<EmployeDto> employeDtoInSomePeriodList = new ArrayList<>();
        employeDaoList = employeeRepository.findAll();
        for(Employee em:employeDaoList){
            EmpMapperImpl empMapper = new EmpMapperImpl();
            employeDtoList.add(empMapper.fromEmpToDto(em));
        }

        for(EmployeDto emp:employeDtoList){
            String firstConnectionDate = emp.getFirstConnectedTime();
            String secondConnectionDate = emp.getLastConnectedTime();
            if(secondConnectionDate != null && lastNumberOfDay !=null){

            }
            else if(secondConnectionDate == null && firstConnectionDate != null){
                //traitement
            }
        }
        return employeDtoInSomePeriodList;
    }*/

    @Override
    public Employee findAllEmployeeByID(long id) {
        Optional<Employee> opt = employeeRepository.findById(id);
        if (opt.isPresent())
            return opt.get();
        else
            return null;
    }
    
    @Override
    public void addEmployee() {
     return ;
    }

    @Override
    public List<BadgingEmploye> displayAllRegistration() {
        return badgingEmpRepository.findAll();
    }

    @Override
    public List<BadgingEmploye> displayAllRegistrationOnSomePeriod(String startDate, String endDate) {
        return null;
    }

    @Override
    public Employee addNewEmploye(EmployeDto employeDto) {
        Employee employee = new Employee();
        EmpMapperImpl mapper = new EmpMapperImpl();
        employee = new EmpMapperImpl().fromEmpDtoToEmp(employeDto);
        String nameFromEmp = employee.getName();
        //String EncriptedPassWord = passwordEncoder.encode(employee.getPass());
        //employee.setPass(employee.getPass());
        //logger.info(employee.getName()+ "......"+ EncriptedPassWord);
        if(!isLoginAlreadyInUse(nameFromEmp))
        {
            return employeeRepository.save(employee);
        }
        else throw new ResourceException("email already exist");
    }
}