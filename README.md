# Setup instructions
1. Setup a PostgreSQL database on your laptop. For Mac (M1), I used this link which made it really easy : https://www.sqlshack.com/setting-up-a-postgresql-database-on-mac/.
2. Clone the GitHub repository to access it locally.
3. Launch the backend application :
   a. from an IDE in which you have installed a Spring Boot plugin
   b. from a terminal :
     * move to the source directory of the project (in which there is the pom.xml file) : ./BreweryBrowsingApp/backend/
     * build the jar file : java -jar target/backend-0.0.1-SNAPSHOT.jar
     * launch the app : mvn spring-boot:run
4. Launch the frontend app :
   a. in a terminal, move to the source directory of the project : ./BreweryBrowsingApp/frontend/
   b. run the following command : ng serve
5. Access the app in any browser at http://localhost:4200/

# Features
## Brewery List
On the left side of the screen, you can see the list of all breweries available from the given API.

You have a search section which you can use to search some breweries by name and (optionally) city and/or type.
NB : it is mandatory to search by name to be able to filter by city and/or type.

## Favorites
From the list of breweries, you can click on any of the lines on the button "+" to add the corresponding brewery to your favorites. 
If it is already in your favorites, then the button will be "-" instead of "+" and, by clicking on it, you can remove the brewery from your favorites.

To access the list of your favorites, you have to click on the button representing a heart at the right side of the subtitle "Breweries" (at the top left of the screen).

## Brewery Infos
You can access the part (which is on the right side of the screen) by clicking on the button representing a magnifying loop on any brewery of the list at the left.
There, you can see some more information about any brewery.

You can reset this part by clicking on the "refresh" button located at the right side of the subtitle "Breweries".

# Additional information
I just want to apologize for the bad design of my app, it is due to multiple points :
* I'm officially colorblind thus I can not be efficient in this aspect
* I did not have much time because of my full-time work for which I have lot of work
* I'm really bad at designing (I'm usually working with mockups done by other team members)
