# Team Information

Team name: TEAM 7
Team members
Domenic Mangano
Zach Kroesen
Connor Bastian
Uttam Bhattarai
Lucas Romero

# Executive Summary

The 7Shoes shoe store has been built using Angular/TypeScript for the front-end and Java for the back-end. The use of Angular/TypeScript provides a dynamic and responsive user interface, while Java allows for robust and scalable back-end processing. This combination of technologies enables this e-store to provide a seamless shopping experience for customers, with easy navigation, fast load times, and secure transactions. The Java backend supports features such as inventory management, order processing, and customer data management.

## Purpose

### Sprint 2:
This project is aimed at giving a soft introduction to fullstack development for novice to intermediate programmers. Learning how a backend interacts with a front end and how to develop both sides of the coin are both major goals for this project.

### Sprint 4: 
Throughout this project, it helped teach participants the skills to create a web-app in Angular and Typescript with a Java-based REST API. These are 3 of the most used languages/frameworks used in the industry today, and are essential building blocks for becoming well-rounded and knowledgeable software developers. On top of this, we learned incredibly important team skills that help software development teams work together in order to complete the shared goal of a complete and well-designed web application, including SCRUM and AGILE principles, as well as planning poker and other important team dynamic practices. 


# Glossary and Acronyms
[Sprint 2 & 4]
Provide a table of terms and acronyms.

| Term |          Definition           |
|------|-------------------------------|
|  SPA | Single Page Application - A modern concept of displaying web pages |
| HTML | Hyper Text Markup Language - The Building blocks of web pages |
| Angular | A single page application framework |
| Java Spring | A java-based web application server |
| REST | A communication standard that allows clients and servers to talk to each other using common procedures over HTTP |
| Component | An isolated block in Angular |



# Requirements

This section describes the features of the application.

## Definition of MVP

For the requirements of the Sprint 2 MVP (Minimum Viable Product), we had to complete functional user and cart objects with accompanying DAO and Controller methods so as allow the user to log in/out, browse shoes in stock by their attributes using the search function, add shoes to their cart, as well as admin login privileges that allowed for inventory management such adding, updating, and deleting shoes from the inventory. As for the requirements of the complete MVP after Sprint 2, the main functional areas left to cover included error checking methods discovered through unit testing. For example, a new method was devised to prevent submitting an order with 0 products selected as well as fixing up any UI related issues.

## MVP Features

### Admin / Store Owner
Create a New Product
Update a Product
Delete a Single Product
Username Assumption 
Owner Access Restriction 
Inventory Management Page

### User / Buyer
Customer Check Out
Customer Product Attribute View
Add/Remove From Shopping Cart 

### General
Get a Single Product
Search for a Product
Get Entire Inventory
Log In/Out Application
User Authentication 
Nav Bar Functionality
Product Browsing Page 
Product Viewing Page 
Home Page 

## Enhancements

The enhancements made throughout the life of this project included improving the user-friendly interface as well as having toastr pop up messages and an advanced checkout mechanism. These enhancements represented our 10% feature of the website and consisted of our own improvements to the design outside of the MVP requirements.

### Enhancement #1: Order Management

To ease in the process of keeping track of user orders for the admin, we created a simple order management page that displays all orders made by users (excluding the admin), where navigation to the “ORDERS” page was a simply labeled button in the navigation bar that appeared when the admin is logged in, and displays each order with separate rows for each order, and orders specifics (username, items in the order, and total cost of the order) separated by columns.

### Enhancement #2: Toastr Popups

Although a small addition, the Toastr popups can make a major difference in terms of overall user experience of the website, as each action completed by the user, such as adding a shoe to their cart, successful checkout, etc, are logged and displayed by a nicely designed Toastr popup in the bottom right corner of the screen.	

### Enhancement #3: Filter Options When Browsing
	
In almost any e-store web application, when surfing the product browsing page you will find a side bar that contains filter options in order to help narrow down the products to help the user find exactly what they are looking for. We decided this would be a great feature to add to the app as it would help us mimic how other top level e-stores operate.

# Application Domain

This section describes the application domain.

