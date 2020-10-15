<!-- View -->
<template>
	<view class="content">
		<view class="light-container">
			<view :class="['light', 'red', redActive ? 'red-active': '']">
			</view>
			<view :class="['light', 'green', greenActive ? 'green-active': '']">
			</view>
		</view>
		<map id="map" :show-location="true" :markers="covers" :scale="scale" :style="{width: '100%', height: mapHeight + 'px'}"
		 :latitude="latitude" :longitude="longitude"></map>
	</view>
</template>

<script>
	var mapContext = null;
	export default {
		// Model
		data() {
			return {
				mapContext: null,
				mapHeight: 0,
				latitude: 0,
				longitude: 0,
				covers: [],
				scale: 22,
				intervalIndex: 0,
				watchId: 0,
				serverConnected: false,
				token: '',
				greenActive: false,
				redActive: false
			}
		},
		onLoad() {
			mapContext = uni.createMapContext("map", this)
			let height = uni.getSystemInfoSync().windowHeight
			let mapHeight = height - uni.upx2px(200)
			this.mapHeight = mapHeight
			this.token = uni.getStorageSync('token')
			this.watchLocation();
			uni.getNetworkType({
			    success: (res) => {
			        if(res.networkType == "none"){
						this.redActive = true;
						this.greenActive = false;
					}
			    }
			});
			uni.onNetworkStatusChange((res) => {
				if(!res.isConnected){
					this.redActive = true;
					this.greenActive = false;
				}
			})
		},
		onShow() {
			if (!this.serverConnected) {
				//ws-benchmark "ws://localhost:8080/vtl-server/websocket.ws/6cbefa44-4102-418d-b234-cb1b3a3c348a" -c 100 -n 20000
				uni.connectSocket({
					url: 'ws://192.168.20.6:8080/vtl-server/websocket.ws/' + this.token
				});
				uni.onSocketOpen((res) => {
					this.serverConnected = true;
					this.sendTestMessage();
				});
				uni.onSocketError(function(res) {
					this.serverConnected = false;
				})
				uni.onSocketMessage((res) => {
					if (res && res.data != undefined) {
						if (res.data == 0) {
							this.greenActive = true;
							this.redActive = false;
						} else {
							this.greenActive = false;
							this.redActive = true;
						}
					}
				})
				uni.onSocketClose((res) => {
					this.serverConnected = false;
				})
			}
		},
		methods: {
			sendTestMessage() {
				// -36.73678776210702 d, 174.7463365484575 d
				uni.sendSocketMessage({
					data: JSON.stringify({
						latitude: -36.73678776210702,
						longitude: 174.7463364584570,
						speed: 0,
						token: this.token
					})
				})

				uni.sendSocketMessage({
					data: JSON.stringify({
						latitude: -36.73678776210702,
						longitude: 174.7463365084572,
						speed: 0,
						token: this.token
					})
				})
			},
			watchLocation() {
				this.watchId = plus.geolocation.watchPosition((res) => {
					let result = res.coords;
					// console.log(result)
					mapContext.$getAppMap().centerAndZoom(new plus.maps.Point(result.longitude, result.latitude), 23)
					mapContext.$getAppMap().showUserLocation(true);
					if (this.serverConnected) {
						uni.sendSocketMessage({
							data: JSON.stringify({
								latitude: result.latitude,
								longitude: result.longitude,
								speed: result.speed,
								token: this.token
							})
						})
					}
				}, function(e) {
					console.log("fail：" + e.message);
				}, {
					'enableHighAccuracy': true,
					'geocode': false,
					'maximumAge': 3000
				});
			}
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;

		.light-container {
			width: 750upx;
			display: flex;
			flex-direction: row;
			justify-content: space-around;
			background-color: rgb(255, 222, 51);
			padding-bottom: 20upx;
			padding-top: 20upx;

			.light {
				width: 160upx;
				height: 160upx;
				border-radius: 50%;
			}

			.green {
				background-color: rgb(16, 84, 29);
			}

			.red {
				background-color: rgb(122, 42, 27);
			}

			.green-active {
				background-color: rgb(41, 204, 150);
			}

			.red-active {
				background-color: rgb(229, 86, 57);
			}
		}
	}
</style>
