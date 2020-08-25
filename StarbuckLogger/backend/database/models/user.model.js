const mongoose = require('mongoose');
const _ = require('lodash');
const jwt = require('jsonwebtoken');
const crypto = require('crypto');
const bcrypt = require('bcryptjs');

// JWT Secret
const jwtSecret = "ThisIstemporaryUntilIunderstandsuperTokensio";

const UserSchema = new mongoose.Schema({
    email: {
        type: String,
        required: true,
        minlength: 1,
        trim: true,
        unique: true
    },
    password: {
        type: String,
        required: true,
        minlength: 8
    },
    sessions: [{
        token: {
            type: String,
            required: true
        },
        expiresAt: {
            type: Number,
            required: true
        }
    }]
});

/* Instance Methods */

UserSchema.methods.toJSON = function(){
    const user = this;
    const userObject = user.toObject();

    return _.omit(userObject, ['password', 'sessions']);
}

UserSchema.methods.generateAccessAuthToken = function() {
    const user = this;
    //console.log(user + '\n');
    return new Promise((resolve, reject) => {
        jwt.sign({_id: user._id.toHexString()}, jwtSecret, {expiresIn: "15m"}, (err, token) => {
            if(!err){
                resolve(token);
            }
            else{
                reject();
            }
        })
    })
}

UserSchema.methods.generateRefreshAuthToken = function() {
    // This method simply generates as 64 byte hex string - it doesn't save it to the database. saveSessionToDatabase() does that.
    return new Promise((resolve, reject) => {
        crypto.randomBytes(64, (err, buffer) => {
            if(!err){
                let token = buffer.toString('hex');
                return resolve(token);
            }
            else{
                console.log(err);
                reject();
            }
        })
    })
}

UserSchema.methods.createSession = function() {
    let user = this;
    return user.generateRefreshAuthToken().then((refreshToken) => {
        return saveSessionToDatabase(user, refreshToken);
    }).then((refreshToken) => {
        return refreshToken;
    }).catch((e) => {
        return Promise.reject("Failed to save session to database.\n", + e);
    })
}

/* Model Methods */
UserSchema.statics.getJWTSecret = () => {
    return jwtSecret;
}

UserSchema.statics.findByIdAndToken = function(_id, token) {
    const user = this;
    return user.findOne({
        _id,
        'session.token': token
    })
}

UserSchema.statics.findByCredentials = function(email, password) {
    let user = this;
    return user.findOne({ email }).then((user) => {
        if(!user){
            return Promise.reject();
        }
        else{
            return new Promise((resolve, reject) => {
                bcrypt.compare(password, user.password, (err, res) => {
                    if(res){
                        resolve(user);
                    }
                    else{
                        reject();
                    }
                })
            })
        }
    })
}

UserSchema.statics.hasRefreshedTokenExpired = (expiresAt) => {
    let secondsSinceEpoch = Date.now() / 1000;

    return (expiresAt > secondsSinceEpoch) ? false : true;
}

UserSchema.pre('save', function(next){
    let user = this;
    let costFactor = 10;
    if(user.isModified('password')){
        bcrypt.genSalt(costFactor, (err, salt) => {
            if(salt){
                bcrypt.hash(user.password, salt, (err, hash) => {
                    if(hash){
                        user.password = hash;
                        next(); 
                    }
                    else{
                        console.log(err);
                    }
                })
            }
            else{
                console.log(err);
            }
        })
    }
    else{
        next();
    }
})

/* Helper Methods */

let saveSessionToDatabase = (user, refreshToken) => {
    return new Promise((resolve, reject) => {
        let expiresAt = generateRefreshTokenExpiryTime();
        user.sessions.push({'token': refreshToken, expiresAt});

        user.save().then(() => {
            return resolve(refreshToken);
        }).catch((e) => {
            reject(e);
        })
    })
}

let generateRefreshTokenExpiryTime = () => {
    let daysUntilExpire = '10';
    let secondUntilExpire = ((daysUntilExpire * 24) * 60) * 60;
    return ((Date.now() / 1000) + secondUntilExpire);
}

const User = mongoose.model('User', UserSchema);

module.exports = User;