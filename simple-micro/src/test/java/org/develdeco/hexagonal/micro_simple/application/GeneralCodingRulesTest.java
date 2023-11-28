package org.develdeco.hexagonal.micro_simple.application;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.GeneralCodingRules.*;

@AnalyzeClasses(packages = "org.develdeco.hexagonal.micro_simple", importOptions = ImportOption.DoNotIncludeTests.class)
@SuppressWarnings("unused")
public class GeneralCodingRulesTest
{
    /**
     * A rule that checks that none of the given classes access Java Util Logging.
     */
    @ArchTest
    public static final ArchRule JAVA_UTIL_LOGGING_IS_FORBIDDEN = NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;

    /**
     * A rule that checks that none of the given classes uses field injection.
     */
    @ArchTest
    public static final ArchRule FIELD_INJECTION_SHOULD_NOT_BE_USED = NO_CLASSES_SHOULD_USE_FIELD_INJECTION;

    /**
     * A rule that checks that none of the given classes access the standard streams System.out and System.err.
     */
    @ArchTest
    public static final ArchRule STANDARD_OUTPUT_STREAMS_SHOULD_NOT_BE_USED = NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;
}
