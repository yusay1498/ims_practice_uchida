import './css/App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Attendance from "./Attendance";
import Login from "./Login";
import TopPage from "./TopPage";

function App() {
  return (
    <BrowserRouter>
      <div>
        <Routes>
            <Route path="" element={<Login/>}></Route>
            <Route path="/attendance" element={<Attendance/>}></Route>
            <Route path="/topPage" element={<TopPage></TopPage>}></Route>
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
