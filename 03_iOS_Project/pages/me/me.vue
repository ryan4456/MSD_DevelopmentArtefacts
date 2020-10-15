<template>
	<view>
		<uni-list>
			<uni-list-item  title="Login email" note="" :rightText="email" :showArrow="false"></uni-list-item>
			<uni-list-item @click="handleAbout" title="About" note=""></uni-list-item>
		</uni-list>
		
		<view class="button-container">
			<button type="warn" size="mini" @click="handleLogout">Logout</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				email: ''
			};
		},
		onLoad(){
			this.email = uni.getStorageSync('email')
		},
		methods: {
			handleLogout(){
				uni.removeStorageSync('token')
				uni.removeStorageSync('email')
				this.jump(3)
			},
			handleAbout(){
				this.jump(4)
			}
		}
	}
</script>

<style lang="scss">
.button-container{
	text-align: center;
	margin-top: 50upx;
}
</style>
