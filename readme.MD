# Demo
### To make sure our application is accessed by humans and not a by any computers or bot.

## Running the app
There are 3 ways to run the app.
1. docker-compose up
2. java -jar demo.jar
3. gradle bootRun

NOTE - It's a spring boot application is running on default port 8080

## Sequence
1. Client will make a HTTP GET request for getting a question on endpoint http://localhost:8080/question
   with request body as

```json
{
    "questionId": "f0c51456-3f16-4925-ba1b-45c4a0c8ac03"
}
```

**questionId** is a random ID that client needs to generate, the server will create a question corresponding to the given ID. Next, when client will provide the answer, same ID needs to passed. This is to ensure the that the question and the answer server received is previously given to a particular client request


2. Server will respond with the question generated in the below JSON format
```json
{
    "questionId": "f0c51456-3f16-4925-ba1b-45c4a0c8ac03",
    "question": "What is the sum of nums ?",
    "nums": [
        18,
        62
    ]
}
```

3. Client will calculate the sum and make a HTTP POST request on
   http://localhost:8080/answer with below JSON request body

```json
{
    "questionId": "f0c51456-3f16-4925-ba1b-45c4a0c8ac03",
    "originalQuestion" : "What is the sum of nums ?",
    "nums" :[
        18,
        62
    ],
    "answer" : 80
}
```

Server will verify that the question and answer was intented for the same client by matching the questionID and will check the sum.
If the sum is correct below is HTTP 200 OK response

```json
{
    "message": "Answer is correct."
}
```

If the answer is wrong or the questionID is incorrect client will recieve 400 BAD request.
```json
{
    "message": "Sorry the answer is incorrect for the ques - What is the sum of nums ? [50, 35]"
}
```

```json
{
    "message": "Question was not generated for this client."
}
```