// access to express
const express = require('express');
// creates an application using express
const app = express();
// access to our database
const mongoose = require('./database/mongoose');
const { User } = require('./database/models');

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

app.use((req, res, next) => {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Methods", "GET, POST, HEAD, OPTIONS, PUT, PATCH, DELETE");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, x-access-token, x-refresh-token, _id");

    res.header("Access-Control-Expose-Headers", "x-access-token, x-refresh-token");
    next();
});

/* User Routes */

/**
 * POST /users
 * Purpose: Sign up users
 */
app.post('/users', (req, res) => {
    let body = req.body;
    let newUser = new User(body);

    newUser.save().then(() => {
        return newUser.createSession();
    }).then((refreshToken) => {
        return newUser.generateAccessAuthToken().then((accessToken) =>{
            return {accessToken, refreshToken};
        });
    }).then((authToken) => {
        res.header('x-refresh-token', authToken.refreshToken)
            .header('x-access-token', authToken.accessToken)
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
    let email = res.body.email;
    let password = res.body.password;
    User.findByCredentials(email, password).then((user) => {
        return user.createSession().then((refreshToken) => {
            return new user.generateAccessAuthToken().then((accessToken) => {
                return {accessToken, refreshToken};
            });
        }).then((authToken) => {
            res.header('x-refresh-token', authToken.refreshToken)
                .header('x-access-token', authToken.accessToken)
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
    req.userObject.generateAccessAuthToken().then((accessToken) => {
        res.header('x-access-token', accessToken).send({accessToken});
    }).catch((e) => {
        res.status(400).send(e);
    })
})
app.listen(3000, () => console.log('Server is connected on Port 3000'));