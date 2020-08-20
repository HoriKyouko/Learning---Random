const mongoose = require('mongoose');

const DrinkSchema = new mongoose.Schema({
    _userId: {
        type: mongoose.Types.ObjectId,
        required: true
    },
    drink: {
        type: String,
        trim: true,
        minlength: 1,
        required: true
    },
    price: {
        type: Number,
        required: true
    },
    type: {
        type: String,
        trim: true,
        minlength: 1,
        required: true
    },
    size: {
        type: String,
        trim: true,
        minlength: 1,
        required: true
    }
});

const Drink = mongoose.model('Drink', DrinkSchema);

module.exports = Drink;