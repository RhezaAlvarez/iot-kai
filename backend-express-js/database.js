// database.js

const mongoose = require('mongoose')
const URL_MONGODB = 'mongodb://localhost:27017/db_lokomotif'

mongoose.connect(URL_MONGODB)
    .then(() => {
        console.log('Connected to ' + URL_MONGODB)
    })
    .catch((error) => {
        console.log('Database connection error: ' + error)
    })

module.exports = mongoose