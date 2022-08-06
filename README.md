# prices microservice

# How to use
- Clone repository
```
git clone git@github.com:nicolucini/prices.git
```

- Build H2 Database docker image
```
docker pull oscarfonts/h2
docker run -d -p 1521:1521 -p 81:81 -v /path/to/local/data_dir:/opt/h2-data --name=MyH2Instance oscarfonts/h2
```

- Run Project
```
./gradlew bootRun  
```

- URL to request prices
```
GET http://localhost:8080/brands/1/products/35455/prices?date=2020-06-14-10:00:00
```