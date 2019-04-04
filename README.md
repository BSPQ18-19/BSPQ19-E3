# SOFTWARE PROCESS AND QUALITY

#### How to run?:
```
mvn clean compile

Create Datanucleus schema:
  mvn datanucleus:schema-create

To start the registry
  start rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false

Launch server:
  mvn exec:java -Pserver

Launch client:
  mvn exec:java -Pclient
```

#### Team members:

* *Petr*
* *Victor*
* *John*
* *Javi*
* *Alberto*
