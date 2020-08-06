// This file will handle connection logic to MongoDB database.
const mongoose = require('mongoose');

mongoose.Promise = global.Promise;

                //call to db  localdb       name of db
mongoose.connect('mongodb://localhost:27017/taskmanager', {useCreateIndex: true, useNewUrlParser: true, useUnifiedTopology: true, useFindAndModify: false})
    .then(() => console.log('Database is connected'))
    .catch((error) => {
        console.log("Error while attempting to connect to MongoDB");
        console.log(error)        
    });

module.exports = mongoose;