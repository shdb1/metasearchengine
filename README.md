# Meta Search Engine 

A meta search engine backed by Yahoo and Google news search engines .


# Prerequisite to run app

1. Java
2. Maven 


# Way to build and run

1. download/clone project.
2. Navigate to root directory using command promot/terminal.
3. run below command to build app (It will generate a jar)
mvn clean install

Once above command executed you will find a jar YOUR_SYSTEM_PATH/target/metasearchengine-beta.jar

4.Now run the above generated jar 
java -jar YOUR_SYSTEM_PATH/target/metasearchengine-beta.jar

5. Now you can open your browser and hit following URL

http://localhost:8080/news/search?query=?

Example : http://localhost:8080/news/search?query=Saurav gangualy's health 

6. Once yo hit the URL you must see output on browser screen (JSON content) and file generated with the name ResultantRanks_A1.txt.









