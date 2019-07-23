# Fault_tolerance

## Introduction
    This is a project for practice and future reference of implementing fault tolerance mechenism in Java Web service.
    It include two famous Java fault tolerance libraries, [Netflix Hystrix](https://github.com/Netflix/Hystrix/wiki) and [Resilience4j](https://resilience4j.readme.io/docs)


## Technology used
    - Web Service : Springboot, Apache Http Client
    - Test : Junit, Mokito

### Modules
    - hystrix : implementation of fault tolerance using Netflix Hystrix
    - resilience4j : implementation of fault tolerance using Resilience4J
    - resilience4j-springboot-annotation : implementation of fault tolerance using Resilience4J. 
        This module uses springboot @CircuitBreaker + config file, no Resilience4J java bean be created.
    
    - fault-tolerance-common : common libs shared by above three modules
    - fault-tolerance-common-test : common test libs shared by above three modules
