package com.bootcamp.finalProject.repositories;

import com.bootcamp.finalProject.exceptions.OrderTypeException;
import com.bootcamp.finalProject.model.Part;
import com.bootcamp.finalProject.utils.ValidationPartUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Thread.sleep;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "spring.datasource.driverClassName=org.h2.Driver"
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SuppressWarnings("all")
class PartRepositoryTest {

    @Autowired
    PartRepository repository;

    @Test
    @Sql(scripts = {"classpath:loadPartRepositoryBase.sql"})
    public void findByLastModificationOrderDefault() throws OrderTypeException, InterruptedException {
        //Arrange
        Date date = new Date();
        sleep(1000);
        List<Part> expected = new ArrayList<>();
        Part p1 = repository.findById(1L).orElse(null);
        Part p2 = repository.findById(2L).orElse(null);
        p1.setLastModification(new Date());
        p2.setLastModification(new Date());

        expected.add(p1);
        expected.add(p2);

        //act
        repository.save(p1);
        repository.save(p2);

        List<Part> actual = repository.findByLastModification(date, ValidationPartUtils.POrderTypeValidation(0));

        //Assert
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    @Sql(scripts = {"classpath:loadPartRepositoryBase.sql"})
    public void findByLastModificationOrderByDescriptionAsc() throws OrderTypeException, InterruptedException {
        //Arrange
        Date date = new Date();
        sleep(1000);
        List<Part> expected = new ArrayList<>();
        Part p1 = repository.findById(1L).orElse(null);
        Part p2 = repository.findById(2L).orElse(null);
        p1.setLastModification(new Date());
        p1.setDescription("b");
        p2.setLastModification(new Date());
        p2.setDescription("a");

        expected.add(p2);
        expected.add(p1);

        //Act
        repository.save(p1);
        repository.save(p2);

        List<Part> actual = repository.findByLastModification(date, ValidationPartUtils.POrderTypeValidation(1));

        //Assert
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    @Sql(scripts = {"classpath:loadPartRepositoryBase.sql"})
    public void findByLastModificationOrderByDescriptionDesc() throws OrderTypeException, InterruptedException {
        //Arrange
        Date date = new Date();
        sleep(1000);
        List<Part> expected = new ArrayList<>();
        Part p1 = repository.findById(1L).orElse(null);
        Part p2 = repository.findById(2L).orElse(null);
        p1.setLastModification(new Date());
        p1.setDescription("a");
        p2.setLastModification(new Date());
        p2.setDescription("b");

        expected.add(p2);
        expected.add(p1);

        //Act
        repository.save(p1);
        repository.save(p2);

        List<Part> actual = repository.findByLastModification(date, ValidationPartUtils.POrderTypeValidation(2));

        //Assert
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    @Sql(scripts = {"classpath:loadPartRepositoryBase.sql"})
    public void findByLastModificationOrderByLastModificationDesc() throws OrderTypeException, InterruptedException {
        //Arrange
        Date date = new Date();
        sleep(1000);
        List<Part> expected = new ArrayList<>();
        Part p1 = repository.findById(1L).orElse(null);
        Part p2 = repository.findById(2L).orElse(null);
        p1.setLastModification(new Date());
        sleep(1000);
        p2.setLastModification(new Date());

        expected.add(p2);
        expected.add(p1);

        //Act
        repository.save(p1);
        repository.save(p2);

        List<Part> actual = repository.findByLastModification(date, ValidationPartUtils.POrderTypeValidation(3));

        //Assert
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    @Sql(scripts = {"classpath:loadPartRepositoryBase.sql"})
    public void findByPriceCreatedAtOrderDefault() throws OrderTypeException, InterruptedException {
        //Arrange
        Date date = new Date();
        sleep(1000);
        List<Part> expected = new ArrayList<>();
        Part p1 = repository.findById(1L).orElse(null);
        Part p2 = repository.findById(2L).orElse(null);
        p1.getPartRecords().get(0).setCreatedAt(new Date());
        p2.getPartRecords().get(0).setCreatedAt(new Date());

        expected.add(p1);
        expected.add(p2);

        //act
        repository.save(p1);
        repository.save(p2);

        List<Part> actual = repository.findByPriceCreateAt(date, ValidationPartUtils.POrderTypeValidation(0));

        //Assert
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    @Sql(scripts = {"classpath:loadPartRepositoryBase.sql"})
    public void findByPriceCreatedAtOrderByDescriptionAsc() throws OrderTypeException, InterruptedException {
        //Arrange
        Date date = new Date();
        sleep(1000);
        List<Part> expected = new ArrayList<>();
        Part p1 = repository.findById(1L).orElse(null);
        Part p2 = repository.findById(2L).orElse(null);
        p1.getPartRecords().get(0).setCreatedAt(new Date());
        p1.setDescription("b");
        p2.getPartRecords().get(0).setCreatedAt(new Date());
        p2.setDescription("a");

        expected.add(p2);
        expected.add(p1);

        //Act
        repository.save(p1);
        repository.save(p2);

        List<Part> actual = repository.findByPriceCreateAt(date, ValidationPartUtils.POrderTypeValidation(1));

        //Assert
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    @Sql(scripts = {"classpath:loadPartRepositoryBase.sql"})
    public void findByPriceCreatedAtOrderByDescriptionDesc() throws OrderTypeException, InterruptedException {
        //Arrange
        Date date = new Date();
        sleep(1000);
        List<Part> expected = new ArrayList<>();
        Part p1 = repository.findById(1L).orElse(null);
        Part p2 = repository.findById(2L).orElse(null);
        p1.getPartRecords().get(0).setCreatedAt(new Date());
        p1.setDescription("a");
        p2.getPartRecords().get(0).setCreatedAt(new Date());
        p2.setDescription("b");

        expected.add(p2);
        expected.add(p1);

        //Act
        repository.save(p1);
        repository.save(p2);

        List<Part> actual = repository.findByPriceCreateAt(date, ValidationPartUtils.POrderTypeValidation(2));

        //Assert
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    @Sql(scripts = {"classpath:loadPartRepositoryBase.sql"})
    public void findByPriceCreatedAtOrderByLastModificationDesc() throws OrderTypeException, InterruptedException {
        //Arrange
        Date date = new Date();
        sleep(1000);
        List<Part> expected = new ArrayList<>();
        Part p1 = repository.findById(1L).orElse(null);
        Part p2 = repository.findById(2L).orElse(null);
        p1.getPartRecords().get(0).setCreatedAt(new Date());
        sleep(1000);
        p2.getPartRecords().get(0).setCreatedAt(new Date());

        expected.add(p2);
        expected.add(p1);

        //Act
        repository.save(p1);
        repository.save(p2);

        List<Part> actual = repository.findByPriceCreateAt(date, ValidationPartUtils.POrderTypeValidation(3));

        //Assert
        Assertions.assertIterableEquals(expected, actual);
    }
}