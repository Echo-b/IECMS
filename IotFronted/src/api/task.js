import request from '@/utils/request'

export function updateTodoListTask(data) {
  return request({
    url: '/task/update',
    method: 'post',
    data
  })
}

export function getAllTodoListTask() {
  return request({
    url: '/task/all',
    method: 'get'
  })
}

export function getTodoListTaskById(tid) {
  return request({
    url: '/task/appoint',
    method: 'get',
    params: { tid }
  })
}

export function addTodoListTask(data) {
  return request({
    url: '/task/add',
    method: 'post',
    data
  })
}

export function deleteTaskById(tid) {
  return request({
    url: '/task/delete',
    method: 'delete',
    params: { tid }
  })
}
