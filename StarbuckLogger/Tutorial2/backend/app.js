/**
 * 
 * API Created using Express and Mongoose to Create, Update, Delete, Get One, and Get All
 * from our database.
 * 
 */

// access to express
const express = require('express');
// creates an application using express
const app = express();
// access to our database
const mongoose = require('./database/mongoose');
// access to our list schema
const {List, Task} = require('./database/models');
// allows application to use JSON data
app.use(express.json());

/**
 * CORS = Cross Origin Request Security.
 * localhost:3000 - backend api
 * localhost:4200 - frontend
 * 
 */
app.use((req, res, next) => {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Methods", "GET, POST, HEAD, OPTIONS, PUT, PATCH, DELETE");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
});

/**
 * List: Create, Update, ReadOne, ReadAll, Delete 
 * Task: Create, Update, ReadOne, ReadAll, Delete
 * GET -> Get data from database.
 * POST -> Save data
 * PUT -> Update an entire documents information
 * PATCH -> Update specific piece of document information.
 * DELETE -> Delete a document.
 */
// "http://localhost:3000/lists" -> [] of information in our lists database

// Route to get all documents in the database
app.get('/lists', (req, res) =>{
    // We want to return an array of all the lists in the database.
    List.find({})
        .then((list) => res.send(list))
        .catch((error) => console.log(error));
}); 
// Route to send a document to the database
app.post('/lists', (req, res) => {
    // We want to create a new list and return the new list document back to the user (which includes the id).
    // The list information fields will be passed in via the JSON request body.
    (new List({'title': req.body.title}))
        .save()
        .then((list) => res.send(list))
        .catch((error) => console.log(error));
});
// Route to patch/update a document in our database
app.patch('/lists/:id', (req, res) =>{
    // We want to update teh specified list (list document with id in the URL) with the new values specified in the JSON body of the request
    List.findOneAndUpdate({'_id': req.params.id}, {$set: req.body})
        .then((list) => res.sendStatus(200))
        .catch((error) => console.log(error));
});
// Route to delete a document in our database
app.delete('/lists/:id', (req, res) =>{
    // We want to delete the specified list (document with id in the URL)
    const deleteTasks = (list) => {
        Task.deleteMany({_listId: list._id})
            .then(() => list)
            .catch((error) => console.log(error));
    };
    List.findByIdAndDelete(req.params.id)
        .then((list) => res.send(deleteTasks(list)))
        .catch((error) => console.log(error));
});

// Route to get a specific document in our database
app.get('/lists/:id', (req, res) =>{
    List.find({ _id: req.params.id})
        .then((list) => res.send(list))
        .catch((error) => console.log(error));
}); /**localhost:3000/lists/listIdnumber */



/**
 * TASKS URL
 * http://localhost:3000/lists/:listId/tasks/:tasksId
 * each task is associated with a list
 */
app.get('/lists/:listId/tasks/', (req, res) =>{
    Task.find({_listId: req.params.listId})
        .then((tasks) => res.send(tasks))
        .catch((error) => console.log(error));
});

app.post('/lists/:listId/tasks/', (req, res) =>{
    // We want to create a new task in a list specified by listId
    (new Task({'_listId': req.params.listId, 'title':req.body.title}))
        .save()
        .then((task) => res.send(task))
        .catch((error) => console.log(error));
});

/*
    This functionality isn't used in this project but useful if you want to get information from a specific item.
    
    app.get('/lists/:listId/tasks/:taskId', (req, res) =>{
        Task.findOne({_listId: req.params.listId, _id: req.params.taskId})
            .then((task) => res.send(task))
            .catch((error) => console.log(error));
    });
*/
app.patch('/lists/:listId/tasks/:taskId', (req, res) =>{
    Task.findOneAndUpdate({_listId: req.params.listId, _id: req.params.taskId}, {$set: req.body})
        .then(() => res.sendStatus(200))
        .catch((error) => console.log(error));
});

app.delete('/lists/:listId/tasks/:taskId', (req, res) =>{
    Task.findOneAndDelete({_listId: req.params.listId, _id: req.params.taskId})
        .then((task) => res.send(task))
        .catch((error) => console.log(error));
});

// checking to see our application is made.
app.listen(3000, () => console.log('Server is connected on Port 3000'));