# Spring Component Testing Proof of Concept

This project shows as example a service that recalculates current demand of fictitious product. Service in `service/testingrequired`
package shows code implemented within a spring component. The test package shows also the implementation of tests with both
`Mockito` and `@SpringBootTest`.

Package `service/notestingrequired` contains already refactored code, which allows to test the service logic without testing
the Spring Component.

PoC created as addition to an article that shall prove the same point (link pending).