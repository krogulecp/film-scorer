package info.example.rest.arch;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@AnalyzeClasses(packages = "info.example.rest")
public class ArchitectureTest {

    @ArchTest
    static final ArchRule no_cycles_by_method_calls_between_slices =
            slices().matching("..(application).(*)..").namingSlices("$2 of $1").should().beFreeOfCycles();

    @ArchTest
    static final ArchRule application_not_accesses_by_other_packages =
            classes().that().resideInAnyPackage("..application..")
            .should().onlyBeAccessed().byClassesThat().resideInAnyPackage("..application..");

    @ArchTest
    static final ArchRule no_spring_in_domain =
            classes().that().resideInAnyPackage("..domain..")
            .should().notBeAnnotatedWith(Controller.class).andShould()
                    .notBeAnnotatedWith(Service.class);
}
