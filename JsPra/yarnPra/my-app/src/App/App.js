import logo from '../logo.svg';
import '../css/App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {AuthProvider} from "./AuthService";
import Login from "./Pages/Login";
import LoggedInRoute from "./LoggedInRoute";

function App() {
  return (
    <BrowserRouter>
      <AuthProvider>
        <Routes>
            <LoggedInRoute path='/' />
            <Route path='/login' element={Login} />
        </Routes>
      </AuthProvider>
    </BrowserRouter>
  );
}

export default App;
