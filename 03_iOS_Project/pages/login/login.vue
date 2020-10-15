<template>
	<view>
		<view class="logo-container">
			<image class="logo" src="../../static/logo.png" mode="aspectFit"></image>
		</view>
		
		<view class="login-container">
			<view class="row">
				<view class="title">
					Email
				</view>
				<input v-model="email" type="email" placeholder="Please enter email" placeholder-class="placeholder" />
			</view>
			<view class="row">
				<view class="title">
					Password
				</view>
				<input v-model="password" type="password" placeholder="Please enter password" placeholder-class="placeholder" />
			</view>
		</view>
		
		<!--login button-->
		<view class="button-login" @click="handleLogin">
			登录
		</view>
		
		
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				email: '',
				password: ''
			};
		},
		onLoad(){
			let token = uni.getStorageSync('token')
			if(token){
				this.jump(2)
			}
		},
		methods: {
			handleLogin(){
				if(this.email == ''){
					this.msg('Please enter email')
					return
				}
				if(this.password == ''){
					this.msg('Please enter password')
					return
				}
				this.get('/login/login', {email: this.email, password: this.password}, data => {
					this.msg(data.message)
					uni.setStorageSync('token', data.data)
					uni.setStorageSync('email', this.email)
					setTimeout(() => {
						this.jump(2)
					}, 1000)
				})
			}
		}
	}
</script>
<style scoped>
	.button-login{
		background-color: #788BE2;
		width: 591rpx;
		height: 91rpx;
		border-radius: 46rpx;
		color: #fff;
		font-size: 30rpx;
		display: flex;
		align-items: center;
		justify-content: center;
		margin-top: 99.5rpx;
		margin-left: 79.5rpx;
	}
	.placeholder{
		font-size: 30rpx;
		color: #85909D;
	}
	.logo-container{
		text-align: center;
		margin-top: 237rpx;
	}
	.logo-container .logo {
		width: 180rpx;
		height: 180rpx;
		border-radius: 27rpx;
	}
	.login-container{
		width: 669.5rpx;
		padding-left: 80.5rpx;
		font-size: 30rpx;
	}
	.login-container .row{
		display: flex;
		width: 590rpx;
		border-bottom: 1rpx solid #E5E5E5;
		height: 91.5rpx;
		align-items:flex-end;
		padding-bottom: 20.5rpx;
	}
	.login-container .row .title{
		line-height: 42rpx;
		width: 100rpx;
		color: #181818;
	}
	.login-container .row input{
		width: 300rpx;
		margin-left: 55.5rpx;
		font-size: 30rpx;
	}
	.login-container .row .button-send{
		border-left: 1rpx solid #E5E5E5;
		height: 27rpx;
		display: flex;
		align-items: center;
		color: #181818;
		padding-left: 20.5rpx;
		width: 120rpx;
		justify-content: center;
	}
	
</style>
