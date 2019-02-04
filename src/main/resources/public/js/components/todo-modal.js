import React, { Component } from 'react'
import {connect} from 'react-redux';
import { Button, Header, Image, Modal, Form } from 'semantic-ui-react'
import { addToDo, updateToDo } from '../action/actionTodo';
import '../../css/ToDoModal.css';

class ToDoModal extends Component {
    constructor(props) {
        super(props);
        this.state = {
            open: this.props.showModal,
            update: this.props.update,
            id: this.props.todo ? this.props.todo.id : undefined,
            title: this.props.todo ? this.props.todo.title : undefined,
            description: this.props.todo ? this.props.todo.description : undefined,
            isChecked: this.props.todo ? this.props.todo.isChecked : undefined,
        };

        this.handleTitleChange = this.handleTitleChange.bind(this);
        this.handleDescriptionChange = this.handleDescriptionChange.bind(this);
        this.saveTodo = this.saveTodo.bind(this);
        this.triggerModal = this.triggerModal.bind(this);
    }
    
    componentWillReceiveProps(props) {
        console.log('component will receive props' + props.showModal);
        this.setState({open: props.showModal})
    }

    handleTitleChange(e) {
        if(!e.target.value) {
            return <div>Please enter title</div>
        }
        this.setState({title: e.target.value})
    }

    handleDescriptionChange(e) {
        this.setState({description: e.target.value})
    }

    saveTodo() {
        if(!this.state.update && this.state.title && this.state.description) {
            this.props.addToDo({
                id: this.state.id,
                title: this.state.title,
                description: this.state.description
            }, (isSuccess) => {
                this.setState({open: false})
                if(!isSuccess)
                    alert('error in creating todo. Please try again')
            })
        }
        else if(this.state.update && this.state.title && this.state.description) {
            this.props.updateToDo({
                id: this.state.id,
                title: this.state.title,
                description: this.state.description,
                isChecked: this.state.isChecked
            }, (isSuccess) => {
                this.setState({open: false})
                if(!isSuccess)
                    alert('error in updating todo. Please try again')
            })
        }
    }

    showError() {
        if(!this.state.title) {
            return <div>Please enter title</div>
        }
        else if(!this.state.description) {
            return <div>Please enter description</div>
        }
    }

    triggerModal() {
        this.setState({open: true});
    }

    render() {
        let content;
        let title;
        if(this.state.update) {
                content=<div style={{width: '20px'}}>
                            <i className="large edit middle aligned icon"
                                onClick={this.triggerModal}>
                            </i>
                        </div>
                let t = this.props.todo.title;
                title="Update task " + t;
        }
        else {
            content = <div style={{width: 'max-content', margin: '0 auto'}}>
                        <Button color='teal' fluid size='large' onClick={this.triggerModal}>Create New ToDo</Button>
                    </div>
            title='Create a Todo';
        }
        return (
                <Modal open={this.state.open} style={{width: '50%'}} 
                    trigger={content}>
                <Modal.Header>{title}</Modal.Header>
                <Modal.Content image>
                    <Form.Input className="margin-bottom-10" fluid label='Title' 
                        onChange={this.handleTitleChange} placeholder='Enter Title' value={this.state.title} />
                    {/* {!this.state.title && <div>Please enter title</div>} */}
                    <Form.Input fluid label='Description' placeholder='Enter description'
                        onChange={this.handleDescriptionChange} value={this.state.description} />
                    {/* {!this.state.description && <div>Please enter description</div>} */}
                    <div className="save-button">
                        <Button color='teal' fluid size='large' onClick={this.saveTodo}>Save</Button>
                    </div>
                </Modal.Content>
                </Modal>
        )
    }
}

const mapStateToProps = (state) => {
    return state;
}
export default connect(mapStateToProps, {addToDo, updateToDo})(ToDoModal);
