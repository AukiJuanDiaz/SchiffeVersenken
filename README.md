# Schiffe versenken 

This project is a distributed application to provide estimations for the remaining travel times of vessels.
The estimations are calculated with Machine Learning models, that were trained in Weka.
At the moment, models are trained for vessel trips between the cities of Bremerhaven and Hamburg as well as Kiel and Gdynia.
The architecture is easy to scale, so with more data it is easily possible to provide the service for more routes.

## About

This project was part of the course "Software Development" at the Technical University Hamburg in the summerterm 2018. 
The final presentation for the course, which contains the history of the project and quality benchmarks for the traveltime estimations, is in the directory "Presentation". The slides are in German. 


## Starting the application

### Easy to use, directly start the .jar

TODO:
- GUI
- Picture of GUI

- Broker
- Modelle entpacken
- Prerequisites: nur java

### Build it yourself

TODO: What things you need to install the software and how to install them

Our project depend on the following libraries:

* [weka](https://www.cs.waikato.ac.nz/ml/weka/) - Machine Learning
* [org.json](https://mvnrepository.com/artifact/org.json/json) - Communication between GUI and Broker<

## Authors

* **Hauke Diers** - [AukiJuanDiaz](https://github.com/AukiJuanDiaz)
* **Arne Grünhagen** - [Arnegrnhgen](https://github.com/Arnegrnhgen)
* **Thilo Fischer** - [Inu7el](https://github.com/Inu7el)


## Acknowledgments

* A big acknowledgment to the course instructor Mr. Marrone for hosting our weekly (and weakly) SCRUM-meetings. 
