// database-schema.js
const mongoose = require('mongoose');

const lokomotifsColletionsSchema = new mongoose.Schema({
  kode_lokomotif: String,
  nama_lokomotif: String,
  dimensi_lokomotif: {panjang: String, lebar: String, tinggi: String},
  status_lokomotif: String,
  waktu_lokomotif: String
});

const Lokomotif = mongoose.model('Lokomotif', lokomotifsColletionsSchema);

module.exports = {
  Lokomotif
}