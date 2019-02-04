import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import {Provider} from 'react-redux';
import {createStore, applyMiddleware} from 'redux';
import thunk from 'redux-thunk';
import combineReducers from '../reducer/reducer';
import ToDoList from './todo-list';
import LoginForm from './login';


const App = () => {
	console.log('rendered App Component');

	var store = createStore(combineReducers, {}, applyMiddleware(thunk));

	return (
		<Provider store={store}>
			<LoginForm 	/>
			<div style={{margin: '10px'}}>
				{/* <ToDoList /> */}
			</div>
		</Provider>
	)
}

ReactDOM.render(
	<App/>,
	document.getElementById('root')
)

