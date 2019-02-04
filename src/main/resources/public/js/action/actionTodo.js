import { TYPE_ADD_TODO, TYPE_UPDATE_TODO, TYPE_REMOVE_TODO, getUserId, getToken } from "../utils/constants";
import axios from 'axios';
import qs from 'qs';
import { TODO_CREATED, TODO_REMOVED, TODO_RETRIVED, TODO_UPDATED } from "../utils/responseCode";

export const addToDo = (toDoObj, callback) => {
    return dispatch => {
        var headers = {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+ getToken()
        }
        var data = {
            id: toDoObj.id,
            title: toDoObj.title,
            description: toDoObj.description,
            userId: getUserId()
        }
        var todo = axios.post('/todo/addTodo', data, {headers: headers}).then(response => {
            // console.log(response);
            if(response.data.resCode == TODO_CREATED) {
                toDoObj.id = response.data.id;
                toDoObj.updatedAt = 'updated 1 sec ago';
                dispatch({ 
                    type: TYPE_ADD_TODO,
                    data: toDoObj
                })
                callback(true);
            }
            else {
                alert(response.data.status);
                callback(false);
            }
        });        
    }
}

export const updateToDo = (toDoObj, callback) => {
    return dispatch => {
        var headers = {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer '+ getToken()
        }
        var data = {
            id: toDoObj.id,
            title: toDoObj.title,
            description: toDoObj.description,
            isChecked: toDoObj.isChecked,
            userId: getUserId()
        }
        var todo = axios.post('/todo/updateTodo', data, {headers: headers}).then(response => {
            // console.log(response);
            if(response.data.resCode == TODO_UPDATED) {
                toDoObj.updatedAt = 'updated 1 sec ago';

                dispatch({ 
                    type: TYPE_UPDATE_TODO,
                    data: toDoObj
                })
                callback(true);
            }
            else {
                alert(response.data.status);
                callback(false);
            }
        });        
    }
}

export const removeToDo = (id, callback) => {
    return dispatch => {
        var headers = {
            'Content-Type': 'application/x-www-form-urlencoded',
            'Authorization': 'Bearer '+ getToken()
        }
        var data = {
            todo_id: id
        }
        var todo = axios.post('/todo/removeTodo', qs.stringify(data), {headers: headers}).then(response => {
            if(response.data.resCode == TODO_REMOVED) {
                dispatch({
                    type: TYPE_REMOVE_TODO,
                    data: id
                })
                callback(true);
            }
            else {
                alert(response.data.status);
                callback(false);
            }
        });
    }
}

export const getTodos = (callback) => {
    return dispatch => {
        var headers = {
            'Authorization': 'Bearer '+ getToken()
        }
        var todo = axios.get('/todo/getAll', {headers: headers}).then(response => {
            console.log(response);
            // response = JSON.parse(response);
            if(response.data.resCode == TODO_RETRIVED) {
                response.data.todos.forEach(todo => {
                    var today = new Date();
                    var todoDate = new Date(todo.updatedAt);
                    var diffMs = (today - todoDate); // milliseconds between now & Christmas
                    var diffDays = Math.floor(diffMs / 86400000); // days
                    var diffHrs = Math.floor((diffMs % 86400000) / 3600000); // hours
                    var diffMins = Math.round(((diffMs % 86400000) % 3600000) / 60000);

                    var str = 'updated ';
                    if(diffDays > 0) str += diffDays + ' days ';
                    if(diffHrs > 0)  str += diffHrs + ' hours ';
                    if(diffMins > 0)  str += diffMins + ' minutes ';
                    if(diffHrs == 0 && diffMins == 0 && diffDays == 0) str += '1 second '
                    str += 'ago';
                    todo.updatedAt = str;
                    todo.isChecked = todo.checked;
                    
                    dispatch({
                        type: TYPE_ADD_TODO,
                        data: todo
                    })
                });
                callback(true);
            }
            else {
                alert(response.data.status);
                callback(false);
            }
        }).catch(error => {
            console.log(error);
        });
    }
}