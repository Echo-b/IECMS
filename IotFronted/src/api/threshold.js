import request from '@/utils/request'

/**
 * @brief get a device threshold
 * @param {*} data
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
 * @param {*} data
 * @returns
 */
export function setDeviceTempThreshold(data) {
  return request({
    url: '/threshold/set/temperature',
    method: 'post',
    data
  })
}

/**
 * @brief set device humidity threshold
 * @param {*} data
 * @returns
 */
export function setDeviceHumiThreshold(data) {
  return request({
    url: '/threshold/set/humidity',
    method: 'post',
    data
  })
}

/**
 * @brief set device light threshold
 * @param {*} data
 * @returns
 */
export function setDeviceLightThreshold(data) {
  return request({
    url: '/threshold/set/light',
    method: 'post',
    data
  })
}
