// index.js
const mongoose  = require("./database");
const { produce } = require("./kafka-producer")
const { consume } = require("./kafka-consumer")
const express = require('express');
const bodyParser = require("body-parser");
const { Kafka } = require("kafkajs");

const port = 3000
const app = express()
app.get('/test', (req, res) => res.send("Port"))
app.listen(port, () => console.log('Hello World Manual berjalan di http://localhost:', port))

app.use(bodyParser.json())

const kafka = new Kafka({
    clientId: 'lokomotif-scheduler',
    brokers: ['localhost:9092']
})

app.post("/send-lokomotif-to-kafka", (req, res) => {
    console.log('=====================================================  =========================================================');
    const receivedData = req.body
    console.log("MessageReceivedOnIndexJS", receivedData)
    produce('lokomotif', receivedData)
    // consume('lokomotif')
    res.send('Data berhasil dikirim ke Kafka'); //harus ada respond, kalau tidak, app.post ini tidak bisa dipanggil berulang kali (kasus ini berulang kali melalui @scheduled di springboot)
})

mongoose.connection.on("connected", () => {
    console.log("Db connected on index.js")
    // const db_lokomotif = mongoose.connection;
    // showMessage(db_lokomotif) 
    consume('lokomotif')
})

// const lokomotif = require('./database-schema')
// lokomotif.find()
//     .then(data => {
//         console.log(data)
//     })
//     .catch(error => {
//         console.log('Error: ' + error)
//     })

