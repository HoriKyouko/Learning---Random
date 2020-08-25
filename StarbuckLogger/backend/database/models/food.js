const mongoose = require('mongoose');

const FoodSchema = new mongoose.Schema({
    _userId: {
        type: mongoose.Types.ObjectId,
        required: true
    },
    foodName: {
        type: String,
        trim: true,
        minlength: 1,
        required: true
    },
    price: {
        type: Number,
        required: true
    },
    review: {
        type: String,
        trim: true
    }
});

const Food = mongoose.model('Food', FoodSchema);

module.exports = Food;