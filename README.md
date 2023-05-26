# Employes

Welcome to the employee detail project! In this project, we have created an application to help companies keep a detailed record of their employees.

Our app features a user-friendly user interface that allows users to add, edit, and delete employee information. Users can add details such as first name, last name, address, phone number, email, and job title.
## Development

Before you can build this project, you must install and configure the following dependencies on your machine:

1. [Node.js][]: We use Node to run a development web server and build the project.
   Depending on your system, you can install Node either from source or as a pre-packaged bundle.

After installing Node, you should be able to run the following command to install development tools.
You will only need to run this command when dependencies change in [package.json](package.json).

```
npm install
```

We use npm scripts and [Angular CLI][] with [Webpack][] as our build system.

Run the following commands in two separate terminals to create a blissful development experience where your browser
auto-refreshes when files change on your hard drive.

```
./mvnw
npm start
```

## Building for production

### Packaging as jar

To build the final jar and optimize the employes application for production, run:

```
./mvnw -Pdev clean verify
```

This will concatenate and minify the client CSS and JavaScript files. It will also modify `index.html` so it references these new files.
To ensure everything worked, run:

```
java -jar target/*.jar
```

Then navigate to [http://localhost:8789](http://localhost:8789) in your browser.


### Packaging as war

To package your application as a war in order to deploy it to an application server, run:

```
./mvnw -Pdev,war clean verify
```

## Testing

To launch your application's tests, run:

```
./mvnw verify
```

### Client tests

Unit tests are run by [Jest][]. They're located in [src/test/javascript/](src/test/javascript/) and can be run with:

```
npm test
```
