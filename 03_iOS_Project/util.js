/**
 * page navigation encapsulation
 * @param {Object} type
 * @param {Object} parameter 
 */
const jump = function(type, parameter) {
	let url = '';
	// -1 nav back, parameter is the depth of back
	// 1 webview
	// 2 index page
	switch (type) {
		case 1:
			parameter = encodeURIComponent(parameter)
			url = '/pages/webview/webview?url=' + parameter;
			break;
		case 2:
			url = '/pages/index/index';
			break;
		case 3:
			url = '/pages/login/login';
			break;
		case 4:
			url = '/pages/about/about';
			break;
		default:
			break;
	}
	if (type == -1) {
		uni.navigateBack({
			delta: parameter
		})
		return;
	}
	if(type == 2){
		uni.switchTab({
			url: url
		})
		return
	}
	if(type == 3){
		uni.reLaunch({
			url: url
		})
		return
	}
	uni.navigateTo({
		url: url
	})
}

const post = function(url, parameters, callback) {
	request(url, parameters, callback, 'POST');
}


const checkLogin = function() {
	const token = uni.getStorageSync("token");
	if (token) {
		return true
	} else {
		return false
	}
}
// API Prefix
const server_url = "http://192.168.20.6:8080/vtl-server";
// encapsulate get request 
const get = function(url, parameters, callback) {
	request(url, parameters, callback, 'GET');
}
// encapsulate request 
const request = function(url, parameters, callback, method) {
	const token = uni.getStorageSync("token");
	if (token) {
		parameters.token = token;
	}
	uni.request({
		url: server_url + url,
		data: parameters,
		method: method,
		dataType: 'json',
		success(res) {
			console.log('requested url: ' + url);
			if (res.statusCode !== 200) {
				msg('server error');
			} else {
				if (res.data.code != 0) {
					msg(res.data.message);
				} else {
					callback(res.data);
				}
			}
		},
		fail(data) {
			msg(data.errMsg);
		}
	})
}

const checkPhone = (phone) => {
	if (!(/^1[3456789]\d{9}$/.test(phone))) {
		return false
	}
	return true
}

const msg = (msg) => {
	uni.showToast({
		icon: 'none',
		title: msg,
		position: 'bottom'
	})
}

const formatDate = (longTime) => {
	var da = new Date(longTime);
	var year = da.getFullYear() + '.';
	var month = da.getMonth() + 1 + '.';
	var date = da.getDate() + '';
	return year + month + date;
}

export default {
	jump: jump,
	get: get,
	post: post,
	checkPhone: checkPhone,
	msg: msg,
	formatDate: formatDate,
	checkLogin: checkLogin
}
