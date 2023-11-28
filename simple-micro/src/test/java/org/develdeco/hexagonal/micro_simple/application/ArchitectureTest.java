package org.develdeco.hexagonal.micro_simple.application;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.onionArchitecture;

@AnalyzeClasses(packages = "org.develdeco.hexagonal.micro_simple", importOptions = ImportOption.DoNotIncludeTests.class)
@SuppressWarnings("unused")
public class ArchitectureTest
{
    public static final String CONTAINER_PACKAGE_IDENTIFIER = "org.develdeco.hexagonal.micro_simple";

    /**
     * Architecture test to check if hexagonal architecture pattern is followed
     */
    @ArchTest
    public static final ArchRule HEXAGONAL_ARCH_RULE = onionArchitecture()
            .domainModels(CONTAINER_PACKAGE_IDENTIFIER.concat(".domain.model.."))
            .domainServices(CONTAINER_PACKAGE_IDENTIFIER.concat(".domain.port.."))
            .applicationServices(CONTAINER_PACKAGE_IDENTIFIER.concat(".application.."))
            .adapter("rest", CONTAINER_PACKAGE_IDENTIFIER.concat(".adapter.in.rest.."))
            .adapter("jpa", CONTAINER_PACKAGE_IDENTIFIER.concat(".adapter.out.jpa.."));


    /**
     * Architecture test to check if IN adapters depends on primary ports
     */
    @ArchTest
    public static final ArchRule PRIMARY_PORT_ARCH_RULE = classes()
            .that()
            .resideInAPackage(CONTAINER_PACKAGE_IDENTIFIER.concat(".adapter.in.."))
            .and()
            .haveSimpleNameEndingWith("Adapter")
            .and()
            .haveSimpleNameNotStartingWith("Abstract")
            .should()
            .dependOnClassesThat(resideInAPackage(CONTAINER_PACKAGE_IDENTIFIER.concat(".application.port..")));

    /**
     * Architecture test to check if OUT adapters depends on secondary ports
     */
    @ArchTest
    public static final ArchRule SECONDARY_PORT_ARCH_RULE = classes()
            .that()
            .resideInAPackage(CONTAINER_PACKAGE_IDENTIFIER.concat(".adapter.out.."))
            .and()
            .haveSimpleNameEndingWith("Adapter")
            .and()
            .haveSimpleNameNotStartingWith("Abstract")
            .should()
            .dependOnClassesThat(resideInAPackage(CONTAINER_PACKAGE_IDENTIFIER.concat(".domain.port..")));

    /**
     * Architecture test to check if primary ports depends on use cases
     */
    @ArchTest
    public static final ArchRule USE_CASE_ARCH_RULE = classes()
            .that()
            .resideInAPackage(CONTAINER_PACKAGE_IDENTIFIER.concat(".application.port.."))
            .and()
            .haveSimpleNameEndingWith("ServicePort")
            .should()
            .dependOnClassesThat(resideInAPackage(CONTAINER_PACKAGE_IDENTIFIER.concat(".application.usecase..")));
}
