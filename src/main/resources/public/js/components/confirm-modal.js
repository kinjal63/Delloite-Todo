import React, { Component } from 'react'
import {connect} from 'react-redux';
import { Button, Header, Image, Modal, Form } from 'semantic-ui-react'
import { removeToDo } from '../action/actionTodo';
import '../../css/ToDoModal.css';

class ConfirmModal extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id: props.id,
            title: props.title
        };

        this.onDeleteToDo = this.onDeleteToDo.bind(this);
        this.triggerModal = this.triggerModal.bind(this);
    }
    
    onDeleteToDo(id, title) { 
        return e => {
            console.log(this.props);
            this.props.removeToDo(id,
                (isSuccess) => {
                    if(!isSuccess)
                        alert('Error in removing todo. Please try again');
                })
            this.setState({open: false})
        }
    }

    triggerModal() {
        this.setState({open: true});
    }

    render() {
        return (
                <Modal open={this.state.open} style={{width: '50%'}}
                    trigger={
                        <div style={{width: '20px'}}>
                            <i className="large trash middle aligned icon"
                                onClick={this.triggerModal}
                                ></i>
                        </div>
                    }>
                    
                <Modal.Header>Confirm Delete</Modal.Header>
                <Modal.Content image>
                    <div>Are you sure you wish to delete {this.state.title}</div>
                    <div className="modal-button">
                        <button style={{backgroundColor:'#00b5ad', padding: '10px', fontWeight: 'bold',
                                color: 'white', borderRadius: '5px', border: 'none'}} 
                               onClick={this.onDeleteToDo(this.state.id, this.state.title)}>
                               Delete</button>
                        <button style={{backgroundColor:'#00b5ad', marginLeft: '10px', padding: '10px', fontWeight: 'bold',
                                color: 'white', borderRadius: '5px', border: 'none'}} 
                               onClick={() => {this.setState({open: false})}}>Cancel</button>
                        {/* <Button color='teal' fluid size='large' onClick={this.saveTodo}>Delete</Button>
                        <Button color='teal' fluid size='large' onClick={this.saveTodo}>Cancel</Button> */}
                    </div>
                </Modal.Content>
                </Modal>
        )
    }
}

const mapStateToProps = (state) => {
    return state;
}
export default connect(mapStateToProps, {removeToDo})(ConfirmModal);
