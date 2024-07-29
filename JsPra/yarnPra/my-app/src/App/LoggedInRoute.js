import {useContext} from "react";
import {AuthContext} from "./AuthService";
import {Route} from "react-router-dom";
import {Redirect} from "react-router";

const LoggedInRoute = ({component, Component, ...rest}) => {
    const user = useContext(AuthContext)

    return (
        <Route
            {...rest}
            render = {props =>
                user ? (
                    <Component {...props} />
                ) : (
                    <Redirect to={'login'} />
                )
            }
        />
    )
}

export default LoggedInRoute