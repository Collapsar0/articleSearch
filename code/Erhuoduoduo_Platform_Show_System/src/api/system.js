import request from '../axios'
// const qs = require('qs')

/**
 * 发送邮箱验证码
 * @param email
 * @returns {AxiosPromise}
 */
export const sendVerCodeByEmail = (email) => {
    return request({
        url: '/users/sendCode',
        method: 'POST',
        params: {
            email
        }
    })
}