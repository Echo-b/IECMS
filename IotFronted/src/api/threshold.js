import request from '@/utils/request'

/**
 * @brief get a device threshold
 * @param {*} did
 * @returns
 */
export function getDeviceThreshold(did) {
  return request({
    url: '/threshold/get',
    method: 'get',
    params: { did }
  })
}

/**
 * @brief insert a threshold into table
 * @param {*} data
 * @returns
 */
export function setDeviceThreshold(data) {
  return request({
    url: '/threshold/setting',
    method: 'post',
    data
  })
}

/**
 * @brief set device temperature threshold
 * @param {*} did
 * @returns
 */
export function setDeviceTempThreshold(did) {
  return request({
    url: '/threshold/set/temperature',
    method: 'post',
    params: { did }
  })
}

/**
 * @brief set device humidity threshold
 * @param {*} did
 * @returns
 */
export function setDeviceHumiThreshold(did) {
  return request({
    url: '/threshold/set/humidity',
    method: 'post',
    params: { did }
  })
}

/**
 * @brief set device light threshold
 * @param {*} did
 * @returns
 */
export function setDeviceLightThreshold(did) {
  return request({
    url: '/threshold/set/light',
    method: 'post',
    params: { did }
  })
}
