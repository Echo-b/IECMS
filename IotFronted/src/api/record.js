import request from '@/utils/request'

export function insertRecord(data) {
  return request({
    url: '/record/insert',
    method: 'post',
    data // {xx=xx,xx=xx}
  })
}

export function getRecords(operator) {
  return request({
    url: '/record/all',
    method: 'get',
    params: { operator }
  })
}

