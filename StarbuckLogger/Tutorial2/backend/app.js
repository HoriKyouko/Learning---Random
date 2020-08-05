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
const {List, Task, User} = require('./database/models');
// access to jsonwebtoken
const jwt = require('jsonwebtoken');
// allows application to use JSON data
app.use(express.json());

/* Middleware */

// check whether the request has a valid JWT token
let authenticate = (req, res, next) => {
    let token = req.header('x-access-token');

    // verify the JWT
    jwt.verify(token, User.getJWTSecret(), (err, decoded) => {
        if(err) {
            // there was an error
            // jwt is invalid - DO NOT AUTHENTICATE!
            res.status(401).send(e);
        }
        else{
            req.user_id = decoded._id;
            next();
        }
    });
}

// Verify refresh token middleware
let verifySession = (req, res, next) => {
    // grab the refresh token from the request header
    let refreshToken = req.header('x-refresh-token');
    // grab the id from the request header.
    let _id = req.header('_id');

    User.findByIdAndToken(_id, refreshToken).then((user) => {
        if(!user){
            // user couldn't be found
            return Promise.reject({
                'error': "User Not Found. Make sure refresh token and user id are correct"
            });
        }
        // The refresh token exists in the database - but we still have to check if it has expired or not.
        req.user_id = user._id;
        req.userObject = user;
        req.refreshToken = refreshToken;

        let isSessionValid = false;

        user.sessions.forEach((session) => {
            if(session.token === refreshToken){
                // check if the session has expired
                if(User.hasRefreshTokenExpired(session.expiresAt) === false){
                    // refresh token has not expired
                    isSessionValid = true;
                }
            }
        });

        if(isSessionValid){
            // the session is valid - call next() to continue with processing this web request.
            next();
        }
        else{
            return Promise.reject({
                'error': "Refresh Token has expired or the session is invalid."
            });
        }
    }).catch((e) => {
        res.status(401).send(e);
    })
}

//

/**
 * CORS = Cross Origin Request Security.
 * localhost:3000 - backend api
 * localhost:4200 - frontend
 * 
 */
