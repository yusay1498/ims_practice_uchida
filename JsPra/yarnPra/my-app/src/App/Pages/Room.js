import {useEffect, useState} from "react";
import firebase from "../config/firebase";

const Room = () => {
    const [messages, setMessages] = useState(null)
    const [value, setValue] = useState('')

    useEffect(() => {
        firebase.firestore()
    }, []);
}