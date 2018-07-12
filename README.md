# Schiffe versenken 

This project is a distributed application to provide estimations for the remaining travel times of vessels.
The estimations are calculated with Machine Learning models, that were trained in Weka.
At the moment, models are trained for vessel trips between the cities of Bremerhaven and Hamburg as well as Kiel and Gdynia.
The architecture is easy to scale, so with more data it is easily possible to provide the service for more routes.

## About

This project was part of the course "Software Development" at the Technical University Hamburg in the summerterm 2018. 
The final presentation for the course, which contains the history of the project and quality benchmarks for the traveltime estimations, is in the directory "Presentation". The slides are in German. 


## Starting the application

### Build it yourself

For building our .java-files, you will need a Java-IDE (e.g. Eclipse). Our code is written in Java 1.8.

Our project depend on the following libraries. For building the code, you need to include these libraries:

* [weka](https://www.cs.waikato.ac.nz/ml/weka/) - Machine Learning
* [org.json](https://mvnrepository.com/artifact/org.json/json) - Communication between GUI and Broker<

There are three main-methods in these classes:

* The main-method of the GUI is in the class MainFrame2
* The main-method of the Broker is in the class Main
* The third main-method belongs to the class DrawHistData and is not needed for using the application

## Authors

* **Hauke Diers** - [AukiJuanDiaz](https://github.com/AukiJuanDiaz)
* **Arne Grünhagen** - [Arnegrnhgen](https://github.com/Arnegrnhgen)
* **Thilo Fischer** - [Inu7el](https://github.com/Inu7el)


## Acknowledgments

* A big acknowledgment to the course instructor Mr. Marrone for hosting our weekly (and weakly) SCRUM-meetings. 
