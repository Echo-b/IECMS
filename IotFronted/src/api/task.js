import request from '@/utils/request'

export function update(data) {
  return request({
    url: '/task/update',
    method: 'post',
    data 
  })
}

export function get() {
  return request({
    url: '/task/get',
    method: 'get'
  })
}
