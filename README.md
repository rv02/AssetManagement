# AssetManagement
[Postman Collection link](https://www.getpostman.com/collections/aa5510e6ecdeeb2ed08f): All api endpooints with examples.

Divided the whole project into different packages in dao, service, controller, model.

#### Model description
The model consists of Category, Employee and Asset Entity. The Employee table is initially populated with two values with id 1 and 2, which can be used for assign, recover asset operations. This is done by data.sql script in src/resources.

#### Controller Description
The Controller consists of CategoryController and AssetController.
In CategoryController all endpoints start with /api/category
In AssetController all endpoints start with /api/asset

#### Service
Gets access to database through Repositories and contains most of the logic.

##### Custom errors
DataNotFoundException, AssetAssignedException, AssetNotAssignedException

The test folder contains test cases for AssetService and CategoryService.
