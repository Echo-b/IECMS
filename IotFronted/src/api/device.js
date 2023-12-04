import request from '@/utils/request'

export function getAllGroupDevice(group_id) {
  return request({
    url: '/device/all',
    method: 'get',
    params: { group_id }
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

export function addDevice(data) {
  return request({
    url: '/device/add',
    method: 'post',
    data
  })
}

export function editDevice(data) {
  return request({
    url: '/device/edit',
    method: 'post',
    data
  })
}

export function deleteDevice(did) {
  return request({
    url: '/device/delete',
    method: 'delete',
    params: { did }
  })
}

export function DeviceControll(data) {
  return request({
    url: '/device/control',
    method: 'post',
    data
  })
}

export function changeDeviceFlag(did) {
  return request({
    url: '/device/change',
    method: 'post',
    params: { did }
  })
}

export function getThresholdDevices(username) {
  return request({
    url: '/device/threshold/all',
    method: 'get',
    params: { username }
  })
}
