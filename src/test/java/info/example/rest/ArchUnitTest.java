package info.example.rest;


import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

public class ArchUnitTest {

    @Test
    public void should_not_have_cycles() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("info.example.rest");

        ArchRule rule = slices().matching("info.example.rest.(*)..").should().beFreeOfCycles();
        rule.check(importedClasses);
    }

    @Test
    public void should_not_exists_spring_components() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("info.example.rest.domain");

        ArchRule rule = classes().should().notBeAnnotatedWith(Controller.class)
            .andShould().notBeAnnotatedWith(Service.class)
            .andShould().notBeAnnotatedWith(Repository.class)
            .andShould().notBeAnnotatedWith(Component.class);

        rule.check(importedClasses);
    }
}
