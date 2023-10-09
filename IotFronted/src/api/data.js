import request from '@/utils/request'

export function getNormalData(pageNum) {
  return request({
    url: '/data/normal/all',
    method: 'get',
    params: { pageNum }
  })
}

export function getNormalDataById(did) {
  return request({
    url: '/data/normal/get',
    method: 'get',
    params: { did }
  })
}

export function deleteNormalData(dno) {
  return request({
    url: '/data/normal/delete',
    method: 'delete',
    params: { dno }
  })
}

export function getNewestNormalData(did) {
  return request({
    url: '/data/normal/getnewest',
    method: 'get',
    params: { did }
  })
}

export function getAlertData(pageNum) {
  return request({
    url: '/data/alert/all',
    method: 'get',
    params: { pageNum }
  })
}

export function getAlertDataById(did) {
  return request({
    url: '/data/alert/get',
    method: 'get',
    params: { did }
  })
}

export function deleteAlertlData(dno) {
  return request({
    url: '/data/alert/delete',
    method: 'delete',
    params: { dno }
  })
}

export function getNewestAlertData(did) {
  return request({
    url: '/data/alert/getnewest',
    method: 'get',
    params: { did }
  })
}

export function getAverageTemperature(did) {
  return request({
    url: '/data/average/get',
    method: 'get',
    params: { did }
  })
}
