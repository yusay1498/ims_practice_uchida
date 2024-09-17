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

    return (
        // 仮で用意したけど他の処理とかがないと全然面白くないので
        // fetchだけ確認して一旦放置することにした
        <div>
            {attendances !== null ? (
                <div>
                    {attendances.length !== 0 ? (
                        attendances.map((item) => (
                            <div key={item.id}>
                                {item.employeeId} :
                                {item.beginWork} :
                                {item.finishWork}
                            </div>
                        ))
                    ) : (
                        <p>勤怠が登録されていません</p>
                    )}
                </div>
            ) : (
                <div>
                    読込中
                </div>
            )}
        </div>
    )
}

export default Attendance