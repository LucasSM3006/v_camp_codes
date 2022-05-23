# v_camp_codes

This is an exercise made for V_Camp.
Its purpose is to test our knowledge in the SOLID principles and multiple design patterns.
These are the patterns we have to apply in this exercise:
- Builder
- Singleton
- Composite
- Factory
- Iterator
- Facade
- Observer

# Scenario

A brief description of the scenario:

It's a made-up e-commerce/commerce platform and it will have to do and follow certain rules.
We, as the platform's developers, must develop a simple simulation/use case of the project.

# What you will find in the folders/packages. (Instructions to download too.)

Starting with the instructions... Simply click "Code" and then "Download Zip".
Put the project into Eclipse, VSC, any other IDE, or just compile the code, and then run the "Client", which is the main method.

If you're not familiar with compiling java files, use the command "javac" on the command prompt. This short video [How to Compile Java Code From Command Prompt](https://youtu.be/zBF1M8dTftk) should show you how.

**OBS. The test package is completely useless, only utilized for unit testing the project.**

_Obs. "Client" has some logic and what not which will make the program work just fine, but you're not actually required to use it, and so you may modify it to suit your needs._

**Builder** - This package contains the builders which will build the products. You don't have to use them, you can just create products one by one, but you can automate the process with a loop or use a director via these builders.

**Singleton** - The class **"ProductInventory"** resides here. It's responsible for holding our products. Its methods allow you to... Get the instance, add a product of the 'Product' class, get a list of the products, get a single product, remove multiple items from the inventory based on their SKU, block products from the stock (Changes boolean 'available' in the Products), unblock products from the inventory, and finally, reset the inventory.

**Entities** - This package contains the entities, aka, products. At the moment, there are five classes, one of which is Product, which is a class used for inheritance, but you could make a regular product out of it. Book, Computer, Shoe, and Skirt, will inherit from Product and add in their own properties, that being their two unique characteristics.

**Composite** - Where the Cart resides. The cart's job is to be filled up with products and block said products from the inventory whenever they're added to it. It's methods allow you to... Attach observers, dettach observers, add a single product to cart, remove multiple products from the cart, get the list of products in the cart, get the amount of products in the cart, get the total cost without shipping, get the total cost plus shipping, get the cost of the shipping, and at last, get the shipping method.

**Facade** - Order is here. The order must be constructed with a cart, and it's cart, the object it was constructed with, cannot be changed/replaced. The order's job is to hold the cart, the shipping, and the status of the order itself. The Order's methods grant you the ability to... Attach observers, dettach observers, change the order status (some changes will require a prior state to be met. An Order cannot be canceled if the status is completed, or shipped, for example.), get the cart, get the shipping (string), get the status, get the order's ID (Which is a static attribute), and to reset the ID.

**Factory** - The factory package is responsible for handling the shipping logic. Inside it there are a few classes.
Aero, AeroShippingCreator, Road, RoadShippingCreator, Shipping, and ShippingCreator. Aero and Road are two classes which you should instantiate, they implement Shipping. The Creators will instantiate a brand new Road or Aero object. And the ShippingCreator...
To utilize it, you use its static method, "getShipping", to get an instance of the shipping. The instance returned will depend on the parameter, totalWeight. If the totalWeight is over 10 kilos, the shipping method returned will be "Road", otherwise, it will be "Aero.".

**Iterator** - There are two classes here, Iterator and OrderList. Iterator is a generic interface, and OrderList is a class which is not only an Iterator but also a Singleton, thus, it cannot be instantiated. OrderList will allow you to get it back via a get method, and also iterate through the list of Orders residing inside it. It also provides a method to reset the list of orders.

**Observers** - This package contains a few classes. BackOffice, which is a very simple class with the sole job of displaying the order list whenever a status, shipping or cart changes inside an order. The Observers, which are CartObserver, ShippingObserver, and StatusObserver, will activate the RenderOrderList method in the BackOffice whenever a relevant change on the object they’re observing is made.

Observer is but an interface.
CartObserver must be attached to a Cart.
ShippingObserver must be attached to an Order.
StatusObserver must be attached to an Order.
