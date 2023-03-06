# Promo Engine ![example workflow](https://github.com/manosbatsis/promo-engine/actions/workflows/push.yml/badge.svg) 

Simple promotion engine for a checkout process.

## Modules

- `promo-engine-api`: promotion engine interfaces.
- `promo-engine-simple`: uses the API module above to provide 
an elementary but deterministic implementation of 
SalesForce B2B [promotion priority rules](https://documentation.b2c.commercecloud.salesforce.com/DOC2/index.jsp?topic=%2Fcom.demandware.dochelp%2Fcontent%2Fb2c_commerce%2Ftopics%2Fpromotions%2Fb2c_promotion_priority_rules.html) as appropriate for this exercise.
See also the main [engine test](promo-engine-simple/src/test/java/com/github/manosbatsis/promo/engine/simple/SimplePromotionEngineTest.java).
- `promo-engine-test`: rough but implementation-independent test utilities

## Build

This project uses Gradle Wrapper. The only prerequisite for building this 
project is JDK 11+. 

To build/test, clone this repo, navigate to the project root and build with gradle.

Linux/Mac:

    ./gradlew build

Windows:

    gradlew.bat build



    