package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public void run(String...strings) throws Exception{
        if(roleRepository.findAll()!=null) {
            roleRepository.save(new Role("USER"));
            roleRepository.save(new Role("ADMIN"));
            Role adminRole = roleRepository.findByRole("ADMIN");
            Role userRole = roleRepository.findByRole("USER");

            User user = new User("admin@admin.com", "password", "admin", "admin", true, "admin");
            user.setRoles(Arrays.asList(adminRole));
            userRepository.save(user);

            user = new User("user@user.com", "password", "user", "user", true, "user");
            user.setRoles(Arrays.asList(userRole));
            userRepository.save(user);
        }

        if(categoryRepository.findAll() != null) {
            categoryRepository.save(new Category("Sedan"));
            categoryRepository.save(new Category("SUV"));
            categoryRepository.save(new Category("Sports Car"));
            categoryRepository.save(new Category("4 X 4"));

            Category sedanCategory = categoryRepository.findByCartype("Sedan");
            Category SUVCategory = categoryRepository.findByCartype("SUV");
            Category sportsCategory = categoryRepository.findByCartype("Sports Car");
            Category truckCategory = categoryRepository.findByCartype("4 X 4");


            Car car = new Car("$51,999", "Audi", "Sport", "2019");
            car.setCategory(sedanCategory);
            carRepository.save(car);

            car = new Car("$40,999", "Ford", "Mustang", "2018");
            car.setCategory(sportsCategory);
            carRepository.save(car);

            car = new Car("$28,999", "Honda", "Civic", "2018");
            car.setCategory(sedanCategory);
            carRepository.save(car);
        }
    }
}
