// In your index.js 
const firestoreService = require('firestore-export-import');
const serviceAccount = require('./credentials.json');
 const databaseURL = "https://leafy-caster-238617.firebaseio.com"
// Initiate Firebase App
firestoreService.initializeApp(serviceAccount, databaseURL);
 
// Start exporting your data
firestoreService
  .backup('events')
  .then(data => console.log(JSON.stringify(data)))