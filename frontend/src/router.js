
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import MessageMessageManager from "./components/listers/MessageMessageCards"
import MessageMessageDetail from "./components/listers/MessageMessageDetail"

import ValidatorValidatorManager from "./components/listers/ValidatorValidatorCards"
import ValidatorValidatorDetail from "./components/listers/ValidatorValidatorDetail"

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
                path: '/validators/validators',
                name: 'ValidatorValidatorManager',
                component: ValidatorValidatorManager
            },
            {
                path: '/validators/validators/:id',
                name: 'ValidatorValidatorDetail',
                component: ValidatorValidatorDetail
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
