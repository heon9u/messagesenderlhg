
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import MessageMessageManager from "./components/listers/MessageMessageCards"
import MessageMessageDetail from "./components/listers/MessageMessageDetail"

import ValidateMessageValidatorManager from "./components/listers/ValidateMessageValidatorCards"
import ValidateMessageValidatorDetail from "./components/listers/ValidateMessageValidatorDetail"

import DeviceDeviceManager from "./components/listers/DeviceDeviceCards"
import DeviceDeviceDetail from "./components/listers/DeviceDeviceDetail"



export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/messages/messages',
                name: 'MessageMessageManager',
                component: MessageMessageManager
            },
            {
                path: '/messages/messages/:id',
                name: 'MessageMessageDetail',
                component: MessageMessageDetail
            },

            {
                path: '/validateMessages/validators',
                name: 'ValidateMessageValidatorManager',
                component: ValidateMessageValidatorManager
            },
            {
                path: '/validateMessages/validators/:id',
                name: 'ValidateMessageValidatorDetail',
                component: ValidateMessageValidatorDetail
            },

            {
                path: '/devices/devices',
                name: 'DeviceDeviceManager',
                component: DeviceDeviceManager
            },
            {
                path: '/devices/devices/:id',
                name: 'DeviceDeviceDetail',
                component: DeviceDeviceDetail
            },




    ]
})
