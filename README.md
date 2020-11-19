To run the server, go to the root directory of the project and enter the command "gradlew bootRun".

For this project, I chose to go with Java and SpringBoot because I am familiar with both, and because they are commonly used in the web development industry.
I created a simple REST controller that uses the OkHttp3 client to make API calls. There are two API calls made, and each has a corresponding POJO that is deserialized with Gson. The response is created by serializing the data from the API calls with a separate POJO.
