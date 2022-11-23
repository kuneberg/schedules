# Schedules service for Geosatis
## Build
Service is dockerized and docker-compose added for service and database run.
To build the app execute following command:
```shell
docker-compose build
```

## Run
To run the service execute the following command:
```shell
docker-compose up
```

## REST API
The service provides following API methods
### Create schedule
Due to requirements not to update schedules in the past there is no way to update existed one, just create new one. 
Schedules for each offender+zone versioned based on create date time. 
Schedule object hierarchy is described in schedule `model` section
#### Method URL
```
POST /offenders/{offenderId}/zones/{zoneId}/schedule
```

#### Request body
```json
{
  "schedule": {
    
  }
}
```

#### Response body
```json
{
  "success": true,
  "data": {
    "id": "schedule id",
    "zoneId": "zone_id",
    "offenderId": "offender_id",
    "schedule": {
      
    },
    "createdAt": "create_date"
  }
}
```

#### Request sample
Url: `POST 'http://localhost:8080/offenders/1/zones/1/schedule'`  
Request body:  
```json
{
    "schedule": {
        "type": "eq",
        "scale": "YEAR",
        "value": 2022
    }
}
```
Response body:
```json
{
    "success": true,
    "data": {
        "id": "637d801ba722a6156f60cb28",
        "zoneId": "1",
        "offenderId": "1",
        "schedule": {
            "type": "eq",
            "scale": "YEAR",
            "value": 2022
        },
        "createdAt": "2022-11-23T03:06:19.907208"
    }
}
```

### Get schedule
Method returns actual (last) schedule information for given offender and zone IDs
#### Method URL
```
GET /offenders/{offenderId}/zones/{zoneId}/schedule
```

#### Response body
```json
{
  "success": true,
  "data": {
    "id": "schedule id",
    "zoneId": "zone_id",
    "offenderId": "offender_id",
    "schedule": {
      
    },
    "createdAt": "create_date"
  }
}
```

#### Request sample
Url: `GET 'http://localhost:8080/offenders/1/zones/1/schedule'`  
Response body:
```json
{
    "success": true,
    "data": {
        "id": "637d801ba722a6156f60cb28",
        "zoneId": "1",
        "offenderId": "1",
        "schedule": {
            "type": "eq",
            "scale": "YEAR",
            "value": 2022
        },
        "createdAt": "2022-11-23T03:06:19.907208"
    }
}
```

### Match schedule
Method returns information is given date matches schedule for given offender and zone IDs
#### Method URL
```
GET /offenders/{offenderId}/zones/{zoneId}/schedule/matches?dateTime={date_time}
```

#### Response body
```json
{
  "success": true,
  "data": true
}
```

#### Request sample
Url: `GET 'http://localhost:8080/offenders/1/zones/1/schedule/matches?dateTime=2022-11-22T11:56:02.528649'`  
Response body:
```json
{
  "success": true,
  "data": true
}
```

## Model
### Scale 
There are multiple scales of date time schedule matching supported like year, month, day of week etc. Full list of supported scales are listed `org.kuneberg.geosatis.schedules.model.schedule.Scale`

### Schedule
To meet the requirements there are multiple types of schedule. 
Each schedule has optional `nested` field which is also schedule to build hierarchy of rules.
#### Equal schedule 
`org.kuneberg.geosatis.schedules.model.schedule.EqualsSchedule` - matches date value to value in schedule for specified scale
Structure:

```json
{
  "type": "eq",
  "scale": "scale",
  "value": int_value,
  "nested": {}
}
```
#### Range schedule 
`org.kuneberg.geosatis.schedules.model.schedule.RangeSchedule` - matches date value to be in range (inclusive) set in schedule for specified scale
```json
{
  "type": "range",
  "scale": "scale",
  "from": int_value,
  "to": int_value,
  "nested": {}
}
```
#### Periodic schedule 
`org.kuneberg.geosatis.schedules.model.schedule.PeriodicSchedule` - matches date value to be equal to one of period slots (zero based values) set in schedule for specified scale
```json
{
  "type": "period",
  "scale": "scale",
  "periodSize": int_value,
  "periodSlots": [int_value],
  "nested": {}
}
```

e.g. to match each Monday and Wednesday the schedule should looks like this:
```json
{
  "type": "period",
  "scale": "WEEK_DAY",
  "periodSize": 7,
  "periodSlots": [0, 2],
  "nested": {}
}
```

#### Composite schedule 
`org.kuneberg.geosatis.schedules.model.schedule.CompositeSchedule` - wraps lists of including and excluding schedule rules. Matches that date doesn't match any schedule from excludings and matches at least one in includings.
```json
{
  "type": "composite",
  "including": [],
  "excluding": []
}
```
e.g. to make schedule match each monday but except for september the schedule should looks like this:
```json
{
  "type": "composite",
  "including": [
    {
      "type": "period",
      "scale": "WEEK_DAY",
      "periodSize": 7,
      "periodSlots": [0],
      "nested": {}
    }
  ],
  "excluding": [
    {
      "type": "eq",
      "scale": "MONTH",
      "value": 9,
      "nested": {}
    }
  ]
}
```




