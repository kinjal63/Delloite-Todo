export var TYPE_ADD_TODO = 'TYPE_ADD_TODO';
export var TYPE_UPDATE_TODO = 'TYPE_UPDATE_TODO';
export var TYPE_REMOVE_TODO = 'TYPE_REMOVE_TODO';
export var TYPE_USER = 'TYPE_USER';
export var TYPE_CURRENT_LOGGED_IN_USER = 'TYPE_CURRENT_LOGGED_IN_USER';

var data = {}

export const setUserId=(userId) => {
    data["userId"] = userId;
    console.log(userId);
}

export const setToken=(token) => {
    data["token"] = token;
    console.log(token);
}

export const getUserId=() => {
    return data.userId;
}

export const getToken=() => {
    // return 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ0b2RvXzEiLCJleHAiOjE1NDkyNzQ2MjN9.YZe2yUzqZoX3Y706P62UR19qQhPpcZ9m1bw8Ht4oagE';
    return data.token;
}