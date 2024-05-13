// kafka-producer.js
const { Kafka } = require("kafkajs");

const kafka = new Kafka({
    clientId: 'lokomotif-scheduler',
    brokers: ['localhost:9092']
})

const producer = kafka.producer()
// producer.connect().then(() => console.log("Producer connected"))

const produce = async (topic, message) => {
    try {
        console.log("Message on Producer", message);
        await producer.connect()
        const result = await producer.send({
            topic: topic,
            messages: [{
                value: JSON.stringify(message)
            }]
        })
    } catch (err) {
        console.log("Error Producer: ", err)
    } finally {
        await producer.disconnect()
    }
}

module.exports = {
    produce
}