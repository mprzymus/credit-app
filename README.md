# credit-app
## Deployment

-Jdk 15 needed

Build database image (in project root directory): docker build -t *image_name* .
Run container: docker run -d --name *container_name* -p 5432:5432 *image_name*

Then start each service with mvn spring-boot:start


## API
### Credit service
#### /api/credits post - add credit, returns credit number
request body:

``` json
{
    "customer": {
        "firstName": "Jan",
        "surname": "Kowalski",
        "pesel": "74231254323"
    },
    "credit": {
        "creditName": "someCreditName"
    },
    "product": {
        "productName": "someProductName",
        "value": "123"
    }
}
```

Response

```json
{
  "creditNumber": 4
}
```

#### /api/credits get - returns all credits:

response:

```json
{
  "credits": [
    {
      "customer": {
        "firstName": "Janek",
        "surname": "Kowalski",
        "pesel": "74231254323"
      },
      "credit": {
        "creditName": "someCreditName"
      },
      "product": {
        "productName": "someProductName",
        "value": 1223
      }
    },
    {
      "customer": {
        "firstName": "Janek",
        "surname": "Kowalski",
        "pesel": "74231254323"
      },
      "credit": {
        "creditName": "someCreditName"
      },
      "product": {
        "productName": "someProductName",
        "value": 1223
      }
    }
  ]
}
```

### Products service

#### /api/products post - adds product

request body:

```json
{
  "product": {
    "productName": "someProductName",
    "value": "1223"
  },
  "creditNumber": "1"
}
```
creditNumber needs to be valid creditId
response is same as request

#### /api/products get - returns all products
response:
```json
{
  "products": [
    {
      "product": {
        "productName": "someProductName",
        "value": 1223
      },
      "creditNumber": 1
    },
    {
      "product": {
        "productName": "someProductName",
        "value": 1223
      },
      "creditNumber": 2
    }
  ]
}
```

### Customers service

#### /api/customers post - adds customer

request body:

```json
{
  "customer": {
    "firstName": "FirstName",
    "surname": "Surname",
    "pesel": "2114324331`"
  },
  "creditNumber": 1
}
```
creditNumber needs to be valid creditId
response is same as request

#### /api/customers get - returns all customers

```json
{
  "customers": [
    {
      "customer": {
        "firstName": "Janek",
        "surname": "Kowalski",
        "pesel": "74231254323"
      },
      "creditNumber": 1
    },
    {
      "customer": {
        "firstName": "Janek",
        "surname": "Kowalski",
        "pesel": "74231254323"
      },
      "creditNumber": 2
    }
  ]
}
```