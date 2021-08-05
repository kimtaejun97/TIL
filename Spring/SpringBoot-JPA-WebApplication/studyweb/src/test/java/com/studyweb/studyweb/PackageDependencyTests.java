package com.studyweb.studyweb;

import com.studyweb.studyweb.modules.account.Account;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@AnalyzeClasses(packagesOf = StudywebApplication.class)
public class PackageDependencyTests {

    private static final String STUDY = "..modules.study..";
    private static final String EVENT = "..modules.event..";
    private static final String ACCOUNT = "..modules.account..";
    private static final String TAG = "..modules.zone..";
    private static final String ZONE = "..modules.tag..";

    @ArchTest
    ArchRule modulesRule = classes().that().resideInAnyPackage("com.studyweb.studyweb.modules..")
            .should().onlyBeAccessed().byClassesThat()
            .resideInAnyPackage("com.studyweb.studyweb.modules..");
    @ArchTest
    ArchRule studyPackageRule = classes().that().resideInAPackage(STUDY)
            .should().onlyBeAccessed().byClassesThat()
            .resideInAnyPackage(STUDY,EVENT);

    @ArchTest
    ArchRule accountPackageRule = classes().that().resideInAPackage(ACCOUNT)
            .should().accessClassesThat()
            .resideInAnyPackage(ACCOUNT, TAG, ZONE);

    @ArchTest
    ArchRule eventPackageRule = classes().that().resideInAPackage(EVENT)
            .should().accessClassesThat()
            .resideInAnyPackage(ACCOUNT, STUDY, EVENT);

    @ArchTest
    ArchRule cycleCheck = slices().matching("com.studyweb.studyweb.modules.(*)..")
            .should().beFreeOfCycles();
}
