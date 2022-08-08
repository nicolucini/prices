# Prices microservice

# How to use
- Clone repository
```
git clone git@github.com:nicolucini/prices.git
```
### Without docker

- Run Project
```
./gradlew bootRun  
```

### With Docker
- Go to project directory
- Build docker image
```
docker build --build-arg JAR_FILE=build/libs/\*.jar -t nicolucini/prices .
```
- Run image
```
docker run -p 8080:8080 nicolucini/prices  
```


###URL to request prices
```
GET http://localhost:8080/brands/1/products/35455/prices?date=2020-06-14-10:00:00
```
- Data will be saved on /tmp/test path if you want to change this path you must change on application.properties
- Stress test plan are in stress folder
