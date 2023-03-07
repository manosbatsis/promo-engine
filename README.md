# Promo Engine ![example workflow](https://github.com/manosbatsis/promo-engine/actions/workflows/push.yml/badge.svg) 

Simple promotion engine for a checkout process.

## Modules

- `promo-engine-api`: promotion engine interfaces.
- `promo-engine-simple`: uses the API module above to provide 
a partial but deterministic implementation of 
SalesForce B2B [promotion priority rules](https://documentation.b2c.commercecloud.salesforce.com/DOC2/index.jsp?topic=%2Fcom.demandware.dochelp%2Fcontent%2Fb2c_commerce%2Ftopics%2Fpromotions%2Fb2c_promotion_priority_rules.html) as appropriate for this exercise.
- `promo-engine-test`: rough but implementation-independent test utilities

## Test Scenarios

The main [engine test](promo-engine-simple/src/test/java/com/github/manosbatsis/promo/engine/simple/SimplePromotionEngineTest.java) 
tries the following:

###  Active Promotions

1. 3 of A's for 130
2. 2 of B's for 45
3. C & D for 30

### Scenarios

__Scenario A__

- 1 * A = 50
- 1 * B = 30
- 1 * C = 20
- Total = 100

__Scenario B__

- 5 * A = 130 + 2*50
- 5 * B = 45 + 45 + 30
- 1 * C = 20 
- Total = 370

__Scenario C__
- 3 * A = 130
- 5 * B = 45 + 45 + 1 * 30
- 1 * C & 1 * D = 30 
- Total 280

## Build

This project uses Gradle Wrapper. The only prerequisite for building this 
project is JDK 11+. 

To build/test, clone this repo, navigate to the project root and build with gradle.

Linux/Mac:

    ./gradlew build

Windows:

    gradlew.bat build



    