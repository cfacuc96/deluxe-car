package com.bootcamp.finalProject.ServicesTests;


import com.bootcamp.finalProject.repositories.PartRepository;
import com.bootcamp.finalProject.services.PartService;
import com.bootcamp.finalProject.services.PartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PartServiceImplTests {

    @Mock
    PartRepository partRepository;

    //PartServiceImpl partService = new PartServiceImpl(partRepository);


}