The domain of this web application is very simple. A user can browse products in the inventory after creating an account. This account consists of a password and username and more information later on. After this is complete the user can then search for items and purchase them using the product filtering methods as well as the shopping cart. When the user is ready to check out, a prompt is provided to input their payment details and your shoes are placed in a newly created order. These orders, as well as the inventory, are managed by the admin of the website. One MVP feature of the admin is that they cannot access the user shopping carts due to privacy issues.

### Original Domain Analysis from Sprint 2:
<img width="468" alt="image" src="https://user-images.githubusercontent.com/111752403/231780025-d373b62d-fdf4-4648-a6d3-d03b5ae4b1b1.png">

### Final Domain Analysis from Sprint 4: 
![Final Domain Analysis](https://user-images.githubusercontent.com/111752403/233374414-d8b64256-44b2-420b-9e8b-e90367a29e1b.png)

# Architecture and Design

This section describes the application architecture.

## Summary

The following Tiers/Layers model shows a high-level view of the webapp's architecture.

![Screenshot 2023-04-20 at 9 12 40 AM](https://user-images.githubusercontent.com/111752403/233376966-92509da0-1da9-4c89-a0d1-31297f654025.png)

The e-store web application is built using the Model–View–ViewModel (MVVM) architecture pattern. 

The Model stores the application data objects including any functionality to provide persistence. 

The View is the client-side SPA built with Angular utilizing HTML, CSS and TypeScript. The View-Model provides RESTful APIs to the client (View) as well as any logic required to manipulate the data objects from the Model.

Both the View-Model and Model are built using Java and Spring Framework. Details of the components within these tiers are supplied below.

Overview of User Interface

The e-store's UI was designed so that it is extremely easy for the user to both find exactly what their looking for, add it to their cart, and checkout, but also be able to poke around the website for things they might not have known they were interested in, such as specific brands in the brand bar that spans the top of the page under the navigation bar, or the featured products that include white sneakers, women’s sneakers, and the legendary Stan Smith tennis shoe. The search function is easy to find and use, and is available on every page as it is included in the navigation bar that is pinned to the top of the app. Because we’re more focused on streetwear style, a white brick background throughout gives off a cool and modern aesthetic along with softly edged buttons and a largely monochromatic color palette.

## View Tier
[Sprint 4]
Provide a summary of the View Tier UI of your architecture. Describe the types of components in the tier and describe their responsibilities.  This should be a narrative description, i.e. it has a flow or "story line" that the reader can follow.

In the View Tier UI the types of components in the tier refer to the various paths on the website. For example there is separation for the navigation bar, the entire homepage, shopping cart, etc. Their responsibilities are given by their names as each file was specifically named after its purpose. 

In the navbar, there were multiple pieces, including buttons that routed the user to specific products based on their sizing (mens, womens, kids), a search bar that allowed the user to look for products, as well as home, shopping cart, and login icons that routed the user to each specified location. When the admin is logged in, the shopping cart icon disappears, but “Inventory” and “Orders” appear so the admin can edit inventory and view orders

In the home page, there were multiple feature bars that would route the user to the product browsing component where it would display the item that was on the feature bar picture (white shoes, nike, jordan, etc)

In the shopping cart component, the user views all the items they have added to their cart and can click their checkout button, allowing them to purchase the items

In the browsing component, there is a expandable filter bar that allows the user to filter down options in the product browsing window by brand, style, price, color, etc.

In the inventory component, the user has multiple buttons that allow them to create, edit, and delete shoes, all brightly colored and labeled in bold font in order to provide easy experience for the admin. 

In the orders component, the admin can view the username of the person who made the order, the items in the order, and the total cost of the order.

The service components (cart, product, checkout, and user services) allowed the front end to send and receive data from the backend API, such as user info, orders, shopping cart, and product info.


### addToCart() Sequence Diagram
<img width="873" alt="Screenshot 2023-04-13 at 9 12 47 AM" src="https://user-images.githubusercontent.com/111752403/231777090-7104893d-4756-4ffa-869d-b2d958a6cb86.png">

### createShoe() Sequence Diagram
<img width="1406" alt="SWEN261 createShoe sequence diagram" src="https://user-images.githubusercontent.com/111752403/233374965-60ec4574-ce88-469d-8704-06ee559f07b8.png">


[Sprint 4]
To adequately show your system, you will need to present the class diagrams where relevant in your design. Some additional tips: Class diagrams only apply to the View-Model and Model Tier. A single class diagram of the entire system will not be effective. You may start with one but will be need to break it down into smaller sections to account for requirements of each of the Tier static models below. Correct labeling of relationships with proper notation for the relationship type, multiplicities, and navigation information will be important.

## View Model Tier
[Sprint 4]

The ViewModel Tier contains all of the DAO's. These are the interfaces for the files as they act as basic functions for every aspect of the website's functionality. For example, the ViewModel tier contains DAO's for the cart, shoe and user. For example, within the CartDAO file, the function clearCart() was in place to flush the cart if the user decided to or after the user put an order in place. Once again, the names of the file represent the responsibility of the functions.

### Shoe ViewModel

<img width="376" alt="SHOE VIEWMODEL" src="https://user-images.githubusercontent.com/111752403/231779349-b38e0580-55fe-4229-8983-29bd65926127.png">

### User ViewModel

<img width="379" alt="USER VIEWMODEL" src="https://user-images.githubusercontent.com/111752403/231779454-194e8329-9cdb-40a3-947a-4fa281d72ff6.png">

### Cart ViewModel

<img width="385" alt="CART VIEWMODEL" src="https://user-images.githubusercontent.com/111752403/231779515-f52c1183-1bae-43f4-b215-6e42b51eb4b4.png">

### Order ViewModel

<img width="392" alt="ORDER VIEWMODEL" src="https://user-images.githubusercontent.com/111752403/231779563-f8e8a069-35d6-4d18-ac3b-53f0e66e3095.png">

At appropriate places as part of this narrative provide one or more updated and properly labeled static models (UML class diagrams) with some details such as critical attributes and methods.

[Replace with your View-Model Tier class diagram 1, etc.](model-placeholder.png)

## Model Tier

The model tier stores all the appropriate data required for the website to function properly. The model contains all of the getters and basic functions of the user, shoe and cart files. The way this is structured is to include the MVP features for the branch as well as any additions we saw fit to improve the functionality of the website. For example, within the User file, the method setUsername() was implemented to set the customer’s username.

### Shoe Model:

<img width="295" alt="shoe model" src="https://user-images.githubusercontent.com/111752403/231779282-8dce428a-8ae6-4607-8ebe-9ff3fc4df45c.png">


### User Model:

<img width="291" alt="user model" src="https://user-images.githubusercontent.com/111752403/231779096-dabbe29b-69ca-4589-9c9b-63e460342aad.png">


### Cart Model:

<img width="293" alt="cart model" src="https://user-images.githubusercontent.com/111752403/231779155-b244e56f-4dd7-42c0-8adb-483cd270a610.png">

### Order Model:

<img width="301" alt="order model" src="https://user-images.githubusercontent.com/111752403/231779230-815a7b40-da71-4d38-8a8c-a9b57dc3e309.png">

# OO Design Principles

We strictly adhered to many of the Object-Oriented Principles, but the 4 main principles we adhered to in this project were primarily implemented in the API side of the project:

Single Responsibility Principle: The API follows the single responsibility principle by ensuring that each class has only one responsibility or reason to change. For example, the class responsible for managing customer data is separate from the class that handles inventory management.

Open/Closed Principle: The API adheres to the open/closed principle by allowing for extension without modification. For example, new features can be         added to the API without modifying existing code.

Liskov Substitution Principle: The API follows the Liskov substitution principle by ensuring that any derived class can be substituted for its base class. For example, any subclass of the shoe class can be used in place of the base shoe class.

Dependency Inversion Principle: The API adheres to the dependency inversion principle by relying on abstractions rather than concrete implementations. This allows for flexibility and ease of modification. For example, the API uses interfaces to define dependencies rather than specific classes.


# Static Code Analysis/Future Design Improvements

The Static Code Analysis Tool flagged the persistence area within our code. This is due to the CartFileDAO functionality having a few errors that were looked into after the MVP features were error-free.

The Static Code Analysis Tool flagged the persistence area within our code. This is due to the OrderFileDAO functionality containing an error that was corrected after the MVP features were already explicitly implemented.

The Static Code Analysis Tool flagged the controller area within our code. This is due to the ShoeController functionality having a few mishaps that were looked into after MVP features were error-free.

The Static Code Analysis Tool flagged the controller area within our code. This is due to the CartController functionality having an error that was looked into after MVP features were error-free.

# Future Improvements 
For future refactoring or other design improvements, our team would most likely explore the checkout process a little more. Given our product passed the MVP requirements with no errors, there were still some functions we wish to include in this area of the website. For example, we wish to devise an Apple Pay method on top of the debit/credit card method already in place. Going more in depth with the order management functionality would also be a welcome addition, as creating a "Shipped Order" and "Cancel Order" buttons for the admin would likely create a better experience for both the user and the admin.

# Testing

This section will provide information about the testing performed and the results of the testing.

## Acceptance Testing

With over 75 passing tests in our backend API, as well as all proposed user stories passing their acceptance criteria tests, none of the user stories failed their tests or have not had any testing yet. The only issues found during testing were small errors within the program that were corrected shortly after. There are no concerns with the functionality of our product, the website works as expected and includes various functions to impede errors from occurring.

[Sprint 2]
All user stories that were in the Sprint 2 backlog were completed in a satisfactory manner along with all required functionality

Log In/Out Application
User Authentication 
Username Assumption 
Owner Access Restriction 
Build Nav Bar 
Create Product Browsing Page 
Create Product Viewing Page 
Add/Remove From Shopping Cart 
Create Home Page 
Create Inventory Management Page 

## Unit Testing and Code Coverage

Our unit testing strategy consisted of covering all MVP functionality early on in the process before deep diving into more advanced features. With this strategy, our product did not have many errors to correct after testing. The team’s coverage targets was around 70-100% of coverage. These values were selected due to our drive to create an error free product to give the user the best experience when purchasing shoes. After additional time spent learning and exploring the Jacoco code analysis report, we were able to pinpoint exactly where our coverage was missing, and after inserting almost 30 new tests into the Persistence and Controller Tier tests, we were able to achieve an outstanding coverage score of 96% (in the code instructions) and a 78% in overall branch coverage. This was a massive improvement over the previous iteration of testing our code coverage, as we had only reported a 68% total code coverage. By doing this, we ensure that fewer errors or bugs can slip through the cracks of our backend, and that every method will function as it is intended to.

## Maven Code Analysis

![OverallCoverage](https://user-images.githubusercontent.com/111752403/233375810-66fedc37-feeb-49f3-a4d1-e12df561a782.png)
![PersistenceCoverage](https://user-images.githubusercontent.com/111752403/233375847-11236d7b-addb-45c0-8731-f60b477993d5.png)
![ControllerCoverage](https://user-images.githubusercontent.com/111752403/233376031-cfe06569-f6df-4ab0-bba2-5639146091b6.png)
![ModelCoverage](https://user-images.githubusercontent.com/111752403/233376032-7fbb8bbf-bcec-4140-8fb6-4d9098a16352.png)
![OrderFileDAOCoverage](https://user-images.githubusercontent.com/111752403/233376091-dbb9b8b7-6bdf-4bfd-989c-e94c8f95a4e3.png)
![UserFileDAOCoverage](https://user-images.githubusercontent.com/111752403/233376096-6ba0cdca-98ca-4c18-b010-923eb2764a66.png)
![CartFileDAOCoverage](https://user-images.githubusercontent.com/111752403/233376101-805a2196-5db3-4f73-ba74-e35c02afcf87.png)
![ShoeFileDAOCoverage](https://user-images.githubusercontent.com/111752403/233376105-2c878453-6318-4423-a0b6-8d2edd963688.png)
![ShoeControllerCoverage](https://user-images.githubusercontent.com/111752403/233376174-7ebf9a34-2ab1-44ed-a696-07bfa56e82f2.png)
![CartControllerCoverage](https://user-images.githubusercontent.com/111752403/233376175-a206b297-5bc2-408d-8066-d84f29b322dd.png)
![UserControllerCoverage](https://user-images.githubusercontent.com/111752403/233376176-352c657c-e7b3-40e0-b298-81256c8f847c.png)
![OrderControllerCoverage](https://user-images.githubusercontent.com/111752403/233376195-eabd933e-f3f2-4e2e-a911-42169200434e.png)
