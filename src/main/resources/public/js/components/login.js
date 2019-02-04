import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Button, Form, Grid, Header, Image, Message, Segment } from 'semantic-ui-react';
import ToDoList from './todo-list';
import {loginUser} from '../action/actionUser';
import { setToken } from '../utils/constants';
import CryptoJS from 'crypto-js';

class LoginForm extends Component {
    constructor(props) {
        super(props);
        this.state={isLoggedIn: false};

        this.onLoginClick = this.onLoginClick.bind(this);
        this.handleUserName = this.handleUserName.bind(this);
        this.handlePassword = this.handlePassword.bind(this);

        this.keySize = 128;
        this.ivSize = 128;
        this.iterations = 100;
        
    }

    


    onLoginClick() {
        var secret = "Kinjal_Todo_Delloite";
        var salt = CryptoJS.lib.WordArray.random(128/8);
  
        var key = CryptoJS.PBKDF2(secret, salt, {
            keySize: this.keySize/16,
            iterations: this.iterations
            });

        var iv = CryptoJS.lib.WordArray.random(128/8);
        
        var encrypted = CryptoJS.AES.encrypt(this.state.password, key, { 
            iv: iv, 
            padding: CryptoJS.pad.Pkcs7,
            mode: CryptoJS.mode.CBC
            
        });

        // var encrypted = CryptoJS.AES.encrypt(this.state.password, secret);
        encrypted = encrypted.toString();

        this.props.loginUser({
            username: this.state.username,
            password: this.state.password
        }, isLoggedIn => {
            this.setState({isLoggedIn: true});
        })
    }

    handleUserName(e) {
        this.setState({username: e.target.value})
    }

    handlePassword(e) {
        this.setState({password: e.target.value})
    }

    render() {
        var page = undefined;

        if(!this.state.isLoggedIn) {
            page =  <div className='login-form'>
                    <style>{`
                        body > div,
                        body > div > div,
                        body > div > div > div.login-form {
                            height: 100%;
                        }
                    `}</style>
                            <Grid textAlign='center' style={{ height: '100%' }} verticalAlign='middle'>
                                <Grid.Column style={{ maxWidth: 450 }}>
                                    <Header as='h2' color='teal' textAlign='center'>
                                        Log-in with your credentials
                                    </Header>
                                    <Form size='large'>
                                        <Segment stacked>
                                            <Form.Input fluid icon='user' iconPosition='left' 
                                                placeholder='Username' onChange={this.handleUserName} />
                                            <Form.Input
                                                fluid icon='lock' iconPosition='left'
                                                placeholder='Password'
                                                type='password' onChange={this.handlePassword}
                                            />
                                            <Button color='teal' fluid size='large' onClick={this.onLoginClick}>
                                                Login
                                    </Button>
                                        </Segment>
                                    </Form>
                        </Grid.Column>
                    </Grid>
                </div>
        }
        else {
            page = <ToDoList />
        }
        return page;
    }
}

const mapStateToProps = (state) =>{
    return state;
}

export default connect(mapStateToProps, {loginUser})(LoginForm);