// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import { getFirestore, collection, getDocs } from "firebase/firestore"
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
    apiKey: "AIzaSyCWz0Ne438zMogra_p3jep8ReCN7vnC5HM",
    authDomain: "yarnpra.firebaseapp.com",
    projectId: "yarnpra",
    storageBucket: "yarnpra.appspot.com",
    messagingSenderId: "492593614165",
    appId: "1:492593614165:web:0f42d78dc18eb269cd33dc",
    measurementId: "G-N4YRHTZ1KL"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);
const db = getFirestore(app);

async function getCities(db) {
    const citiesCol = collection(db, 'cities');
    const citySnapshot = await getDocs(citiesCol);
    const cityList = citySnapshot.docs.map(doc => doc.data());
    return cityList;
}