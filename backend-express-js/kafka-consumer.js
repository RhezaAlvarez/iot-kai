// kafka-consumer.js

const { Kafka } = require("kafkajs");
const mongoose = require('./database')
const { Lokomotif } = require('./database-schema')

const kafka = new Kafka({
    clientId: 'lokomotif-scheduler',
    brokers: ['localhost:9092']
})

const consumer = kafka.consumer({ groupId: 'lokomotif-group' })
// consumer.connect().then(() => console.log("Consumer connected"))
// const db_lokomotif = mongoose.connection;
const consume = async (topic) => {
    try {
        await consumer.connect()
        await consumer.subscribe({ topic: topic })
        await consumer.run({
            eachMessage: async ({ message }) => {
                try {
                    dataFromProducer = JSON.parse(message.value.toString())
                    console.log("Message on Consumer", dataFromProducer)
                    for(const item of dataFromProducer){
                        const {kodeLokomotif, namaLokomotif, dimensiLokomotif, status, waktu} = item

                        const lokomotif = new Lokomotif({
                            kodeLokomotif: kodeLokomotif,
                            namaLokomotif: namaLokomotif,
                            dimensiLokomotif: {
                                panjang: dimensiLokomotif.panjang,
                                lebar: dimensiLokomotif.lebar,
                                tinggi: dimensiLokomotif.tinggi
                            },
                            status: status,
                            waktu: waktu
                        })
                        console.log("Lokomotif", JSON.stringify(lokomotif))
                        await lokomotif.save()
                    }
                } catch (err) {
                    console.log("Error parsing data from Producer: ", err)
                }
            }
        })
    } catch (err) {
        console.log("Error Consumer: ", err)
    }
    // CONSUMER JANGAN DI DISCONNECT seperti Producer, atau nanti fungsi run nya tidak akan bisa berjalan 
    /* finally {
         await consumer.disconnect() 
     } */

    // db.on(async () => {
    //     console.log("Koneksi db berhasil pada kafka-consumer")
    
    //     consumer.on('message', async function (message) {
    //         console.log('Menerima pesan Kafka: ', message)
    //         const data = JSON.parse(message.value)
        
    //         // simpan to mongodb
    //         const lokomotifs_collection = db_lokomotif.collection("lokomotifs")
    //         try{
    //             console.log("Data dari springboot: ", data)
    //         }
    //         catch (err){
    //             console.log("Error integrasi dari springboot: ", err);
    //         }
    //     })
    // })

    // db.once('open', async () => {
    //     console.log("Koneksi db berhasil pada kafka-consumer")
    
    //     consumer.on('message', async function (message) {
    //         console.log('Menerima pesan Kafka: ', message)
    //         const data = JSON.parse(message.value)
        
    //         // simpan to mongodb
    //         const lokomotifs_collection = db_lokomotif.collection("lokomotifs")
    //         try{
    //             console.log("Data dari springboot: ", data)
    //         }
    //         catch (err){
    //             console.log("Error integrasi dari springboot: ", err);
    //         }
    //     })
    // })
}

module.exports = {
    consume
}
