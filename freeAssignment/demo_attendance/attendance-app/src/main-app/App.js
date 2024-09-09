import './css/App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Attendance from "./Attendance";

function App() {
  return (
    <BrowserRouter>
      <div>
        <Routes>
            <Route path="/attendance" element={<Attendance/>}></Route>
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
