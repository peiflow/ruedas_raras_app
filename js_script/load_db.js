// In your index.js
const firestoreService = require('firestore-export-import');
const serviceAccount = require('./credentials.json');
const databaseURL = "https://leafy-caster-238617.firebaseio.com"
const filepath = "D:/export.json" 
// Initiate Firebase App
firestoreService.initializeApp(serviceAccount, databaseURL);
 
// Start importing your data
// The array of date and location fields are optional
firestoreService.restore(filepath);