app.use((req, res, next) => {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Methods", "GET, POST, HEAD, OPTIONS, PUT, PATCH, DELETE");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, x-access-token, x-refresh-token, _id");

    res.header("Access-Control-Expose-Headers", "x-access-token, x-refresh-token");
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
app.get('/lists', authenticate, (req, res) =>{
    // We want to return an array of all the lists in the database that belong to the authenticated user.
    List.find({
        _userId: req.user_id
    }).then((list) => res.send(list))
      .catch((error) => console.log(error));
}); 

// Route to send a document to the database
app.post('/lists', authenticate, (req, res) => {
    // We want to create a new list and return the new list document back to the user (which includes the id).
    // The list information fields will be passed in via the JSON request body.
    (new List({'title': req.body.title, '_userId': req.user_id}))
        .save()
        .then((list) => res.send(list))
        .catch((error) => console.log(error));
});

// Route to patch/update a document in our database
app.patch('/lists/:id', authenticate, (req, res) =>{
    // We want to update teh specified list (list document with id in the URL) with the new values specified in the JSON body of the request
    List.findOneAndUpdate({'_id': req.params.id, _userId: req.user_id}, {$set: req.body})
        .then((list) => res.send({'message': 'updated succesfully'})) // might need to change to send instead of sendStatus...
        .catch((error) => console.log(error));
});
// Route to delete a document in our database
app.delete('/lists/:id', authenticate, (req, res) =>{
    // We want to delete the specified list (document with id in the URL)
    const deleteTasks = (list) => {
        Task.deleteMany({_listId: list._id})
            .then(() => console.log("Tasks from " + list._id + " were deleted!"));
    };
    List.findByIdAndRemove({_id: req.params.id, _userId: req.user_id})
        .then((list) => res.send(deleteTasks(list)))
        .catch((error) => console.log(error));
});

// Route to get a specific document in our database
app.get('/lists/:id', (req, res) =>{
    List.find({ _id: req.params.id})
        .then((list) => res.send(list))
        .catch((error) => console.log(error));
}); /**localhost:3000/lists/listIdnumber*/



/**
 * TASKS URL
 * http://localhost:3000/lists/:listId/tasks/:tasksId
 * each task is associated with a list
 */
app.get('/lists/:listId/tasks/', authenticate, (req, res) =>{
    Task.find({_listId: req.params.listId})
        .then((tasks) => res.send(tasks))
        .catch((error) => console.log(error));
});

app.post('/lists/:listId/tasks/', authenticate, (req, res) =>{
    // We want to create a new task in a list specified by listId

    List.findOne({
        _id: req.params.listId,
        _userId: req.user_id
    }).then((list) => {
        if(list){
            // list object is valid
            // therefore the currently authenticated user can create new tasks
            return true;
        }
        return false;
    }).then((canCreateTask) => {
        if(canCreateTask){
           (new Task({'_listId': req.params.listId, 'title':req.body.title}))
                .save().then((task) => res.send(task))
            .catch((error) => console.log(error)); 
        }
        else {
            res.sendStatus(404);
        }
    })
});

/*
    This functionality isn't used in this project but useful if you want to get information from a specific item.
    
    app.get('/lists/:listId/tasks/:taskId', (req, res) =>{
        Task.findOne({_listId: req.params.listId, _id: req.params.taskId})
            .then((task) => res.send(task))
            .catch((error) => console.log(error));
    });
*/
app.patch('/lists/:listId/tasks/:taskId', authenticate, (req, res) =>{

    List.findOne({
        _id: req.params.listId,
        _userId: req.user_id
    }).then((list) => {
        if(list){
            // list object with the specified conditions was found
            // therefore teh currently authenticated user can make updates to tasks within this list
            return true;
        }
        return false;
    }).then((canUpdateTasks) => {
        if(canUpdateTasks){
            // the currently authenticated user can update tasks.
            Task.findOneAndUpdate({_listId: req.params.listId, _id: req.params.taskId}, {$set: req.body})
                .then(() => res.send({message: "Updated Successfully"}))
                .catch((error) => console.log(error));
        }
        else{
            res.sendStatus(404);
        }
    })

    
});

app.delete('/lists/:listId/tasks/:taskId', authenticate, (req, res) =>{
    List.findOne({
        _id: req.params.listId,
        _userId: req.user_id
    }).then((list) => {
        if(list){
            // list object with the specified conditions was found
            // therefore teh currently authenticated user can make updates to tasks within this list
            return true;
        }
        return false;
    }).then((canDeleteTask) => {
        if(canDeleteTask){
            Task.findOneAndDelete({_listId: req.params.listId, _id: req.params.taskId})
                .then((task) => res.send(task))
                .catch((error) => console.log(error));
        }
        else{
            res.sendStatus(404);
        }
    })

    
});

/* User Routes */

/**
 * POST /users
 * Purpose: Sign up users
 */
app.post('/users', (req, res) =>{
    // User sign up
    let body = req.body;
    let newUser = new User(body);

    newUser.save().then(() => {
        return newUser.createSession();
    }).then((refreshToken) =>{
        // Session created successfully - refreshToken returned.
        // now we generate an access auth token for the user.
        return newUser.generateAccessAuthToken().then((accessToken) => {
            return {accessToken, refreshToken};
        });
    }).then((authTokens) => {
        res.header('x-refresh-token', authTokens.refreshToken)
            .header('x-access-token', authTokens.accessToken)
            .send(newUser);
    }).catch((e) => {
        res.status(400).send(e);
    })
})

/**
 * POST /users/login
 * Purpose Login
 */
app.post('/users/login', (req, res) => {
    let email = req.body.email;
    let password = req.body.password;

    User.findByCredentials(email, password).then((user) => {
        return user.createSession().then((refreshToken) => {
            // Session created successfully - refreshToken returned.
            // now we generate an access auth token for the user.
            return user.generateAccessAuthToken().then((accessToken) => {
                // access auth token generated successfully, now we return an object containing the auth tokens
                return {accessToken, refreshToken};
            });
        }).then((authTokens) =>{
            res.header('x-refresh-token', authTokens.refreshToken)
                .header('x-access-token', authTokens.accessToken)
                .send(user);
        })
    }).catch((e) => {
        res.status(400).send(e);
    });
})


/**
 * GET /users/me/access-token
 * Purpose generates and returns an access token
 */
app.get('/users/me/access-token', verifySession, (req, res) => {
    // we know that the user caller is authenticated and we have the user_id and user object available to us.
    req.userObject.generateAccessAuthToken().then((accessToken) => {
        res.header ('x-access-token', accessToken).send({ accessToken });
    }).catch((e) => {
        res.status(400).send(e);
    });
})
// checking to see our application is made.
app.listen(3000, () => console.log('Server is connected on Port 3000'));