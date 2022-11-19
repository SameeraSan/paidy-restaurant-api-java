### START

## Paidy Restaurant Service
---
### Introduction

Create a restaurant application which accepts menu items from various serving staff in the restaurant. This application must then store the item along with a cooking time for the item to be completed. The application must be able to give a quick snapshot of any or all items on its list at any time. It must also be able to remove specific orders from the list of orders on demand.
---

### Requirements

- The client (the restaurant staff “devices” making the requests) MUST be able to: add one or more items with a table number, remove an item for a table, and query the items still remaining for a table.
- The application MUST, upon creation request, store the item, the table number, and how long the item will take to cook.
- The application MUST, upon deletion request, remove a specified item for a specified table number.
- The application MUST, upon query request, show all items for a specified table number.
- The application MUST, upon query request, show a specified item for a specified table number.
- The application MUST accept at least 10 simultaneous incoming add/remove/query requests.
- The client MAY limit the number of specific tables in its requests to a finite set (at least 100).
- The application MAY assign a length of time for the item to prepare as a random time between 5-15 minutes.
- The application MAY keep the length of time for the item to prepare static (in other words, the time does not have to be counted down in real time, only upon item creation and then removed with the item upon item deletion).

#### Functional requirements

- There is no need for UI;
- Prevent the drone from being loaded with more weight that it can carry;
- Prevent the drone from being in LOADING state if the battery level is **below 25
%**;
- Introduce a periodic task to check drones battery levels and create history/audi
  t event log for this.
---

### Assumptions
- The time to prepare does not have to be kept up-to-date. It can also just be generated as some random amount of time between 5 and 15 minutes and kept static from then on.
- The table and items can be identified in any chosen manner, but it has to be consistent. So if a request comes in for table "4", for example, any other requests for table "4" must refer to the same table.
- Clients can be simulated as simple threads in a main() function calling the main server application with a variety of requests. There should be more than one, preferably around 5-10 running at any one time.
- The API is up to the developer. HTTP REST is acceptable, but direct API calls are also acceptable if they mimic an HTTP REST-like API (e.g. api_call1(string id, string resource), etc.).


---

# Project Instructions

### Requirements

- Java 17
- IDE (IntelliJ)
- H2 in memory database
- Container environment (Docker)
- Test tool (Postman)
---

#### How to build

- Clone the Main branch from  GIT - https://github.com/SameeraSan/Musala_Drone_Task.git
- Import the project to intelliJ
- Configure the application.properties (refer application.property Configure section)
- Update Maven
- Maven build

### application.property Configure

- server.port=5569

- drone.config.min-battery-level = 25.00 
> Minimum Battery percentage
- drone.config.preload-db-data = true
> By enabling this the application will automatically pre-load the medication data into H2 db, otherwise you'll have to insert data manually to Medications table
> 

- drone.config.scheduler.enabled=false
- drone.config.scheduler.interval=5000
> Will enable the periodic task to check drone battery and the time gap between each cycle
> 
- Database config and h2 database configurations 
> Keep as it is unless you want to change db configs
> 
- H2 Database URL
> url=jdbc:h2:file:/opt/Musala/DroneApp/m2_db/drone_db
> 
- logging.file.name = /opt/Musala/DroneApp/logs/drone_api_common.log
> Complete log file name and the location, please change according to the environment
>
- log.battery.audit.name = /opt/Musala/DroneApp/logs/drone_battery_audit.log
> A separate log for the periodic task scheduler
> 
### Once you complete the configuring project you can run the project 
> To run the project you can use either the IDE or the provided docker image, below you can find the docker run instructions
> 
#### Docker instructions

- download the docker image from this url - https://drive.google.com/drive/folders/1Dzmew-CZvBaSUrXlmOlZnrdhvQUYNWF3?usp=sharing
- docker load -i DroneDockerImage.tar\
- docker run -p **port**:5569 **imageID/name**
> Give a port to **port**, and give either the imageId or the name to **imageID/name**

--- 


### How Test the services

- Requests MediaType: **APPLICATION_JSON**
- Responses MediaType: **APPLICATION_JSON**

#### Register a drone
- drone register url (Post) = **localhost:5569/v1/drone/register/**
> Request : {
"serialNumber" : "0001",
"model":"LIGHT_WEIGHT",
"weightLimit" : 100,
"battery" : 50
}
> 
> Response : {
"serialNumber": "0005",
"responseMessage": "Drone registered"
}
> 
#### Get available drones
- available drones url (Get) - **localhost:5559/v1/drone/available/**
- > Respinse : {
  "responseMessage": "Drone registered",
  "drones": [
  {
  "serialNumber": "0003",
  "model": "LIGHT_WEIGHT",
  "weightLimit": 10.0,
  "battery": 50.0,
  "state": "IDLE"
  },
  {
  "serialNumber": "0001",
  "model": "LIGHT_WEIGHT",
  "weightLimit": 10.0,
  "battery": 50.0,
  "state": "IDLE"
  },
  {
  "serialNumber": "0002",
  "model": "LIGHT_WEIGHT",
  "weightLimit": 10.0,
  "battery": 50.0,
  "state": "IDLE"
  },
  {
  "serialNumber": "0005",
  "model": "LIGHT_WEIGHT",
  "weightLimit": 100.0,
  "battery": 50.0,
  "state": "IDLE"
  }
  ]
  }
  > 
#### Check drone battery level
- Drone battery check url (Post) - **localhost:5569/v1/drone/battery/**
> Request : {
"serialNumber" : "0001"
}
> Response : {
"serialNumber": "0001",
"responseMessage": "Drone battery level loaded",
"batteryLevel": "50.0%"
}
> 
#### Get loaded medication list of a drone
- Drone loaded medication url (Post) - **localhost:5569/v1/drone/loaded-medications/**
> Request : {
"serialNumber" : "0001"
}
> Response : {
"serialNumber": "0001",
"responseMessage": "Medication List loaded",
"totalWeight": 10.0,
"medicationList": [
{
"id": "40289f108316e7b4018316ed30f30000",
"serialNumber": null,
"code": "MEDI_10",
"state": "LOADED",
"weight": 10.0,
"location": "kandy"
}
]
}
> 
#### Load medications to a drone
- load medication to a drone url (post) - **localhost:5569/v1/drone/load/**
> Request : {
"serialNumber" : "0001",
"medicationCode":"MEDI_10",
"location" : "kandy",
"loadingComplete" : false
}
> Response : {
"serialNumber": "0001",
"responseMessage": "Medication loaded successfully"
}
> 
---

### END