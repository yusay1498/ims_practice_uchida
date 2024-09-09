import {useEffect, useState} from "react";

const Attendance = () => {
    const [attendances, setAttendances] = useState(null)

    useEffect(() => {
        const loadAttendance = async () => {
            try {
                const response = await fetch("/attendances")
                if (response.ok) {
                    const data = await response.json();
                    setAttendances(data)
                } else {
                    const data = await response.text();
                    console.error(response.status, data)
                }
            } catch (error) {
                console.error(error)
            }
        }
        loadAttendance()
    }, [])

    console.log(attendances)
    return (
        <div>
            確認
        </div>
    )
}

export default Attendance