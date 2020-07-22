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
const List = require('./database/models/list');
// access to our task schema
const Task = require('./database/models/task');
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
// Route to get all documents in the database
app.get('/lists', (req, res) =>{ // "http://localhost:3000/lists" -> [] of information in our lists database
    List.find({})
        .then((list) => res.send(list))
        .catch((error) => console.log(error));
}); 
// Route to send a document to the database
app.post('/lists', (req, res) => {
    (new List({'title': req.body.title}))
        .save()
        .then((list) => res.send(list))
        .catch((error) => console.log(error));
});
// Route to get a specific document in our database
app.get('/lists/:listId', (req, res) =>{
    List.find({ _id: req.params.listId})
        .then((list) => res.send(list))
        .catch((error) => console.log(error));
}); /**localhost:3000/lists/listIdnumber */
// Route to patch/update a document in our database
app.patch('/lists/:listId', (req, res) =>{
    List.findOneAndUpdate({'_id': req.params.listId }, {$set: req.body})
        .then((list) => res.send(list))
        .catch((error) => console.log(error));
});
// Route to delete a document in our database
app.delete('/lists/:listId', (req, res) =>{
    const deleteTasks = (list) => {
        Task.deleteMany({_listId: list._id})
            .then(() => list)
            .catch((error) => console.log(error));
    };
    List.findByIdAndDelete(req.params.listId)
        .then((list) => res.send(deleteTasks(list)))
        .catch((error) => console.log(error));
});

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
    (new Task({'_listId': req.params.listId, 'title':req.body.title}))
        .save()
        .then((task) => res.send(task))
        .catch((error) => console.log(error));
});

app.get('/lists/:listId/tasks/:taskId', (req, res) =>{
    Task.findOne({_listId: req.params.listId, _id: req.params.taskId})
        .then((task) => res.send(task))
        .catch((error) => console.log(error));
});

app.patch('/lists/:listId/tasks/:taskId', (req, res) =>{
    Task.findOneAndUpdate({_listId: req.params.listId, _id: req.params.taskId}, {$set: req.body})
        .then((task) => res.send(task))
        .catch((error) => console.log(error));
});

app.delete('/lists/:listId/tasks/:taskId', (req, res) =>{
    Task.findOneAndDelete({_listId: req.params.listId, _id: req.params.taskId})
        .then((task) => res.send(task))
        .catch((error) => console.log(error));
});

// checking to see our application is made.
app.listen(3000, () => console.log('Server is connected on Port 3000'));