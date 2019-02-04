import {combineReducers} from 'redux';
import {TYPE_ADD_TODO, TYPE_UPDATE_TODO, TYPE_REMOVE_TODO, TYPE_USER, TYPE_CURRENT_LOGGED_IN_USER} from "../utils/constants";

export const todos = (oldTodos=[], action) => {
	if(oldTodos && action.type === TYPE_ADD_TODO) {
		return [...oldTodos, action.data];
	}
	else if(oldTodos && action.type === TYPE_UPDATE_TODO) {
		oldTodos.map(todo => {
			if(todo.id == action.data.id) {
				todo.title = action.data.title;
				todo.description = action.data.description;
				todo.updatedAt = action.data.updatedAt,
				todo.isChecked = action.data.isChecked;
			}
		})
		return [...oldTodos];
	}
	else if(oldTodos && action.type === TYPE_REMOVE_TODO) {
		var updatedTodos = oldTodos.filter((todo)=> {
			return todo.id != action.data;
		})
		return updatedTodos;
	} 

	return oldTodos;
}

const users = (oldUsers=[], action) => {
	if(oldUsers && action.type === TYPE_USER) {
		return [...oldUsers, action.data];
	}
	return oldUsers;
}

const currentLoggedInUser = (oldLoggedInUser=[], action) => {
	if(oldLoggedInUser && action.type == TYPE_CURRENT_LOGGED_IN_USER) {
		return oldLoggedInUser;
	}
	return oldLoggedInUser;
}

export default combineReducers({
	todoList: todos,
	userList: users,
	currentLoggedInUser: currentLoggedInUser
})

