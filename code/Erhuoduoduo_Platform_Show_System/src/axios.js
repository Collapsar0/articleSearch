import axios from "axios";
import router from "./router";
import Element from "element-ui"

import global_ from './Global.vue'
axios.defaults.baseURL = global_.BASE_URL
// axios.defaults.baseURL = "http://localhost:8080"

const request = axios.create({
    timeout: 15000,
    headers: {
        'Content-Type': "application/json; charset=utf-8"
    }
})

request.interceptors.request.use(config => {
    config.headers['Authorization'] = localStorage.getItem("token")
    return config
})

request.interceptors.response.use(
    response => {

        console.log("response ->" + response)

        let res = response.data

        if (res.code === 200) {
            return response
        } else {
            Element.Message.error(!res.message ? '系统异常' : res.message)
            return Promise.reject(response.data.message)
        }
    },
    error => {

        console.log("下面是错误返回")
        console.log(error.response)

        if (error.response.data) {
            error.massage = error.response.data.message
        }

        if (error.response.status === 401 || error.response.status === 403) {
            router.push("/login")
        }

        Element.Message.error(error.massage, {duration: 3000})
        return Promise.reject(error)
    }
)

export default request