import React, {Component} from 'react';
import {connect} from 'react-redux';
import {todos} from '../reducer/reducer';
import { addToDo, updateToDo, removeToDo, getTodos } from '../action/actionTodo';
import { Button } from 'semantic-ui-react';
import ToDoModal from './todo-modal';
import '../../css/todo.css';
import { confirmAlert } from 'react-confirm-alert';
import ConfirmModal from './confirm-modal';

class ToDoList extends Component {
    constructor(props) {
        super(props);
        this.state = {showModal: false};

        this.onCreateToDo = this.onCreateToDo.bind(this);
        this.isChecked = this.isChecked.bind(this);
        this.onDeleteToDo = this.onDeleteToDo.bind(this);
    }

    componentDidMount() {
        this.getTodos();       
    }

    getTodos() {
        this.props.getTodos((isSuccess) => {
            console.log(isSuccess);
        })
    }

    isChecked(id, title, description) {
        return e => {
            console.log('todo isChecked');
            this.props.updateToDo({
                id: id,
                title: title,
                description: description,
                isChecked: e.target.checked
            }, (isSuccess) => {
                this.setState({open: false})
                if(!isSuccess)
                    alert('error in updating todo. Please try again')
            })
        }
    }

    onDeleteToDo(id, title) { 
        return e => {
            if (window.confirm('Are you sure you wish to delete this item?')) {
                () => this.props.removeToDo(id,
                (isSuccess) => {
                    if(!isSuccess)
                        alert('Error in removing todo. Please try again');
                })
            }
        }
    }

    renderTodoList() {
        var list = this.props.todoList.map((todo) => {
            console.log(todo.title);
            return (
                <div key={todo.id} className="ui relaxed divided list">
                    <div className="item" style={{margin: '8px'}}>
                        <div className="ui checkbox" style={{float: 'left', margin: '8px'}}>
                            <input type="checkbox" name="todo" onChange={this.isChecked(todo.id, todo.title, todo.description)}
                            checked={todo.isChecked} /><label></label>                          
                        </div>
                        <div className="content" style={{float: 'left'}}>
                            <a className="header">{todo.title}</a>
                            <div className="description">{todo.updatedAt}</div>
                        </div>
                        <div style={{float: 'right', margin: '8px'}}>
                            <ConfirmModal id={todo.id} title={todo.title}/>
                            {/* <i className="large trash middle aligned icon"
                                onClick={this.onDeleteToDo(todo.id, todo.title)} 
                                ></i> */}
                        </div>
                        <div style={{float: 'right', margin: '8px'}}>
                            <ToDoModal open={this.state.showModal} update="true" todo={todo} />
                        </div>
                    </div>
                </div>
            )
        });        
        
        console.log(list);
        return list;
    }

    // onClick={this.onDeleteToDo(todo.id, todo.title)}
    onCreateToDo() {
        this.setState({showModal: true});
    }

    render() {
        return (
            <div>
                {/* <div style={{width: 'max-content', margin: '0 auto'}}>
                    <Button color='teal' fluid size='large' onClick={this.onCreateToDo}>Create New ToDo</Button>
                </div> */}
                <div style={{width: 'max-content', margin: '0 auto'}}>
                    <ToDoModal open={this.state.showModal} />
                </div>
                
                {this.props.todoList.length == 0 && <div className="todo-create">No Todos are created yet</div>}

                {this.props.todoList.length > 0 && <div style={{border: '2px solid green', 'borderRadius': '20px', margin: '20px auto', width: '40%'}}>
                    {this.renderTodoList()}
                </div>}
                {/* <div style={{width: 'max-content', margin: '0 auto'}}>
                    <Button >Save</Button>
                </div> */}
            </div>
            )
    }
}

const mapStateToProps = (state) => {
    return state;
}

const mapDispatchToProps = (dispatch) => {
    addToDo: () => dispatch({type: TYPE_ADD_TODO, data:'123'}),
    removeToDo
}
export default connect(mapStateToProps, {addToDo, updateToDo, removeToDo, getTodos})(ToDoList);