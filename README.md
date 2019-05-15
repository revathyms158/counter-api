# counter-api

Implemented the below RESTful Services, which provide the solution of the following tasks.

Task 1: Search the following texts, which will return the counts
respectively.

Eg: 
Sample Request
> curl http://host/counter-api/search -H"Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" -
d’{“searchText”:[“Duis”, “Sed”, “Donec”, “Augue”, “Pellentesque”, “123”]}’ -H"Content-
Type: application/json" –X POST
Result in JSON:
> {"counts": [{"Duis": 11}, {"Sed": 16}, {"Donec": 8}, {"Augue": 7}, {"Pellentesque": 6},
{"123": 0}]}

Task 2: Provide the top 20 (as Path Variable) Texts, which has the
highest counts in the Sample Paragraphs provided. 

Result should be in csv format and user will be able to put 10, 20, 30 or 5 as the top listing.

Eg: 
Sample Request
> curl http://host/counter-api/top/20 -H"Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" -
H”Accept: text/csv”
As an example of the result if I put /top/5:
text1|100
text2|91
text3|80
text4|70
text5|60

Technologies used :

Apache Maven 3.2.3, Java 6, Tomcat 7.0.12, Spring Framework 4.3.0.RELEASE, Spring Security 4.1.1.RELEASE
Provided paragraph is in the classpath (dataFile.txt)

For building & testing the APIs: 
  1. mvn clean install 
  2. Take the counter-api.war file from /target folder and deploy it in the tomcat server.
