import { TYPE_USER, TYPE_CURRENT_LOGGED_IN_USER, setToken } from "../utils/constants";
import axios from 'axios';
import { USER_LOGIN_FAIL } from "../utils/responseCode";

const headers = {
    'Content-Type': 'application/json'
}

export const loginUser = (userObj, callback) => { 
    return (dispatch) => {
        var data = {
            username: userObj.username,
            password: userObj.password
        }
        axios.post('/user/login', data, headers).then(response => {
            console.log(response);
            if(response.data.resCode == USER_LOGIN_FAIL) {
                alert('Your username or password is incorrect');
            }
            else {
                setToken(response.data.authToken);
                callback();

                dispatch({
                    type: TYPE_USER,
                    data: userObj
                });
            }
        }).catch(error => {
            if(error.data.status == 401) {
                console.log('Unauthorized');
            }
            console.log(error);
        })
    }
}

export const currentUserLoggedIn = (userObj) => { 
    return async (dispatch, userObj) => {
        dispatch({
            type: TYPE_CURRENT_LOGGED_IN_USER,
            data: userObj
        });
    }
}

