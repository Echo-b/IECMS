import request from '@/utils/request'

export function getAllDevice() {
  return request({
    url: '/device/all',
    method: 'get'
  })
}

export function getAppointPageDevice(pageNum) {
  return request({
    url: '/device/appointpage',
    method: 'get',
    params: { pageNum }
  })
}

export function getDeviceById(did) {
  return request({
    url: '/device/get',
    method: 'get',
    params: { did }
  })
}

export function addDevice(form) {
  return request({
    url: '/device/add',
    method: 'post',
    params: form
  })
}

export function editDevice(form) {
  return request({
    url: '/device/edit',
    method: 'post',
    params: form
  })
}

export function deleteDevice(did) {
  return request({
    url: '/device/delete',
    method: 'delete',
    params: { did }
  })
}

export function DeviceControll(form) {
  return request({
    url: '/device/control',
    method: 'post',
    params: form
  })
}

